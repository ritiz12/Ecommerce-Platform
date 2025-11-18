package com.example.user_service.service;

import com.example.user_service.data.LoginTokenResponse;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class KeycloakService {

    private final Keycloak keycloak;

  //  @Autowired
    private final WebClient webClient;

    @Value("${keycloak.admin.server-url}")
    private String authServerUrl;

    @Value("${keycloak.admin.client-id}")
    private String clientId;

    @Value("${keycloak.admin.client-secret}")
    private String clientSecret;

    @Value("${keycloak.admin.realm}")
    private String realm;

    public KeycloakService(Keycloak keycloak, final WebClient webClient) {
        this.keycloak = keycloak;
        this.webClient = webClient;
    }

    public String createKeycloakUser(String email, String password, String firstName, String lastName) {
        Response response = null;
        try {
            UserRepresentation user = new UserRepresentation();
            user.setUsername(email);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEnabled(true);

            UsersResource usersResource = keycloak.realm(realm).users();
            response = usersResource.create(user);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed to create user in Keycloak: HTTP " + response.getStatus());
            }

            // Extract Keycloak user ID from the Location header
            String location = response.getHeaderString("Location");
            if (location == null || !location.contains("/")) {
                throw new RuntimeException("Failed to extract user ID from Keycloak response");
            }
            String userId = location.substring(location.lastIndexOf('/') + 1);

            // Set password
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setTemporary(false);
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(password);

            usersResource.get(userId).resetPassword(credential);

            return userId;

        }
        finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public LoginTokenResponse loginUser(final String email, final String password) {
        String tokenUrl = authServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("username", email);
        body.add("password", password);
        body.add("scope", "openid profile email");

        Map<String, Object> response = webClient.post()
                                                .uri(tokenUrl)
                                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                .bodyValue(body)
                                                .retrieve()
                                                .bodyToMono(Map.class)
                                                .block();

        if (response == null) {
            throw new RuntimeException("Failed to authenticate user with Keycloak");
        }

        LoginTokenResponse tokenResponse = new LoginTokenResponse();
        tokenResponse.setAccessToken((String) response.get("access_token"));
        tokenResponse.setRefreshToken((String) response.get("refresh_token"));
        tokenResponse.setExpiresIn(((Number) response.get("expires_in")).longValue());

        return tokenResponse;

    }
}

package com.example.user_service.service;

import com.example.user_service.data.LoginTokenResponse;
import com.example.user_service.data.UserLoginRequest;
import com.example.user_service.data.UserLoginResponse;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final KeycloakService keycloakService;
    private final UserRepository userRepository;

    public UserService(KeycloakService keycloakService, UserRepository userRepository) {
        this.keycloakService = keycloakService;
        this.userRepository = userRepository;
    }

    /**
     * Login user via Keycloak.
     *
     * @param request
     * @return
     */
    public LoginTokenResponse loginUser(final UserLoginRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

      LoginTokenResponse loginResponse =  keycloakService.loginUser(request.getEmail(), request.getPassword());

      return loginResponse;
    }

    public User registerUser(String email, String password, String firstName, String lastName) {
        // Create user in Keycloak
        String keycloakId = keycloakService.createKeycloakUser(email, password, firstName, lastName);

        // Save user in local DB
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setKeycloakId(keycloakId);
        userRepository.save(user);

        return user;
    }
}

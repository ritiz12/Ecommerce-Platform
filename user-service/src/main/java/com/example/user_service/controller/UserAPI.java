package com.example.user_service.controller;

import com.example.user_service.data.*;
import com.example.user_service.entity.User;
import com.example.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class UserAPI {

    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
        try {
            User user = userService.registerUser(
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName()
                                                );

            UserRegisterResponse response = new UserRegisterResponse(
                "Registration successful! Please verify your email.",
                user.getKeycloakId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginTokenResponse> login(@RequestBody UserLoginRequest request) {
        var response = userService.loginUser(request);
        System.out.println("Login response: " + response);
        return response != null ?
            ResponseEntity.ok(response) :
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }




}

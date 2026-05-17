package com.example.demo.users.controller;


import com.example.demo.config.ApplicationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.users.repository.VerificationCodeRepository;
import com.example.demo.users.schema.CreateClientRequest;
import com.example.demo.users.schema.ForgotPasswordRequest;
import com.example.demo.users.schema.UpdateUserPasswordRequest;
import com.example.demo.users.schema.UpdateUserRequest;
import com.example.demo.users.schema.UserResponse;
import com.example.demo.users.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("api/users")
public class UserController {
    

    private final ApplicationProperties applicationProperties;
    private final UserService userService;

    public UserController(UserService userService, VerificationCodeRepository verificationCodeRepository, ApplicationProperties applicationProperties) {
        this.userService = userService;
        this.applicationProperties = applicationProperties;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateClientRequest entity) {
        UserResponse response = userService.createClient(entity);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(@RequestParam String token) {
        userService.verifyEmail(token);

        return ResponseEntity
            .status(302)
            .header("Location", applicationProperties.getLoginPageUrl())
            .build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest req) {
        userService.forgotPassword(req.getEmail());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody UpdateUserPasswordRequest requestDTO) {
        userService.resetPassword(requestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UpdateUserRequest request) {
        UserResponse user = userService.update(request);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/password")
    public ResponseEntity<UserResponse> updatePassword(
        @Valid @RequestBody UpdateUserPasswordRequest requestDTO) {
        UserResponse user = userService.updatePassword(requestDTO);
        return ResponseEntity.ok(user);
    }

}
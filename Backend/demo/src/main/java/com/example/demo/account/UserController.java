package com.example.demo.account;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.account.schema.CreateClientRequest;
import com.example.demo.account.schema.UserResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("api/users")
public class UserController {
    

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateClientRequest entity) {
        UserResponse response = userService.createClient(entity);
        return ResponseEntity.ok(response);
    }
    

}
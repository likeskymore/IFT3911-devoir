package com.example.demo.account.schema;

import com.example.demo.account.models.Role;
import com.example.demo.account.models.User;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private Role role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}

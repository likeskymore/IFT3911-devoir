package com.example.demo.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.account.models.User;


@RestController
@RequestMapping("api/account")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // Update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUserData) {

        return userService.getUserById(id)
                .map(user -> {
                    user.setUsername(newUserData.getUsername());
                    user.setFirstName(newUserData.getFirstName());
                    user.setLastName(newUserData.getLastName());
                    user.setEmail(newUserData.getEmail());
                    user.setPassword(newUserData.getPassword());
                    user.setRole(newUserData.getRole());
                    User updatedUser = userService.saveUser(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        ResponseEntity<Object> objectResponseEntity = userService.getUserById(id)
                .map(user -> {
                    userService.deleteUser(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
        if (objectResponseEntity.getStatusCode().is2xxSuccessful()) {
            logger.info("User with ID {} is deleted successfully", id);
        }
        // Return 204 No Content when the User is not found. This is to comply with the HTTP specification.
        return ResponseEntity.noContent().build();
    }

}
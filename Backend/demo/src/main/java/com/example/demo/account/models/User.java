package com.example.demo.account.models;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.account.schema.CreateClientRequest;
import com.example.demo.entity.AbstractEntity;
import com.example.demo.util.ApplicationContextProvider;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@Setter
public abstract class User extends AbstractEntity {
    private String username;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(CreateClientRequest data) {
        PasswordEncoder passwordEncoder = ApplicationContextProvider.bean(PasswordEncoder.class);
        this.username = data.getUsername();
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.email = data.getEmail();
        this.password = passwordEncoder.encode(data.getPassword());
        this.role = Role.CLIENT;
    }

}

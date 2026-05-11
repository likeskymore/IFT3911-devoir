package com.example.demo.account.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

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
public abstract class User extends AbstractEntity implements UserDetails {
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    @Setter
    private boolean verified = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Setter
    @OneToOne(mappedBy = "user")
    private VerificationCode verificationCode;

    public User(CreateClientRequest data) {
        PasswordEncoder passwordEncoder = ApplicationContextProvider.bean(PasswordEncoder.class);
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.email = data.getEmail();
        this.password = passwordEncoder.encode(data.getPassword());
        this.role = Role.CLIENT;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // If you want to not allow the user to login before verifying their email, you can change this to
    // return verified;
    @Override
    public boolean isEnabled() {
        return true;
    }

}

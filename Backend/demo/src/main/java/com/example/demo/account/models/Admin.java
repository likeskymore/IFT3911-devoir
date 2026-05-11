package com.example.demo.account.models;

import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;

@Entity
public class Admin extends User {

    @Column(unique = true)  
    private Long adminNumber;

    public Admin() {}

    public Admin( String firstName, String lastName, String email, String password) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setEmail(email);
        super.setPassword(password);
    }

    @PrePersist
    private void generateAdminNumber() {
        if (adminNumber == null) {
            this.adminNumber = Math.abs(new Random().nextLong() % 1_000_000);
        }
    }

    public Long getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(Long adminNumber) {
        this.adminNumber = adminNumber;
    }
}

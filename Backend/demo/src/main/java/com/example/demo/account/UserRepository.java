package com.example.demo.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.account.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        
}
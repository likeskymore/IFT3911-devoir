package com.example.demo.account;

import org.springframework.stereotype.Service;

import com.example.demo.account.models.Client;
import com.example.demo.account.models.User;
import com.example.demo.account.schema.CreateClientRequest;
import com.example.demo.account.schema.UserResponse;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        }
    }

    public User findByEmail(String email) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public UserResponse createClient(CreateClientRequest request) {
        Client client = new Client(request);
        client = userRepository.save(client);
        return new UserResponse(client);
    }
}
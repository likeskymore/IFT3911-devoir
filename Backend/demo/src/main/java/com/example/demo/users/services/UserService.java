package com.example.demo.users.services;

import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.users.jobs.SendWelcomeEmailJob;
import com.example.demo.users.models.Client;
import com.example.demo.users.models.User;
import com.example.demo.users.models.VerificationCode;
import com.example.demo.users.repository.UserRepository;
import com.example.demo.users.repository.VerificationCodeRepository;
import com.example.demo.users.schema.CreateClientRequest;
import com.example.demo.users.schema.UserResponse;
import com.example.demo.util.exception.ApiException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;


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
    public UserResponse createClient(@Valid CreateClientRequest request) {
        Client client = new Client(request);
        client = userRepository.save(client);
        sendVerificationEmail(client);
        return new UserResponse(client);
    }

    
    private void sendVerificationEmail(User user) {
        VerificationCode verificationCode = new VerificationCode (user);
        user.setVerificationCode(verificationCode);
        verificationCodeRepository.save(verificationCode);
        SendWelcomeEmailJob sendWelcomeEmailJob = new SendWelcomeEmailJob(user.getId());
        BackgroundJobRequest.enqueue(sendWelcomeEmailJob);
        
    }

    @Transactional
    public void verifyEmail(String code) {
        VerificationCode verificationCode = verificationCodeRepository.findByCode(code)
                .orElseThrow(() -> ApiException.builder().status(400).message("Invalid token").build());
        User user = verificationCode.getUser();
        user.setVerified(true);
        userRepository.save(user);
    }
}
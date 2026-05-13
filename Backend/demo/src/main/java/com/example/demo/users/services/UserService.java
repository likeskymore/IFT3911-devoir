package com.example.demo.users.services;

import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.users.jobs.SendResetPasswordEmailJob;
import com.example.demo.users.jobs.SendWelcomeEmailJob;
import com.example.demo.users.models.Client;
import com.example.demo.users.models.PasswordResetToken;
import com.example.demo.users.models.User;
import com.example.demo.users.models.VerificationCode;
import com.example.demo.users.repository.PasswordResetTokenRepository;
import com.example.demo.users.repository.UserRepository;
import com.example.demo.users.repository.VerificationCodeRepository;
import com.example.demo.users.schema.CreateClientRequest;
import com.example.demo.users.schema.UpdateUserPasswordRequest;
import com.example.demo.users.schema.UserResponse;
import com.example.demo.util.exception.ApiException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

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

    @Transactional
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> ApiException.builder().status(404).message("User not found").build());
        PasswordResetToken passwordResetToken = new PasswordResetToken(user);
        passwordResetTokenRepository.save(passwordResetToken);
        SendResetPasswordEmailJob sendResetPasswordEmailJob = new SendResetPasswordEmailJob(passwordResetToken.getId());
        BackgroundJobRequest.enqueue(sendResetPasswordEmailJob);
    }

    @Transactional
    public void resetPassword(UpdateUserPasswordRequest request) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(request.getPasswordResetToken())
            .orElseThrow(() -> ApiException.builder().status(404).message("Password reset token not found").build());

        if (passwordResetToken.isExpired()) {
        throw ApiException.builder().status(400).message("Password reset token is expired").build();
        }

        User user = passwordResetToken.getUser();
        user.updatePassword(request.getNewPassword());
        userRepository.save(user);
    }
}
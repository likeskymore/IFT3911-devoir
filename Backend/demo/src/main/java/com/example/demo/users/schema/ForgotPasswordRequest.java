package com.example.demo.users.schema;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ForgotPasswordRequest {
  @Email
  private String email;
}

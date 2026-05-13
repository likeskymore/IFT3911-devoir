package com.example.demo.users.schema;

import com.example.demo.util.validators.PasswordMatch;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@PasswordMatch(passwordField = "newPassword", passwordConfirmationField = "confirmPassword")
public class UpdateUserPasswordRequest {
  @NotNull
  @Length(min = 8)
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "must contain at least one uppercase letter, one lowercase letter, and one digit.")
  private String newPassword;
  private String confirmPassword;
  private String passwordResetToken;
}

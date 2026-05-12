package com.example.demo.users.schema;

import com.example.demo.util.ClientUtil;
import com.example.demo.util.validators.PasswordMatch;
import com.example.demo.util.validators.Unique;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch(
    passwordField = "password",
    passwordConfirmationField = "passwordConfirmation"
)
@ClientUtil
public class CreateClientRequest {

    @Email
    @NotNull
    @Unique(
        columnName = "email",
        tableName = "users",
        message = "User with this email already exists"
    )
    private String email;

    @NotNull
    @Length(min = 8)
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
        message = "must contain at least one uppercase letter, one lowercase letter, and one digit."
    )
    private String password;

    @NotNull
    private String passwordConfirmation;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;
}

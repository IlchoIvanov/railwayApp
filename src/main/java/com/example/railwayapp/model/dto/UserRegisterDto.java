package com.example.railwayapp.model.dto;

import com.example.railwayapp.util.annotations.UniqueEmail;
import com.example.railwayapp.util.annotations.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {
    @Email
    @NotBlank(message = "{register_valid_email}")
    @UniqueEmail(message = "{register_unique_email}")
    private String email;
    @NotBlank(message = "{register_valid_username}")
    @Size(min = 4, max = 50, message = "{register_username_size}")
    @UniqueUsername(message = "{register_unique_username}")
    private String username;
    @NotBlank(message = "{register_valid_password}")
    @Size(min = 6, max = 50, message = "{register_password_size}")
    private String password;
    private String confirmPassword;

    public @Email @NotBlank(message = "{register_valid_email}") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank(message = "{register_valid_email}") String email) {
        this.email = email;
    }

    public @NotBlank(message = "{register_valid_username}") @Size(min = 4, max = 50, message = "{register_username_size}") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "{register_valid_username}") @Size(min = 4, max = 50, message = "{register_username_size}") String username) {
        this.username = username;
    }

    public @NotBlank(message = "{register_valid_password}") @Size(min = 6, max = 50, message = "{register_password_size}") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "{register_valid_password}") @Size(min = 6, max = 50, message = "{register_password_size}") String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

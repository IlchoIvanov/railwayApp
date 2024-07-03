package com.example.railwayapp.Model.Dto;

import com.example.railwayapp.Util.Annotations.UniqueEmail;
import com.example.railwayapp.Util.Annotations.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {
    @Email
    @NotBlank(message = "Въведете валиден имейл.")
    @UniqueEmail(message = "Имейлът вече съществува.")
    private String email;
    @NotBlank(message = "Въведете валидно потребителско име.")
    @Size(min = 4, max = 50, message = "Дължината трябва да бъде между 4 и 50 символа")
    @UniqueUsername(message = "Потребителското име вече съществува")
    private String username;
    @NotBlank(message = "Въведете валидно потребителско име.")
    @Size(min = 6, max = 50, message = "Дължината трябва да бъде между 6 и 50 символа")
    private String password;
    private String confirmPassword;

    public @Email @NotBlank(message = "Въведете валиден имейл.") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank(message = "Въведете валиден имейл.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Въведете валидно потребителско име.") @Size(min = 4, max = 50, message = "Дължината трябва да бъде между 4 и 50 символа") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Въведете валидно потребителско име.") @Size(min = 4, max = 50, message = "Дължината трябва да бъде между 4 и 50 символа") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Въведете валидно потребителско име.") @Size(min = 6, max = 50, message = "Дължината трябва да бъде между 6 и 50 символа") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Въведете валидно потребителско име.") @Size(min = 6, max = 50, message = "Дължината трябва да бъде между 6 и 50 символа") String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

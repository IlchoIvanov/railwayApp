package com.example.railwayapp.util.validation;

import com.example.railwayapp.service.UserService;
import com.example.railwayapp.util.annotations.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return this.userService.findUserByUsername(value) == null;
    }
}

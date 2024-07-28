package com.example.railwayapp.util.validation;

import com.example.railwayapp.service.UserService;

import com.example.railwayapp.util.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

        private final UserService userService;

        public UniqueEmailValidator(UserService userService) {
            this.userService = userService;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return this.userService.findUserByEmail(value) == null;
        }

}

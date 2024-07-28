package com.example.railwayapp.Util.Validation;

import com.example.railwayapp.service.UserService;

import com.example.railwayapp.Util.Annotations.UniqueEmail;
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

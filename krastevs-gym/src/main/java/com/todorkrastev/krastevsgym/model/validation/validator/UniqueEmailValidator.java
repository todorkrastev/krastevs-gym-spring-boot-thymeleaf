package com.todorkrastev.krastevsgym.model.validation.validator;

import com.todorkrastev.krastevsgym.model.validation.annotation.UniqueEmail;
import com.todorkrastev.krastevsgym.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.doesEmailExist(email);
    }

}

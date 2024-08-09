package com.todorkrastev.krastevsgym.validation.validator;

import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.validation.annotation.UniqueEmail;
import com.todorkrastev.krastevsgym.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

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
//        if (email == null || email.isEmpty()) {
//            return false;
//        }

        Optional<KrastevsGymUserDetails> currentUserOpt = userService.getCurrentUser();
        if (currentUserOpt.isEmpty()) {
            return false;
        }

        KrastevsGymUserDetails currentUser = currentUserOpt.get();
        return !userService.doesEmailExist(email) || currentUser.getEmail().equals(email);
    }

}

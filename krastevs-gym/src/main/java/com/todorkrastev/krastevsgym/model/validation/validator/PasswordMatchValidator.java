package com.todorkrastev.krastevsgym.model.validation.validator;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.model.validation.annotation.PasswordMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRegisterDTO> {
    private String message;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        message = constraintAnnotation.message();
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {
        final String password = userRegisterDTO.getPassword();
        final String confirmPassword = userRegisterDTO.getConfirmPassword();

        if (password == null && confirmPassword == null) {
            return true;
        } else {
            boolean isValid = password != null && password.equals(confirmPassword);

            if (!isValid) {
                context.unwrap(HibernateConstraintValidatorContext.class)
                        .buildConstraintViolationWithTemplate(message)
                        .addPropertyNode("confirmPassword")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
            }

            return isValid;
        }
    }
}

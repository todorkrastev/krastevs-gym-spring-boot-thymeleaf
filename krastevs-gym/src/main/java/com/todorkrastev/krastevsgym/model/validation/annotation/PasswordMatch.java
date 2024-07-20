package com.todorkrastev.krastevsgym.model.validation.annotation;

import com.todorkrastev.krastevsgym.model.validation.validator.PasswordMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatch {
    String message() default "{compare.both.passwords.validator.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

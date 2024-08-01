package com.todorkrastev.krastevsgym.validation.annotation;

import com.todorkrastev.krastevsgym.validation.validator.UniqueTitleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueTitleValidator.class)
public @interface UniqueTitle {
    String message() default "{validation.uniqueTitle.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
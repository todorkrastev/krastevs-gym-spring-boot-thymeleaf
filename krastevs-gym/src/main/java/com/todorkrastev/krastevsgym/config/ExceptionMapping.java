package com.todorkrastev.krastevsgym.config;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapping {

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleNotFound() {
        // TODO: code a 404 view in html/css
        return "error-404";
    }
}

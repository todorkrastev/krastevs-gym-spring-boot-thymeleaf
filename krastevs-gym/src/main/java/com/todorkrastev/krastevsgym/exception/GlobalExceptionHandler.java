package com.todorkrastev.krastevsgym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleObjectNotFound(ResourceNotFoundException resourceNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("resource-not-found");
        modelAndView.addObject("message", resourceNotFoundException.getMessage());

        return modelAndView;
    }
}

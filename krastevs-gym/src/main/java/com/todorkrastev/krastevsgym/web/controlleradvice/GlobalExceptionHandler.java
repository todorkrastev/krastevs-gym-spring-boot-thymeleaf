package com.todorkrastev.krastevsgym.web.controlleradvice;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleObjectNotFound(ResourceNotFoundException resourceNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("resource-not-found");
        modelAndView.addObject("message", resourceNotFoundException.getMessage());
        LOGGER.warn("{} {}", resourceNotFoundException.getMessage(), resourceNotFoundException.getStackTrace());

        return modelAndView;
    }
}

package com.todorkrastev.krastevsgym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PriceRangeNotFoundException extends RuntimeException {
    private final String input;

    public PriceRangeNotFoundException(String input) {
        super(String.format("Product not found with price range %s", input.replaceAll("-", " ")));
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}

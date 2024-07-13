package com.todorkrastev.krastevsgym.model.dto;

import jakarta.validation.constraints.*;

public class UserRegisterDTO {
    @NotBlank(message = "First name must not be null and must contain at least one non-whitespace character!")
    @Size(min = 1, message = "First name must have at least 1 character!")
    private String firstName;

    @NotBlank(message = "Last name must not be null and must contain at least one non-whitespace character!")
    @Size(min = 1, message = "Last name must have at least 1 character!")
    private String lastName;

    @Email(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "Enter valid email address!")
    private String email;

    @NotBlank(message = "Password must not be null and must contain at least one non-whitespace character!")
    @Size(min = 5, message = "Password must have at least 5 character!")
    private String password;

    public UserRegisterDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

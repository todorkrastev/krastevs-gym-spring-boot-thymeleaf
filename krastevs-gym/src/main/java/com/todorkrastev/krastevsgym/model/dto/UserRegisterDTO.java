package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.validation.annotation.PasswordMatch;
import jakarta.validation.constraints.*;

@PasswordMatch
public class UserRegisterDTO {
    @NotBlank(message = "{user.register.dto.first.name.not.blank}")
    @Size(min = 1, message = "{user.register.dto.first.name.size}")
    private String firstName;

    @NotBlank(message = "{user.register.dto.last.name.not.blank}")
    @Size(min = 1, message = "{user.register.dto.last.name.size}")
    private String lastName;

    @Email(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "{user.register.dto.email}")
//    @UniqueEmail --> my custom validation
    private String email;

    @NotBlank(message = "{user.register.dto.password.not.blank}")
    @Size(min = 5, message = "{user.register.dto.password.size}")
    private String password;

    @NotBlank(message = "{user.register.dto.password.not.blank}")
    @Size(min = 5, message = "{user.register.dto.password.size}")
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}

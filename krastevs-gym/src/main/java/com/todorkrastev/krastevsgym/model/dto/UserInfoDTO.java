package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.validation.annotation.UniqueEmail;
import jakarta.validation.constraints.*;

public class UserInfoDTO {

    @NotBlank(message = "{user.info.dto.first.name.not.blank}")
    private String firstName;

    @NotBlank(message = "{user.info.dto.last.name.not.blank}")
    private String lastName;

    @Email(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "{user.info.dto.email}")
    @UniqueEmail
    private String email;

    private Integer age;

    @Min(value = 10, message = "{user.info.dto.weight.min.size}")
    @Max(value = 180, message = "{user.info.dto.weight.max.size}")
    private Integer weight;

    @Min(value = 100, message = "{user.info.dto.height.min.size}")
    @Max(value = 250, message = "{user.info.dto.height.max.size}")
    private Integer height;

    private String image;


    public UserInfoDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserInfoDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserInfoDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfoDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserInfoDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public UserInfoDTO setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public UserInfoDTO setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public String getImage() {
        return image;
    }

    public UserInfoDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}

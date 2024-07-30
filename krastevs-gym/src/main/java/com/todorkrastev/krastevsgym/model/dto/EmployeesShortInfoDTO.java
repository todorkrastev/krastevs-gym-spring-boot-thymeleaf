package com.todorkrastev.krastevsgym.model.dto;

public class EmployeesShortInfoDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String position;

    private String imageUrl;

    public EmployeesShortInfoDTO() {
    }

    public Long getId() {
        return id;
    }

    public EmployeesShortInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeesShortInfoDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeesShortInfoDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public EmployeesShortInfoDTO setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public EmployeesShortInfoDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}

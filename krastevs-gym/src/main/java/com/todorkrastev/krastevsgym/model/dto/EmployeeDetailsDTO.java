package com.todorkrastev.krastevsgym.model.dto;

public class EmployeeDetailsDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String position;
    private String phoneNumber;
    private String email;
    private String imageUrl;
    private String qualifications;
    private String performanceModule;
    private String personalInfo;

    public EmployeeDetailsDTO() {
    }

    public String getId() {
        return id;
    }

    public EmployeeDetailsDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeDetailsDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeDetailsDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public EmployeeDetailsDTO setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EmployeeDetailsDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeDetailsDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public EmployeeDetailsDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getQualifications() {
        return qualifications;
    }

    public EmployeeDetailsDTO setQualifications(String qualifications) {
        this.qualifications = qualifications;
        return this;
    }

    public String getPerformanceModule() {
        return performanceModule;
    }

    public EmployeeDetailsDTO setPerformanceModule(String performanceModule) {
        this.performanceModule = performanceModule;
        return this;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public EmployeeDetailsDTO setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
        return this;
    }
}

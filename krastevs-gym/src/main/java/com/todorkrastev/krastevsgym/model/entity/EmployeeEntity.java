package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT", name = "image_url")
    private String imageUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String qualifications;

    @Column(nullable = false, columnDefinition = "TEXT", name = "performance_module")
    private String performanceModule;

    @Column(nullable = false, columnDefinition = "TEXT", name = "personal_info")
    private String personalInfo;

    public EmployeeEntity() {
    }

    public Long getId() {
        return id;
    }

    public EmployeeEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public EmployeeEntity setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EmployeeEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public EmployeeEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getQualifications() {
        return qualifications;
    }

    public EmployeeEntity setQualifications(String qualifications) {
        this.qualifications = qualifications;
        return this;
    }

    public String getPerformanceModule() {
        return performanceModule;
    }

    public EmployeeEntity setPerformanceModule(String performanceModule) {
        this.performanceModule = performanceModule;
        return this;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public EmployeeEntity setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
        return this;
    }
}

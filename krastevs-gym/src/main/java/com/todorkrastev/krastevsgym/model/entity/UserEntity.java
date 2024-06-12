package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.UserLevelEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Integer age;

    private Integer weight;

    private Integer height;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserLevelEnum level;

    @ManyToMany
    private Set<RoleEntity> roles;

    @ManyToMany
    private Set<ExerciseEntity> exercises;

    public UserEntity() {
        this.roles = new HashSet<>();
        this.exercises = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public UserEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public UserEntity setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public UserEntity setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserLevelEnum getLevel() {
        return level;
    }

    public UserEntity setLevel(UserLevelEnum level) {
        this.level = level;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Set<ExerciseEntity> getExercises() {
        return exercises;
    }

    public UserEntity setExercises(Set<ExerciseEntity> exercises) {
        this.exercises = exercises;
        return this;
    }
}

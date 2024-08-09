package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.uuid.UUIDSequence;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.*;

import static org.hibernate.type.SqlTypes.VARCHAR;


@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UUIDSequence
    @JdbcTypeCode(VARCHAR)
    private UUID uuid;

    private String firstName;

    private String lastName;

    private Integer age;

    private Integer weight;

    private Integer height;

    private String image;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles;

    @OneToMany(targetEntity = ExerciseEntity.class, mappedBy = "user")
    private List<ExerciseEntity> exercises;

    public UserEntity() {
        this.roles = new HashSet<>();
        this.exercises = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UserEntity setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getImage() {
        return image;
    }

    public UserEntity setImage(String image) {
        this.image = image;
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

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public List<ExerciseEntity> getExercises() {
        return exercises;
    }

    public UserEntity setExercises(List<ExerciseEntity> exercises) {
        this.exercises = exercises;
        return this;
    }
}

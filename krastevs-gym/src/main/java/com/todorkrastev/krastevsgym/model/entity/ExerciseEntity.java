package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercises")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "gif_url", columnDefinition = "TEXT")
    private String gifUrl;

    @Column(name = "muscles_worked_url", columnDefinition = "TEXT")
    private String musclesWorkedUrl;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @OneToMany(targetEntity = ExerciseNoteEntity.class, mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ExerciseNoteEntity> notes;

    @ManyToOne(optional = false)
    private EquipmentTypeEntity equipmentType;

    @ManyToOne(optional = false)
    private ExerciseCategoryEntity category;

    @ManyToOne(optional = false)
    private UserEntity user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public ExerciseEntity() {
        this.notes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ExerciseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExerciseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExerciseEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public ExerciseEntity setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
        return this;
    }

    public String getMusclesWorkedUrl() {
        return musclesWorkedUrl;
    }

    public ExerciseEntity setMusclesWorkedUrl(String musclesWorkedUrl) {
        this.musclesWorkedUrl = musclesWorkedUrl;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public ExerciseEntity setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public List<ExerciseNoteEntity> getNotes() {
        return notes;
    }

    public ExerciseEntity setNotes(List<ExerciseNoteEntity> notes) {
        this.notes = notes;
        return this;
    }

    public EquipmentTypeEntity getEquipmentType() {
        return equipmentType;
    }

    public ExerciseEntity setEquipmentType(EquipmentTypeEntity equipmentType) {
        this.equipmentType = equipmentType;
        return this;
    }

    public ExerciseCategoryEntity getCategory() {
        return category;
    }

    public ExerciseEntity setCategory(ExerciseCategoryEntity category) {
        this.category = category;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ExerciseEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ExerciseEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
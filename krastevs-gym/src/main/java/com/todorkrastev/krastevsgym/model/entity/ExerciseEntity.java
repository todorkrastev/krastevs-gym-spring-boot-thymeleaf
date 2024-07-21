package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import jakarta.persistence.*;

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

    @Column(name = "muscles_worked_url",columnDefinition = "TEXT")
    private String musclesWorkedUrl;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "equipment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EquipmentTypeEnum equipmentType;

    @ManyToOne(optional = false)
    private ExerciseCategoryEntity category;

    @ManyToOne(optional = false)
    private UserEntity user;

    public ExerciseEntity() {
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

    public String getNotes() {
        return notes;
    }

    public ExerciseEntity setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public EquipmentTypeEnum getEquipmentType() {
        return equipmentType;
    }

    public ExerciseEntity setEquipmentType(EquipmentTypeEnum equipmentType) {
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
}

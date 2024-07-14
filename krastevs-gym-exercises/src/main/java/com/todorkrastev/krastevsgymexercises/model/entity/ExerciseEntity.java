package com.todorkrastev.krastevsgymexercises.model.entity;

import com.todorkrastev.krastevsgymexercises.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgymexercises.model.enums.ExerciseCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exercise_name", nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column(name = "gif_url")
    private String gifUrl;

    @Column
    private String musclesWorkedUrl;

    @Column
    private String instructions;

    @Column
    private String notes;

    @Column(name = "exercise_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExerciseCategoryEnum exerciseCategoryEnum;

    @Column(name = "equipment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EquipmentTypeEnum equipmentTypeEnum;

    private String picture;

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

    public ExerciseCategoryEnum getExerciseCategoryEnum() {
        return exerciseCategoryEnum;
    }

    public ExerciseEntity setExerciseCategoryEnum(ExerciseCategoryEnum exerciseCategoryEnum) {
        this.exerciseCategoryEnum = exerciseCategoryEnum;
        return this;
    }

    public EquipmentTypeEnum getEquipmentTypeEnum() {
        return equipmentTypeEnum;
    }

    public ExerciseEntity setEquipmentTypeEnum(EquipmentTypeEnum equipmentTypeEnum) {
        this.equipmentTypeEnum = equipmentTypeEnum;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public ExerciseEntity setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}


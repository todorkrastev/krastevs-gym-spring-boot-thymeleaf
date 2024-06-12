package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exercises")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "exercise_name", nullable = false, unique = true)
    private String exerciseName;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "equipment_type")
    @Enumerated(EnumType.STRING)
    private EquipmentTypeEnum equipmentTypeEnum;

    @OneToMany(targetEntity = PictureEntity.class, mappedBy = "exercise")
    private Set<PictureEntity> pictures;

    @ManyToMany
    private Set<ExerciseCategoryEntity> exerciseCategories;

    public ExerciseEntity() {
        this.pictures = new HashSet<>();
        this.exerciseCategories = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public ExerciseEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public ExerciseEntity setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public ExerciseEntity setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public EquipmentTypeEnum getEquipmentTypeEnum() {
        return equipmentTypeEnum;
    }

    public ExerciseEntity setEquipmentTypeEnum(EquipmentTypeEnum equipmentTypeEnum) {
        this.equipmentTypeEnum = equipmentTypeEnum;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public ExerciseEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public Set<ExerciseCategoryEntity> getExerciseCategories() {
        return exerciseCategories;
    }

    public ExerciseEntity setExerciseCategories(Set<ExerciseCategoryEntity> exerciseCategories) {
        this.exerciseCategories = exerciseCategories;
        return this;
    }
}

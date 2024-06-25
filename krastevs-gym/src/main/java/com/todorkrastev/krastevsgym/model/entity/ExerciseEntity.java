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
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    private String musclesWorkedUrl;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(columnDefinition = "TEXT")
    private String notes;

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public ExerciseEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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

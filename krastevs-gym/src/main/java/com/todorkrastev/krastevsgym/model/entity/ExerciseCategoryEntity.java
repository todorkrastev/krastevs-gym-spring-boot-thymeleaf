package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise_categories")
public class ExerciseCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Enumerated(EnumType.STRING)
    public ExerciseCategoryEnum category;

    @Column(columnDefinition = "TEXT")
    public String description;

    @Column(name = "gif_url", nullable = false, columnDefinition = "TEXT")
    public String gifUrl;

    @OneToMany(targetEntity = ExerciseEntity.class, mappedBy = "category")
    private List<ExerciseEntity> exercises;

    //    private ExerciseCategoryDTO mapToInfo(ExerciseEntity exercise) {
//        ExerciseCategoryDTO dto = modelMapper.map(exercise, ExerciseCategoryDTO.class);
//
//        Optional<ExerciseCategoryEntity> first = exercise.getCategories().stream().findFirst();
//        first.ifPresent(exerciseCategoryEntity -> dto.setExerciseCategory(exerciseCategoryEntity.category()));
//
//        return dto;
//    }

    public ExerciseCategoryEntity() {
        this.exercises = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ExerciseCategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ExerciseCategoryEnum getCategory() {
        return category;
    }

    public ExerciseCategoryEntity setCategory(ExerciseCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExerciseCategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public ExerciseCategoryEntity setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
        return this;
    }

    public List<ExerciseEntity> getExercises() {
        return exercises;
    }

    public ExerciseCategoryEntity setExercises(List<ExerciseEntity> exercises) {
        this.exercises = exercises;
        return this;
    }
}

package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise_categories")
public class ExerciseCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Enumerated(EnumType.STRING)
    public ExerciseCategoryEnum exerciseCategory;

    @Column(columnDefinition = "TEXT")
    public String description;

    @Column(name = "gif_url", nullable = false, columnDefinition = "TEXT")
    public String gifUrl;

    public ExerciseCategoryEntity() {
    }

    public long getId() {
        return id;
    }

    public ExerciseCategoryEntity setId(long id) {
        this.id = id;
        return this;
    }

    public ExerciseCategoryEnum getExerciseCategory() {
        return exerciseCategory;
    }

    public ExerciseCategoryEntity setExerciseCategory(ExerciseCategoryEnum exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
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
}

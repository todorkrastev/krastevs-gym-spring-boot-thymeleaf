package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;

public class ExerciseShortInfoDTO {
    private Long categoryId;
    private String categoryDescription;
    private ExerciseCategoryEnum category;

    private Long exerciseId;
    private String exerciseName;
    private String exerciseGifUrl;

    public ExerciseShortInfoDTO() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public ExerciseShortInfoDTO setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public ExerciseShortInfoDTO setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        return this;
    }

    public ExerciseCategoryEnum getCategory() {
        return category;
    }

    public ExerciseShortInfoDTO setCategory(ExerciseCategoryEnum category) {
        this.category = category;
        return this;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public ExerciseShortInfoDTO setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
        return this;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public ExerciseShortInfoDTO setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public String getExerciseGifUrl() {
        return exerciseGifUrl;
    }

    public ExerciseShortInfoDTO setExerciseGifUrl(String exerciseGifUrl) {
        this.exerciseGifUrl = exerciseGifUrl;
        return this;
    }
}

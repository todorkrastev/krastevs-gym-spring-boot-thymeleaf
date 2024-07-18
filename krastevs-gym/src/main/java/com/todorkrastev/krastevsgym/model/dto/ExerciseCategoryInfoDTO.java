package com.todorkrastev.krastevsgym.model.dto;


public class ExerciseCategoryInfoDTO {
    private Long categoryId;
    private String categoryDescription;
    private String categoryGifUrl;
    private String category;

    private Long exerciseId;
    private String exerciseName;
    private String exerciseGifUrl;


    public ExerciseCategoryInfoDTO() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public ExerciseCategoryInfoDTO setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public ExerciseCategoryInfoDTO setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        return this;
    }

    public String getCategoryGifUrl() {
        return categoryGifUrl;
    }

    public ExerciseCategoryInfoDTO setCategoryGifUrl(String categoryGifUrl) {
        this.categoryGifUrl = categoryGifUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ExerciseCategoryInfoDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public ExerciseCategoryInfoDTO setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
        return this;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public ExerciseCategoryInfoDTO setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public String getExerciseGifUrl() {
        return exerciseGifUrl;
    }

    public ExerciseCategoryInfoDTO setExerciseGifUrl(String exerciseGifUrl) {
        this.exerciseGifUrl = exerciseGifUrl;
        return this;
    }
}

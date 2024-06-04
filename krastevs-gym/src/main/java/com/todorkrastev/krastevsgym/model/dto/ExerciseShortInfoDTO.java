package com.todorkrastev.krastevsgym.model.dto;

public class ExerciseShortInfoDTO {
    private long id;
    private String exerciseName;
    private String imageUrl;

    public ExerciseShortInfoDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

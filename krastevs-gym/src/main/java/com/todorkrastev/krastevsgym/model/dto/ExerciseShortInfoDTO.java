package com.todorkrastev.krastevsgym.model.dto;

public class ExerciseShortInfoDTO {
    private long id;
    private String name;
    private String imageUrl;

    public ExerciseShortInfoDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

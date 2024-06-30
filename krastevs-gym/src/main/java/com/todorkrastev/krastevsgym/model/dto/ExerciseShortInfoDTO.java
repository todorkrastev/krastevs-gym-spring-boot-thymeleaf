package com.todorkrastev.krastevsgym.model.dto;

public class ExerciseShortInfoDTO {
    private long id;
    private String name;
    private String gifUrl;

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

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }
}

package com.todorkrastev.krastevsgym.model.dto;


public class CreateActivityDTO {
    private Long id;
    private String title;
    private String description;
    private String imageURL;

    public CreateActivityDTO() {
    }

    public Long getId() {
        return id;
    }

    public CreateActivityDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CreateActivityDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateActivityDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public CreateActivityDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }
}

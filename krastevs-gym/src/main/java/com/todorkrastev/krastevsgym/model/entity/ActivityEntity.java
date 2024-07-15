package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "activities")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageURL;

    public ActivityEntity() {
    }

    public Long getId() {
        return id;
    }

    public ActivityEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ActivityEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActivityEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ActivityEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }
}

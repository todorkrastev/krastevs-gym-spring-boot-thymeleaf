package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="pictures")
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @ManyToOne(optional = false)
    private UserEntity author;

    @ManyToOne(optional = false)
    private ExerciseEntity exercise;

    public PictureEntity() {
    }

    public long getId() {
        return id;
    }

    public PictureEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PictureEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public PictureEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public PictureEntity setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
        return this;
    }
}

package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="pictures")
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @ManyToOne(optional = false)
    private UserEntity author;

    public PictureEntity() {
    }

    public Long getId() {
        return id;
    }

    public PictureEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PictureEntity setName(String name) {
        this.name = name;
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
}

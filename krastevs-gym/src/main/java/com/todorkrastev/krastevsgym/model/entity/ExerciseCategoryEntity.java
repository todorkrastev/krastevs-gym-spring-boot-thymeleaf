package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise_categories")
public class ExerciseCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Enumerated(EnumType.STRING)
    public ExerciseCategoryEnum category;

    @Column(columnDefinition = "TEXT")
    public String description;

    @Column(name = "gif_url", nullable = false, columnDefinition = "TEXT")
    public String gifUrl;

    public ExerciseCategoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public ExerciseCategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ExerciseCategoryEnum getCategory() {
        return category;
    }

    public ExerciseCategoryEntity setCategory(ExerciseCategoryEnum category) {
        this.category = category;
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

package com.todorkrastev.krastevsgym.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "exercise_name", nullable = false, unique = true)
    private String exerciseName;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @OneToMany(targetEntity = Picture.class, mappedBy = "exercise")
    private Set<Picture> pictures;

    @ManyToMany
    private Set<ExerciseCategory> exerciseCategories;

    public Exercise() {
        this.pictures = new HashSet<>();
        this.exerciseCategories = new HashSet<>();
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<ExerciseCategory> getExerciseCategories() {
        return exerciseCategories;
    }

    public void setExerciseCategories(Set<ExerciseCategory> exerciseCategories) {
        this.exerciseCategories = exerciseCategories;
    }
}

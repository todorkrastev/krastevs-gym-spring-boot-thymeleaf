package com.todorkrastev.krastevsgym.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String exerciseName;

    private String instructions;

    @OneToOne
    private Picture picture;

    @OneToMany(targetEntity = Note.class, mappedBy = "exercise")
    private Set<Note> notes;

    @OneToMany(targetEntity = ExerciseSet.class, mappedBy = "exercise")
    private List<ExerciseSet> exerciseSets;

    @ManyToOne(optional = false)
    private WorkoutDay workoutDay;

    @ManyToMany
    private Set<ExerciseCategory> exerciseCategories;

    public Exercise() {
        this.notes = new HashSet<>();
        this.exerciseSets = new ArrayList<>();
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

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public List<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    public WorkoutDay getWorkoutDay() {
        return workoutDay;
    }

    public void setWorkoutDay(WorkoutDay workoutDay) {
        this.workoutDay = workoutDay;
    }

    public Set<ExerciseCategory> getExerciseCategories() {
        return exerciseCategories;
    }

    public void setExerciseCategories(Set<ExerciseCategory> exerciseCategories) {
        this.exerciseCategories = exerciseCategories;
    }
}

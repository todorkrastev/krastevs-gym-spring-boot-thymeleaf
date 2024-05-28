package com.todorkrastev.krastevsgym.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String workoutName;

    @OneToMany(targetEntity = WorkoutDay.class, mappedBy = "workout")
    private Set<WorkoutDay> workoutDays;

    @ManyToMany
    private Set<WorkoutCategory> workoutCategories;

    public Workout() {
        this.workoutDays = new HashSet<>();
        this.workoutCategories = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public Set<WorkoutDay> getWorkoutDays() {
        return workoutDays;
    }

    public void setWorkoutDays(Set<WorkoutDay> workoutDays) {
        this.workoutDays = workoutDays;
    }

    public Set<WorkoutCategory> getWorkoutCategories() {
        return workoutCategories;
    }

    public void setWorkoutCategories(Set<WorkoutCategory> workoutCategories) {
        this.workoutCategories = workoutCategories;
    }
}

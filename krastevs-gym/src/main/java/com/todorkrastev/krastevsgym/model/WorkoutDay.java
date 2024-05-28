package com.todorkrastev.krastevsgym.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workout_days")
public class WorkoutDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String workoutDayName;

    @OneToMany(targetEntity = Exercise.class, mappedBy = "workoutDay")
    private Set<Exercise> exercises;

    @ManyToOne(optional = false)
    private Workout workout;

    public WorkoutDay() {
        this.exercises = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWorkoutDayName() {
        return workoutDayName;
    }

    public void setWorkoutDayName(String workoutDayName) {
        this.workoutDayName = workoutDayName;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
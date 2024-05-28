package com.todorkrastev.krastevsgym.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise_sets")
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long repetitions;

    private Long liftedWeight;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Exercise exercise;

    public ExerciseSet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Long repetitions) {
        this.repetitions = repetitions;
    }

    public Long getLiftedWeight() {
        return liftedWeight;
    }

    public void setLiftedWeight(Long liftedWeight) {
        this.liftedWeight = liftedWeight;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}

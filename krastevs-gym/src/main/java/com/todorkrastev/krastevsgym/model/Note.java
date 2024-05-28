package com.todorkrastev.krastevsgym.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String exerciseNote;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Exercise exercise;

    public Note() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExerciseNote() {
        return exerciseNote;
    }

    public void setExerciseNote(String exerciseNote) {
        this.exerciseNote = exerciseNote;
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

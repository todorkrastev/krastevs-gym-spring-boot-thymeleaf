package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "notes")
public class ExerciseNoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private ExerciseEntity exercise;

    public ExerciseNoteEntity() {
    }

    public Long getId() {
        return id;
    }

    public ExerciseNoteEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public ExerciseNoteEntity setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public ExerciseNoteEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public ExerciseNoteEntity setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
        return this;
    }
}
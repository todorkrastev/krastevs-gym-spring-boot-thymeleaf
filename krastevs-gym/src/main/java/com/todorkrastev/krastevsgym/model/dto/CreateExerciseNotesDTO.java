package com.todorkrastev.krastevsgym.model.dto;

public class CreateExerciseNotesDTO {
    private String notes;

    public CreateExerciseNotesDTO() {
    }

    public String getNotes() {
        return notes;
    }

    public CreateExerciseNotesDTO setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}

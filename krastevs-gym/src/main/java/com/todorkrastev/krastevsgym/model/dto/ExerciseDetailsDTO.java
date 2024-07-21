package com.todorkrastev.krastevsgym.model.dto;

public class ExerciseDetailsDTO {
    private Long id;
    private String name;
    private String description;
    private String gifUrl;
    private String musclesWorkedUrl;
    private String instructions;
    private String notes;

    private Long creatorId;

    public ExerciseDetailsDTO() {
    }

    public Long getId() {
        return id;
    }

    public ExerciseDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExerciseDetailsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExerciseDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public ExerciseDetailsDTO setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
        return this;
    }

    public String getMusclesWorkedUrl() {
        return musclesWorkedUrl;
    }

    public ExerciseDetailsDTO setMusclesWorkedUrl(String musclesWorkedUrl) {
        this.musclesWorkedUrl = musclesWorkedUrl;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public ExerciseDetailsDTO setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public ExerciseDetailsDTO setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public ExerciseDetailsDTO setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }
}

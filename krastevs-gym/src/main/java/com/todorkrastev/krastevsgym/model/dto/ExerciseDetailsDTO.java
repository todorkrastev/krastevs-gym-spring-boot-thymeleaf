package com.todorkrastev.krastevsgym.model.dto;

public record ExerciseDetailsDTO(
        Long id,
        String name,
        String description,
        String videoUrl,
        String musclesWorkedUrl,
        String instructions,
        String notes) {
}

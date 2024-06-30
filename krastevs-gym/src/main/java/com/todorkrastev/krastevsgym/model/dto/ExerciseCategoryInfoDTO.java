package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;

public record ExerciseCategoryInfoDTO(
        Long id,
        ExerciseCategoryEnum exerciseCategory,
        String description,
        String gifUrl) {
}

package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateExerciseDTO(
        @NotBlank(message = "{create.exercise.dto.name.not.blank}")
        @Size(min = 1, message = "{create.exercise.dto.name.size}")
        String name,
        String description,
        @NotNull(message = "{create.exercise.dto.equipment.type}")
        EquipmentTypeEnum equipmentTypeEnum,
        @NotNull(message = "{create.exercise.dto.exercise.category}")
        ExerciseCategoryEnum exerciseCategoryEnum,
        String instructions,
        String videoUrl
) {

    public static CreateExerciseDTO empty() {
        return new CreateExerciseDTO(null, null, null, null, null, null);
    }
}

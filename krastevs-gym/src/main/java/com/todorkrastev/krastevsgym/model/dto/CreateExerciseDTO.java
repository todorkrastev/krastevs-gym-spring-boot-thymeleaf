package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateExerciseDTO(
        @NotBlank(message = "{create.exercise.name.not.blank}")
        @Size(min = 1, message = "{create.exercise.name.size}")
        String name,
        String instructions,
        @NotNull(message = "You must select the equipment type!")
        EquipmentTypeEnum equipmentTypeEnum,
        @NotNull(message = "You must select the exercise category!")
        ExerciseCategoryEnum exerciseCategoryEnum
) {

    public static CreateExerciseDTO empty() {
        return new CreateExerciseDTO(null, null, null, null);
    }
}

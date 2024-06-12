package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;

public record CreateExerciseDTO(
        String exerciseName,
        String instructions,
        EquipmentTypeEnum equipmentTypeEnum,
        ExerciseCategoryEnum exerciseCategoryEnum
) {

    public static CreateExerciseDTO empty() {
        return new CreateExerciseDTO(null, null, null, null);
    }
}

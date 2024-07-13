package com.todorkrastev.krastevsgymexercises.model.dto;

import com.todorkrastev.krastevsgymexercises.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgymexercises.model.enums.ExerciseCategoryEnum;

public record CreateExerciseDTO(
        String name,
        String description,
        EquipmentTypeEnum equipmentTypeEnum,
        ExerciseCategoryEnum exerciseCategoryEnum,
        String instructions,
        String picture
) {

    public static CreateExerciseDTO empty() {
        return new CreateExerciseDTO(null, null, null, null, null, null);
    }
}

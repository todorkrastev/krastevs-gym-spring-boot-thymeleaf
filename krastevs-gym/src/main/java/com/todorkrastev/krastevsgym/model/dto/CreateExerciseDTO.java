package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateExerciseDTO {
    private Long currUserId;

    @NotBlank(message = "{create.exercise.dto.name.not.blank}")
    @Size(min = 1, message = "{create.exercise.dto.name.size}")
    private String name;

    private String description;

    @NotNull(message = "{create.exercise.dto.equipment.type}")
    private EquipmentTypeEnum equipmentType;

    @NotNull(message = "{create.exercise.dto.exercise.category}")
    private ExerciseCategoryEnum category;

    private String instructions;

    private String gifUrl;

    public CreateExerciseDTO() {
    }

    public Long getCurrUserId() {
        return currUserId;
    }

    public CreateExerciseDTO setCurrUserId(Long currUserId) {
        this.currUserId = currUserId;
        return this;
    }

    public @NotBlank(message = "{create.exercise.dto.name.not.blank}") @Size(min = 1, message = "{create.exercise.dto.name.size}") String getName() {
        return name;
    }

    public CreateExerciseDTO setName(@NotBlank(message = "{create.exercise.dto.name.not.blank}") @Size(min = 1, message = "{create.exercise.dto.name.size}") String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateExerciseDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public @NotNull(message = "{create.exercise.dto.equipment.type}") EquipmentTypeEnum getEquipmentType() {
        return equipmentType;
    }

    public CreateExerciseDTO setEquipmentType(@NotNull(message = "{create.exercise.dto.equipment.type}") EquipmentTypeEnum equipmentType) {
        this.equipmentType = equipmentType;
        return this;
    }

    public @NotNull(message = "{create.exercise.dto.exercise.category}") ExerciseCategoryEnum getCategory() {
        return category;
    }

    public CreateExerciseDTO setCategory(@NotNull(message = "{create.exercise.dto.exercise.category}") ExerciseCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public CreateExerciseDTO setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public CreateExerciseDTO setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
        return this;
    }
}

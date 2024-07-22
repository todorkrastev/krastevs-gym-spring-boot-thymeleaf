package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EditExerciseDTO {
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

    public EditExerciseDTO() {
    }

    public Long getCurrUserId() {
        return currUserId;
    }

    public EditExerciseDTO setCurrUserId(Long currUserId) {
        this.currUserId = currUserId;
        return this;
    }

    public @NotBlank(message = "{create.exercise.dto.name.not.blank}") @Size(min = 1, message = "{create.exercise.dto.name.size}") String getName() {
        return name;
    }

    public EditExerciseDTO setName(@NotBlank(message = "{create.exercise.dto.name.not.blank}") @Size(min = 1, message = "{create.exercise.dto.name.size}") String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EditExerciseDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public @NotNull(message = "{create.exercise.dto.equipment.type}") EquipmentTypeEnum getEquipmentType() {
        return equipmentType;
    }

    public EditExerciseDTO setEquipmentType(@NotNull(message = "{create.exercise.dto.equipment.type}") EquipmentTypeEnum equipmentType) {
        this.equipmentType = equipmentType;
        return this;
    }

    public @NotNull(message = "{create.exercise.dto.exercise.category}") ExerciseCategoryEnum getCategory() {
        return category;
    }

    public EditExerciseDTO setCategory(@NotNull(message = "{create.exercise.dto.exercise.category}") ExerciseCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public EditExerciseDTO setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public EditExerciseDTO setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
        return this;
    }
}
package com.todorkrastev.krastevsgymexercises.service.impl;

import com.todorkrastev.krastevsgymexercises.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgymexercises.model.dto.ExerciseDetailsDTO;
import com.todorkrastev.krastevsgymexercises.model.entity.ExerciseEntity;
import com.todorkrastev.krastevsgymexercises.repository.ExerciseRepository;
import com.todorkrastev.krastevsgymexercises.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public void createExercise(CreateExerciseDTO createExerciseDTO) {
        ExerciseEntity exercise = new ExerciseEntity()
                .setName(createExerciseDTO.name())
                .setDescription(createExerciseDTO.description())
                .setEquipmentTypeEnum(createExerciseDTO.equipmentTypeEnum())
                .setExerciseCategory(createExerciseDTO.exerciseCategoryEnum())
                .setInstructions(createExerciseDTO.instructions())
                .setPicture(createExerciseDTO.picture());


        //  ExerciseEntity exercise = modelMapper.map(createExerciseDTO, ExerciseEntity.class);
        exerciseRepository.save(exercise);
    }

    @Override
    public ExerciseDetailsDTO getOfferById(Long id) {
        return exerciseRepository
                .findById(id)
                .map(exerciseEntity ->
                        new ExerciseDetailsDTO(
                                exerciseEntity.getId(),
                                exerciseEntity.getName(),
                                exerciseEntity.getDescription(),
                                exerciseEntity.getGifUrl(),
                                exerciseEntity.getMusclesWorkedUrl(),
                                exerciseEntity.getInstructions(),
                                exerciseEntity.getNotes()))
                .orElseThrow(() -> new IllegalArgumentException("Not found!"));
    }

    @Override
    public void deleteExercise(Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }

    @Override
    public List<ExerciseDetailsDTO> getAllOffers() {
        return exerciseRepository
                .findAll()
                .stream()
                .map(exerciseEntity ->
                        new ExerciseDetailsDTO(
                                exerciseEntity.getId(),
                                exerciseEntity.getName(),
                                exerciseEntity.getDescription(),
                                exerciseEntity.getGifUrl(),
                                exerciseEntity.getMusclesWorkedUrl(),
                                exerciseEntity.getInstructions(),
                                exerciseEntity.getNotes()))
                .toList();
    }
}

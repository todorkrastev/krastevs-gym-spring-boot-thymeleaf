package com.todorkrastev.krastevsgymexercises.service;

import com.todorkrastev.krastevsgymexercises.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgymexercises.model.dto.ExerciseDetailsDTO;

import java.util.List;

public interface ExerciseService {
    ExerciseDetailsDTO createExercise(CreateExerciseDTO createExerciseDTO);

    void deleteExercise(Long exerciseId);

    ExerciseDetailsDTO getOfferById(Long id);

    List<ExerciseDetailsDTO> getAllOffers();
}

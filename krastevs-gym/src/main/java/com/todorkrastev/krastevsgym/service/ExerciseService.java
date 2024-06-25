package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;

import java.util.List;

public interface ExerciseService {
    List<ExerciseShortInfoDTO> getAll();

    long createExercise(CreateExerciseDTO createExerciseDTO);

    ExerciseDetailsDTO getExerciseDetails(Long id);
}

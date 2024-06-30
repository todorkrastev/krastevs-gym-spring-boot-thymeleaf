package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;

import java.util.List;


public interface ExerciseService {
    long createExercise(CreateExerciseDTO createExerciseDTO);

    ExerciseDetailsDTO getExerciseDetails(Long id);

    List<ExerciseShortInfoDTO> getExercisesByGivenCategory(ExerciseCategoryEnum exerciseCategory);
}

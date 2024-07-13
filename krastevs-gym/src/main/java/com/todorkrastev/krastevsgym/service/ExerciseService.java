package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;

import java.util.List;


public interface ExerciseService {
    List<ExerciseDetailsDTO> getAllExercises();

    void createExercise(CreateExerciseDTO createExerciseDTO);

    void deleteExercise(Long id);

    ExerciseDetailsDTO getExerciseDetails(Long id);

    List<ExerciseShortInfoDTO> getExercisesByGivenCategory(ExerciseCategoryEnum exerciseCategory);
}

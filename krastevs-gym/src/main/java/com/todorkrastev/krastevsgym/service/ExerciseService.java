package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.*;

import java.util.List;


public interface ExerciseService {

    Long createExercise(CreateExerciseDTO createExerciseDTO);

    Long deleteExercise(Long id);

    ExerciseDetailsDTO getExerciseDetails(Long id);

    List<ExerciseShortInfoDTO> getExercisesByCategoryIdAndUserId(Long categoryId, Long userId);

    void createExerciseNotes(CreateExerciseNotesDTO createExerciseNotesDTO, Long id);

    ExerciseDetailsDTO editExercise(Long id, EditExerciseDTO editExerciseDTO);
}

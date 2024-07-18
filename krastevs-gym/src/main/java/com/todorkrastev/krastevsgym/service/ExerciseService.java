package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.*;

import java.util.List;


public interface ExerciseService {
    List<ExerciseDetailsDTO> getAllExercises();

    void createExercise(CreateExerciseDTO createExerciseDTO);

    void deleteExercise(Long id);

    ExerciseDetailsDTO getExerciseDetails(Long id);

    List<ExerciseShortInfoDTO> getExercisesByCategoryId(Long id);

    void createExerciseNotes(CreateExerciseNotesDTO createExerciseNotesDTO, Long id);
}

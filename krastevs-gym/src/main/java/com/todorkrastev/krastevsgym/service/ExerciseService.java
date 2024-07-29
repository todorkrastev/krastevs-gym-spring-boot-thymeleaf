package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.*;

import java.util.List;


public interface ExerciseService {

    Long createExercise(CreateExerciseDTO createExerciseDTO);

    Long deleteExercise(Long id);

    ExerciseDetailsDTO getExerciseDetails(Long exerciseId, Long currentUserId);

    List<ExerciseShortInfoDTO> getExercisesByCategoryIdAndUserId(Long categoryId, Long userId);

    void createExerciseNotes(CreateExerciseNotesDTO createExerciseNotesDTO, Long exerciseId, Long currentUserId);

    ExerciseDetailsDTO editExercise(Long id, ExerciseDetailsDTO editExerciseDTO, Long authorId);

    List<ExerciseShortInfoDTO> getExercisesByTypeAndUserId(Long typeId, Long userId, Long categoryId);

    void editExerciseNotes(CreateExerciseNotesDTO createExerciseNotesDTO, Long exerciseId, Long currentUserId);

    boolean isTheCreatorOfTheExercise(Long id, Long authorId);
}

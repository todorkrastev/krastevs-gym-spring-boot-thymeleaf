package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.entity.ExerciseNoteEntity;

public interface ExerciseNoteService {
    ExerciseNoteEntity findByExerciseIdAndAuthorId(Long exerciseId, Long currentUserId);
}

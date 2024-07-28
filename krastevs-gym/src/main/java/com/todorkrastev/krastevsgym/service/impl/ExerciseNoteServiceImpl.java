package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.entity.ExerciseNoteEntity;
import com.todorkrastev.krastevsgym.repository.ExerciseNoteRepository;
import com.todorkrastev.krastevsgym.service.ExerciseNoteService;
import org.springframework.stereotype.Service;

@Service
public class ExerciseNoteServiceImpl implements ExerciseNoteService {
    private final ExerciseNoteRepository exerciseNoteRepository;

    public ExerciseNoteServiceImpl(ExerciseNoteRepository exerciseNoteRepository) {
        this.exerciseNoteRepository = exerciseNoteRepository;
    }


    @Override
    public ExerciseNoteEntity findByExerciseIdAndAuthorId(Long exerciseId, Long currentUserId) {
        return exerciseNoteRepository.findByExercise_IdAndAuthor_Id(exerciseId, currentUserId).orElse(null);
    }
}

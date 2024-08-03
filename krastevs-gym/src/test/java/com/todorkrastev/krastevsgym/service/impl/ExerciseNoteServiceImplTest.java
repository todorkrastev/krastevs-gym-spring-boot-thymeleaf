package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.entity.ExerciseNoteEntity;
import com.todorkrastev.krastevsgym.repository.ExerciseNoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseNoteServiceImplTest {

    private ExerciseNoteServiceImpl exerciseNoteService;

    @Mock
    private ExerciseNoteRepository mockExerciseNoteRepository;

    @BeforeEach
    void setUp() {
        exerciseNoteService = new ExerciseNoteServiceImpl(mockExerciseNoteRepository);
    }

    @Test
    void findByExerciseIdAndAuthorId_NoteExists_ReturnsExerciseNoteEntity() {
        Long exerciseId = 1L;
        Long authorId = 1L;
        ExerciseNoteEntity entity = new ExerciseNoteEntity();

        when(mockExerciseNoteRepository.findByExercise_IdAndAuthor_Id(exerciseId, authorId)).thenReturn(Optional.of(entity));

        ExerciseNoteEntity result = exerciseNoteService.findByExerciseIdAndAuthorId(exerciseId, authorId);

        assertNotNull(result);
        assertEquals(entity, result);
    }

    @Test
    void findByExerciseIdAndAuthorId_NoteNotFound_ReturnsNull() {
        Long exerciseId = 1L;
        Long authorId = 1L;

        when(mockExerciseNoteRepository.findByExercise_IdAndAuthor_Id(exerciseId, authorId)).thenReturn(Optional.empty());

        ExerciseNoteEntity result = exerciseNoteService.findByExerciseIdAndAuthorId(exerciseId, authorId);

        assertNull(result);
    }
}
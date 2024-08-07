package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseNotesDTO;
import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;
import com.todorkrastev.krastevsgym.model.entity.ExerciseEntity;
import com.todorkrastev.krastevsgym.model.entity.ExerciseNoteEntity;
import com.todorkrastev.krastevsgym.repository.ExerciseNoteRepository;
import com.todorkrastev.krastevsgym.repository.ExerciseRepository;
import com.todorkrastev.krastevsgym.service.ExerciseCategoryService;
import com.todorkrastev.krastevsgym.service.ExerciseNoteService;
import com.todorkrastev.krastevsgym.service.EquipmentTypeService;
import com.todorkrastev.krastevsgym.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceImplTest {

    private ExerciseServiceImpl exerciseService;

    @Mock
    private ExerciseRepository mockExerciseRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private ExerciseNoteService mockExerciseNoteService;

    @Mock
    private ExerciseCategoryService mockExerciseCategoryService;

    @Mock
    private EquipmentTypeService mockEquipmentTypeService;

    @Mock
    private UserService mockUserService;

    @Mock
    private ExerciseNoteRepository mockExerciseNoteRepository;

    @BeforeEach
    void setUp() {
        exerciseService = new ExerciseServiceImpl(
                mockExerciseRepository,
                mockModelMapper,
                mockExerciseCategoryService,
                mockUserService,
                mockEquipmentTypeService,
                mockExerciseNoteService,
                mockExerciseNoteRepository
        );
    }

    @Test
    void editExerciseNotes_UpdatesNotesCorrectly() {
        Long exerciseId = 1L;
        Long authorId = 1L;
        CreateExerciseNotesDTO createExerciseNotesDTO = new CreateExerciseNotesDTO()
                .setNotes("Updated notes");
        ExerciseNoteEntity note = new ExerciseNoteEntity();

        when(mockExerciseNoteService.findByExerciseIdAndAuthorId(exerciseId, authorId)).thenReturn(note);

        exerciseService.editExerciseNotes(createExerciseNotesDTO, exerciseId, authorId);

        verify(mockModelMapper).map(createExerciseNotesDTO, note);
        verify(mockExerciseNoteRepository).save(note);
    }

    @Test
    void isTheCreatorOfTheExercise_ReturnsCorrectBoolean() {
        Long id = 1L;
        Long authorId = 1L;

        when(mockExerciseRepository.existsByIdAndUserId(id, authorId)).thenReturn(true);

        boolean result = exerciseService.isTheCreatorOfTheExercise(id, authorId);

        assertTrue(result);
    }

    @Test
    void deleteExercise_DeletesExerciseAndReturnsCategoryId() {
        Long id = 1L;
        ExerciseEntity entity = new ExerciseEntity();
        entity.setCategory(new ExerciseCategoryEntity());
        entity.getCategory().setId(1L);

        when(mockExerciseRepository.findById(id)).thenReturn(Optional.of(entity));

        Long result = exerciseService.deleteExercise(id);

        verify(mockExerciseRepository).deleteById(id);
        assertEquals(1L, result);
    }
}
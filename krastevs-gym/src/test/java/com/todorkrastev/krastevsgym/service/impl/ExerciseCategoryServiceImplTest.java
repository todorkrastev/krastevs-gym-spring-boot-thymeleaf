package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.ExerciseCategoryInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.repository.ExerciseCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseCategoryServiceImplTest {

    private ExerciseCategoryServiceImpl exerciseCategoryService;

    @Mock
    private ExerciseCategoryRepository mockExerciseCategoryRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        exerciseCategoryService = new ExerciseCategoryServiceImpl(mockExerciseCategoryRepository, mockModelMapper);
    }

    @Test
    void getAllCategories_ReturnsListOfExerciseCategoryInfoDTOs() {
        List<ExerciseCategoryEntity> entities = List.of(new ExerciseCategoryEntity());
        ExerciseCategoryInfoDTO dto = new ExerciseCategoryInfoDTO();

        when(mockExerciseCategoryRepository.findAll()).thenReturn(entities);
        when(mockModelMapper.map(any(ExerciseCategoryEntity.class), eq(ExerciseCategoryInfoDTO.class))).thenReturn(dto);

        List<ExerciseCategoryInfoDTO> result = exerciseCategoryService.getAllCategories();

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
    }

    @Test
    void findById_ExerciseCategoryNotFound_ThrowsResourceNotFoundException() {
        Long id = 1L;

        when(mockExerciseCategoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> exerciseCategoryService.findById(id));
    }

    @Test
    void findByCategory_ExerciseCategoryExists_ReturnsExerciseCategoryEntity() {
        ExerciseCategoryEnum category = ExerciseCategoryEnum.ABS;
        ExerciseCategoryEntity entity = new ExerciseCategoryEntity();

        when(mockExerciseCategoryRepository.findByCategory(category)).thenReturn(Optional.of(entity));

        ExerciseCategoryEntity result = exerciseCategoryService.findByCategory(category);

        assertNotNull(result);
        assertEquals(entity, result);
    }

    @Test
    void findByCategory_ExerciseCategoryNotFound_ReturnsNull() {
        ExerciseCategoryEnum category = ExerciseCategoryEnum.ABS;

        when(mockExerciseCategoryRepository.findByCategory(category)).thenReturn(Optional.empty());

        ExerciseCategoryEntity result = exerciseCategoryService.findByCategory(category);

        assertNull(result);
    }
}
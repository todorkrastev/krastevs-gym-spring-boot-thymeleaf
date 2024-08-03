package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.DepartmentCategoryDTO;
import com.todorkrastev.krastevsgym.model.entity.DepartmentCategoryEntity;
import com.todorkrastev.krastevsgym.repository.DepartmentCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentCategoryServiceImplTest {

    private DepartmentCategoryServiceImpl departmentCategoryService;

    @Mock
    private DepartmentCategoryRepository mockDepartmentCategoryRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        departmentCategoryService = new DepartmentCategoryServiceImpl(mockDepartmentCategoryRepository, mockModelMapper);
    }

    @Test
    void findAll_ReturnsListOfDepartmentCategoryDTOs() {
        List<DepartmentCategoryEntity> entities = List.of(new DepartmentCategoryEntity());
        DepartmentCategoryDTO dto = new DepartmentCategoryDTO();

        when(mockDepartmentCategoryRepository.findAll()).thenReturn(entities);
        when(mockModelMapper.map(any(DepartmentCategoryEntity.class), eq(DepartmentCategoryDTO.class))).thenReturn(dto);

        List<DepartmentCategoryDTO> result = departmentCategoryService.findAll();

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
    }
}
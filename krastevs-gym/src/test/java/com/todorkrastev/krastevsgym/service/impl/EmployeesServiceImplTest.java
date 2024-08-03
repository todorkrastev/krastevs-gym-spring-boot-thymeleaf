package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.EmployeeDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.EmployeesShortInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.EmployeeEntity;
import com.todorkrastev.krastevsgym.repository.EmployeeRepository;
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
class EmployeesServiceImplTest {

    private EmployeesServiceImpl employeesService;

    @Mock
    private EmployeeRepository mockEmployeeRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        employeesService = new EmployeesServiceImpl(mockEmployeeRepository, mockModelMapper);
    }

    @Test
    void findAll_ReturnsListOfEmployeesShortInfoDTOs() {
        List<EmployeeEntity> entities = List.of(new EmployeeEntity());
        EmployeesShortInfoDTO dto = new EmployeesShortInfoDTO();

        when(mockEmployeeRepository.findAll()).thenReturn(entities);
        when(mockModelMapper.map(any(EmployeeEntity.class), eq(EmployeesShortInfoDTO.class))).thenReturn(dto);

        List<EmployeesShortInfoDTO> result = employeesService.findAll();

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
    }

    @Test
    void findById_EmployeeExists_ReturnsEmployeeDetailsDTO() {
        Long id = 1L;
        EmployeeEntity entity = new EmployeeEntity();
        EmployeeDetailsDTO dto = new EmployeeDetailsDTO();

        when(mockEmployeeRepository.findById(id)).thenReturn(Optional.of(entity));
        when(mockModelMapper.map(entity, EmployeeDetailsDTO.class)).thenReturn(dto);

        EmployeeDetailsDTO result = employeesService.findById(id);

        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void findById_EmployeeNotFound_ThrowsResourceNotFoundException() {
        Long id = 1L;

        when(mockEmployeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeesService.findById(id));
    }
}
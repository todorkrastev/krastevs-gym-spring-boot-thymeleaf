package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.EquipmentTypeDTO;
import com.todorkrastev.krastevsgym.model.entity.EquipmentTypeEntity;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.repository.EquipmentTypeRepository;
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
class EquipmentTypeServiceImplTest {

    private EquipmentTypeServiceImpl equipmentTypeService;

    @Mock
    private EquipmentTypeRepository mockEquipmentTypeRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        equipmentTypeService = new EquipmentTypeServiceImpl(mockEquipmentTypeRepository, mockModelMapper);
    }

    @Test
    void getAllTypes_ReturnsListOfEquipmentTypeDTOs() {
        List<EquipmentTypeEntity> entities = List.of(new EquipmentTypeEntity());
        EquipmentTypeDTO dto = new EquipmentTypeDTO();

        when(mockEquipmentTypeRepository.findAll()).thenReturn(entities);
        when(mockModelMapper.map(any(EquipmentTypeEntity.class), eq(EquipmentTypeDTO.class))).thenReturn(dto);

        List<EquipmentTypeDTO> result = equipmentTypeService.getAllTypes();

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
    }

    @Test
    void findById_EquipmentTypeNotFound_ThrowsResourceNotFoundException() {
        Long id = 1L;

        when(mockEquipmentTypeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> equipmentTypeService.findById(id));
    }

    @Test
    void findByCategory_EquipmentTypeExists_ReturnsEquipmentTypeEntity() {
        EquipmentTypeEnum type = EquipmentTypeEnum.BODY_WEIGHT;
        EquipmentTypeEntity entity = new EquipmentTypeEntity();

        when(mockEquipmentTypeRepository.findByType(type)).thenReturn(Optional.of(entity));

        EquipmentTypeEntity result = equipmentTypeService.findByCategory(type);

        assertNotNull(result);
        assertEquals(entity, result);
    }
}
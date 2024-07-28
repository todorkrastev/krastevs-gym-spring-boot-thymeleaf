package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.EquipmentTypeDTO;
import com.todorkrastev.krastevsgym.model.entity.EquipmentTypeEntity;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.repository.EquipmentTypeRepository;
import com.todorkrastev.krastevsgym.service.EquipmentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService {

    private final EquipmentTypeRepository equipmentTypeRepository;
    private final ModelMapper modelMapper;

    public EquipmentTypeServiceImpl(EquipmentTypeRepository equipmentTypeRepository, ModelMapper modelMapper) {
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EquipmentTypeDTO> getAllTypes() {
        return equipmentTypeRepository
                .findAll()
                .stream()
                .map(equipmentTypeEntity -> modelMapper.map(equipmentTypeEntity, EquipmentTypeDTO.class))
                .toList();
    }

    @Override
    public void findById(Long typeId) {
        equipmentTypeRepository
                .findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("EquipmentType", "id", typeId));
    }

    @Override
    public EquipmentTypeEntity findByCategory(EquipmentTypeEnum type) {
        return equipmentTypeRepository
                .findByType(type)
                .orElse(null);
    }
}

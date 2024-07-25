package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.EquipmentTypeDTO;
import com.todorkrastev.krastevsgym.repository.EquipmentTypeRepository;
import com.todorkrastev.krastevsgym.service.EquipmentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

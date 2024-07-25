package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.EquipmentTypeDTO;

import java.util.List;

public interface EquipmentTypeService {
    List<EquipmentTypeDTO> getAllTypes();

    void findById(Long typeId);
}

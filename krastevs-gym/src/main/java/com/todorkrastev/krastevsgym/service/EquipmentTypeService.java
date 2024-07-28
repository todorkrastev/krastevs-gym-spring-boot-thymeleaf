package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.EquipmentTypeDTO;
import com.todorkrastev.krastevsgym.model.entity.EquipmentTypeEntity;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;

import java.util.List;

public interface EquipmentTypeService {
    List<EquipmentTypeDTO> getAllTypes();

    void findById(Long typeId);

    EquipmentTypeEntity findByCategory(EquipmentTypeEnum type);
}

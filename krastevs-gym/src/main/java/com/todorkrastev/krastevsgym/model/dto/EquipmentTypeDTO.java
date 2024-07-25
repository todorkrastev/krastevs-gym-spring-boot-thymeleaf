package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;

public class EquipmentTypeDTO {
    private Long id;
    private EquipmentTypeEnum type;

    public EquipmentTypeDTO() {
    }

    public Long getId() {
        return id;
    }

    public EquipmentTypeDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public EquipmentTypeEnum getType() {
        return type;
    }

    public EquipmentTypeDTO setType(EquipmentTypeEnum type) {
        this.type = type;
        return this;
    }
}

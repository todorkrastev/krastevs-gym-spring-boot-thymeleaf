package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "equipment_types")
public class EquipmentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EquipmentTypeEnum type;

    public EquipmentTypeEntity() {
    }

    public Long getId() {
        return id;
    }

    public EquipmentTypeEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public EquipmentTypeEnum getType() {
        return type;
    }

    public EquipmentTypeEntity setType(EquipmentTypeEnum type) {
        this.type = type;
        return this;
    }
}

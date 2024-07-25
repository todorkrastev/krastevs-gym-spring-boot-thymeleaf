package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.EquipmentTypeEntity;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentTypeEntity, Long> {
    Optional<EquipmentTypeEntity> findByType(EquipmentTypeEnum equipmentTypeEnum);
}

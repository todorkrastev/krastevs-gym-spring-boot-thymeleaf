package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.DepartmentCategoryEntity;
import com.todorkrastev.krastevsgym.model.enums.DepartmentCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentCategoryRepository extends JpaRepository<DepartmentCategoryEntity, Long> {
    Optional<DepartmentCategoryEntity> findByCategory(DepartmentCategoryEnum departmentCategoryEnum);
}

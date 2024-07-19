package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategoryEntity, Long> {
    Optional<ExerciseCategoryEntity> findByCategory(ExerciseCategoryEnum category);
}

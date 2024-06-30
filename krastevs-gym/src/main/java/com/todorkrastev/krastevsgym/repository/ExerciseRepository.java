package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ExerciseEntity;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    List<ExerciseEntity> findAllByExerciseCategory(ExerciseCategoryEnum exerciseCategory);
}
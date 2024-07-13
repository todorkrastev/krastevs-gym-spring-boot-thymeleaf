package com.todorkrastev.krastevsgymexercises.repository;

import com.todorkrastev.krastevsgymexercises.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
}

package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
}

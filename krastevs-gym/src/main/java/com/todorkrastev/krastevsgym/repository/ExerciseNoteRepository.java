package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ExerciseNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseNoteRepository extends JpaRepository<ExerciseNoteEntity, Long> {

    @Query("SELECT en FROM ExerciseNoteEntity en WHERE en.exercise.id = :exerciseId AND en.author.id = :currentUserId")
    Optional<ExerciseNoteEntity> findByExercise_IdAndAuthor_Id(Long exerciseId, Long currentUserId);
}

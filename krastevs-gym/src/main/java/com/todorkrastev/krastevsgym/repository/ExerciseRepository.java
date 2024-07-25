package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    @Query("select e from ExerciseEntity e join e.user u where e.category.id = ?1 and u.id in (?2, ?3) order by e.id desc ")
    List<ExerciseEntity> findAllByCategoryIdAndAdminIdAndUserId(Long categoryId, Long adminId, Long userId);

    @Query("select e from ExerciseEntity e join e.user u where e.equipmentType.id = ?1 and u.id in (?2, ?3) and e.category.id = ?4 order by e.id desc")
    List<ExerciseEntity> findAllByTypeIdAndAdminIdAndUserIdAndCategoryId(Long typeId, Long adminId, Long userId, Long categoryId);
}
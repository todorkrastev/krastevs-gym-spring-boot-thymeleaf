package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {

    @Query("SELECT e FROM ExerciseEntity e WHERE e.category.id = :categoryId AND (e.user.id = :userId OR e.user.id = :adminId) ORDER BY e.createdAt DESC")
    List<ExerciseEntity> findAllByCategoryIdAndAdminIdAndUserId(@Param("categoryId") Long categoryId, @Param("adminId") Long adminId, @Param("userId") Long userId);

    @Query("SELECT e FROM ExerciseEntity e JOIN e.user u WHERE e.equipmentType.id = ?1 AND u.id IN (?2, ?3) AND e.category.id = ?4 ORDER BY e.createdAt DESC ")
    List<ExerciseEntity> findAllByTypeIdAndAdminIdAndUserIdAndCategoryId(Long typeId, Long adminId, Long userId, Long categoryId);
}





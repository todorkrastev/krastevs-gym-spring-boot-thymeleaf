package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u join u.roles r where r.role = :userRoleEnum")
    UserEntity findAdminByCategory(UserRoleEnum userRoleEnum);

    @Query("SELECT u FROM UserEntity u join u.exercises e where e.id = :exerciseId")
    UserEntity findUserByExerciseId(Long exerciseId);
}

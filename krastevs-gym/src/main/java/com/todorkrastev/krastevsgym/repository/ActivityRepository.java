package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
    @Query("select a from ActivityEntity a order by a.id asc")
    Set<ActivityEntity> findAllByOrderByIdAsc();
}

package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.PriceFilterEntity;
import com.todorkrastev.krastevsgym.model.enums.PriceFilterEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceFilterRepository extends JpaRepository<PriceFilterEntity, Long> {
    Optional<PriceFilterEntity> findByFilter(PriceFilterEnum filter);
}

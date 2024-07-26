package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ExRateEntity;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExRateRepository extends JpaRepository<ExRateEntity, Long> {
    Optional<ExRateEntity> findByCurrency(@NotEmpty String currency);


    List<ExRateEntity> findAllByCurrencyIn(List<String> eur);
}

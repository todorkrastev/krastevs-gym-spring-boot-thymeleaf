package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.HeroImagesEntity;
import com.todorkrastev.krastevsgym.model.enums.HeroImageCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroImageRepository extends JpaRepository<HeroImagesEntity, Long> {
    @Query("select h from HeroImagesEntity h where h.category = ?1")
    HeroImagesEntity findByCategory(HeroImageCategoryEnum category);
}

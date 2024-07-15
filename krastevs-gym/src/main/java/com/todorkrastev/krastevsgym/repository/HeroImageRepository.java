package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.HeroImagesEntity;
import com.todorkrastev.krastevsgym.model.enums.HeroImageCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroImageRepository extends JpaRepository<HeroImagesEntity, Long> {
    HeroImagesEntity findByHeroPageCategory(HeroImageCategoryEnum heroImageCategory);
}

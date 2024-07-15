package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.HeroImageDTO;
import com.todorkrastev.krastevsgym.model.enums.HeroImageCategoryEnum;

public interface HeroImageService {
    HeroImageDTO getHeroPageByGivenCategory(HeroImageCategoryEnum heroImageCategoryEnum);
}

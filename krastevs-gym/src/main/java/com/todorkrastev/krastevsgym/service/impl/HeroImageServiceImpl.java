package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.HeroImageDTO;
import com.todorkrastev.krastevsgym.model.entity.HeroImagesEntity;
import com.todorkrastev.krastevsgym.model.enums.HeroImageCategoryEnum;
import com.todorkrastev.krastevsgym.repository.HeroImageRepository;
import com.todorkrastev.krastevsgym.service.HeroImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class HeroImageServiceImpl implements HeroImageService {
    private HeroImageRepository heroPageRepository;
    private ModelMapper modelMapper;

    public HeroImageServiceImpl(HeroImageRepository heroPageRepository, ModelMapper modelMapper) {
        this.heroPageRepository = heroPageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HeroImageDTO getHeroPageByGivenCategory(HeroImageCategoryEnum heroImageCategoryEnum) {
        HeroImagesEntity entity = heroPageRepository.findByCategory(heroImageCategoryEnum);

        return modelMapper.map(entity, HeroImageDTO.class);
    }
}

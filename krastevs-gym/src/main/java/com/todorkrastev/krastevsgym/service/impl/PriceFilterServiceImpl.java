package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.PriceFilterDTO;
import com.todorkrastev.krastevsgym.repository.PriceFilterRepository;
import com.todorkrastev.krastevsgym.service.PriceFilterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceFilterServiceImpl implements PriceFilterService {
    private final PriceFilterRepository priceFilterRepository;
    private final ModelMapper modelMapper;

    public PriceFilterServiceImpl(PriceFilterRepository priceFilterRepository, ModelMapper modelMapper) {
        this.priceFilterRepository = priceFilterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PriceFilterDTO> findAll() {
        return priceFilterRepository
                .findAll()
                .stream()
                .map(priceFilterEntity -> modelMapper.map(priceFilterEntity, PriceFilterDTO.class))
                .toList();
    }
}

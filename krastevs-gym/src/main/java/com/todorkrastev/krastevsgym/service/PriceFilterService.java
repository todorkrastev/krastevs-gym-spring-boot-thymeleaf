package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.PriceFilterDTO;

import java.util.List;

public interface PriceFilterService {

    List<PriceFilterDTO> findAll();
}

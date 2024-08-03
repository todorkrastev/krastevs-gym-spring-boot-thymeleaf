package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.PriceFilterDTO;
import com.todorkrastev.krastevsgym.model.entity.PriceFilterEntity;
import com.todorkrastev.krastevsgym.repository.PriceFilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceFilterServiceImplTest {

    private PriceFilterServiceImpl priceFilterService;

    @Mock
    private PriceFilterRepository mockRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        priceFilterService = new PriceFilterServiceImpl(mockRepository, mockModelMapper);
    }

    @Test
    void findAll_ReturnsPriceFilterDTOList() {
        PriceFilterEntity priceFilterEntity = new PriceFilterEntity();
        PriceFilterDTO priceFilterDTO = new PriceFilterDTO();

        when(mockRepository.findAll()).thenReturn(List.of(priceFilterEntity));
        when(mockModelMapper.map(priceFilterEntity, PriceFilterDTO.class)).thenReturn(priceFilterDTO);

        List<PriceFilterDTO> result = priceFilterService.findAll();

        assertEquals(1, result.size());
        assertEquals(priceFilterDTO, result.getFirst());
    }
}
package com.todorkrastev.krastevsgym.model.dto;

import java.math.BigDecimal;
import java.util.Map;

public record ExRatesDTO(String base, Map<String, BigDecimal> rates) {
}

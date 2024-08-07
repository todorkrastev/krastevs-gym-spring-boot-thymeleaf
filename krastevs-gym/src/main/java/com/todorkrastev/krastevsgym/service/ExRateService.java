package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.ExRatesDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExRateService {

    boolean hasInitializedExRates();

    ExRatesDTO fetchExRates();

    void updateRates(ExRatesDTO exRatesDTO);

    Optional<BigDecimal> findExRate(String from, String to);

    BigDecimal convert(String from, String to, BigDecimal amount);

    List<String> getEURAndCHFAndUSDCurrencies(String EUR, String CHF, String USD);

    void deleteAllRates();
}

package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.config.ForexApiConfig;
import com.todorkrastev.krastevsgym.exception.ApiObjectNotFoundException;
import com.todorkrastev.krastevsgym.model.entity.ExRateEntity;
import com.todorkrastev.krastevsgym.repository.ExRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ExRateServiceImplTest {
    private static final class TestRates {
        // SUD  -> base
        // CUR1 -> 4
        // CUR2 -> 0,5

        private static final String BASE_CURRENCY = "SUD";

        private static final ExRateEntity CUR1 = new ExRateEntity()
                .setCurrency("CUR1").setRate(new BigDecimal("4"));

        private static final ExRateEntity CUR2 = new ExRateEntity()
                .setCurrency("CUR2").setRate(new BigDecimal("0.5"));
    }

    private ExRateServiceImpl toTest;

    @Mock(strictness = Mock.Strictness.LENIENT)
    private ExRateRepository mockRepository;

    @BeforeEach
    void setUp() {
        toTest = new ExRateServiceImpl(
                mockRepository,
                null,
                new ForexApiConfig().setBase(TestRates.BASE_CURRENCY)
        );
    }

    @Test
    void hasInitializedExRates() {
        when(mockRepository.count()).thenReturn(0L);
        assertFalse(toTest.hasInitializedExRates());

        when(mockRepository.count()).thenReturn(-5L);
        assertFalse(toTest.hasInitializedExRates());

        when(mockRepository.count()).thenReturn(10L);
        assertTrue(toTest.hasInitializedExRates());
    }

    @Test
    void fetchExRates() {
    }

    @Test
    void updateRates() {
    }

    @Test
    void findExRate() {
    }

    @ParameterizedTest(name = "Converting {2} {0} to {1}. Expected {3}")
    @CsvSource(
            textBlock = """
                    SUD, CUR1,  1, 4.00
                    SUD, CUR1,  2, 8.00
                    SUD, SUD,   1, 1
                    CUR1, CUR2, 1, 0.12
                    CUR2, CUR1, 1, 8.00
                    TKD, TKD,  1, 1
                    """
    )
    void convert(String from, String to, BigDecimal amount, BigDecimal expected) {
        initExRates();

        BigDecimal result = toTest.convert(from, to, amount);

        assertEquals(expected, result);
    }

    @Test
    void apiObjectNotFoundException() {
        assertThrows(ApiObjectNotFoundException.class,
                () -> toTest.convert("NOT_EXIST_1", "NOT_EXIST_2", BigDecimal.ONE));
    }

    private void initExRates() {
        when(mockRepository.findByCurrency(TestRates.CUR1.getCurrency()))
                .thenReturn(Optional.of(TestRates.CUR1));
        when(mockRepository.findByCurrency(TestRates.CUR2.getCurrency()))
                .thenReturn(Optional.of(TestRates.CUR2));
    }
}
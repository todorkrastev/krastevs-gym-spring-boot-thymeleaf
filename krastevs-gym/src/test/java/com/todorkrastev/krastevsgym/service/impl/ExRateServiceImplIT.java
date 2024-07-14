package com.todorkrastev.krastevsgym.service.impl;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import com.todorkrastev.krastevsgym.config.ForexApiConfig;
import com.todorkrastev.krastevsgym.model.dto.ExRatesDTO;
import com.todorkrastev.krastevsgym.service.ExRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnableWireMock(
        @ConfigureWireMock(name = "exchange-rate-service")
)
public class ExRateServiceImplIT {
    @InjectWireMock("exchange-rate-service")
    private WireMockServer wireMockServer;

    @Autowired
    private ExRateService exRateService;

    @Autowired
    private ForexApiConfig forexApiConfig;

    @BeforeEach
    void setUp() {
        forexApiConfig.setUrl(wireMockServer.baseUrl() + "/test-currencies");
    }

    @Test
    void testFetchExchangeRates() {
        wireMockServer.stubFor(get("/test-currencies").willReturn(
                aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                  {
                                      "base": "USD",
                                      "rates": {
                                      "EUR": 0.92,
                                      "CHF": 0.89
                                      }
                                  }
                                """)));

        ExRatesDTO exRatesDTO = exRateService.fetchExRates();

        assertEquals("USD", exRatesDTO.base());
        assertEquals(2, exRatesDTO.rates().size());
        assertEquals(new BigDecimal("0.92"), exRatesDTO.rates().get("EUR"));
        assertEquals(new BigDecimal("0.89"), exRatesDTO.rates().get("CHF"));
    }
}

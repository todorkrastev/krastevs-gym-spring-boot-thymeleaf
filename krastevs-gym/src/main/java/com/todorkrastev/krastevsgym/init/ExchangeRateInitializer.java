package com.todorkrastev.krastevsgym.init;

import com.todorkrastev.krastevsgym.service.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
@ConditionalOnProperty(name = "forex.init-ex-rates", havingValue = "true")
public class ExchangeRateInitializer implements CommandLineRunner {
    private final ExRateService exRateService;

    public ExchangeRateInitializer(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @Override
    public void run(String... args) {
        if (!exRateService.hasInitializedExRates()) {
            exRateService.updateRates(exRateService.fetchExRates());
        }
    }
}

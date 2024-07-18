package com.todorkrastev.krastevsgym.init;

import com.todorkrastev.krastevsgym.service.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//TODO: Uncomment after the implementaion of the Integration test
//@Component
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

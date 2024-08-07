package com.todorkrastev.krastevsgym.scheduler.impl;

import com.todorkrastev.krastevsgym.scheduler.ExchangeRateScheduler;
import com.todorkrastev.krastevsgym.service.ExRateService;
import com.todorkrastev.krastevsgym.web.aop.LogExRatesScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class ExchangeRateSchedulerImpl implements ExchangeRateScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateSchedulerImpl.class);
    private final ExRateService exRateService;

    public ExchangeRateSchedulerImpl(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @LogExRatesScheduler
    @Scheduled(cron = "0 0 * * * ?", zone="${timezone}") // Runs every day at 00:00 AM
    @Override
    public void fetchAndUpdateExchangeRates() {
        if (exRateService.hasInitializedExRates()) {
            exRateService.deleteAllRates();
            exRateService.updateRates(exRateService.fetchExRates());
        } else {
            exRateService.updateRates(exRateService.fetchExRates());
        }

        LOGGER.info("===================Exchange rates updated successfully.===================");
    }
}

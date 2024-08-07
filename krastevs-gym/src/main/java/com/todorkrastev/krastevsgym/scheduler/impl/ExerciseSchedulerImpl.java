package com.todorkrastev.krastevsgym.scheduler.impl;

import com.todorkrastev.krastevsgym.scheduler.ExerciseScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ExerciseSchedulerImpl implements ExerciseScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseSchedulerImpl.class);

    @Scheduled(fixedRate = 3600000) // Runs every hour
    //@Scheduled(fixedRate = 3000)
    @Override
    public void remindToStandUp() {
        LOGGER.info("===================Time to drink your protein shake!=================");
    }

    @Scheduled(cron = "0 0 9 * * ?") //Runs every day at 9:00 AM
    @Override
    public void yourDailyStatus() {
        LOGGER.info("===================Good morning! Your daily status!===================");
    }
}

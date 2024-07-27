package com.todorkrastev.krastevsgym.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ExerciseSchedulerImpl implements ExerciseScheduler {

    @Scheduled(fixedRate = 3600000) // Runs every hour
    //@Scheduled(fixedRate = 3000)
    @Override
    public void remindToStandUp() {
        System.out.println("Time to stand up!");
    }

    @Scheduled(cron = "0 0 9 * * ?") //Runs every day at 9:00 AM
    @Override
    public void yourDailyStatus() {
        System.out.println("Good morning! Your daily status!");
    }
}

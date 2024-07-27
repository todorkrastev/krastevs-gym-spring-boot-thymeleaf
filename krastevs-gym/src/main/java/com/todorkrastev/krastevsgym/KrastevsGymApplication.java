package com.todorkrastev.krastevsgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KrastevsGymApplication {

    public static void main(String[] args) {
        SpringApplication.run(KrastevsGymApplication.class, args);
    }
}

package com.todorkrastev.krastevsgym.init;

import com.todorkrastev.krastevsgym.service.DbService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeAppDatabase implements CommandLineRunner {
    private final DbService dbService;

    public InitializeAppDatabase(DbService dbService) {
        this.dbService = dbService;
    }


    @Override
    public void run(String... args) {
        dbService.init();
    }
}

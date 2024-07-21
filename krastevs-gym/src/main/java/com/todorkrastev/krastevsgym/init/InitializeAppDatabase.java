package com.todorkrastev.krastevsgym.init;

import com.todorkrastev.krastevsgym.service.DbServiceInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeAppDatabase implements CommandLineRunner {
    private final DbServiceInitializer dbServiceInitializer;

    public InitializeAppDatabase(DbServiceInitializer dbServiceInitializer) {
        this.dbServiceInitializer = dbServiceInitializer;
    }


    @Override
    public void run(String... args) {
        dbServiceInitializer.init();
    }
}

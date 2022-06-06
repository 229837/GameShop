package com.app.gameshop;

import com.app.gameshop.igdbconnection.IGDBConnection;
import com.app.gameshop.model.Genre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameShopApplication {
    public static void main(String[] args) {
        IGDBConnection.getTop10GamesAllTime();
        IGDBConnection.getGame("Portal");

        SpringApplication.run(GameShopApplication.class, args);
    }
}

package com.app.GameShop;

import com.app.gameshop.igdbconnection.IGDBConnection;
import com.app.gameshop.model.Genre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameShopApplication.class, args);
    }
}

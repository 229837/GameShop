package com.app.gameshop;

import com.app.gameshop.igdbconnection.IGDBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameShopApplication {
    public static void main(String[] args) {
        IGDBConnection.TestMethod();
        SpringApplication.run(GameShopApplication.class, args);
    }
}

package com.app.gameshop.services;

import com.app.GameShop.services.RecomendationService;
import com.app.gameshop.model.Product;
import com.app.gameshop.model.Client;
import com.app.gameshop.model.Genre;
import com.app.gameshop.repositories.ProductRepository;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RecomendationServiceTest {

    @Test
    public void test() {
        Random rand = new Random();

        ProductService ps = new ProductService(new ProductRepository());

        for(int i=0; i<100; i++) {
            Product p1 = new Product();
            p1.setId(UUID.randomUUID());
            p1.setName("Gra" + Integer.toString(i));
            p1.setPrice(5.25f + (((rand.nextInt()%19)-10) * 0.37f));
            if(i == 0) {
                p1.setReleaseDate(LocalDate.of(2010, 1, 1));
            } else if (i == 1) {
                p1.setReleaseDate(LocalDate.of(2020, 1, 1));
            } else {
                p1.setReleaseDate(LocalDate.of(2010 + (Math.abs(rand.nextInt())%31)-15, 1, 1));
            }
            if(i < 75) {
                p1.setGenre(Genre.FPS);
            }
            else {
                p1.setGenre(Genre.STRATEGY);
            }
            p1.getDetails().setNumberOfPurchases(Math.abs(rand.nextInt())%100);
            ps.add(p1);
        }

        Client client = new Client(UUID.randomUUID(), "aaa", "bbb", 10, 10, 2010);
        for(int i=0; i<ps.getAll().size(); i++) {
            if(Math.abs(rand.nextInt()%100) < 15) {
                client.getAccount().getMyGames().add(ps.getAll().get(i));
            }
        }

        List<Product> prop = RecomendationService.getRecommentatedProducts(client, ps);
        for (Product product : prop) {
            System.out.println(product);
        }
    }

}
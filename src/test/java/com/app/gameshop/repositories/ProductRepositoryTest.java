package com.app.gameshop.repositories;

import com.app.gameshop.model.Product;
import com.app.gameshop.model.Genre;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepositoryTest {

    @Test
    public void ProductRepositoryTest() {

        Product p1 = new Product();
        p1.setId(UUID.randomUUID());
        p1.setName("Gra1");
        p1.setPrice(10.25f);
        p1.setGenre(Genre.FPS);

        Product p2 = new Product();
        p2.setId(UUID.randomUUID());
        p2.setName("Gra2");
        p2.setPrice(9.75f);
        p2.setGenre(Genre.STRATEGY);

        ProductRepository pr = new ProductRepository();

        assertEquals(pr.getAll().size(), 0);

        assertEquals(pr.size(), 0);
        assertEquals(pr.get(-1), null);
        assertEquals(pr.get(0), null);
        assertEquals(pr.get(1), null);

        assertEquals(pr.add(p1), true);
        assertEquals(pr.size(), 1);

        assertEquals(pr.get(0), p1);

        assertEquals(pr.remove(p2), false);

        assertEquals(pr.add(p2), true);
        assertEquals(pr.size(), 2);

        assertEquals(pr.get(0), p1);
        assertEquals(pr.get(1), p2);

        assertEquals(pr.getAll().size(), 2);
        assertEquals(pr.getAll().get(0), p1);
        assertEquals(pr.getAll().get(1), p2);

        assertEquals(pr.findByName("Gra1"), p1);

        assertEquals(pr.remove(p1), true);
        assertEquals(pr.remove(p1), false);

    }
}
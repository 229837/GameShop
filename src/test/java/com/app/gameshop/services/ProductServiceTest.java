package com.app.gameshop.services;

import com.app.gameshop.model.Product;
import com.app.gameshop.model.Genre;
import com.app.gameshop.repositories.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    public void ProductServiceTest() {

        UUID savedUUID = UUID.randomUUID();

        Product p1 = new Product();
        p1.setId(savedUUID);
        p1.setName("Gra1");
        p1.setPrice(10.25f);
        p1.setGenre(Genre.FPS);

        Product p2 = new Product();
        p2.setId(UUID.randomUUID());
        p2.setName("Gra2");
        p2.setPrice(9.75f);
        p2.setGenre(Genre.STRATEGY);

        ProductService ps = new ProductService(new ProductRepository());

        assertEquals(ps.getAll().size(), 0);
        assertEquals(ps.getByID(-1), null);
        assertEquals(ps.getByID(0), null);
        assertEquals(ps.getByID(1), null);

        assertEquals(ps.add(p1), true);
        assertEquals(ps.getAll().size(), 1);

        assertEquals(ps.getByID(0), p1);

        assertEquals(ps.remove(p2), false);

        assertEquals(ps.add(p2), true);
        assertEquals(ps.getAll().size(), 2);

        assertEquals(ps.getByID(0), p1);
        assertEquals(ps.getByID(1), p2);

        assertEquals(ps.getAll().size(), 2);
        assertEquals(ps.getAll().get(0), p1);
        assertEquals(ps.getAll().get(1), p2);

        assertEquals(ps.find(savedUUID), p1);

        assertEquals(ps.remove(p1), true);
        assertEquals(ps.remove(p1), false);
    }
}
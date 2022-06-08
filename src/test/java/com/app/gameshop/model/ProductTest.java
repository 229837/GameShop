package com.app.gameshop.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void idTest() {
        Product p = new Product();
        p.setId(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
        assertEquals(p.getId(), UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
    }

    @Test
    void nameTest() {
        Product p = new Product();
        p.setName("GraNr1");
        assertEquals(p.getName(), "GraNr1");
    }

    @Test
    void priceTest() {
        Product p = new Product();
        p.setPrice(9.99f);
        assertEquals(p.getPrice(), 9.99f);
    }

    @Test
    void genreTest() {
        Product p = new Product();
        p.setGenre(Genre.FPS);
        assertEquals(p.getGenre(), Genre.FPS);
    }

    @Test
    void releaseDateTest() {
        Product p = new Product();
        LocalDate ld = LocalDate.now();
        p.setReleaseDate(ld);
        assertEquals(p.getReleaseDate(), ld);
    }

    @Test
    void metadataTest() {
        Product p = new Product();

        assertEquals(p.getDetails().getDateAdded().getYear(), LocalDateTime.now().getYear());
        assertEquals(p.getDetails().getDateAdded().getMonth(), LocalDateTime.now().getMonth());
        assertEquals(p.getDetails().getDateAdded().getDayOfMonth(), LocalDateTime.now().getDayOfMonth());
        assertEquals(p.getDetails().getDateAdded().getHour(), LocalDateTime.now().getHour());
        assertEquals(p.getDetails().getDateAdded().getMinute(), LocalDateTime.now().getMinute());
        assertEquals(p.getDetails().getDateAdded().getSecond(), LocalDateTime.now().getSecond());

        assertEquals(p.getDetails().getNumberOfPurchases(), 0);

        p.getDetails().increasePurchases();
        assertEquals(p.getDetails().getNumberOfPurchases(), 1);

        p.getDetails().increasePurchases();
        assertEquals(p.getDetails().getNumberOfPurchases(), 2);
    }

    @Test
    void commentsTest() {
        Product product = new Product();
        Client client = new Client(UUID.randomUUID(), "Client", "1234", 1, 1, 1999);
        Comment comment0 = new Comment(client, "Test Comment 0", 3, LocalDateTime.now());
        Comment comment1 = new Comment(client, "Test Comment 1", 3, LocalDateTime.now());
        Comment comment2 = new Comment(client, "Test Comment 2", 3, LocalDateTime.now());
        assertEquals(0, product.getComments().size());
        product.addComment(comment0);
        assertEquals(1, product.getComments().size());
        assertTrue(product.getComments().contains(comment0));
        product.addComment(comment1);
        assertEquals(2, product.getComments().size());
        assertTrue(product.getComments().contains(comment1));
        product.addComment(comment2);
        assertEquals(3, product.getComments().size());
        assertTrue(product.getComments().contains(comment2));
        product.addComment(comment2);
        assertEquals(3, product.getComments().size());
        assertTrue(product.getComments().contains(comment2));
    }
}
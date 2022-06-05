package com.app.gameshop.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void moneyAccountTest() {
        Account account = new Account();

        assertEquals(account.getWallet().getBalance(), 0);
        assertEquals(account.getMyGames().getAll().size(), 0);

        assertTrue(account.getWallet().AddMoney(10.25f));
        assertEquals(account.getWallet().getBalance(), 10.25f);
        assertTrue(account.getWallet().AddMoney(1.25f));
        assertEquals(account.getWallet().getBalance(), 11.5f);
        assertTrue(account.getWallet().RemoveMoney(10.25f));
        assertEquals(account.getWallet().getBalance(), 1.25f);
        assertFalse(account.getWallet().RemoveMoney(10.25f));
        assertEquals(account.getWallet().getBalance(), 1.25f);
    }

    @Test
    void gamesAccountTest() {

        Product p1 = new Product();
        p1.setId(UUID.randomUUID());
        p1.setName("Gra1");
        p1.setPrice(10.25f);
        p1.setGenre(Genre.FPS);

        Product p2 = new Product();
        p2.setId(UUID.randomUUID());
        p2.setName("Gra2");
        p2.setPrice(0.00f);
        p2.setGenre(Genre.STRATEGY);

        Account account = new Account();

        assertEquals(account.getWallet().getBalance(), 0);
        assertEquals(account.getMyGames().getAll().size(), 0);

        assertEquals(account.getMyGames().getAll().size(), 0);
        assertTrue(account.getMyGames().add(p1));
        assertEquals(account.getMyGames().getAll().size(), 1);
        assertTrue(account.getMyGames().add(p2));
        assertEquals(account.getMyGames().getAll().size(), 2);
        assertFalse(account.getMyGames().add(p2));
        assertEquals(account.getMyGames().getAll().size(), 2);
    }

}
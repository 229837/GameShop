package com.app.gameshop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    @Test
    void intToGenre() {
        assertEquals(Genre.intToGenre(0), Genre.Undefined);
        assertEquals(Genre.intToGenre(1), Genre.STRATEGY);
        assertEquals(Genre.intToGenre(2), Genre.FPS);
        assertEquals(Genre.intToGenre(3), Genre.Undefined);
    }
}
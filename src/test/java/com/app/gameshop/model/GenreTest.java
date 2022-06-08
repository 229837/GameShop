package com.app.gameshop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    @Test
    void intToGenre() {
        assertEquals(Genre.intToGenre(0), Genre.Undefined);
        assertEquals(Genre.intToGenre(1), Genre.STRATEGY);
        assertEquals(Genre.intToGenre(2), Genre.FPS);
        assertEquals(Genre.intToGenre(3), Genre.FIGHTING);
        assertEquals(Genre.intToGenre(4), Genre.RACING);
        assertEquals(Genre.intToGenre(5), Genre.PUZZLE);
        assertEquals(Genre.intToGenre(6), Genre.RPG);
        assertEquals(Genre.intToGenre(7), Genre.SIMULATOR);
        assertEquals(Genre.intToGenre(8), Genre.SPORT);
        assertEquals(Genre.intToGenre(9), Genre.MUSIC);
        assertEquals(Genre.intToGenre(10), Genre.PLATFORMER);
        assertEquals(Genre.intToGenre(11), Genre.ACTION);
        assertEquals(Genre.intToGenre(12), Genre.POINTNCLICK);
        assertEquals(Genre.intToGenre(13), Genre.Undefined);
    }
}
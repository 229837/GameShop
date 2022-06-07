package com.app.gameshop.model;

public enum Genre {
    Undefined, STRATEGY, FPS;

    public static Genre intToGenre(int genre) {
        switch (genre) {
            case 1: return STRATEGY;
            case 2: return FPS;
            default: return Undefined;
        }
    }

    public static int genreToInt(Genre genre) {
        switch (genre) {
            case STRATEGY: return 1;
            case FPS: return 2;
            default: return 0;
        }
    }
}

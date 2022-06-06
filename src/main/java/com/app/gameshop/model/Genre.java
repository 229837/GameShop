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
}

package com.app.gameshop.model;

public enum Genre {
    Undefined, STRATEGY, FPS, FIGHTING, RACING, PUZZLE, RPG, SIMULATOR, SPORT, MUSIC, PLATFORMER, ACTION, POINTNCLICK;

    public static Genre intToGenre(int genre) {
        switch (genre) {
            case 1: return STRATEGY;
            case 2: return FPS;
            case 3: return FIGHTING;
            case 4: return RACING;
            case 5: return PUZZLE;
            case 6: return RPG;
            case 7: return SIMULATOR;
            case 8: return SPORT;
            case 9: return MUSIC;
            case 10: return PLATFORMER;
            case 11: return ACTION;
            case 12: return POINTNCLICK;
            default: return Undefined;
        }
    }
}

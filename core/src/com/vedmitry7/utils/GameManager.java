package com.vedmitry7.utils;

import com.vedmitry7.enums.GameState;


public class GameManager {
    static  {
        gameState = GameState.RUNNING;
    }
    static GameState gameState;

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        GameManager.gameState = gameState;
    }
}

package com.vedmitry7.utils;


public class Constants {

    public static final int APP_WIDTH = 155;
    public static final int APP_HEIGHT = 320;

    public static final int GAME_FIELD_HEIGHT = 310;
    public static final int GAME_FIELD_WIDTH = 160;
    public static final float BLOCK_SIZE = 15.5f;


    public static final String PREFERENCE_NAME = "prefs";

    public static final float INC_STEP_TIME = 0.035f;
    public static final float MAX_STEP_TIME = 0.8f;
    public static final float MIN_STEP_TIME = 0.1f;
    public static final float DROPING_TIME = 0.02f;


    public static final int RIGHT = 1;
    public static final int LEFT = -1;

    public final static int[][][] SHAPES = {
            {       {0,0,0,0},
                    {0,0,0,0},
                    {1,1,1,1},
                    {0,0,0,0},
                    {4, 100}}, // I
            {       {0,0,0,0},
                    {0,1,1,0},
                    {0,1,1,0},
                    {0,0,0,0},
                    {3, 101}}, // O
            {       {1,0,0,0},
                    {1,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0},
                    {3, 102}}, // J
            {       {0,0,1,0},
                    {1,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0},
                    {3, 102}}, // L
            {       {0,1,1,0},
                    {1,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0},
                    {3, 104}}, // S
            {       {1,1,1,0},
                    {0,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0},
                    {3, 103}}, // T
            {       {1,1,0,0},
                    {0,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0},
                    {3, 104}}  // Z
    };
}

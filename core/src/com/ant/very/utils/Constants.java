package com.ant.very.utils;

public class Constants {
    public static final int MAP_HEIGHT = 30;
    public static final int MAP_WIDTH = 30;

    public static final int TILES_VERTICALLY = 13;
    public static final int TILES_HORIZONTALLY = 9;

    public static final String ACTION_MOVE = "move";
    public static final String ACTION_PICKUP = "pick up";
    public static final String ACTION_BUY = "buy";
    public static final String BOT_CALL = "ring ring";
    // Arguments:
    public static final String DIRECTION_UP = "up";
    public static final String DIRECTION_DOWN = "down";
    public static final String DIRECTION_LEFT = "left";
    public static final String DIRECTION_RIGHT = "right";
    public static final String ITEM_FOOD = "food";


    public enum Tiles { // This is PROBABLY to be changed, I'm thinking about introducing a Tile class
        SAND,  // 0
        METAL, // 1
        BOX,   // 2
        LAVA,  // 3
//        GRASS, // 4
        CAKE,  // 5
        STONE, // 6
        BOMB;  // 7

        public static int count() {
            return values().length;
        }
    }
}

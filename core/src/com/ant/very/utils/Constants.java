package com.ant.very.utils;

/**
 * Created by hubert on 28.10.14.
 */
public class Constants {
    public static final int MAP_HEIGHT = 30;
    public static final int MAP_WIDTH = 30;

    public static final int TILES_VERTICALLY = 13;
    public static final int TILES_HORIZONTALLY = 9;

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

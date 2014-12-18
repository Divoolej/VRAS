package com.ant.very.utils;

/**
 * Created by hubert on 28.10.14.
 */
public class Constants {
    public static final int MAP_HEIGHT = 308;
    public static final int MAP_WIDTH = 19;

    public static final int TILES_VERTICALLY = 13;
    public static final int TILES_HORIZONTALLY = 9;

    public enum Sprites {
        EMPTY,
        BACKGROUND,
        BEDROCK,
        MARBLE1,
        MARBLE2,
        SAND,
        STONE,
        CHERRY,
        GRASS,
        MUSHROOM_BROWN,
        MUSHROOM_RED,
        SNAIL;

        private final int value;
        public static int count() {
            return values().length;
        }
        /*private Sprites(int value) {
            this.value = value;
        }*/
        private Sprites() {
            value = values().length; // Will see if this works.
        }
        public int toInt() {return value;}
    }
}

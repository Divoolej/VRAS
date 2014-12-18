package com.ant.very.utils;

/**
 * Created by hubert on 28.10.14.
 */
public class Constants {
    public static final int MAP_HEIGHT = 208;
    public static final int MAP_WIDTH = 19;

    public static final int TILES_VERTICALLY = 13;
    public static final int TILES_HORIZONTALLY = 9;

    public enum Sprites {
        EMPTY(0),
        BACKGROUND(1),
        BEDROCK(2),
        MARBLE1(3),
        MARBLE2(4),
        SAND(5),
        STONE(6),
        CHERRY(7),
        MUSHROOM_BROWN(8),
        MUSHROOM_RED(9),
        SNAIL(10),
        ANT(11);

        private final int value;
        public static int count() {
            return values().length;
        }
        private Sprites(int value) {
            this.value = value;
        }
        public int toInt() {return value;}
    }
}

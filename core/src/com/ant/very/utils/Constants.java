package com.ant.very.utils;

/**
 * Created by hubert on 28.10.14.
 */
public class Constants {
    public static final int LEVEL_ONE_START = 9;
    public static final int LEVEL_ONE_END = 69;
    public static final int LEVEL_TWO_START = 70;
    public static final int LEVEL_TWO_END = 140;
    public static final int LEVEL_THREE_START = 141;
    public static final int LEVEL_THREE_END = 200;
    public static final int BASE_WIDTH = 9;
    public static final int BASE_HEIGHT = 8;

    public static final int MAP_HEIGHT = 208;
    public static final int MAP_WIDTH = 19;

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

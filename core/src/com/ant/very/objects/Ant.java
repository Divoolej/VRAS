package com.ant.very.objects;

import com.ant.very.utils.Constants;

public class Ant {

    /*private final int NORTH = 0;
    private final int EAST = 1;
    private final int SOUTH = 2;
    private final int WEST = 3;*/

    private int x, y; //The coordinates of Ant, in tiles.
    private static Ant ant;
    //private int direction; // e.g. "go 1 meter up" will change this to NORTH, it's then used in move() // OLD OLD OLD but i'm keeping it for now

    private Ant() { // 'private' prevents other classes from instantiating
        x = (Constants.MAP_WIDTH - 9) / 2 + 4; // 4 is a magic number, don't ask!
        y = 3;
    }

    public static Ant getInstance() { // Singleton pattern; lazy instantiation
        if (ant == null) {
            ant = new Ant();
        }
        return ant;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // LINK WITH PARSER HERE
    /*
    public example_interact(string command) {
        if command == "north"
            WorldMap.getInstance().at(x, y + 1).onInteract()
        ETC
    }
     */

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

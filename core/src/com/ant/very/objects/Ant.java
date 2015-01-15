package com.ant.very.objects;

import com.ant.very.WorldMap;
import com.ant.very.utils.Constants;

import static com.ant.very.utils.Constants.*;

public class Ant {

    private int x, y; //The coordinates of Ant, in tiles.
    private Equipment eq;
    private static Ant ant;
    //private int direction; // e.g. "go 1 meter up" will change this to NORTH, it's then used in moveBy() // OLD OLD OLD but i'm keeping it for now

    private Ant() { // 'private' prevents other classes from instantiating
        x = (Constants.MAP_WIDTH - 9) / 2 + 4; // 4 is a magic number, don't ask!
        y = 8;
        eq = new Equipment(5);
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

    public void move(String direction) {
        switch (direction) {
            case DIRECTION_DOWN:
                WorldMap.getInstance().at(x, y - 1).onInteract();
                break;
            case DIRECTION_UP:
                WorldMap.getInstance().at(x, y + 1).onInteract();
                break;
            case DIRECTION_LEFT:
                WorldMap.getInstance().at(x - 1, y).onInteract();
                break;
            case DIRECTION_RIGHT:
                WorldMap.getInstance().at(x + 1, y).onInteract();
                break;
        }
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

package com.ant.very.objects;

import com.ant.very.WorldMap;
import com.ant.very.utils.Constants;

import static com.ant.very.utils.Constants.*;

public class Ant {

    private int hp, fuel;
    private int x, y; //The coordinates of Ant, in tiles.
    private static Ant ant;
    //private int direction; // e.g. "go 1 meter up" will change this to NORTH, it's then used in moveBy() // OLD OLD OLD but i'm keeping it for now

    private Ant(int hp, int fuel) {
        this.hp = 100;
        this.fuel = 100; // 'private' prevents other classes from instantiating
        this.x = (Constants.MAP_WIDTH - 9) / 2 + 4; // 4 is a magic number, don't ask!
        this.y = 8;
    }

    public static Ant getInstance() { // Singleton pattern; lazy instantiation
        if (ant == null) {
            ant = new Ant(100, 100);
        }
        return ant;
    }

    public void moveInDirection(String direction) {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}

package com.ant.very.objects;

import com.ant.very.WorldMap;
import com.ant.very.utils.Constants;

import static com.ant.very.utils.Constants.*;

public class Ant {

    private int x, y; //The coordinates of Ant, in tiles.

    public Inventory getEq() {
        return eq;
    }

    private Inventory eq;
    private static Ant ant;
    //private int direction; // e.g. "go 1 meter up" will change this to NORTH, it's then used in moveBy() // OLD OLD OLD but i'm keeping it for now

    private Ant() {
        x = (Constants.MAP_WIDTH - 9) / 2 + 4; // 4 is a magic number, don't ask!
        y = 2;
        eq = new Inventory(5);
    }

    public static Ant getInstance() {
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

    public void moveInDirection(String direction) { //Trebuszq - handle the result from onWalk
        switch (direction) {
            case DIRECTION_DOWN:
                WorldMap.getInstance().at(x, y - 1).onWalk();
                break;
            case DIRECTION_UP:
                WorldMap.getInstance().at(x, y + 1).onWalk();
                break;
            case DIRECTION_LEFT:
                WorldMap.getInstance().at(x - 1, y).onWalk();
                break;
            case DIRECTION_RIGHT:
                WorldMap.getInstance().at(x + 1, y).onWalk();
                break;
        }
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getQuantity(String foundItem) { //Trebuszq - add other cherry-berries
        switch (foundItem) {
            case ITEM_CHERRY:
                return String.valueOf(eq.getNumCherries()) + " cherries.";
            case ITEM_FUEL:
                return String.valueOf(eq.getCurrentFuel()) + " fuel.";
            case ITEM_FREE_SPACE:
                return String.valueOf(eq.getFreeSpace() + " free spaces in my inventory");
        }
        return " no such thing!";
    }

    public void digInDirection(String direction) { //Trebuszq - handle the result from onDig
        switch (direction) {
            case DIRECTION_DOWN:
                WorldMap.getInstance().at(x, y - 1).onDig();
                break;
            case DIRECTION_UP:
                WorldMap.getInstance().at(x, y + 1).onDig();
                break;
            case DIRECTION_LEFT:
                WorldMap.getInstance().at(x - 1, y).onDig();
                break;
            case DIRECTION_RIGHT:
                WorldMap.getInstance().at(x + 1, y).onDig();
                break;
        }
    }

    //TODO: Trebuszq - assign this function to the keyword "pick up";
    public void    pickUp() {
        WorldMap.getInstance().at(x, y).onDig();
    }

    public void lookInDirection(String direction) { //TODO: Trebuszq - handle the result from onLook
        switch (direction) {
            case DIRECTION_DOWN:
                WorldMap.getInstance().at(x, y - 1).onLook();
                break;
            case DIRECTION_UP:
                WorldMap.getInstance().at(x, y + 1).onLook();
                break;
            case DIRECTION_LEFT:
                WorldMap.getInstance().at(x - 1, y).onLook();
                break;
            case DIRECTION_RIGHT:
                WorldMap.getInstance().at(x + 1, y).onLook();
                break;
        }
    }
}

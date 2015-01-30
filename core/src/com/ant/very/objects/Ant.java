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

    public String moveInDirection(String direction) {
        switch (direction) {
            case DIRECTION_DOWN:
                return WorldMap.getInstance().at(x, y - 1).onWalk();
            case DIRECTION_UP:
                return WorldMap.getInstance().at(x, y + 1).onWalk();
            case DIRECTION_LEFT:
                return WorldMap.getInstance().at(x - 1, y).onWalk();
            case DIRECTION_RIGHT:
                return WorldMap.getInstance().at(x + 1, y).onWalk();
            case LOCATION_HOME:
                return WorldMap.getInstance().at(9, 2).onWalk();
            default:
                // (never gonna happen)
                return "wat";
        }
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getQuantity(String foundItem) {
        switch (foundItem) {
            case ITEM_CHERRY:
                return String.valueOf("I've got " + eq.getNumCherries()) + " cherries.";
            case ITEM_BERRY:
                return String.valueOf("I've got " + eq.getNumBerries()) + " berries.";
            case ITEM_BLUEBERRY:
                return String.valueOf("I've got " + eq.getNumBlueberries()) + " blueberries.";
            case ITEM_RASPBERRY:
                return String.valueOf("I've got " + eq.getNumRaspberries()) + " raspberries.";
            case ITEM_FUEL:
                return String.valueOf("I've got " + eq.getCurrentFuel()) + " fuel.";
            case ITEM_FREE_SPACE:
                return String.valueOf("I've got " + eq.getFreeSpace() + " free spaces in my inventory");
            case ITEM_MONEY:
            case "cash":
            case "gold":
                return String.valueOf("I've got " + eq.getMoney() + " gold pieces.");
        }
        return " no such thing!";
    }

    public String digInDirection(String direction) {
        switch (direction) {
            case DIRECTION_DOWN:
                return WorldMap.getInstance().at(x, y - 1).onDig();
            case DIRECTION_UP:
                return WorldMap.getInstance().at(x, y + 1).onDig();
            case DIRECTION_LEFT:
                return WorldMap.getInstance().at(x - 1, y).onDig();
            case DIRECTION_RIGHT:
                return WorldMap.getInstance().at(x + 1, y).onDig();
            default:
                return "wat do";
        }
    }

    public String pickUp() {
        return WorldMap.getInstance().at(x, y).onDig();
    }

    public String lookInDirection(String direction) {
        switch (direction) {
            case DIRECTION_DOWN:
                return WorldMap.getInstance().at(x, y - 1).onLook();
            case DIRECTION_UP:
                return WorldMap.getInstance().at(x, y + 1).onLook();
            case DIRECTION_LEFT:
                return WorldMap.getInstance().at(x - 1, y).onLook();
            case DIRECTION_RIGHT:
                return WorldMap.getInstance().at(x + 1, y).onLook();
            default:
                return "wat do";
        }
    }
}

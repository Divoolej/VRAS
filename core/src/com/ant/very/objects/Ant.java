package com.ant.very.objects;

import com.ant.very.WorldMap;
import com.ant.very.utils.Constants;

import static com.ant.very.utils.Constants.*;

public class Ant {

    private int x, y; //The coordinates of Ant, in tiles.
     private int hp;
    private int maxHp = 100;
    private int fuelBurnSpeed = 5;

    public Inventory getEq() {
        return eq;
    }

    private Inventory eq;
    private static Ant ant;
    //private int direction; // e.g. "go 1 meter up" will change this to NORTH, it's then used in moveBy() // OLD OLD OLD but i'm keeping it for now

    private Ant() {
        x = (Constants.MAP_WIDTH - 9) / 2 + 4; // 4 is a magic number, don't ask!
        y = 8;
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

    public void eatCherry() {
        this.eq.removeCherry(1);
        hp += 20;
    }

    public String getQuantity(String foundItem) {
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

    public void digInDirection(String direction) {
        
    }
}

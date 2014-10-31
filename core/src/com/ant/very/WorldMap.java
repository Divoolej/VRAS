package com.ant.very;

import com.ant.very.utils.Constants;
import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Divoolej on 2014-10-27.
 */

public class WorldMap {
    // This is OK, but we could create a tile class for added functionality.
    public static final int DIRT_TILE = 0;
    public static final int STONE_TILE = 1;
    public static final int TRAP_TILE = 2;

    private static WorldMap worldMap = null;

    private final int height;
    private final int width;
    private Map<Coordinate, Integer> hashMap;

    private class Coordinate {
        final int x;
        final int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // The methods below are necessary to pass this object as the key for the hash map.
        public int hashCode() {
            return x >> 16 & y;
        }

        public boolean equals(Object o) {
            if (o instanceof Coordinate) {
                Coordinate c = (Coordinate)o;
                return c.x==x && c.y==y;
            }
            return false;
        }
    }

    private WorldMap(int width, int height) { // Prevent other classes from instantiating
        this.height = height;
        this.width = width;
        hashMap = new HashMap<Coordinate, Integer>();
        generateMap();
    }

    public static WorldMap getInstance() { // Singleton pattern; lazy instantiation
        if (worldMap == null) {
            worldMap = new WorldMap(Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
        }
        return worldMap;
    }

    private void generateMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                hashMap.put(new Coordinate(x, y), DIRT_TILE);
            }
        }
    }

    public int getTileType(int x, int y) {
        return hashMap.get(new Coordinate(x, y));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

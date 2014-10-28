package com.ant.very;

import com.ant.very.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Divoolej on 2014-10-27.
 */

public class WorldMap {
    private static WorldMap worldMap = null;

    private final int height;
    private final int width;
    private Map<Coordinate, Tile> map;

    private class Coordinate {
        final int x;
        final int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object o) {
            if (o instanceof Coordinate) {
                Coordinate c = (Coordinate)o;
                return c.x==x && c.y==y;
            }
            return false;
        }
    }

    public enum Tile {
        GROUND,
        STONE,
        TRAP
    }

    private WorldMap(int height, int width) { // Prevent other classes from instantiating
        this.height = height;
        this.width = width;
        map = new HashMap<Coordinate, Tile>();
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
                map.put(new Coordinate(x, y), Tile.GROUND);
            }
        }
    }

    public Tile getTile(int x, int y) {
        return map.get(new Coordinate(x, y));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

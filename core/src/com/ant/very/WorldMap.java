package com.ant.very;

import com.ant.very.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private static WorldMap worldMap = null;

    private final int height;
    private final int width;
    private Map<Coordinate, Tile> hashMap;

    enum Tile {
//        GRASS_TILE,
//        BOX_TILE,
//        BOMB_TILE
        DIRT_TILE,
        METAL_TILE,
        LAVA_TILE,
        STONE_TILE,
        CAKE_TILE,
    }

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
        hashMap = new HashMap<Coordinate, Tile>();
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
                hashMap.put(new Coordinate(x, y), getRandomTile());
            }
        }
    }

    public Tile getRandomTile() {
        return Tile.values()[(int)(Math.random()*Tile.values().length)];
    }

    public Tile getTileType(int x, int y) {
        return hashMap.get(new Coordinate(x, y));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

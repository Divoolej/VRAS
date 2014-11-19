package com.ant.very;

/*
 * A Singleton class for generating and storing map data
 */

import com.ant.very.utils.Constants;

public class WorldMap {
    private static WorldMap worldMap = null; // The one and only instance

    private final int height; // The height of the map array, measured in tiles
    private final int width;  // The width of the map array, measured in tiles

    private int[][] map; // The Array containing the map data, each field contains an integer
                         // which represents certain unique type of game object. Possible values
                         // can be found in the Constants class

    private WorldMap(int width, int height) { // 'private' prevents other classes from instantiating
        this.height = height;
        this.width = width;

        map = new int[width][height];

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
                map[x][y] = getRandomTile();
            }
        }
    }

    // Function returns the id of a tile given at a target (x, y) position
    public int at(int x, int y) {
        return map[x][y];
    }

    // Function returns an id of a random tile, currently used for debugging.
    public int getRandomTile() {
        return (int)( Math.random() * Constants.Tiles.count() );
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

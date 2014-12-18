package com.ant.very;

/*
 * A Singleton class for generating and storing map data
 */

import com.ant.very.objects.MapEntity;
import com.ant.very.objects.map.Bedrock;
import com.ant.very.objects.map.Empty;
import com.ant.very.utils.Constants;

public class WorldMap {
    private static WorldMap worldMap = null; // The one and only instance

    private final int height; // The height of the map array, measured in tiles
    private final int width;  // The width of the map array, measured in tiles

    private final int LEVEL_ONE_START = 9;
    private final int LEVEL_ONE_END = 79;
    private final int LEVEL_TWO_START = 80;
    private final int LEVEL_TWO_END = 180;
    private final int LEVEL_THREE_START = 181;
    private final int LEVEL_THREE_END = 211;
    private final int BASE_WIDTH = 9;
    private final int BASE_HEIGHT = 8;

    private MapEntity[][] map; // The Array containing the map data, each field contains an integer
                         // which represents certain unique type of game object. Possible values
                         // can be found in the Constants class

    private WorldMap(int width, int height) { // 'private' prevents other classes from instantiating
        this.height = height;
        this.width = width;

        map = new MapEntity[width][height];

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
                fillMapWithEmpty();
                generateBase();
                generateLevelOne(LEVEL_ONE_START, LEVEL_ONE_END);
                generateLevelTwo(LEVEL_TWO_START, LEVEL_TWO_END);
                generateLevelThree(LEVEL_THREE_START, LEVEL_THREE_END);
            }
        }
    }

    private void fillMapWithEmpty() {
        for (int x0 = 0; x0 < width; x0++) {
            for (int y0 = 0; y0 < height; y0++) {
                map[x0][y0] = new Empty();
            }
        }
    }

    private void generateBase() {
        int left_and_right_of_base_width = (width - BASE_WIDTH) / 2;
        for (int x0 = 0; x0 <= left_and_right_of_base_width; x0++)
        {
            for (int y0 = 0; y0 < BASE_HEIGHT; y0++)
            {
                map[x0][y0] = new Bedrock();
            }
        }
        for (int x0 = left_and_right_of_base_width + BASE_WIDTH - 1; x0 < width; x0++)
        {
            for (int y0 = 0; y0 < BASE_HEIGHT; y0++)
            {
                map[x0][y0] = new Bedrock();
            }
        }
        for (int x0 = left_and_right_of_base_width + 1; x0 < left_and_right_of_base_width + BASE_WIDTH - 1; x0++) {
            map[x0][0] = new Bedrock();
        }
        map[left_and_right_of_base_width + 1][BASE_HEIGHT - 1] = new Bedrock();
        map[left_and_right_of_base_width + 2][BASE_HEIGHT - 1] = new Bedrock();
        map[left_and_right_of_base_width + BASE_WIDTH - 2][BASE_HEIGHT - 1] = new Bedrock();
        map[left_and_right_of_base_width + BASE_WIDTH - 3][BASE_HEIGHT - 1] = new Bedrock();
    }

    private void generateLevelOne(int startY, int endY) {

    }

    private void generateLevelTwo(int startY, int endY) {

    }

    private void generateLevelThree(int startY, int endY) {

    }

    // Function returns the MapEntity given at a target (x, y) position
    public MapEntity at(int x, int y) {
        return map[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

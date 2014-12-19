package com.ant.very;

import static com.ant.very.utils.Constants.*;

/*
 * A Singleton class for generating and storing map data
 */

import com.ant.very.objects.MapEntity;
import com.ant.very.objects.map.Bedrock;
import com.ant.very.objects.map.Cherry;
import com.ant.very.objects.map.Empty;
import com.ant.very.objects.map.Sand;
import com.ant.very.utils.Constants;

public class WorldMap {
    private static WorldMap worldMap = null; // The one and only instance

    private final int mapHeight; // The height of the map array, measured in tiles
    private final int mapWidth;  // The width of the map array, measured in tiles

    private MapEntity[][] map; // The Array containing the map data, each field contains an integer
                         // which represents certain unique type of game object. Possible values
                         // can be found in the Constants class

    private WorldMap(int mapWidth, int mapHeight) { // 'private' prevents other classes from instantiating
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        map = new MapEntity[mapWidth][mapHeight];

        generateMap();
    }

    public static WorldMap getInstance() { // Singleton pattern; lazy instantiation
        if (worldMap == null) {
            worldMap = new WorldMap(MAP_WIDTH, MAP_HEIGHT);
        }
        return worldMap;
    }

    private void generateMap() {
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                fillMapWithEmpty();
                generateBase();
                generateLevelOne(LEVEL_ONE_START, LEVEL_ONE_END);
//                generateLevelTwo(LEVEL_TWO_START, LEVEL_TWO_END);
//                generateLevelThree(LEVEL_THREE_START, LEVEL_THREE_END);
            }
        }
    }

    private void fillMapWithEmpty() {
        for (int x0 = 0; x0 < mapWidth; x0++) {
            for (int y0 = 0; y0 < mapHeight; y0++) {
                map[x0][y0] = new Empty(x0, y0);
            }
        }
    }

    private void generateBase() {
        int left_and_right_of_base_width = (mapWidth - BASE_WIDTH) / 2;
        for (int x0 = 0; x0 <= left_and_right_of_base_width; x0++)
        {
            for (int y0 = 0; y0 < BASE_HEIGHT; y0++)
            {
                map[x0][y0] = new Bedrock();
            }
        }
        for (int x0 = left_and_right_of_base_width + BASE_WIDTH - 1; x0 < mapWidth; x0++)
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
        for (int x = 0; x < Constants.MAP_WIDTH; x++) {
            for (int y = startY; y < endY; y++) {
                int chance = (int)((Math.random() * 10) + 1);
                if (chance == 1) {
                    map[x][y] = new Cherry(x, y);
                } else if (chance <= 3) {
                    map[x][y] = new Bedrock();
                } else {
                    map[x][y] = new Sand(x, y);
                }
            }
        }
    }

//    private void generateLevelTwo(int startY, int endY) {
//
//    }
//
//    private void generateLevelThree(int startY, int endY) {
//
//    }

    // Function returns the MapEntity given at a target (x, y) position
    public MapEntity at(int x, int y) {
        return map[x][y];
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setEmpty(int x, int y) {
        map[x][y] = new Empty(x, y);
    }
}

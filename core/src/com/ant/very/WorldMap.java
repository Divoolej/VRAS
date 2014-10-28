package com.ant.very;

/**
 * Created by Divoolej on 2014-10-27.
 */
public class WorldMap {
    private int w, h;
    private int[][] map;

    public WorldMap(int x, int y) {
        w = x;
        h = y;
        generate();
    }

    public void generate() {
        map = new int[w][h];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                map[i][j] = 0;
        }
    }

    public int getTile(int x, int y) {
        return map[x][y];
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}

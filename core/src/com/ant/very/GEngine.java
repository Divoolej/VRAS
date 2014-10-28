package com.ant.very;

import com.ant.very.Ant;
import com.ant.very.Camera;
import com.ant.very.WorldMap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Divoolej on 2014-10-27.
 */
public class GEngine { //This is the graphics "engine" of the Simulator. It's crappy.
    private WorldMap mapInfo;
    private Camera cameraInfo;
    private Ant antInfo;
    private TextureAtlas atlas;
    private Sprite[] sprites;
    private double scale;
    private int tileSize;
    SpriteBatch batch;

    public GEngine(WorldMap map, Ant ant, Camera camera, SpriteBatch batch)
    {
        mapInfo = map;
        antInfo = ant;
        cameraInfo = camera;
        tileSize = cameraInfo.getWidth() / 9;
        scale = tileSize / 86.0;
        this.batch = batch;
        loadAssets();
    }

    public void loadAssets() {
        atlas = new TextureAtlas("assets.txt");
        sprites = new Sprite[1];
        //
        sprites[0] = new Sprite(atlas.findRegion("dirt"));
        sprites[0].setSize(tileSize, tileSize);
        //
    }

    public void drawAll()
    {
        drawMap();
    }

    private void drawMap() {
        int start_x = cameraInfo.getX() / tileSize;
        int start_y = cameraInfo.getY() / tileSize;
        int end_x = start_x + 10;
        int end_y = start_y + 13;

        for (int i = 0; i < end_x - start_x; i++)
        {
            for (int j = 0; j < end_y - start_y; j++)
            {
                int tile = mapInfo.getTile(i + start_x, j + start_y);
                sprites[tile].setPosition(i * tileSize - (cameraInfo.getX() % tileSize), j * tileSize - (cameraInfo.getY() % tileSize));
                sprites[tile].draw(batch);
            }
        }
    }

    public void dispose() {
        atlas.dispose();
    }
}

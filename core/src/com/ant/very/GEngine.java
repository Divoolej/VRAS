package com.ant.very;

import com.ant.very.objects.Ant;
import com.ant.very.objects.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Divoolej on 2014-10-27.
 */
public class GEngine {
    private WorldMap worldMap;
    private Camera camera;
    private TextureAtlas atlas;
    private int tileSize;

    SpriteBatch batch;

    private Ant ant;
    private Sprite dirtSprite;

    public GEngine(WorldMap map, Ant ant, Camera camera, SpriteBatch batch) {
        this.worldMap = map;
        this.ant = ant;
        this.camera = camera;
        this.batch = batch;
        tileSize = this.camera.getWidth() / 9;
        loadAssets();
    }

    public void loadAssets() {
        atlas = new TextureAtlas("assets.txt");
        dirtSprite = new Sprite(atlas.findRegion("dirt"));
        dirtSprite.setSize(tileSize, tileSize);
    }

    public void drawAll()
    {
        drawMap();
    }

    private void drawMap() {
        int start_x = camera.getX() / tileSize;
        int start_y = camera.getY() / tileSize;
        int end_x = start_x + 10;
        int end_y = start_y + 13;

        for (int i = 0; i < end_x - start_x; i++) {
            for (int j = 0; j < end_y - start_y; j++) {
                WorldMap.Tile tile = worldMap.getTile(i + start_x, j + start_y);
                dirtSprite.setPosition(i * tileSize - (camera.getX() % tileSize),
                        j * tileSize - (camera.getY() % tileSize));
                dirtSprite.draw(batch);
            }
        }
    }

    public void dispose() {
        atlas.dispose();
    }
}

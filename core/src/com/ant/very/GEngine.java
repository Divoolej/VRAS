package com.ant.very;

import com.ant.very.objects.Ant;
import com.ant.very.objects.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Divoolej on 2014-10-27.
 */
public class GEngine {
    private Camera camera;
    private TextureAtlas atlas;
    private int tileSize;
    private WorldMap map;

    SpriteBatch batch;

    private Ant ant;
    private Sprite dirtSprite;

    public GEngine(Ant ant, Camera camera, SpriteBatch batch) {
        map = WorldMap.getInstance();
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
                Sprite sprite = new Sprite();

                switch (map.getTileType(i, j)) {
                    case WorldMap.DIRT_TILE:
                        sprite = dirtSprite;
//                    case WorldMap.STONE_TILE;
//                        sprite = stoneSprite;
//                    case WorldMap.TRAP_TILE:
//                        sprite = trapSprite;
                      default:
                          Gdx.app.log(this.getClass().getName(), "Error while drawing map tiles");
                }

                sprite.setPosition(i * tileSize - (camera.getX() % tileSize),
                        j * tileSize - (camera.getY() % tileSize));
                sprite.draw(batch);
            }
        }
    }

    public void dispose() {
        atlas.dispose();
    }
}

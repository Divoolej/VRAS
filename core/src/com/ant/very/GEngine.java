package com.ant.very;

import com.ant.very.objects.Ant;
import com.ant.very.objects.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class GEngine {
    private Camera camera;
    private TextureAtlas atlas;
    private int tileSize;
    private WorldMap map;

    SpriteBatch batch;

    // Sprites:
//    private Ant ant;
    private Sprite sandSprite;
    private Sprite metalSprite;
    private Sprite boxSprite;
    private Sprite stoneSprite;
    private Sprite lavaSprite;
    private Sprite grassSprite;
    private Sprite cakeSprite;
    private Sprite bombSprite;

    public GEngine(Ant ant, Camera camera, SpriteBatch batch) {
        map = WorldMap.getInstance();
//        this.ant = ant;
        this.camera = camera;
        this.batch = batch;
        tileSize = this.camera.getWidth() / 9;

        atlas = new TextureAtlas("VrasPack.pack");
        loadSprites();
        setSpriteSizes();
    }

    // Those two methods should maybe be iterating an array, doesn't really matter though:
    public void loadSprites() {
        sandSprite = new Sprite(atlas.findRegion("sand"));
        metalSprite = new Sprite(atlas.findRegion("metal"));
        boxSprite = new Sprite(atlas.findRegion("box"));
        lavaSprite = new Sprite(atlas.findRegion("lava"));
        grassSprite = new Sprite(atlas.findRegion("grass"));
        cakeSprite = new Sprite(atlas.findRegion("cake"));
        stoneSprite = new Sprite(atlas.findRegion("stone"));
        bombSprite = new Sprite(atlas.findRegion("bomb"));
    }

    public void setSpriteSizes() {
        sandSprite.setSize(tileSize, tileSize);
        metalSprite.setSize(tileSize, tileSize);
        boxSprite.setSize(tileSize, tileSize);
        lavaSprite.setSize(tileSize, tileSize);
        cakeSprite.setSize(tileSize, tileSize);
        stoneSprite.setSize(tileSize, tileSize);
        grassSprite.setSize(tileSize, tileSize);
        bombSprite.setSize(tileSize, tileSize);
    }

    public void drawAll()
    {
        // The UI is rendered in VRAS.
        drawMap();
    }

    private void drawMap() {
        int start_x = camera.getX() / tileSize;
        int start_y = camera.getY() / tileSize;
        int end_x = start_x + 10;
        int end_y = start_y + 14;

        for (int i = 0; i < end_x - start_x; i++) {
            for (int j = 0; j < end_y - start_y; j++) {
                Sprite sprite = new Sprite();

                switch (map.getTileType(i, j)) {
                    case DIRT_TILE:
                        sprite = sandSprite;
                        break;
                    case LAVA_TILE:
                        sprite = lavaSprite;
                        break;
//                    case BOX_TILE:
//                        sprite = boxSprite;
//                        break;
                    case METAL_TILE:
                        sprite = metalSprite;
                        break;
                    case STONE_TILE:
                        sprite = stoneSprite;
                        break;
//                    case GRASS_TILE:
//                        sprite = grassSprite;
//                        break;
                    case CAKE_TILE:
                        sprite = cakeSprite;
                        break;
//                    case BOMB_TILE:
//                        sprite = bombSprite;
//                        break;
                    default:
                        Gdx.app.error(this.getClass().getName(),
                                  "Error while drawing map tile: <" + i + ">, <" + j + ">.");
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

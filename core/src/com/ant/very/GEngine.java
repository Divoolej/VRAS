package com.ant.very;

import com.ant.very.objects.Ant;
import com.ant.very.objects.Camera;
import com.ant.very.utils.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Vector;

public class GEngine {
    private Camera camera;
    private TextureAtlas atlas;
    private int tileSize;
    private WorldMap map;

    private SpriteBatch batch;

    // Sprites:
    private Vector<Sprite> sprites;

    public GEngine(Camera camera, SpriteBatch batch) {
        map = WorldMap.getInstance();
        this.camera = camera;
        this.batch = batch;
        tileSize = this.camera.getWidth() / 9;

        atlas = new TextureAtlas("VrasPack.pack");
        loadSprites();
        setSpriteSizes();
    }

    public void loadSprites() {
        sprites = new Vector<Sprite>();
        for (int i = 0; i < Constants.Sprites.count(); i++) {
            sprites.add(new Sprite(atlas.findRegion(
                    Constants.Sprites.values()[i].toString().toLowerCase()) ) );
                        // Taking the region name from Constants makes the code a lot
                        // more independent. Using the Vector helps to get rid of the switch later on.
        }
    }

    public void setSpriteSizes() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).setSize(tileSize, tileSize);
        }
    }

    public void drawAll()
    {
        // The UI is rendered in VRAS.
        drawMap();
    }

    private void drawMap() {
        int startX = camera.getX() / tileSize;
        int startY = camera.getY() / tileSize;
        int endX = startX + Constants.TILES_HORIZONTALLY + 1;
        endX = ((endX > Constants.MAP_WIDTH) ? (endX - 1) : (endX));
        int endY = ( (startY + Constants.TILES_VERTICALLY + 1 > Constants.MAP_HEIGHT) ?
                (Constants.MAP_HEIGHT) : (startY + Constants.TILES_VERTICALLY + 1) );

        for (int i = 0; i < endX - startX; i++)
        {
            for (int j = 0; j < endY - startY; j++)
            {
                int spriteId = map.at(i + startX, j + startY).getSpriteId();

                //Draw the background tile first
                sprites.get(Constants.Sprites.BACKGROUND.toInt()).setPosition(i * tileSize -
                        (camera.getX() % tileSize), j * tileSize - (camera.getY() % tileSize));

                sprites.get(Constants.Sprites.BACKGROUND.toInt()).draw(batch);

                //Then draw the actual object
                sprites.get(spriteId).setPosition(i * tileSize - (camera.getX() % tileSize),
                        j * tileSize - (camera.getY() % tileSize));

                sprites.get(spriteId).draw(batch);
            }
        }

        int aX = Ant.getInstance().getX();
        int aY = Ant.getInstance().getY();

        if (aX >= startX && aX <= endX - 1) {
            if (aY >= startY && aY < endY - 1) {
                sprites.get(Constants.Sprites.ANT.toInt()).setPosition((aX - startX) * tileSize -
                        (camera.getX() % tileSize), (aY - startY) * tileSize - (camera.getY() % tileSize));
                sprites.get(Constants.Sprites.ANT.toInt()).draw(batch);
            }
        }
    }

    public void dispose() {
        atlas.dispose();
    }
}

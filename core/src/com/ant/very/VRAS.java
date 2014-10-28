package com.ant.very;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VRAS extends ApplicationAdapter implements InputProcessor {
    SpriteBatch batch;
        BitmapFont font; //debug
    Camera camera;
    GEngine gEngine;
    Ant ant;
    WorldMap map;

    @Override
    public void create () {
        batch = new SpriteBatch();
          font = new BitmapFont(); //debug
          font.setColor(Color.RED); //debug
        Gdx.input.setInputProcessor(this);

        map = new WorldMap(30, 30);
        camera = new Camera(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                (int)((Gdx.graphics.getWidth() / 9.0) * map.getW() - Gdx.graphics.getWidth()),
                (int)((Gdx.graphics.getWidth() / 9.0) * map.getH() - ((Gdx.graphics.getWidth() / 9.0) * 12))); //We want our our "ground" to take 12x9 tiles, the rest of the screen is for communication
        ant = new Ant();
        gEngine = new GEngine(map, ant, camera, batch);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
            gEngine.drawAll();
            font.draw(batch, "x = " + camera.getX() + ", y = " + camera.getY(), 30, 30); //debug
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        gEngine.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        camera.setDraggedX(screenX);
        camera.setDraggedY(screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        camera.equalizeOrigin();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        camera.moveTo(camera.getOriginX() + camera.getDraggedX() - screenX, camera.getOriginY() + screenY - camera.getDraggedY());
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

package com.ant.very;

import com.ant.very.objects.Ant;
import com.ant.very.objects.Camera;
import com.ant.very.objects.Ui;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class VRAS extends ApplicationAdapter implements InputProcessor {
    private static final String TAG = "VRAS";
    public static final int FONT_SIZE = 16;
    public int GFX_WIDTH;
    public int GFX_HEIGHT;

    private SpriteBatch batch;
    private Camera camera;
    private GEngine gEngine;
    private Ant ant;
    private WorldMap map;

    // UI stuff:
    private Ui ui;
    private BitmapFont font;

    // For android methods:
    private ActionResolver actionResolver;

    public VRAS(ActionResolver ar) {
        actionResolver = ar;
    }

    @Override
    public void create () {
        GFX_WIDTH = Gdx.graphics.getWidth();
        GFX_HEIGHT = Gdx.graphics.getHeight();

        ui = new Ui(actionResolver);

        // Pass the ui like this:
        // VRAS -> ActionResolver -> MyListener
        // And update the UI from there on various events (i.e. SpeechRecognizer callbacks).
        actionResolver.setUi(ui);

        // Create a bitmap font from a ttf file using the freetype module.
        createFont();
        font.setColor(Color.RED);

        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(new InputMultiplexer(ui.getStage(), this));

        map = WorldMap.getInstance();
        camera = new Camera(0, 0, GFX_WIDTH, GFX_HEIGHT,
                (int)((GFX_WIDTH / 9.0f) * map.getWidth() - GFX_WIDTH),
                (int)((GFX_WIDTH / 9.0f) * map.getHeight() - ((GFX_WIDTH / 9.0f) * 12)));
                 // We want our our "ground" to take 12x9 tiles, the
                 // rest of the screen is for communication

        ant = new Ant();
        gEngine = new GEngine(ant, camera, batch);
    }

    private void createFont() {
        FreeTypeFontGenerator generator;
        FreeTypeFontParameter parameter;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        parameter = new FreeTypeFontParameter();
        parameter.size = FONT_SIZE;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        gEngine.drawAll();
        font.draw(batch, "x = " + camera.getX() + ", y = " + camera.getY(), 30, 30); //debug
        batch.end();

        ui.renderUI();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        gEngine.dispose();
        ui.disposeUi();
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
        camera.moveTo(camera.getOriginX() + camera.getDraggedX()
                - screenX, camera.getOriginY() + screenY - camera.getDraggedY());
        return true;
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

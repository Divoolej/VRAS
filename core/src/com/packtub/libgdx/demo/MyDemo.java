package com.packtub.libgdx.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import javafx.scene.Camera;

public class MyDemo extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texture;
    OrthographicCamera camera;
    Sprite sprite;

    private float rot;

	@Override
	public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();
		texture = new Texture("badlogic.jpg");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegion region =
                new TextureRegion(texture, 0, 0,
                        texture.getWidth(), texture.getHeight());

        sprite = new Sprite(region);
        sprite.setSize(0.4f,
                0.4f * sprite.getHeight() / sprite.getWidth());
        sprite.setOrigin(sprite.getWidth() / 2,
                sprite.getHeight() / 2);
        sprite.setPosition(-sprite.getWidth() / 2,
                -sprite.getHeight() / 2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        final float degreesPerSecond = 10.0f;
        rot = (rot + Gdx.graphics.getDeltaTime() * degreesPerSecond) % 360;
        final float shakeAplitudeInDegrees = 5.0f;
        float shake = MathUtils.sin(rot) * shakeAplitudeInDegrees;
        sprite.setRotation(shake);
        sprite.draw(batch);
		batch.end();
	}

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}

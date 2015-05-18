package com.ttc.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends com.badlogic.gdx.Game {

	public Viewport viewport;
	public Camera camera;
    private Texture loadingTexture;

	public SpriteBatch batch;
	public AssetManager assetManager;
	public Rectangle screenBound;

    private boolean firstScreenSetUp = false;

    @Override
	public void create () {

		batch = new SpriteBatch();
		assetManager = new AssetManager();
		screenBound = new Rectangle(0, 0, 800, 480);

        loadingTexture = new Texture("LoadScreen.png");

		// Set up the viewport.
		camera = new OrthographicCamera(800, 480);
		viewport = new FitViewport(800, 480, camera);

		// Set up the asset manager.
		assetManager.load("badlogic.jpg", Texture.class);
		assetManager.load("player.png", Texture.class);
		assetManager.load("HPBar.png", Texture.class);
		assetManager.load("HPContent.png", Texture.class);
		assetManager.load("testLevelBackground.png", Texture.class);
		assetManager.load("catfish.png", Texture.class);
		assetManager.load("GameOver.png", Texture.class);
        assetManager.load("Underwater.png", Texture.class);
        assetManager.load("TitleScreen.png", Texture.class);
        assetManager.load("Victory.png", Texture.class);
	}

	/**
	 * Readjust the viewport.
	 */
    @Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void render () {
        super.render();

		if (!assetManager.update()) {
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.begin();
			batch.draw(loadingTexture, 0, 0);
			batch.end();
		} else {
            if (!firstScreenSetUp) {
                firstScreenSetUp = true;
                setScreen(new Title(this));
            }
		}
	}
}

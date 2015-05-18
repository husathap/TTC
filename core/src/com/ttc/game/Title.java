package com.ttc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Hubert on 2015-05-17.
 */
public class Title implements Screen {

    public final Game game;

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw((Texture) game.assetManager.get("TitleScreen.png"), 0, 0);
        game.batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new TestLevel(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Title(Game game) {
        this.game = game;
    }

}

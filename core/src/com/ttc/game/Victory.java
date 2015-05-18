package com.ttc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.ttc.game.Game;

/**
 * Created by Hubert on 2015-05-17.
 */
public class Victory implements Screen {

    final Game game;

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw((Texture) game.assetManager.get("Victory.png"), 0, 0);
        game.batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new Title(game));
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

    public Victory(Game game) {
        this.game = game;
    }
}

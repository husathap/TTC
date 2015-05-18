package com.ttc.game;

import com.badlogic.gdx.Screen;

import java.util.List;

/**
 * A class represents the dialogue in the game. It can be used standalone as its own screen or where
 * it can be used for overlaying.
 *
 * Created by Hubert on 2015-05-05.
 */
public class Dialogue implements Screen {

    public List<String[]> commands; // The commands for the dialogue.

    /**
     * Display a new text.
     * @param txt The text to be displayed.
     */
    public void text(String txt) {
        commands.add(new String[] {"text", txt});
    }

    /**
     * Display a new background.
     * @param textureAssetManagerKey The name of the texture in the asset manager. Use "" for remove background.
     */
    public void background(String textureAssetManagerKey) {
        commands.add(new String[] {"background", textureAssetManagerKey});
    }

    /**
     * Display a new character portrait.
     * @param textureAssetManagerKey The name of the texture in the asset manager. Use "" to remove portrait.
     */
    public void changePortrait(String textureAssetManagerKey) {
        commands.add(new String[] {"changePortrait", textureAssetManagerKey});
    }

    /**
     * Change music.
     * @param musicAssetManagerKey The name of the music in the asset manager. Use "" to stop the music.
     */
    public void changeMusic(String musicAssetManagerKey) {
        commands.add(new String[] {"changePortrait", musicAssetManagerKey});
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
}

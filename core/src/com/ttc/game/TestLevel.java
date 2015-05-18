package com.ttc.game;

import com.ttc.game.Game;
import com.ttc.game.Level;
import com.ttc.game.enemies.Catfish;

/**
 * Created by Hubert on 2015-05-15.
 */
public class TestLevel extends Level {

    int waveCounter = 0;

    @Override
    public boolean generateEnemyWave() {
        switch (waveCounter) {
            case 0:
                enemies.add(new Catfish(800, 100, this));
                waveCounter++;
                break;
            case 1:
                enemies.add(new Catfish(800, 100, this));
                enemies.add(new Catfish(850, 200, this));
                waveCounter++;
                break;
            case 2:
                return true;
        }

        return false;
    }

    @Override
    public void nextScreen() {
        this.game.setScreen(new Victory(this.game));
    }

    public TestLevel(Game game) {
        super(game);

        backgroundTexture = game.assetManager.get("Underwater.png");
    }
}

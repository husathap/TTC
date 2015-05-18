package com.ttc.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ttc.game.Enemy;
import com.ttc.game.Level;

/**
 * Created by Hubert on 2015-05-13.
 */
public class Catfish extends Enemy {

    private boolean screenCrossed;

    @Override
    public boolean isDead() {
        return screenCrossed && !level.game.screenBound.overlaps(this.getBoundingRectangle());
    }

    @Override
    public void updateAI(float delta) {
        if (!screenCrossed)
            screenCrossed = level.game.screenBound.overlaps(this.getBoundingRectangle());

        this.setX(this.getX() - 30 * delta);
    }

    public Catfish(int x, int y, Level level) {
        super(0.09f, TextureRegion.split((Texture) level.game.assetManager.get("catfish.png"), 100, 62)[0], level);

        this.setX(x);
        this.setY(y);
        this.setHP(5);
        this.setMaxHP(5);
        this.setAttack(10);

        screenCrossed = level.game.screenBound.contains(this.getBoundingRectangle());
    }
}

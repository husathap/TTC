package com.ttc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * Created by Hubert on 2015-05-09.
 */
public class Player extends AnimatedSprite {

    private float HP;
    private float maxHP;
    private Level level;

    public float getHP() {
        return HP;
    }

    public void setHP(float newHP) {
        HP = newHP;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public float getHPRatio() {
        return HP / maxHP;
    }

    public Player(Level level) {
        super(0.1f, TextureRegion.split((Texture)level.game.assetManager.get("player.png"), 50, 50)[0],
                Animation.PlayMode.LOOP);
        setPosition(375, 215);

        maxHP = 100;
        HP = 100;
    }
}

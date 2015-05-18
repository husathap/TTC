package com.ttc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import org.w3c.dom.css.Rect;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hubert on 2015-05-12.
 */
public abstract class Enemy extends AnimatedSprite {


    private float HP;
    private float maxHP;
    private float attack;
    protected Level level;

    // Health properties.
    public float getHP() {
        return HP;
    }

    public void setHP(float newHP) {
        HP = newHP;
    }

    public float getHPRatio() {
        return HP / maxHP;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(float newMaxHP) {
        maxHP = newMaxHP;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float newAttack) {
        attack = newAttack;
    }


    // Collision management.
    public boolean hasCollided(Player p) {
        return getBoundingRectangle().overlaps(p.getBoundingRectangle());
    }

    // Input management.
    public boolean isTapped(Camera cameraToUnproject) {
        Vector3 unprojVec = cameraToUnproject.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return Gdx.input.justTouched() &&
                getBoundingRectangle().contains(unprojVec.x, unprojVec.y);
    }

    /**
     * Indicate that the enemy should be removed apart from the fact that it has insufficient HP.
     */
    public abstract boolean isDead();

    /**
     * Update the AI of the enemy.
     */
    public abstract void updateAI(float delta);

    /**
     * Create a new AnimatedSprite.
     *
     * @param frameDuration  The speed of animation.
     * @param textureRegions The TextureRegions that serve as animation frames. To ensure stability, all TextureRegions
     *                       must have the same Texture.
     */
    public Enemy(float frameDuration, TextureRegion[] textureRegions, Level level) {
        super(frameDuration, textureRegions, Animation.PlayMode.LOOP);
        this.level = level;
    }


    public void render(float deltaTime, SpriteBatch batch) {
        super.render(deltaTime, batch);
    }
}

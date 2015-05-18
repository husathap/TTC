package com.ttc.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * A mixin between Animation and Sprite to provide an animation that can be manipulated like a sprite.
 *
 * Created by Hubert on 2015-05-06.
 */
public class AnimatedSprite {

    private Animation animation;
    private Sprite sprite;
    private float stateTime;

    /** Create a new AnimatedSprite.
     *
     * @param frameDuration The speed of animation.
     * @param textureRegions The TextureRegions that serve as animation frames. To ensure stability, all TextureRegions
     *                       must have the same Texture.
     * @param playMode How the animation will occur.
     */
    public AnimatedSprite(float frameDuration, TextureRegion[] textureRegions, Animation.PlayMode playMode) {
        sprite = new Sprite(textureRegions[0]);
        animation = new Animation(frameDuration, textureRegions);
        animation.setPlayMode(playMode);
        stateTime = 0;
    }

    /** Animate the sprite and render it. Use this method if you have multiple frames.
    *
    * @param delta The time passed since the last frame update.
    * @param batch The SpriteBatch used for rendering. */
    public void render(float delta, SpriteBatch batch) {
        stateTime += delta;
        sprite.setRegion(animation.getKeyFrame(stateTime));
        sprite.draw(batch);

        // Reset the stateTime if the animation is done.
        if (isAnimationFinished(stateTime)) {
            stateTime = 0;
        }
    }

    /** Render the sprite without animating it. Use this method if you only have a single frame or you want to freeze
    * a sprite.
    *
    * @param batch the SpriteBatch used for drawing.*/
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    // Currying functions for sprite. //

    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public float getOriginX() {
        return sprite.getOriginX();
    }

    public float getOriginY() {
        return sprite.getOriginY();
    }

    public float getScaleX() {
        return sprite.getScaleX();
    }

    public float getScaleY() {
        return sprite.getScaleY();
    }

    public Color getColor() {
        return sprite.getColor();
    }

    public float getRotation() {
        return sprite.getRotation();
    }

    public void setBounds(float x, float y, float width, float height) {
        sprite.setBounds(x, y, width, height);
    }

    public void setSize(float width, float height) {
        sprite.setSize(width, height);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void setX(float x) {
        sprite.setX(x);
    }

    public void setY(float y) {
        sprite.setY(y);
    }

    public void setCenterX(float x) {
        sprite.setCenterX(x);
    }

    public void setCenterY(float y) {
        sprite.setCenterY(y);
    }

    public void setCenter(float x, float y) {
        sprite.setCenter(x, y);
    }

    public void translateX(float xAmount) {
        sprite.translateX(xAmount);
    }

    public void translateY(float yAmount) {
        sprite.translateY(yAmount);
    }

    public void translate(float xAmount, float yAmount) {
        sprite.translate(xAmount, yAmount);
    }

    public void setColor(Color tint) {
        sprite.setColor(tint);
    }

    public void setAlpha(float a) {
        sprite.setAlpha(a);
    }

    public void setColor(float r, float g, float b, float a) {
        sprite.setColor(r, g, b, a);
    }

    public void setColor(float color) {
        sprite.setColor(color);
    }

    public void setOrigin(float originX, float originY) {
        sprite.setOrigin(originX, originY);
    }

    public void setOriginCenter() {
        sprite.setOriginCenter();
    }

    public void setRotation(float degrees) {
        sprite.setRotation(degrees);
    }

    public void rotate(float degrees) {
        sprite.rotate(degrees);
    }

    public void rotate90(boolean clockwise) {
        sprite.rotate90(clockwise);
    }

    public void setScale(float scaleXY) {
        sprite.setScale(scaleXY);
    }

    public void scale(float amount) {
        sprite.scale(amount);
    }

    // Currying the functions for animation //

    public float getAnimationDuration() {
        return animation.getAnimationDuration();
    }

    public float getFrameDuration() {
        return animation.getFrameDuration();
    }

    public Animation.PlayMode getPlayMode() {
        return animation.getPlayMode();
    }

    public boolean isAnimationFinished(float stateTime) {
        return animation.isAnimationFinished(stateTime);
    }

    public void setFrameDuration(float frameDuration) {
        animation.setFrameDuration(frameDuration);
    }

    public void setPlayMode(Animation.PlayMode playMode) {
        animation.setPlayMode(playMode);
    }
}

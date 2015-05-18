package com.ttc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.ttc.game.enemies.Catfish;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Hubert on 2015-05-06.
 */
public abstract class Level implements Screen {

    public final Game game;

    public Player player;
    public float invincibilityTime = 0;

    private Texture HPBarTexture;
    private AnimatedSprite HPBarContentAnimatedSprite;
    protected Texture backgroundTexture;

    public List<Enemy> enemies = new LinkedList<Enemy>();
    public List<Enemy> disappearingEnemies = new LinkedList<Enemy>();

    public void show() {

    }

    @Override
    public void render(float delta) {

        // If there's no more enemy add, then goto other screen.
        if (enemies.isEmpty() && disappearingEnemies.isEmpty()) {
            if (generateEnemyWave()) {
                nextScreen();
            }
        }

        // This is for later stuffs.
        boolean enemyTapped = false;

        // Update the enemies.
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.updateAI(delta);

            // Check if the enemy is dead.
            if (e.isDead()) {
                enemies.remove(i);
                i--;
                continue;
            }

            // Check if the enemy is tapped.
            if (e.isTapped(game.camera)) {
                enemyTapped = true;
                e.setHP(e.getHP() - 1);

                // Red-tinting to indicate HP-level.
                e.setColor(e.getColor().r,
                           e.getColor().g  * e.getHPRatio(),
                           e.getColor().b  * e.getHPRatio(),
                           e.getColor().a);

                if (e.getHP() <= 0) {
                    disappearingEnemies.add(e);
                    enemies.remove(i);
                    i--;

                    continue;
                }
            }

            // Check if the enemy has collided with the player.
            if (e.hasCollided(player) && invincibilityTime <= 0) {
                player.setHP(player.getHP() - e.getAttack());

                if (player.getHP() <= 0) {
                    // GAME OVER DUDE!
                    this.game.setScreen(new GameOver(this.game));
                } else {
                    // Invicible period.
                    invincibilityTime = 1;
                }
            }
        }

        // Update the disappearing enemies.
        for (int i = 0; i < disappearingEnemies.size(); i++) {
            Enemy d = disappearingEnemies.get(i);

            if (d.isTapped(game.camera))
                enemyTapped = true;

            if (d.getColor().a > 0) {
                float temp = d.getColor().a - 4 * delta;

                if (temp < 0)
                    temp = 0;

                d.setAlpha(temp);
            } else {
                disappearingEnemies.remove(i);
                i--;
            }
        }

        // Teleportation
        if (Gdx.input.justTouched() && !enemyTapped) {
            Vector3 newPos = game.camera.unproject(new Vector3(Gdx.input.getX() - player.getWidth() / 2, Gdx.input.getY() + player.getHeight() / 2, 0));
            player.setPosition(newPos.x , newPos.y);
        }

        // Update invincibility time.
        if (invincibilityTime > 0) {
            invincibilityTime -= delta;
        }

        // Rendering the scene.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(backgroundTexture, 0, 0);

        for (Enemy d : disappearingEnemies) {
            d.render(game.batch);
        }

        for (Enemy e : enemies) {
            e.render(delta, game.batch);
        }


        if (invincibilityTime > 0)
            player.render(game.batch);
        else
            player.render(delta, game.batch);

        game.batch.draw(HPBarTexture, 0, 0);
        HPBarContentAnimatedSprite.render(delta, game.batch);

        game.batch.end();

        HPBarContentAnimatedSprite.setSize(player.getHPRatio() * 330, 50);
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

    public abstract boolean generateEnemyWave();
    public abstract void nextScreen();

    public Level(Game game) {
        this.game = game;
        player = new Player(this);
        HPBarTexture = game.assetManager.get("HPBar.png");

        Texture HPBarContentTexture = game.assetManager.get("HPContent.png");
        TextureRegion[] HPBarContentTextureRegions = new TextureRegion[] {
                new TextureRegion(HPBarContentTexture, 0, 0, 330, 50),
                new TextureRegion(HPBarContentTexture, 0, 50, 330, 50),
                new TextureRegion(HPBarContentTexture, 0, 100, 330, 50),
                new TextureRegion(HPBarContentTexture, 0, 150, 330, 50),
        };

        HPBarContentAnimatedSprite = new AnimatedSprite(0.1f, HPBarContentTextureRegions, Animation.PlayMode.LOOP);
        HPBarContentAnimatedSprite.setPosition(70, 0);

        backgroundTexture = game.assetManager.get("testLevelBackground.png");
    }
}

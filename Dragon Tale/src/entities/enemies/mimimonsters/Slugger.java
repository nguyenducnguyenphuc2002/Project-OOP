package entities.enemies.mimimonsters;

import entities.enemies.Enemy;
import entities.player.Player;
import objects.Animation;
import storage.LoadEntities;
import tilemap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Slugger extends Enemy {
    public static int SLUGGER = 3;
    private BufferedImage[] sprites;

    public Slugger(TileMap tm) {
        super(tm);

        moveSpeed = 0.4;
        maxSpeed = 0.4;
        fallSpeed = 0.5;
        maxFallSpeed = 15.0;

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        health = maxHealth = 2;
        damage = 2;

        sprites = LoadEntities.loadLine(LoadEntities.SLUGGER, width,height);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300L);
        right = true;
        facingRight = true;
    }

    private void getNextPosition() {
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        }

        if (falling) {
            dy += fallSpeed;
        }

    }

    public void update() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000L;
            if (elapsed > 400L) {
                flinching = false;
            }
        }

        if (right && dx == 0.0) {
            right = false;
            left = true;
            facingRight = false;
        } else if (left && dx == 0.0) {
            right = true;
            left = false;
            facingRight = true;
        }

        animation.update();
    }

    public void draw(Graphics2D g) {
        setMapPosition();
        setMapPosition(tileMap.getx(), tileMap.gety());
        super.draw(g);
    }

    @Override
    public int getIndex(){
        return 3;
    }
}

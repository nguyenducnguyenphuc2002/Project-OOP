package entities.enemies.minimonsters;

import entities.enemies.Enemy;
import objects.Animation;
import storage.LoadEntities;
import tilemap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends Enemy {
    private BufferedImage[] sprites;


    public Bird(TileMap tm) {
        super(tm);
        moveSpeed = 0.3;
        maxSpeed = 0.3;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;
        width = 28;
        height = 64;
        cwidth = 10;
        cheight = 10;
        health = maxHealth = 3;
        damage = 4;


        sprites = LoadEntities.loadLine(LoadEntities.BIRD, width,height); // load bird
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300L);
        right = true;
        facingRight = true;
    }

    private void getNextPosition() {

        if(left) dx = -moveSpeed;
        else if(right) dx = moveSpeed;
        else dx = 0;

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
        return 1;
    }
}

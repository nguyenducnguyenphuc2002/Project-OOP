package entities.enemies.minimonsters;
import entities.enemies.Enemy;
import objects.Animation;
import ui.LoadEntities;
import tilemap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hero extends Enemy {
    private BufferedImage[] sprites;
    private int jumpHeight;
    private int maxJumpHeight;

    public Hero(TileMap tm) {
        super(tm);

        moveSpeed = 0.6;
        maxSpeed = 1.2;
        fallSpeed = 0.6;
        maxFallSpeed = 4.0;
        jumpStart = -16;
        stopJumpSpeed = 0.5;
        maxJumpHeight = (int)y + 200;

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        health = maxHealth = 2;
        damage = 1;


        sprites = LoadEntities.loadLine(LoadEntities.HERO, this.width, this.height);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(100);

        up = true;
    }

    private void getNextPosition() {
        // jumping
        if (up) {
            if (!jumping && !falling) {
                dy = jumpStart;
                jumping = true;
                jumpHeight = (int) y;
            }

            if (jumping && dy < 0) {
                dy += stopJumpSpeed; // reduce jump speed when the key is released
            }

            if (jumping && dy >= 0) {
                jumping = false;
                falling = true;
            }
        }

        // falling
        if (falling) {
            dy += fallSpeed;

            if (dy > maxFallSpeed) {
                dy = maxFallSpeed;
            }
        }
    }


    public void update() {
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //shroomie movement
        if(dy == 0) {
            up = !up;
            down = !down;
        }

        //System.out.println(jumpHeight);

        //check flinching
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 400) {
                flinching = false;
            }
        }

        //update animation
        animation.update();
    }

    public void draw(Graphics2D g) {

        setMapPosition();
        setMapPosition(tileMap.getx(), tileMap.gety());

        super.draw(g);
    }

    @Override
    public int getIndex(){
        return 4;
    }
}



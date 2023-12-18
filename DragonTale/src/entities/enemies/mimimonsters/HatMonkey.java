package entities.enemies.mimimonsters;

import entities.enemies.Enemy;
import objects.Animation;
import storage.LoadEntities;
import tilemap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HatMonkey extends Enemy{
    private BufferedImage[] sprites;

    public HatMonkey(TileMap tm) {
        super(tm);

        moveSpeed = 0.7;
        maxSpeed = 0.3;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        health = maxHealth = 2;
        damage = 2;


        this.sprites = LoadEntities.loadLine(LoadEntities.HATMONKEY, this.width, this.height);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(100L);
        this.right = true;
        this.facingRight = true;
    }

    private void  getNextPosition() {
        //movement
        if (this.left) {
            this.dx -= this.moveSpeed;
            if (this.dx < -this.maxSpeed) {
                this.dx = -this.maxSpeed;
            }
        } else if (this.right) {
            this.dx += this.moveSpeed;
            if (this.dx > this.maxSpeed) {
                this.dx = this.maxSpeed;
            }
        }

        if (this.falling) {
            this.dy += this.fallSpeed;
        }
    }

    public void update() {

        //update position
        getNextPosition();
        checkTileMapCollision();
        //slugger turns around if it hits a wall or is about to fall off a cliff
        setPosition(xtemp, ytemp);
        if (this.flinching) {
            long elapsed = (System.nanoTime() - this.flinchTimer) / 1000000L;
            if (elapsed > 400L) {
                this.flinching = false;
            }
        }

        if(dx == 0) {
            left = !left;
            right = !right;
            facingRight = !facingRight;
        }

        //check flinching
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000L;
            if (elapsed > 400) {
                flinching = false;
            }
        }

        //update animation
        animation.update();
    }

    public void draw(Graphics2D g) {
        //if (notOnScreen()) return;

        setMapPosition();
        setMapPosition(tileMap.getx(), tileMap.gety());

        super.draw(g);
    }

    public int getIndex(){
        return 2;
    }
}

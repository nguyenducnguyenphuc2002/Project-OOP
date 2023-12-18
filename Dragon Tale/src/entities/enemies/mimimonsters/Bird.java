package entities.enemies.mimimonsters;

import entities.enemies.Enemy;
import entities.player.Player;
import objects.Animation;
import storage.LoadEntities;
import tilemap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends Enemy {
    private BufferedImage[] sprites;


    public Bird(TileMap tm) {
        super(tm);
        this.moveSpeed = 0.3;
        this.maxSpeed = 0.3;
        this.fallSpeed = 0.2;
        this.maxFallSpeed = 10.0;
        this.width = 28;
        this.height = 64;
        this.cwidth = 10;
        this.cheight = 10;
        this.health = this.maxHealth = 3;
        this.damage = 4;


        this.sprites = LoadEntities.loadLine(LoadEntities.BIRD, this.width,this.height); // load bird
        this.animation = new Animation();
        this.animation.setFrames(this.sprites);
        this.animation.setDelay(300L);
        this.right = true;
        this.facingRight = true;
    }

    private void getNextPosition() {
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

    }

    public void update() {
        this.getNextPosition();
        this.checkTileMapCollision();
        this.setPosition(this.xtemp, this.ytemp);
        if (this.flinching) {
            long elapsed = (System.nanoTime() - this.flinchTimer) / 1000000L;
            if (elapsed > 400L) {
                this.flinching = false;
            }
        }

        if (this.right && this.dx == 0.0) {
            this.right = false;
            this.left = true;
            this.facingRight = false;
        } else if (this.left && this.dx == 0.0) {
            this.right = true;
            this.left = false;
            this.facingRight = true;
        }

        this.animation.update();
    }

    public void draw(Graphics2D g) {
        this.setMapPosition(this.tileMap.getx(), this.tileMap.gety());
        super.draw(g);
    }


    @Override
    public int getIndex(){
        return 1;
    }
}

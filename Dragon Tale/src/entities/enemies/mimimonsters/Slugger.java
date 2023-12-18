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

        this.moveSpeed = 0.4;
        this.maxSpeed = 0.4;
        this.fallSpeed = 0.5;
        this.maxFallSpeed = 15.0;

        this.width = 30;
        this.height = 30;
        this.cwidth = 20;
        this.cheight = 20;

        this.health = this.maxHealth = 2;
        this.damage = 2;

        this.sprites = LoadEntities.loadLine(LoadEntities.SLUGGER, this.width,this.height);
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

        if (this.falling) {
            this.dy += this.fallSpeed;
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
        this.setMapPosition();
        this.setMapPosition(this.tileMap.getx(), this.tileMap.gety());
        super.draw(g);
    }

    @Override
    public int getIndex(){
        return 3;
    }
}

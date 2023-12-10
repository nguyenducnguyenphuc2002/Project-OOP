package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Boss extends Enemy{
    private BufferedImage[] sprites;
    private int direction = 0;

    public Boss(TileMap tm) {
        super(tm);
        this.moveSpeed = 0.8;
        this.maxSpeed = 0.8;
        this.fallSpeed = 0.2;
        this.maxFallSpeed = 0.2;
        this.width = 40;
        this.height = 40;
        this.cwidth = 30;
        this.cheight = 30;
        this.health = this.maxHealth = 100;
        this.damage = 5;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("C:/Users/DELL/Downloads/Bat.jpg"));
            this.sprites = new BufferedImage[1];

            for(int i = 0; i < this.sprites.length; ++i) {
                this.sprites[i] = spritesheet.getSubimage(i * this.width, 0, this.width, this.height);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

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

        if (this.right  && this.dx == 0.0) {
            this.right = false;
            this.left = true;
            this.facingRight = false;
        } else if (this.left  && this.dx == 0.0) {
            this.right = true;
            this.left = false;
            this.facingRight = true;
        }
        if (dy == 0) {
            dy -= 5;
        }


        this.animation.update();
    }

    public void draw(Graphics2D g) {
        this.setMapPosition(this.tileMap.getx(), this.tileMap.gety());
        super.draw(g);
    }
    public boolean getDirection (){
        return new Random().nextInt(0,10) > 5;
    }
}


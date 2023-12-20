package entities.enemies.boss;

import objects.Animation;
import objects.MapObject;
import storage.LoadEntities;
import tilemap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Venom extends MapObject {

    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;
    public Venom(TileMap tm, boolean right) {
        super(tm);
<<<<<<< HEAD
        this.moveSpeed = 3.8;
        if (right) {
            this.dx = this.moveSpeed;
        } else {
            this.dx = -this.moveSpeed;
        }

        this.width = 45;
        this.height = 45;
        this.cwidth = 14;
        this.cheight = 14;


        this.sprites = LoadEntities.loadLine(LoadEntities.VENOM, this.width,this.height);
        this.animation = new Animation();
        this.animation.setFrames(this.sprites);
        this.animation.setDelay(70L);
=======
        moveSpeed = 3.8;
        if (right) {
            dx = moveSpeed;
        } else {
            dx = -moveSpeed;
        }

        width = 45;
        height = 45;
        cwidth = 14;
        cheight = 14;


        sprites = LoadEntities.loadLine(LoadEntities.VENOM, width,height);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(70L);
>>>>>>> NguyenPhuc

    }

    public void setHit() {
<<<<<<< HEAD
        if (!this.hit) {
            this.hit = true;
            this.dx = 0.0;
=======
        if (!hit) {
            hit = true;
            dx = 0.0;
>>>>>>> NguyenPhuc
        }
    }

    public boolean shouldRemove() {
<<<<<<< HEAD
        return this.remove;
    }

    public void update() {
        this.checkTileMapCollision();
        this.setPosition(this.xtemp, this.ytemp);
        if (this.dx == 0.0 && !this.hit) {
            this.setHit();
        }

        this.animation.update();
        if (this.hit && this.animation.hasPlayedOnce()) {
            this.remove = true;
=======
        return remove;
    }

    public void update() {
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        if (dx == 0.0 && !hit) {
            setHit();
        }

        animation.update();
        if (hit && animation.hasPlayedOnce()) {
            remove = true;
>>>>>>> NguyenPhuc
        }

    }

    public void draw(Graphics2D g) {

<<<<<<< HEAD
        this.setMapPosition();

        this.setMapPosition(this.tileMap.getx(), this.tileMap.gety());
=======
        setMapPosition();

        setMapPosition(tileMap.getx(), tileMap.gety());
>>>>>>> NguyenPhuc
        super.draw(g);
    }


}

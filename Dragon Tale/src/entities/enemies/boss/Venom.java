package entities.enemies.boss;

import objects.Animation;
import objects.MapObject;
import ui.LoadEntities;
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

    }

    public void setHit() {
        if (!this.hit) {
            this.hit = true;
            this.dx = 0.0;
        }
    }

    public boolean shouldRemove() {
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
        }

    }

    public void draw(Graphics2D g) {

        this.setMapPosition();

        this.setMapPosition(this.tileMap.getx(), this.tileMap.gety());
        super.draw(g);
    }


}

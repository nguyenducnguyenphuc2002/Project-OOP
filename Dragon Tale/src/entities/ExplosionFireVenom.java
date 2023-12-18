package entities;

import objects.Animation;
import storage.LoadEntities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class ExplosionFireVenom {

    private int x;
    private int y;
    private int xmap;
    private int ymap;
    private int width;
    private int height;
    private Animation animation;
    private BufferedImage[] sprites;
    private boolean remove;

    public ExplosionFireVenom(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 30;

        this.sprites = LoadEntities.loadLine(LoadEntities.EXPLOSIONFIREVENOM, this.width, this.height);
        this.animation = new Animation();
        this.animation.setFrames(this.sprites);
        this.animation.setDelay(70L);
    }

    public void update() {
        this.animation.update();
        if (this.animation.hasPlayedOnce()) {
            this.remove = true;
        }

    }

    public boolean shouldRemove() {
        return this.remove;
    }

    public void setMapPosition(int x, int y) {
        this.xmap = x;
        this.ymap = y;
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.animation.getImage(), this.x + this.xmap - this.width / 2, this.y + this.ymap - this.height / 2, (ImageObserver)null);
    }
}



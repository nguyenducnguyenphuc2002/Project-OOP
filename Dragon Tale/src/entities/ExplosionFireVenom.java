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
        width = 30;
        height = 30;

        sprites = LoadEntities.loadLine(LoadEntities.EXPLOSIONFIREVENOM, width, height);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(70L);
    }

    public void update() {
        animation.update();
        if (animation.hasPlayedOnce()) {
            remove = true;
        }

    }

    public boolean shouldRemove() {
        return remove;
    }

    public void setMapPosition(int x, int y) {
        xmap = x;
        ymap = y;
    }

    public void draw(Graphics2D g) {
        g.drawImage(animation.getImage(),
                x + xmap - width / 2,
                y + ymap - height / 2,
                null);
    }
}



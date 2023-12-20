package entities.enemies.die;

import objects.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DieEnemies {
    protected int x;

    protected int y;
    protected int xmap;
    protected int ymap;

    protected int width;

    protected int height;

    protected Animation animation;

    protected BufferedImage[] sprites;
    protected boolean remove;

    public DieEnemies(int x, int y) {
        this.x = x;
        this.y = y;


        animation = new Animation();
<<<<<<< HEAD


=======
//        animation.setFrames(sprites);
>>>>>>> NguyenPhuc
    }

    public void update() {
        animation.update();
        if(animation.hasPlayedOnce()) {
            remove = true;
        }
    }

    public boolean shouldRemove() {
<<<<<<< HEAD
        return this.remove;
=======
        return remove;
>>>>>>> NguyenPhuc
    }

    public void setMapPosition(int x, int y) {
        xmap = x;
        ymap = y;
    }

    public void draw(Graphics2D g) {
<<<<<<< HEAD
        g.drawImage(animation.getImage(),
                x + xmap - width / 2,
                y + ymap - height / 2,
                null);
=======
        g.drawImage(animation.getImage(), x + xmap - width / 2, y + ymap - height / 2, null);
>>>>>>> NguyenPhuc
    }

}

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

    }

    public void setHit() {
        if (!hit) {
            hit = true;
            dx = 0.0;
        }
    }

    public boolean shouldRemove() {
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
        }

    }

    public void draw(Graphics2D g) {

        setMapPosition();

        setMapPosition(tileMap.getx(), tileMap.gety());
        super.draw(g);
    }


}

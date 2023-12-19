package entities.player;

import objects.Animation;
import objects.MapObject;
import storage.LoadEntities;
import tilemap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FireBall extends MapObject {
    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;

    public FireBall(TileMap tm, boolean right) {
        super(tm);
        facingRight = right;
        moveSpeed = 3.8;
        if (right) {
            dx = moveSpeed;
        } else {
            dx = -moveSpeed;
        }

        width = 30;
        height = 30;
        cwidth = 14;
        cheight = 14;

        sprites = LoadEntities.loadLine(LoadEntities.FIREBALL, width,height);
        hitSprites = LoadEntities.loadLine(LoadEntities.HITFIREBALL, width, height);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(70L);

    }

    public void setHit() {
        if (!hit) {
            hit = true;
            animation.setFrames(hitSprites);
            animation.setDelay(70L);
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

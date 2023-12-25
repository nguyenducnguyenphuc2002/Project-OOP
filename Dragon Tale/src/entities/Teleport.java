package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import objects.Animation;
import objects.MapObject;
import ui.LoadEntities;
import tilemap.TileMap;

public class Teleport extends MapObject {
    private BufferedImage[] sprites;

    public Teleport(TileMap tm) {
        super(tm);
        facingRight = true;
        width = height = 40;
        cwidth = 20;
        cheight = 40;

        //load sprites
        sprites = LoadEntities.loadLine(LoadEntities.TELEPORT, width, height);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(1);
    }

    public void update() {
        setPosition(x, y);

        animation.update();
    }

    public void draw(Graphics2D g) {
        setMapPosition();

        super.draw(g);
    }
}

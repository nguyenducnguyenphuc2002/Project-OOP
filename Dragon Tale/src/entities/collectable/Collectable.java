package entities.collectable;

import objects.Animation;
import objects.MapObject;
import ui.LoadEntities;
import tilemap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Collectable extends MapObject {

    private BufferedImage[] sprites;

    public Collectable(TileMap tm) {
        super(tm);
        width = 15;
        height = 15;
        cwidth = 7;
        cheight = 7;

        sprites = LoadEntities.loadLine(LoadEntities.COIN, width,height);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300L);
    }

    public void update() {

        animation.update();
    }

    public void draw(Graphics2D g) {
        setMapPosition();
        setMapPosition(tileMap.getx(), tileMap.gety());

        super.draw(g);
    }
}
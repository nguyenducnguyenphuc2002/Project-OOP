package entities.enemies.die;


import objects.Animation;
import storage.LoadEntities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DieSlugger extends DieEnemies{

    public DieSlugger(int x, int y) {
        super(x, y);

        width = 30;
        height = 30;

        sprites = LoadEntities.loadLine(LoadEntities.DIESLUGGER, width, height);
        animation.setFrames(sprites);
        animation.setDelay(70L);
    }

}

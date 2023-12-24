package entities.enemies.die;

import storage.LoadEntities;

import java.awt.*;

public class DieArachnik extends DieEnemies {
    public DieArachnik(int x, int y) {
        super(x, y);

        width = 30;
        height = 30;

        sprites = LoadEntities.loadLine(LoadEntities.DIEARACHNIK, width, height);
        animation.setFrames(sprites);
        animation.setDelay(70L);
    }

}


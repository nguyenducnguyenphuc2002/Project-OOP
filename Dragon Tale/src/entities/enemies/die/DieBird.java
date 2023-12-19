package entities.enemies.die;

import storage.LoadEntities;

import java.awt.*;

public class DieBird extends DieEnemies {
    public DieBird(int x, int y) {
        super(x, y);

        width = 28;
        height = 29;



        sprites = LoadEntities.loadLine(LoadEntities.DIEBIRD, width, height);
        animation.setFrames(sprites);
        animation.setDelay(70L);
    }

}


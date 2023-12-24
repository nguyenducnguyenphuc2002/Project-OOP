package entities.enemies.die;

import storage.LoadEntities;

import java.awt.*;

public class DieBoss extends DieEnemies {
    public DieBoss(int x, int y) {
        super(x, y);
        width = 100;
        height = 83;

        sprites = LoadEntities.loadLine(LoadEntities.BOSSDIE, width, height);
        animation.setFrames(sprites);
        animation.setDelay(70L);
    }

}


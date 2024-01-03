package entities.enemies.die;

import ui.LoadEntities;

import java.awt.*;

public class DieArachnik extends DieEnemies {
    public DieArachnik(int x, int y) {
        super(x, y);

        this.width = 28;
        this.height = 29;



        this.sprites = LoadEntities.loadLine(LoadEntities.DIEARACHNIK, this.width, this.height);
        this.animation.setFrames(this.sprites);
        this.animation.setDelay(70L);
    }

}


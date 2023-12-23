package entities.enemies.die;

import storage.LoadEntities;

public class DieHero extends DieEnemies {
    public DieHero(int x, int y) {
        super(x, y);

        width = 30;
        height = 30;

        sprites = LoadEntities.loadLine(LoadEntities.DIEHERO, width, height);
        animation.setFrames(sprites);
        animation.setDelay(70L);
    }


}

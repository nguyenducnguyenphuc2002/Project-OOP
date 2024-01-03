package entities.enemies.die;


import ui.LoadEntities;

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

package entities.enemies.die;

import storage.LoadEntities;

import java.awt.*;

public class DieBird extends DieEnemies {
    public DieBird(int x, int y) {
        super(x, y);

        this.width = 28;
        this.height = 29;



        this.sprites = LoadEntities.loadLine(LoadEntities.DIEBIRD, this.width, this.height);
        this.animation.setFrames(this.sprites);
        this.animation.setDelay(70L);
    }
    @Override
    public void update() {
        super.update();
    }
    @Override
    public boolean shouldRemove() {
        return super.shouldRemove();
    }
    public void setMapPosition(int x, int y) {
        super.setMapPosition(x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }

}


package entities.enemies.die;

import storage.LoadEntities;

import java.awt.*;

public class DieBoss extends DieEnemies {
    public DieBoss(int x, int y) {
        super(x, y);
<<<<<<< HEAD
        width = 100;
        height = 83;

        sprites = LoadEntities.loadLine(LoadEntities.BOSSDIE, width, height);
        animation.setFrames(sprites);
        animation.setDelay(70L);
=======
        this.width = 100;
        this.height = 83;

        this.sprites = LoadEntities.loadLine(LoadEntities.BOSSDIE, this.width, this.height);
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
>>>>>>> NguyenPhuc
    }

}


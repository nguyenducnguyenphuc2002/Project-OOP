package entities.enemies.die;

import storage.LoadEntities;

import java.awt.*;

public class DieHatMonkey extends DieEnemies {
    public DieHatMonkey(int x, int y) {
        super(x, y);

        width = 30;
        height = 30;

        this.sprites = LoadEntities.loadLine(LoadEntities.DIEHATMONKEY, this.width, this.height);
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


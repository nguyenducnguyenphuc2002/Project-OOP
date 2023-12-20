package entities.enemies.die;

<<<<<<< HEAD

import objects.Animation;
import storage.LoadEntities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DieSlugger extends DieEnemies{

=======
import storage.LoadEntities;

import java.awt.*;

public class DieSlugger extends DieEnemies {
>>>>>>> NguyenPhuc
    public DieSlugger(int x, int y) {
        super(x, y);

        width = 30;
        height = 30;

        sprites = LoadEntities.loadLine(LoadEntities.DIESLUGGER, width, height);
        animation.setFrames(sprites);
        animation.setDelay(70L);
    }
<<<<<<< HEAD

}
=======
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

>>>>>>> NguyenPhuc

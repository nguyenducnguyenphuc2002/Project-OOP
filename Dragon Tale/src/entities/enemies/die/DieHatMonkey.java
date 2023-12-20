package entities.enemies.die;

import storage.LoadEntities;

import java.awt.*;

public class DieHatMonkey extends DieEnemies {
<<<<<<< HEAD
	public DieHatMonkey(int x, int y) {
		super(x, y);

		width = 30;
		height = 30;

		sprites = LoadEntities.loadLine(LoadEntities.DIEHATMONKEY, width, height);
		animation.setFrames(sprites);
		animation.setDelay(70L);
	}
	
=======
    public DieHatMonkey(int x, int y) {
        super(x, y);

        width = 30;
        height = 30;

        sprites = LoadEntities.loadLine(LoadEntities.DIEHATMONKEY, width, height);
        animation.setFrames(sprites);
        animation.setDelay(70L);
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
>>>>>>> NguyenPhuc

}


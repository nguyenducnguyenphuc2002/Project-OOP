package Entity;

import TileMap.TileMap;

public class Enemy extends MapObject {
<<<<<<< HEAD
=======


>>>>>>> 8fc2bba (update)
    protected int health;
    protected int maxHealth;
    protected boolean dead;
    protected int damage;
    protected boolean flinching;
    protected long flinchTimer;

    public Enemy(TileMap tm) {
        super(tm);
    }

    public boolean isDead() {
        return this.dead;
    }

    public int getDamage() {
        return this.damage;
    }

    public void hit(int damage) {
        if (!this.dead && !this.flinching) {
            this.health -= damage;
            if (this.health < 0) {
                this.health = 0;
            }

            if (this.health == 0) {
                this.dead = true;
            }

<<<<<<< HEAD
=======

>>>>>>> 8fc2bba (update)
            this.flinching = true;
            this.flinchTimer = System.nanoTime();
        }
    }

    public void update() {
    }
}

package entities.enemies;

import entities.enemies.boss.Venom;
import entities.player.Player;
import objects.MapObject;
import tilemap.TileMap;

import java.util.ArrayList;

public class Enemy extends MapObject {
    public static int BIRD = 1;
    public static int HATMONKEY = 2;
    public static int SLUGGER = 3;
    public static int MUSHROOM = 4; // con này là của Nguyên sau Nguyên thêm vào nha
    public static int BOSS = 5;

    protected int health;
    protected int maxHealth;
    protected boolean dead;
    protected int damage;
    protected boolean flinching;
    protected long flinchTimer;
    protected ArrayList<Venom> venoms;

    public Enemy(TileMap tm) {
        super(tm);
        this.venoms = new ArrayList<>();

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



            this.flinching = true;
            this.flinchTimer = System.nanoTime();
        }
    }

    public void update() {
    }

    public int checkAttack(Player player){
        return -1;
    }

    public ArrayList<Venom> getVenoms() {
        return venoms;
    }
    public int getIndex(){
        return -1;
    }
}

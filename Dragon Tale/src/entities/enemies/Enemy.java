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
	public static int ARACHNIK = 6;
	
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
	
	public boolean isDead() { return dead; }
	
	public int getDamage() { return damage; }
	
	public void hit(int damage) {
		if(dead || flinching) return;
		health -= damage;
		if(health < 0) health = 0;
		if(health == 0) dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
	public void update() {}

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















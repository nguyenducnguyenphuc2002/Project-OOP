package entities.enemies.minimonsters;

import entities.enemies.Enemy;
import objects.Animation;
import ui.LoadEntities;
import tilemap.TileMap;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Slugger extends Enemy {
	
	private BufferedImage[] sprites;
	
	public Slugger(TileMap tm) {
		
		super(tm);
		
		moveSpeed = 0.4;
		maxSpeed = 0.4;
		fallSpeed = 0.5;
		maxFallSpeed = 15.0;
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		health = maxHealth = 2;
		damage = 1;
		
		// load sprites
		this.sprites = LoadEntities.loadLine(LoadEntities.SLUGGER, this.width,this.height);
		this.animation = new Animation();
		this.animation.setFrames(this.sprites);
		this.animation.setDelay(300L);
		this.right = true;
		this.facingRight = true;

		
	}
	
	private void getNextPosition() {
		
		// movement
		if(left) dx = -moveSpeed;
		else if(right) dx = moveSpeed;
		else dx = 0;
		if(falling) {
			dy += fallSpeed;
			if(dy > maxFallSpeed) dy = maxFallSpeed;
		}
		
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// check flinching
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinching = false;
			}
		}
		
		// if it hits a wall, go other direction
		if(right && dx == 0) {
			right = false;
			left = true;
			facingRight = false;
		}
		else if(left && dx == 0) {
			right = true;
			left = false;
			facingRight = true;
		}
		
		// update animation
		animation.update();
		
	}
	
	public void draw(Graphics2D g) {
		
//		if(notOnScreen()) return;
		
		setMapPosition();
		setMapPosition(tileMap.getx(), tileMap.gety());
		
		super.draw(g);
		
	}

	@Override
	public int getIndex(){
		return 3;
	}


}












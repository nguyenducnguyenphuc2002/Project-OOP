package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class FireBall extends MapObject{
	
	private boolean hit; // kiểm tra xem cầu lửa đã chạm vào thứ gì chưa 
	private boolean remove; // kiểm tra xem có nên loại bỏ cầu lửa đó không
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites; // loạt ảnh phóng ra cầu lửa
	
	public FireBall(TileMap tm, boolean right) {
		super(tm);
		
		facingRight = right;
		
		moveSpeed = 3.8; // tốc độ di chuyển của Fire Ball
		if(right) dx = moveSpeed;
		else dx = -moveSpeed;
		
		width = 30;
		height = 30;
		cwidth = 14;
		cheight = 14;
		
		// load sprites
		try {

			BufferedImage spritesheet = ImageIO.read(new File("Resources/Sprites/Player/fireball.gif"));


			sprites = new BufferedImage[4];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
			hitSprites = new BufferedImage[3];
			for(int i = 0; i < hitSprites.length; i++) {
				hitSprites[i] = spritesheet.getSubimage(
					i * width,
					height,
					width,
					height
				);
			}
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(70);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setHit() {
		if(hit) return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		dx = 0;
	}
	
	public boolean shouldRemove() { return remove; }
	
	public void update() {
		checkTileMapCollision();
		calculateCorners(x + 30, ydest);
		setPosition(xtemp, ytemp);
		
		if(dx == 0 && !hit) {
			setHit();
		}
		
		animation.update();
		if(hit && animation.hasPlayedOnce()) {
			remove = true;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		super.draw(g);
		
	}
	
}



















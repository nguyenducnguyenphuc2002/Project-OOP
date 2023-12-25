package tilemap;

import main.GamePanel;
import ui.LoadBackground;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale; // tỉ lệ di chuyển  
	
	public Background(File file, double ms) {

		image = LoadBackground.loadImage(file);
		moveScale = ms;
		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			g.drawImage(
				image,
				(int)x + GamePanel.WIDTH,
				(int)y,
				null
			);
		}
		if(x > 0) {
			g.drawImage(
				image,
				(int)x - GamePanel.WIDTH,
				(int)y,
				null
			);
		}
	}
	
}








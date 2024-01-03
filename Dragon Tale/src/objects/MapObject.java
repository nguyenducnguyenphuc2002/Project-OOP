package objects;

import java.awt.Rectangle;

import main.GamePanel;
import tilemap.TileMap;

public abstract class MapObject { // đối tượng trên bản đồ
	
	// tile stuff
	protected TileMap tileMap;
	protected int tileSize; // kích thước ô
	protected double xmap;
	protected double ymap;
	
	// position and vector
	protected double x;
	protected double y;
	protected double dx; // hướng của x
	protected double dy; // hướng của y
	
	// dimensions : xác định kích thước của object
	protected int width;
	protected int height;
	
	//collision box: sử dụng để xác định va chạm với enemies, kích thước thực 
	protected int cwidth;
	protected int cheight;
	
	// collision: những thứ va chạm khác
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight; // biến xoay mặt sang trái: dùng để xác định xem sang trái hay sang phải 
	
	// movement: xác định đối tượng đang làm gì
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	// movement attributes: các tác đọng vặt lý
	protected double moveSpeed; // tốc độ di chuyển
	protected double maxSpeed; // tốc độ tối đa
	protected double stopSpeed; // tốc độ giảm đi
	protected double fallSpeed; // tốc độ rơi
	protected double maxFallSpeed; // tốc độ rơi tối đa
	protected double jumpStart; // độ cao mà vật có thể nhảy
	protected double stopJumpSpeed; // tốc độ nhảy giảm đi

	// Objects in map
	protected int health; // mạng hiện tại
	protected int maxHealth;// mạng cao nhất
	protected boolean dead;
	// constructor
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	// cần nghiêm cứu thêm
	public boolean intersects(MapObject o) { // giao nhau giữa 2 đối tượng trên bản đồ va chạm nhau
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(
				(int)x - cwidth,
				(int)y - cheight,
				cwidth,
				cheight
		);
	}
	// cần chạy test để hiểu hơn
	public void calculateCorners(double x, double y) {

		int leftTile = (int) (x - cwidth / 2) / tileSize;
		int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
		int topTile = (int) (y - cheight / 2) / tileSize;
		int bottomTile = (int) (y + cheight / 2 - 1) / tileSize;

		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);

		if (bl != -1 && br != -1) {
			topLeft = tl == 1;
			topRight = tr == 1;
			bottomLeft = bl == 1;
			bottomRight = br == 1;
		} else {
			dead = true;
		}
	}
	
	// cần nghiêm cứu thêm
	public void checkTileMapCollision() { // kiểm tra đối tượng có chạy vào một ô bị chặn hoặc một ô bình thường
		
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydest);
		if (dy < 0.0) {
			if (!topLeft && !topRight) {
				ytemp += dy;
			} else {
				dy = 0.0;
				ytemp = (currRow * tileSize + (double) cheight / 2);
			}
		}
		if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				ytemp = (currRow + 1) * tileSize - (double) cheight / 2; // đứng ngay trên ô vừa hạ cách
			}
			else {
				ytemp += dy;
			}
		}
		
		calculateCorners(xdest, y);
		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
				xtemp = currCol * tileSize + (double) cwidth / 2; // đứng ngay bên phải của ô vừa chạm vào
			}
			else {
				xtemp += dx;
			}
		}
		if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
				xtemp = (currCol + 1) * tileSize - (double) cwidth / 2; // dứng ngay bên trái ô vừa chạm vào
			}
			else {
				xtemp += dx;
			}
		}
		
		if(!falling) { // trường hợp không bị té
			calculateCorners(x, ydest + 1);
			if(!bottomLeft && !bottomRight) {
				falling = true;
			}
		}
		
	}
	
	public int getx() { return (int)x; }
	public int gety() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	public void setMapPosition(){
	}
	public void setMapPosition(int x, int y) {
		xmap = x;
		ymap = y;
	}
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setJumping(boolean b) { jumping = b; }
	
	// cần chạy test để rõ hơn 
	public boolean notOnScreen() { // x+ xmap, y + ymap: vị trí cuối cùng của người chơi
		return x + xmap + width < 0 ||
			x + xmap - width > GamePanel.WIDTH ||
			y + ymap + height < 0 ||
			y + ymap - height > GamePanel.HEIGHT;
	}
	
	public void draw(java.awt.Graphics2D g) {
		if (facingRight) {
			g.drawImage(animation.getImage(),
					(int)(x + xmap - (double)(width / 2)),
					(int)(y + ymap - (double)(height / 2)),
					null);
		} else {
			g.drawImage(animation.getImage(),
					(int)(x + xmap - (double)(width / 2) + (double)width),
					(int)(y + ymap - (double)(height / 2)), - width, height,
					null);
		}
	}
	
}



























package Entity;

import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public abstract class MapObject {
    protected TileMap tileMap;
    protected int tileSize;
    protected double xmap;
    protected double ymap;
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    protected int width;
    protected int height;
    protected int cwidth;
    protected int cheight;
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
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;
    protected boolean facingRight;
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;
<<<<<<< HEAD
    protected int health;
=======
    protected static int health;
>>>>>>> 8fc2bba (update)
    protected int maxHealth;
    protected boolean dead;

    public MapObject(TileMap tm) {
        this.tileMap = tm;
        this.tileSize = tm.getTileSize();
    }

    public boolean intersects(MapObject o) {
        Rectangle r1 = this.getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }

    public Rectangle getRectangle() {
        return new Rectangle((int)this.x - this.cwidth, (int)this.y - this.cheight, this.cwidth, this.cheight);
    }

    public void calculateCorners(double x, double y) {
        int leftTile = (int)(x - (double)(this.cwidth / 2)) / this.tileSize;
        int rightTile = (int)(x + (double)(this.cwidth / 2) - 1.0) / this.tileSize;
        int topTile = (int)(y - (double)(this.cheight / 2)) / this.tileSize;
        int bottomTile = (int)(y + (double)(this.cheight / 2) - 1.0) / this.tileSize;
        int tl = this.tileMap.getType(topTile, leftTile);
        int tr = this.tileMap.getType(topTile, rightTile);
        int bl = this.tileMap.getType(bottomTile, leftTile);
        int br = this.tileMap.getType(bottomTile, rightTile);
        if (bl != -1 && br != -1) {
            this.topLeft = tl == 1;
            this.topRight = tr == 1;
            this.bottomLeft = bl == 1;
            this.bottomRight = br == 1;
        } else {
            this.dead = true;
        }

    }

    public void checkTileMapCollision() {
        this.currCol = (int)this.x / this.tileSize;
        this.currRow = (int)this.y / this.tileSize;
        this.xdest = this.x + this.dx;
        this.ydest = this.y + this.dy;
        this.xtemp = this.x;
        this.ytemp = this.y;
        this.calculateCorners(this.x, this.ydest);
        if (this.dy < 0.0) {
            if (!this.topLeft && !this.topRight) {
                this.ytemp += this.dy;
            } else {
                this.dy = 0.0;
                this.ytemp = (double)(this.currRow * this.tileSize + this.cheight / 2);
            }
        }

        if (this.dy > 0.0) {
            if (!this.bottomLeft && !this.bottomRight) {
                this.ytemp += this.dy;
            } else {
                this.dy = 0.0;
                this.falling = false;
                this.ytemp = (double)((this.currRow + 1) * this.tileSize - this.cheight / 2);
            }
        }

        this.calculateCorners(this.xdest, this.y);
        if (this.dx < 0.0) {
            if (!this.topLeft && !this.bottomLeft) {
                this.xtemp += this.dx;
            } else {
                this.dx = 0.0;
                this.xtemp = (double)(this.currCol * this.tileSize + this.cwidth / 2);
            }
        }

        if (this.dx > 0.0) {
            if (!this.topRight && !this.bottomRight) {
                this.xtemp += this.dx;
            } else {
                this.dx = 0.0;
                this.xtemp = (double)((this.currCol + 1) * this.tileSize - this.cwidth / 2);
            }
        }

        if (!this.falling) {
            this.calculateCorners(this.x, this.ydest + 1.0);
            if (!this.bottomLeft && !this.bottomRight) {
                this.falling = true;
            }
        }

    }

    public int getx() {
        return (int)this.x;
    }

    public int gety() {
        return (int)this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getCWidth() {
        return this.cwidth;
    }

    public int getCHeight() {
        return this.cheight;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

<<<<<<< HEAD
    public void setMapPosition() {
=======
    public void setMapPosition(int getx, int gety) {
>>>>>>> 8fc2bba (update)
        this.xmap = (double)this.tileMap.getx();
        this.ymap = (double)this.tileMap.gety();
    }

    public void setLeft(boolean b) {
        this.left = b;
    }

    public void setRight(boolean b) {
        this.right = b;
    }

    public void setUp(boolean b) {
        this.up = b;
    }

    public void setDown(boolean b) {
        this.down = b;
    }

    public void setJumping(boolean b) {
        this.jumping = b;
    }

    public boolean notOnScreen() {
        return this.x + this.xmap + (double)this.width < 0.0 || this.x + this.xmap - (double)this.width > 320.0 || this.y + this.ymap + (double)this.height < 0.0 || this.y + this.ymap - (double)this.height > 240.0;
    }

    public void draw(Graphics2D g) {
        if (this.facingRight) {
            g.drawImage(this.animation.getImage(), (int)(this.x + this.xmap - (double)(this.width / 2)), (int)(this.y + this.ymap - (double)(this.height / 2)), (ImageObserver)null);
        } else {
            g.drawImage(this.animation.getImage(), (int)(this.x + this.xmap - (double)(this.width / 2) + (double)this.width), (int)(this.y + this.ymap - (double)(this.height / 2)), -this.width, this.height, (ImageObserver)null);
        }

    }
}

package objects;

import tilemap.TileMap;
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

    protected int health;
    protected int maxHealth;
    protected boolean dead;

    public MapObject(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTileSize();
    }

    public boolean intersects(MapObject o) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }

    public Rectangle getRectangle() {
        return new Rectangle((int)x - cwidth, (int)y - cheight, cwidth, cheight);
    }

    public void calculateCorners(double x, double y) {
        int leftTile = (int)(x - (double)(cwidth / 2)) / tileSize;
        int rightTile = (int)(x + (double)(cwidth / 2) - 1.0) / tileSize;
        int topTile = (int)(y - (double)(cheight / 2)) / tileSize;
        int bottomTile = (int)(y + (double)(cheight / 2) - 1.0) / tileSize;
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

    public void checkTileMapCollision() {
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

        if (dy > 0.0) {
            if (!bottomLeft && !bottomRight) {
                ytemp += dy;
            } else {
                dy = 0.0;
                falling = false;
                ytemp = ((currRow + 1) * tileSize - (double) cheight / 2);
            }
        }

        calculateCorners(xdest, y);
        if (dx < 0.0) {
            if (!topLeft && !bottomLeft) {
                xtemp += dx;
            } else {
                dx = 0.0;
                xtemp = (currCol * tileSize + (double) cwidth / 2);
            }
        }

        if (dx > 0.0) {
            if (!topRight && !bottomRight) {
                xtemp += dx;
            } else {
                dx = 0.0;
                xtemp = (currCol + 1) * tileSize - (double) cwidth / 2;
            }
        }

        if (!falling) {
            calculateCorners(x, ydest + 1.0);
            if (!bottomLeft && !bottomRight) {
                falling = true;
            }
        }

    }

    public int getx() {
        return (int)x;
    }

    public int gety() {
        return (int)y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

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

    public void setMapPosition(){

    }
    public void setMapPosition(int x, int y) {

        xmap = x;
        ymap = y;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void setJumping(boolean b) {
        jumping = b;
    }

    public boolean notOnScreen() {
        return x + xmap + (double)width < 0.0 || x + xmap - (double)width > 320.0 || y + ymap + (double)height < 0.0 || y + ymap - (double)height > 240.0;
    }

    public void draw(Graphics2D g) {
        if (facingRight) {
            g.drawImage(animation.getImage(), (int)(x + xmap - (double)(width / 2)), (int)(y + ymap - (double)(height / 2)), null);
        } else {
            g.drawImage(animation.getImage(), (int)(x + xmap - (double)(width / 2) + (double)width), (int)(y + ymap - (double)(height / 2)), -width, height, null);
        }

    }


}

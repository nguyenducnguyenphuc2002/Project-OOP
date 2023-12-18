package tilemap;

import storage.LoadBackground;
import storage.LoadTileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileMap {
    private double x;
    private double y;
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    private double tween;
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        this.numRowsToDraw = 240 / tileSize + 2;
        this.numColsToDraw = 320 / tileSize + 2;
        this.tween = 0.07;
    }

    public void loadTiles(File file) {
        tileset = LoadTileMap.loadTile(file);
        numTilesAcross = tileset.getWidth() / tileSize;
        tiles = new Tile[2][numTilesAcross];

        BufferedImage subimage;
        for (int col = 0; col < numTilesAcross; col++) {
            subimage = tileset.getSubimage(
                    col * tileSize,
                    0,
                    tileSize,
                    tileSize
            );
            tiles[0][col] = new Tile(subimage, Tile.NORMAL);
            subimage = tileset.getSubimage(
                    col * tileSize,
                    tileSize,
                    tileSize,
                    tileSize
            );
            tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
        }

    }

    public void loadMap(File file) {

        LoadTileMap.loadMap(file, this);

        this.width = this.numCols * this.tileSize;
        this.height = this.numRows * this.tileSize;
        this.xmin = 320 - this.width;
        this.xmax = 0;
        this.ymin = 240 - this.height;
        this.ymax = 0;



    }

    public int getTileSize() {
        return this.tileSize;
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

    public int getType(int row, int col) {
        if (row >= 0 && row < this.numRows && col >= 0 && col < this.numCols) {
            int rc = this.map[row][col];
            int r = rc / this.numTilesAcross;
            int c = rc % this.numTilesAcross;
            return this.tiles[r][c].getType();
        } else {
            return -1;
        }
    }

    public void setTween(double d) {
        this.tween = d;
    }

    public void setPosition(double x, double y) {
        this.x += (x - this.x) * this.tween;
        this.y += (y - this.y) * this.tween;
        this.fixBounds();
        this.colOffset = (int)(-this.x) / this.tileSize;
        this.rowOffset = (int)(-this.y) / this.tileSize;
    }

    private void fixBounds() {
        if (this.x < (double)this.xmin) {
            this.x = (double)this.xmin;
        }

        if (this.y < (double)this.ymin) {
            this.y = (double)this.ymin;
        }

        if (this.x > (double)this.xmax) {
            this.x = (double)this.xmax;
        }

        if (this.y > (double)this.ymax) {
            this.y = (double)this.ymax;
        }

    }

    public void draw(Graphics2D g) {
        for(int row = this.rowOffset; row < this.rowOffset + this.numRowsToDraw && row < this.numRows; ++row) {
            for(int col = this.colOffset; col < this.colOffset + this.numColsToDraw && col < this.numCols; ++col) {
                if (this.map[row][col] != 0) {
                    int rc = this.map[row][col];
                    int r = rc / this.numTilesAcross;
                    int c = rc % this.numTilesAcross;
                    g.drawImage(this.tiles[r][c].getImage(), (int)this.x + col * this.tileSize, (int)this.y + row * this.tileSize, (ImageObserver)null);
                }
            }
        }

    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }
}
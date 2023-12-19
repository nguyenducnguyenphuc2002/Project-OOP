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
        numRowsToDraw = 240 / tileSize + 2;
        numColsToDraw = 320 / tileSize + 2;
        tween = 0.07;
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

        width = numCols * tileSize;
        height = numRows * tileSize;
        xmin = 320 - width;
        xmax = 0;
        ymin = 240 - height;
        ymax = 0;



    }

    public int getTileSize() {
        return tileSize;
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

    public int getType(int row, int col) {
        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
            int rc = map[row][col];
            int r = rc / numTilesAcross;
            int c = rc % numTilesAcross;
            return tiles[r][c].getType();
        } else {
            return -1;
        }
    }

    public void setTween(double d) {
        tween = d;
    }

    public void setPosition(double x, double y) {
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;
        fixBounds();
        colOffset = (int)(-this.x) / tileSize;
        rowOffset = (int)(-this.y) / tileSize;
    }

    private void fixBounds() {
        if (x < (double)xmin) {
            x = xmin;
        }

        if (y < (double)ymin) {
            y = ymin;
        }

        if (x > (double)xmax) {
            x = xmax;
        }

        if (y > (double)ymax) {
            y = ymax;
        }

    }

    public void draw(Graphics2D g) {
        for(int row = rowOffset; row < rowOffset + numRowsToDraw && row < numRows; ++row) {
            for(int col = colOffset; col < colOffset + numColsToDraw && col < numCols; ++col) {
                if (map[row][col] != 0) {
                    int rc = map[row][col];
                    int r = rc / numTilesAcross;
                    int c = rc % numTilesAcross;
                    g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, (ImageObserver)null);
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
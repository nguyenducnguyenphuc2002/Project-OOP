package TileMap;

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
        try {
            this.tileset = ImageIO.read(file);
            this.numTilesAcross = this.tileset.getWidth() / this.tileSize;
            this.tiles = new Tile[2][this.numTilesAcross];

            for(int col = 0; col < this.numTilesAcross; ++col) {
                BufferedImage subimage = this.tileset.getSubimage(col * this.tileSize, 0, this.tileSize, this.tileSize);
                this.tiles[0][col] = new Tile(subimage, 0);
                subimage = this.tileset.getSubimage(col * this.tileSize, this.tileSize, this.tileSize, this.tileSize);
                this.tiles[1][col] = new Tile(subimage, 1);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void loadMap(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            this.numCols = Integer.parseInt(br.readLine());
            this.numRows = Integer.parseInt(br.readLine());
            this.map = new int[this.numRows][this.numCols];
            this.width = this.numCols * this.tileSize;
            this.height = this.numRows * this.tileSize;
            this.xmin = 320 - this.width;
            this.xmax = 0;
            this.ymin = 240 - this.height;
            this.ymax = 0;
            String delims = "\\s+";

            for(int row = 0; row < this.numRows; ++row) {
                String line = br.readLine();
                String[] tokens = line.split(delims);

                for(int col = 0; col < this.numCols; ++col) {
                    this.map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

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
}
package storage;

import tilemap.TileMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class LoadTileMap {
    public static String TILES = "tilesets/grasstileset.gif";
    public static String MAP = "maps/level1-1.map";

    public static void loadTileMap(TileMap tileMap, String mapName, String tilesName){
<<<<<<< HEAD
        tileMap.loadTiles(new File("resources/"+tilesName));
        tileMap.loadMap(new File("resources/"+mapName));
=======
        tileMap.loadTiles(new File("D:/Assignments/Project-OOP/Dragon Tale/src/resources/"+tilesName));
        tileMap.loadMap(new File("D:/Assignments/Project-OOP/Dragon Tale/src/resources/"+mapName));
>>>>>>> NguyenPhuc
        tileMap.setPosition(0.0, 0.0);
        tileMap.setTween(1.0);
    }

    public static BufferedImage loadTile(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadMap(File file, TileMap tileMap) {
        try {
            FileInputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            tileMap.setNumCols(Integer.parseInt(br.readLine()));
            tileMap.setNumRows(Integer.parseInt(br.readLine()));
            int[][] tempMap = new int[tileMap.getNumRows()][tileMap.getNumCols()];
            tileMap.setMap(tempMap);

            String delims = "\\s+";
            for(int row = 0; row < tileMap.getNumRows(); ++row) {
                String line = br.readLine();
                String[] tokens = line.split(delims);

                for(int col = 0; col < tileMap.getNumCols(); ++col) {
                    tempMap[row][col] = Integer.parseInt(tokens[col]);
                }
            }
            tileMap.setMap(tempMap);
        } catch (Exception var9) {
            var9.printStackTrace();
        }
    }

}

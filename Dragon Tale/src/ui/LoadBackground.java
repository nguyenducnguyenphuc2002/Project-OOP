package ui;

import gamestate.Level1State;
import tilemap.Background;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LoadBackground {
    public static String MENUBACKGROUND = "menubg";
    public static String HELPBACKGROUND = "helpstate (1)";
    public static String LEVEL1STATE = "grassbg1";



    public static Background loadBackground(String fileName) {
        File file = new File("resources/backgrounds/" + fileName+ ".gif");
        if (Objects.equals(fileName, LEVEL1STATE)) {
            return new Background(file, 0.1);
        } else {
            return new Background(file, 1);
        }
    }

    public static BufferedImage loadImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

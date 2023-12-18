package storage;

import tilemap.Background;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LoadBackground {
    public static String MENUBACKGROUND = "menubg";

    public static String LEVEL1STATE = "grassbg1";



    public static Background loadBackground(String fileName) {
        File file = new File("D:/Assignments/Project-OOP/Disanour_Tale/src/resources/Backgrounds/" + fileName+ ".gif");
        if (Objects.equals(fileName, MENUBACKGROUND)) {
            return new Background(file, 1);
        } else {
            return new Background(file, 0.1);
        }
    }





}

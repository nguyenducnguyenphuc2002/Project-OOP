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
<<<<<<< HEAD
        File file = new File("resources/backgrounds/" + fileName+ ".gif");
=======
        File file = new File("D:/Assignments/Project-OOP/Dragon Tale/src/resources/backgrounds/" + fileName+ ".gif");
>>>>>>> NguyenPhuc
        if (Objects.equals(fileName, MENUBACKGROUND)) {
            return new Background(file, 1);
        } else {
            return new Background(file, 0.1);
        }
    }

<<<<<<< HEAD
    public static BufferedImage loadImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

=======
>>>>>>> NguyenPhuc
}

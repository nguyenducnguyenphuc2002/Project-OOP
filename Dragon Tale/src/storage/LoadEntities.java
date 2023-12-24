package storage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LoadEntities {
    public static String ARACHNIK = "enemies/arachnik.gif";
    public static String DIEARACHNIK = "enemies/arachnikdie.gif";
    public static String SLUGGER = "enemies/slugger.gif";
    public static String DIESLUGGER = "enemies/sluggeronfire.gif";
    public static String DIEHATMONKEY = "enemies/diehatmonekey.gif";
    public static String HATMONKEY = "enemies/hatmonkey.gif";
    public static String COIN = "player/coin.png";
    public static String FIREBALL = "player/fireball.gif";
    public static String HITFIREBALL = "player/hitfireball.gif";
    public static String PLAYER = "player/playersprites.gif";
    public static String BIRD = "enemies/bird.gif";
    public static String DIEBIRD = "enemies/diebird.gif";
    public static String BOSS = "enemies/boss3green.gif";
    public static String BOSSDIE = "enemies/bossdie3green.gif";
    public static String VENOM = "enemies/venom.gif";
    public static String TELEPORT = "/portal.gif";
    public static String EXPLOSIONFIREVENOM = "enemies/explosionfirevenom.gif";
    public static String ICON = "player/iconplayer.gif";



    public static BufferedImage[] loadLine(String name, int width, int height){
        try {
            BufferedImage image = ImageIO.read(new File("resources/sprites/"+name));

            int num = image.getWidth() / width ;

            BufferedImage[] sprites = new BufferedImage[num];
            for (int i = 0; i < num; ++i) {
                sprites[i] = image.getSubimage(i * width, 0, width, height);
            }
            return sprites;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static ArrayList<BufferedImage[]> loadMatrix(String name, int[] numFrames, int width, int height){
        try {
            ArrayList<BufferedImage[]> sprites = new ArrayList<>();
            BufferedImage image = ImageIO.read(new File("resources/sprites/"+name));

            int num = image.getHeight() / height;

            for (int i = 0; i < num; ++i) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {
                    if (i != 6) {
                        bi[j] = image.getSubimage(j * width, i * height, width, height);
                    } else {
                        bi[j] = image.getSubimage(j * width * 2, i * height, width * 2, height);
                    }
                }

                sprites.add(bi);
            }

            return sprites;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Image LoadIcon(String fileName){
        return new ImageIcon("resources/sprites/"+fileName).getImage();
    }


}

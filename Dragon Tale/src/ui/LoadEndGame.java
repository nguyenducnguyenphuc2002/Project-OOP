package ui;

import tilemap.Background;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class LoadEndGame {
    public static String ENDGAME = "GameOverBG";
    private static Font font;
    public static int coinsCollected = 0;
    public static int sluggerKills = 0;
    public static int arachnikKills = 0;
    public static int monkeyKills = 0;
    public static int heroKills = 0;
    public static int birdKills = 0;
    public static int bossKills = 0;

    public static Background loadEndGame(String fileName) {
        File file = new File("resources/backgrounds/" + fileName+ ".gif");
        return new Background(file, 1);
    }

    public static void LoadInfo(Graphics2D g){
        font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Your Scores: " + LoadEndGame.coinsCollected, 115, 78);

        // draw number of monsters killed by type
        String sluggerText = "x: " + LoadEndGame.sluggerKills;
        String arachnikText = "x: " + LoadEndGame.arachnikKills;
        String monkeyText = "x: " + LoadEndGame.monkeyKills;
        String heroText = "x: " + LoadEndGame.heroKills;
        String birdText = "x: " + LoadEndGame.birdKills;
        String bossText = "x: " + LoadEndGame.bossKills;


        int iconSize = 20;
        int iconX = 110;
        int iconY = 90;

        // draw slugger icon and text
        g.setColor(Color.BLACK);
        g.drawString(sluggerText, iconX - iconSize, iconY + iconSize);


        // draw arachnik icon and text
        g.setColor(Color.BLACK);
        g.drawString(arachnikText, iconX + iconSize*3, iconY + iconSize);

        // draw monkey icon and text
        g.setColor(Color.BLACK);
        g.drawString(monkeyText, iconX + iconSize*7, iconY + iconSize);


        // draw hero icon and text
        g.setColor(Color.BLACK);
        g.drawString(heroText, iconX - iconSize, iconY + iconSize*3);

        // draw bird icon and text
        g.setColor(Color.BLACK);
        g.drawString(birdText, iconX + iconSize*3, iconY + iconSize*3);


        // draw boss icon and text
        g.setColor(Color.BLACK);
        g.drawString(bossText, iconX + iconSize*8, iconY + iconSize*3);

    }
}

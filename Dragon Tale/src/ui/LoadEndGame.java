package ui;

import tilemap.Background;

import java.io.File;
import java.util.Objects;

public class LoadEndGame {
    public static String ENDGAME = "GameOverBG";

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
}

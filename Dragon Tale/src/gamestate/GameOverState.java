package gamestate;

import audio.AudioPlayer;
import entities.player.Player;
import ui.LoadBackground;
import ui.LoadEndGame;
import ui.LoadEntities;
import tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class GameOverState extends GameState {

    private Background bg;
    private Color titleColor;
    private Font titleFont;
    private Font font;


    public GameOverState(GameStateManager gsm) {

        this.gsm = gsm;

        bg = LoadEndGame.loadEndGame(LoadEndGame.ENDGAME);

        titleColor = new Color(128, 0, 0);
        titleFont = new Font("Century Gothic",
                Font.PLAIN,
                28);
        font = new Font("Arial", Font.PLAIN, 10);


        }



    @Override
    public void init() {
        // TODO Auto-generated method stub
        /*// Start playing the audio
        audioPlayer.play();*/

    }


    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        // draw bg
        bg.draw(g);
        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);

        g.drawString("Game Over", 80, 70);

        // draw number of coins collected
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Your Scores: " + LoadEndGame.coinsCollected, 110, 90);

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


    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            // Go back to the main menu when Enter is pressed
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }


    @Override
    public void keyReleased(int k) {
        // TODO Auto-generated method stub

    }
}
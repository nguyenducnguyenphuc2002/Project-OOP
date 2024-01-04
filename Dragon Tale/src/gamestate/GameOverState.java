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



    public GameOverState(GameStateManager gsm) {

        this.gsm = gsm;

        bg = LoadEndGame.loadEndGame(LoadEndGame.ENDGAME);

        titleColor = new Color(128, 0, 0);
        titleFont = new Font("Century Gothic",
                Font.PLAIN,
                28);
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

        g.drawString("Game Over", 80, 55);

        LoadEndGame.LoadInfo(g);
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
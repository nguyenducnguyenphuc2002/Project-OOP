package gamestate;

import audio.AudioPlayer;
import storage.LoadEntities;
import tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class GameOverState extends GameState{

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {"Return to menu", "Audio Toggle"};

    private Color titleColor;
    private Font titleFont;

    private Font font;

    private AudioPlayer audioPlayer;
    private boolean isAudioPlaying;


    private int coinsCollected;
    private int totalMonstersKilled;
    private int sluggerKills;
    private int arachnikKills;
    private int monkeyKills;
    private SharedData sharedData;


    // Setter methods for the kill counters
    public void setCoinsCollected(int coinsCollected) {
        this.coinsCollected = coinsCollected;
    }
    public void setTotalMonstersKilled(int totalMonstersKilled) {
        this.totalMonstersKilled = totalMonstersKilled;
    }

    public void setSluggerKills(int sluggerKills) {
        this.sluggerKills = sluggerKills;
    }

    public void setArachnikKills(int arachnikKills) {
        this.arachnikKills = arachnikKills;
    }

    public void setMonkeyKills(int monkeyKills) {
        this.monkeyKills = monkeyKills;
    }

    public GameOverState(GameStateManager gsm,SharedData sharedData) {

        this.gsm = gsm;
        this.sharedData = sharedData;
        try {
            bg = new Background(new File("resources/backgrounds/menubg.gif"), 1);
            bg.setVector(-0.1, 0);

            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic",
                    Font.PLAIN,
                    28);
            font = new Font("Arial", Font.PLAIN, 12);

            // add audio player button
            audioPlayer = new AudioPlayer("resources/music/level1-1.wav");

        } catch(Exception e) {
            e.printStackTrace();
        }
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

        drawMenuStats(g);
    }

    public void drawMenuStats(Graphics2D g){
        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Game Over", 80, 70);

        // draw return to menu option + stats
        g.setFont(font);
        for (int i = 0; i < options.length; i++){
            if (i == currentChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 120, 190 + i * 15);
        }


        // set value from sharedData
        setCoinsCollected(sharedData.getCoinsCollected());
        setArachnikKills(sharedData.getArachnikKills());
        setMonkeyKills(sharedData.getMonkeyKills());
        setSluggerKills(sharedData.getSluggerKills());
        setTotalMonstersKilled(sharedData.getTotalMonstersKilled());

        // draw number of coins collected
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Coins Collected: " + coinsCollected, 120, 95);

        // draw number of monsters killed by type
        String sluggerText = "x: " + sluggerKills;
        String arachnikText = "x: " + arachnikKills;
        String monkeyText = "x: " + monkeyKills;
        String totalMonstersKilledText = "Total Monsters Killed: " + totalMonstersKilled;

        int iconSize = 20;
        int iconX = 120;
        int iconY = 100;

        // draw slugger icon and text
        g.setColor(Color.BLACK);
        g.drawString(sluggerText, iconX + iconSize + 5, iconY + iconSize - 5);
        g.drawImage(Content.Slugger[0][0], iconX, iconY + iconSize * 0 - 5, iconSize, iconSize, null);

        // draw arachnik icon and text
        g.setColor(Color.BLACK);
        g.drawString(arachnikText, iconX + iconSize + 5, iconY + 2 * iconSize - 5);
        g.drawImage(Content.Arachnik[0][0], iconX, iconY + iconSize * 1 - 5, iconSize, iconSize, null);

        // draw monkey icon and text
        g.setColor(Color.BLACK);
        g.drawString(monkeyText, iconX + iconSize + 5, iconY + 3 * iconSize - 5);
        g.drawImage(Content.HatMonkey[0][0], iconX, iconY + 2 * iconSize - 5, iconSize, iconSize, null);

        // show total kill
        g.setColor(Color.BLACK);
        g.drawString(totalMonstersKilledText, iconX , iconY + 4 * iconSize - 5);
    }
    private void select() {
        if(currentChoice == 0) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (currentChoice == 1){
            if(isAudioPlaying){
                audioPlayer.stop();
            }
            else{
                audioPlayer.play();
            }
            isAudioPlaying = !isAudioPlaying;
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }

    }


    @Override
    public void keyReleased(int k) {
        // TODO Auto-generated method stub

    }

}

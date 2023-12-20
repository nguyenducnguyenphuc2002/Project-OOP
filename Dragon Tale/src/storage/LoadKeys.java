package storage;

import audio.AudioPlayer;
import entities.player.Player;
<<<<<<< HEAD
=======
import gamestate.GameState;
import gamestate.Level1State;
>>>>>>> NguyenPhuc
import gamestate.MenuState;

import java.awt.event.KeyEvent;

public class LoadKeys {
    public static boolean PRESSED = true;

    public static boolean RELEASED = false;
    private static boolean turnOnMusic = true;
    private static AudioPlayer bgMusic;



    public static void ControllingPlayer(int k, Player player, boolean check){
        if(k == KeyEvent.VK_LEFT) player.setLeft(check);
        if(k == KeyEvent.VK_RIGHT) player.setRight(check);
        if(k == KeyEvent.VK_UP) player.setUp(check);
        if(k == KeyEvent.VK_DOWN) player.setDown(check);
        if(k == KeyEvent.VK_W) player.setJumping(check);
        if(k == KeyEvent.VK_E) player.setGliding(check);
        if (check == PRESSED) {
            if(k == KeyEvent.VK_R) player.setScratching();
            if(k == KeyEvent.VK_F) player.setFiring();
        }
    }

    public static void ChooseOptions(int k, MenuState menuState){
        bgMusic = LoadAudio.loadbgMusic(LoadAudio.audio);
        if(k == KeyEvent.VK_ENTER){
            menuState.select();
        }
        if(k == KeyEvent.VK_UP) {
            menuState.setCurrentChoice(menuState.getCurrentChoice() - 1);
            if(menuState.getCurrentChoice() == - 1) {
                menuState.setCurrentChoice(menuState.getOptions().length - 1);
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            menuState.setCurrentChoice(menuState.getCurrentChoice() + 1);
            if(menuState.getCurrentChoice() == menuState.getOptions().length) {
                menuState.setCurrentChoice(0);
            }
        }
    }

    public static void ControllingMusic(int k){
        if (k == KeyEvent.VK_M) {

            if (turnOnMusic) {
                System.out.println(1);
                bgMusic.play();
                turnOnMusic = !turnOnMusic;
            }
            else {
                System.out.println(2);
                bgMusic.stop();
                turnOnMusic = !turnOnMusic;
            }
        }

    }


}



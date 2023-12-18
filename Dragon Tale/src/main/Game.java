package main;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Game {
    public Game() {
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Dragon Tale");
        window.setContentPane(new GamePanel());
        window.setIconImage(new ImageIcon("D:/Assignments/Project-OOP/Disanour_Tale/src/resources/sprites/player/iconplayer.gif").getImage());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);

    }
}

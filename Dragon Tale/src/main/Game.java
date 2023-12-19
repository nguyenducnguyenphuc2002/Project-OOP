package main;

import storage.LoadEntities;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Game {
    public Game() {
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Dragon Tale");
        window.setContentPane(new GamePanel());
        window.setIconImage(LoadEntities.LoadIcon(LoadEntities.ICON));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);

    }
}

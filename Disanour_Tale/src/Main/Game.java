package Main;

import javax.swing.JFrame;

public class Game {
    public Game() {
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Dragon Tale");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}

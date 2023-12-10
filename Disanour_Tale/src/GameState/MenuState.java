package GameState;

import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

public class MenuState extends GameState {
    private Background bg;
    private int currentChoice = 0;
    private final String[] options = new String[]{"Start", "Help", "Quit"};
    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
<<<<<<< HEAD
            this.bg = new Background(new File("D:/Assignments/ProjectOOP/Disanour_Tale/src/Resources/Backgrounds/menubg.gif"), 1.0);
=======
            this.bg = new Background(new File("/Assignments/ProjectOOP/Disanour_Tale/src/Resources/Backgrounds/menubg.gif"), 1.0);
>>>>>>> 8fc2bba (update)
            this.bg.setVector(-0.1, 0.0);
            this.titleColor = new Color(128, 0, 0);
            this.titleFont = new Font("Century Gothic", 0, 28);
            this.font = new Font("Arial", 0, 12);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void init() {
    }

    public void update() {
        this.bg.update();
    }

    public void draw(Graphics2D g) {
        this.bg.draw(g);
        g.setColor(this.titleColor);
        g.setFont(this.titleFont);
        g.drawString("Dragon Tale", 80, 70);
        g.setFont(this.font);

        for(int i = 0; i < this.options.length; ++i) {
            if (i == this.currentChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }

            g.drawString(this.options[i], 145, 140 + i * 15);
        }

    }

    private void select() {
        if(currentChoice == 0) {
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
        if(currentChoice == 1) {
            // help
        }
        if(currentChoice == 2) {
            System.exit(0);
        }
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
            select();
        }
        if(k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }
    public void keyReleased(int k) {}

}
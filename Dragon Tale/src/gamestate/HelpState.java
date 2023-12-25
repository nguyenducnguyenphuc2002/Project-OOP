//package gamestate;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
//import java.io.File;
//
//import tilemap.Background;
//
//public class HelpState extends GameState {
//
//    private Background bg;
//
//    private String[] helpContent = {
//            "Welcome to Dragon Tale!",
//            "",
//            "Controls:",
//            "  Arrow Keys - Move",
//            "  Space - Jump",
//            "  S - Attack",
//            "  Q - Quit",
//            "Objective:",
//            "  Collect coins and defeat enemies",
//            "  to reach the end of each level.",
//            "",
//            "Press Enter to go back to the main menu."
//    };
//
//    private Color titleColor;
//    private Font titleFont;
//    private Color textColor;
//    private Font textFont;
//
//    public HelpState(GameStateManager gsm) {
//        this.gsm = gsm;
//        try {
//            bg = new Background(new File("resources/backgrounds/pixelbackgroundnight.png"),1);
//            //bg.setVector(0, 0);
//
//            titleColor = new Color(200, 50, 50);
//            titleFont = new Font("Arial", Font.BOLD, 18);
//
//            textColor = new Color(255, 255, 255);
//            textFont = new Font("Arial", Font.PLAIN, 10);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void init() {
//        // Initialize help state if needed
//    }
//
//    @Override
//    public void update() {
//        bg.update();
//    }
//
//    @Override
//    public void draw(Graphics2D g) {
//        // Draw background
//        bg.draw(g);
//
//        // Draw title
//        g.setColor(titleColor);
//        g.setFont(titleFont);
//        String title = "Help";
//        g.drawString(title, (320 - g.getFontMetrics().stringWidth(title)) / 2, 30);
//
//        // Draw help content
//        g.setColor(textColor);
//        g.setFont(textFont);
//        int lineHeight = 15;
//        int startY = 70;
//
//        for (int i = 0; i < helpContent.length; i++) {
//            g.drawString(helpContent[i], 20, startY + i * lineHeight);
//        }
//    }
//
//    @Override
//    public void keyPressed(int k) {
//        if (k == KeyEvent.VK_ENTER) {
//            // Go back to the main menu when Enter is pressed
//            gsm.setState(GameStateManager.MENUSTATE);
//        }
//    }
//
//    @Override
//    public void keyReleased(int k) {
//        // Handle key releases if needed
//    }
//}
//
package gamestate;

import tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class HelpState extends GameState {

    private Background bg;
    private Color titleColor;
    private Font titleFont;
    private Color textColor;
    private Font textFont;

    public HelpState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background(new File("resources/backgrounds/helpstate (1).png"), 1);

            titleColor = new Color(200, 50, 50);
            titleFont = new Font("Arial", Font.BOLD, 18);

            textColor = new Color(255, 255, 255);
            textFont = new Font("Arial", Font.PLAIN, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        // Initialize help state if needed
    }

    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw background
        bg.draw(g);

        // Draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        String title = "Welcome to Dragon Tale";
        int titleX = (320 - g.getFontMetrics().stringWidth(title)) / 2;
        int titleY = 20;
        g.drawString(title, titleX, titleY);


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
        // Handle key releases if needed
    }
}

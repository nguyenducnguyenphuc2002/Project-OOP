package main;

import gamestate.GameStateManager;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;
    private Thread thread;
    private boolean running;
    private final long targetTime;
    private BufferedImage image;
    private Graphics2D g;
    private GameStateManager gsm;

    public GamePanel() {
        int FPS = 60;
        targetTime = (long)(1000 / FPS);
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }

    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D)image.getGraphics();
        running = true;
        gsm = new GameStateManager();
    }

    public void run() {
        init();

        while(running) {
            long start = System.nanoTime();
            update();
            draw();
            drawToScreen();
            long elapsed = System.nanoTime() - start;
            long wait = targetTime - elapsed / 1000000L;
            if (wait < 0L) {
                wait = 5L;
            }

            try {
                Thread.sleep(wait);
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }

    }

    private void drawToScreen() {
        Graphics2D g2 = (Graphics2D)getGraphics();
        g2.drawImage(image, 0, 0, 960, 720, (ImageObserver)null);
        g2.dispose();
    }

    private void draw() {
        gsm.draw(g);
    }

    private void update() {
        gsm.update();
    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }
}

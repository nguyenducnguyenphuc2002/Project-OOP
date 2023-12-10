package Main;

import GameState.GameStateManager;
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
        this.targetTime = (long)(1000 / FPS);
        this.setPreferredSize(new Dimension(960, 720));
        this.setFocusable(true);
        this.requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (this.thread == null) {
            this.thread = new Thread(this);
            this.addKeyListener(this);
            this.thread.start();
        }

    }

    private void init() {
        this.image = new BufferedImage(320, 240, 1);
        this.g = (Graphics2D)this.image.getGraphics();
        this.running = true;
        this.gsm = new GameStateManager();
    }

    public void run() {
        this.init();

        while(this.running) {
            long start = System.nanoTime();
            this.update();
            this.draw();
            this.drawToScreen();
            long elapsed = System.nanoTime() - start;
            long wait = this.targetTime - elapsed / 1000000L;
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
        Graphics2D g2 = (Graphics2D)this.getGraphics();
        g2.drawImage(this.image, 0, 0, 960, 720, (ImageObserver)null);
        g2.dispose();
    }

    private void draw() {
        this.gsm.draw(this.g);
    }

    private void update() {
        this.gsm.update();
    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        this.gsm.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key) {
        this.gsm.keyReleased(key.getKeyCode());
    }
}

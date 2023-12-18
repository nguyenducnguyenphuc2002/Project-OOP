package objects;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean playedOnce = false;

    public Animation() {
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        this.currentFrame = 0;
        this.startTime = System.nanoTime();
        this.playedOnce = false;
    }

    public void setDelay(long d) {
        this.delay = d;
    }

    public void setFrame(int i) {
        this.currentFrame = i;
    }

    public void update() {
        if (this.delay != -1L) {
            long elapsed = (System.nanoTime() - this.startTime) / 1000000L;
            if (elapsed > this.delay) {
                ++this.currentFrame;
                this.startTime = System.nanoTime();
            }

            if (this.currentFrame == this.frames.length) {
                this.currentFrame = 0;
                this.playedOnce = true;
            }

        }
    }

    public int getFrame() {
        return this.currentFrame;
    }

    public BufferedImage getImage() {
        return this.frames[this.currentFrame];
    }

    public boolean hasPlayedOnce() {
        return this.playedOnce;
    }
}

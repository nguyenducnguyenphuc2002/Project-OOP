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
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }

    public void setDelay(long d) {
        delay = d;
    }

    public void setFrame(int i) {
        currentFrame = i;
    }

    public void update() {
        if (delay != -1L) {
            long elapsed = (System.nanoTime() - startTime) / 1000000L;
            if (elapsed > delay) {
                ++currentFrame;
                startTime = System.nanoTime();
            }

            if (currentFrame == frames.length) {
                currentFrame = 0;
                playedOnce = true;
            }

        }
    }
    

    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }
}

package tilemap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;

public class Background {
    private BufferedImage image;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double moveScale;

    public Background(File file, double ms) {
        try {
            image = ImageIO.read(file);
            moveScale = ms;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void setPosition(double x, double y) {
        this.x = x * moveScale % 320.0;
        this.y = y * moveScale % 240.0;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, (ImageObserver)null);
        if (x < 0.0) {
            g.drawImage(image, (int)x + 320, (int)y, (ImageObserver)null);
        }

        if (x > 0.0) {
            g.drawImage(image, (int)x - 320, (int)y, (ImageObserver)null);
        }

    }
}

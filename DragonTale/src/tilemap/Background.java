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
            this.image = ImageIO.read(file);
            this.moveScale = ms;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void setPosition(double x, double y) {
        this.x = x * this.moveScale % 320.0;
        this.y = y * this.moveScale % 240.0;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        this.x += this.dx;
        this.y += this.dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.image, (int)this.x, (int)this.y, (ImageObserver)null);
        if (this.x < 0.0) {
            g.drawImage(this.image, (int)this.x + 320, (int)this.y, (ImageObserver)null);
        }

        if (this.x > 0.0) {
            g.drawImage(this.image, (int)this.x - 320, (int)this.y, (ImageObserver)null);
        }

    }
}

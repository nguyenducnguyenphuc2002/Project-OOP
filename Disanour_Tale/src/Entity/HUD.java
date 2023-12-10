package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;

public class HUD {
    private Player player;
    private BufferedImage image;
    private Font font;

    public HUD(Player p) {
        this.player = p;

        try {
            this.image = ImageIO.read(new File("D:/Assignments/ProjectOOP/Disanour_Tale/src/Resources/HUD/hud.gif"));
            this.font = new Font("Arial", 0, 14);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void draw(Graphics2D g) {
        g.drawImage(this.image, 0, 10, (ImageObserver)null);
        g.setFont(this.font);
        g.setColor(Color.WHITE);
        g.drawString(this.player.getHealth() + "/" + this.player.getMaxHealth(), 30, 25);
        int var10001 = this.player.getFire() / 100;
        g.drawString("" + var10001 + "/" + this.player.getMaxFire() / 100, 30, 45);
    }
}

package objects;

import entities.player.Player;
import storage.LoadHUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class HUD {
    private Player player;
    private BufferedImage hudLeft;
    private BufferedImage hudRight;
    private Font font;

    public HUD(Player p) {
        this.player = p;

        this.hudLeft = LoadHUD.loadImage(LoadHUD.HUDLEFT);
        this.hudRight = LoadHUD.loadImage(LoadHUD.HUDRIGHT);;
        this.font = new Font("Arial", 0, 14);

    }

    public void draw(Graphics2D g) {
        g.drawImage(this.hudLeft, 0, 10, (ImageObserver)null);
        g.drawImage(this.hudRight, 246, 10, null);
        g.setFont(this.font);
        g.setColor(Color.YELLOW);
        g.drawString((int) this.player.getHealth() + "/" + (int) this.player.getMaxHealth(), 30, 25);
        int var10001 = this.player.getFire() / 100;
        g.drawString("" + var10001 + "/" + this.player.getMaxFire() / 100, 30, 45);
        g.drawString(String.valueOf(player.getCoinAmount()), 260, 25);
        g.drawString(String.valueOf(player.getScore()), 260, 45);
    }
}

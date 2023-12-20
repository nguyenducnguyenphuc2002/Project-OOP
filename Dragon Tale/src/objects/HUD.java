package objects;

import entities.player.Player;
import storage.LoadHUD;

<<<<<<< HEAD
import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
	
	private Player player;

	private BufferedImage hudLeft;
	private BufferedImage hudRight;

	private Font font;
	
	public HUD(Player p) {
		player = p;


		hudLeft = LoadHUD.loadImage(LoadHUD.HUDLEFT);
		hudRight = LoadHUD.loadImage(LoadHUD.HUDRIGHT);;
		font = new Font("Arial", 0, 14);


	}
	
	public void draw(Graphics2D g) {
		g.drawImage(hudLeft, 0, 10, null);
		g.drawImage(hudRight, 246, 10, null);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString((int) player.getHealth() + "/" + (int) player.getMaxHealth(), 30, 25);
		int var10001 = player.getFire() / 100;
		g.drawString("" + var10001 + "/" + player.getMaxFire() / 100, 30, 45);
		g.drawString(String.valueOf(player.getCoinAmount()), 260, 25);
		g.drawString(String.valueOf(player.getScore()), 260, 45);

	}
	
}













=======
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
        player = p;

        hudLeft = LoadHUD.loadImage(LoadHUD.HUDLEFT);
        hudRight = LoadHUD.loadImage(LoadHUD.HUDRIGHT);;
        font = new Font("Arial", 0, 14);

    }

    public void draw(Graphics2D g) {
        g.drawImage(hudLeft, 0, 10, (ImageObserver)null);
        g.drawImage(hudRight, 246, 10, null);
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString((int) player.getHealth() + "/" + (int) player.getMaxHealth(), 30, 25);
        int var10001 = player.getFire() / 100;
        g.drawString("" + var10001 + "/" + player.getMaxFire() / 100, 30, 45);
        g.drawString(String.valueOf(player.getCoinAmount()), 260, 25);
        g.drawString(String.valueOf(player.getScore()), 260, 45);
    }
}
>>>>>>> NguyenPhuc

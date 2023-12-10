package Entity;

import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FireBall extends MapObject {
    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;

    public FireBall(TileMap tm, boolean right) {
        super(tm);
        this.facingRight = right;
        this.moveSpeed = 3.8;
        if (right) {
            this.dx = this.moveSpeed;
        } else {
            this.dx = -this.moveSpeed;
        }

        this.width = 30;
        this.height = 30;
        this.cwidth = 14;
        this.cheight = 14;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("D:/Assignments/ProjectOOP/Disanour_Tale/src/Resources/Sprites/Player/fireball.gif"));
            this.sprites = new BufferedImage[4];

            int i;
            for(i = 0; i < this.sprites.length; ++i) {
                this.sprites[i] = spritesheet.getSubimage(i * this.width, 0, this.width, this.height);
            }

            this.hitSprites = new BufferedImage[3];

            for(i = 0; i < this.hitSprites.length; ++i) {
                this.hitSprites[i] = spritesheet.getSubimage(i * this.width, this.height, this.width, this.height);
            }

            this.animation = new Animation();
            this.animation.setFrames(this.sprites);
            this.animation.setDelay(70L);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void setHit() {
        if (!this.hit) {
            this.hit = true;
            this.animation.setFrames(this.hitSprites);
            this.animation.setDelay(70L);
            this.dx = 0.0;
        }
    }

    public boolean shouldRemove() {
        return this.remove;
    }

    public void update() {
        this.checkTileMapCollision();
        this.setPosition(this.xtemp, this.ytemp);
        if (this.dx == 0.0 && !this.hit) {
            this.setHit();
        }

        this.animation.update();
        if (this.hit && this.animation.hasPlayedOnce()) {
            this.remove = true;
        }

    }

    public void draw(Graphics2D g) {
        this.setMapPosition();
        super.draw(g);
    }
}

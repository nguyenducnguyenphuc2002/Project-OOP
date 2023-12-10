package Entity;

import Audio.AudioPlayer;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Player extends MapObject {
    private int fire;
    private int maxFire;
    private boolean flinching;
    private long flinchTimer;
    private boolean firing;
    private int fireCost;
    private int fireBallDamage;
    private ArrayList<FireBall> fireBalls;
    private boolean scratching;
    private int scratchDamage;
    private int scratchRange;
    private boolean gliding;
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = new int[]{2, 8, 1, 2, 4, 2, 5};
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIREBALL = 5;
    private static final int SCRATCHING = 6;
    private HashMap<String, AudioPlayer> sfx;

    public Player(TileMap tm) {
        super(tm);
        this.width = 30;
        this.height = 30;
        this.cwidth = 20;
        this.cheight = 20;
        this.moveSpeed = 0.3;
        this.maxSpeed = 1.6;
        this.stopSpeed = 0.4;
        this.fallSpeed = 0.15;
        this.maxFallSpeed = 4.0;
        this.jumpStart = -4.8;
        this.stopJumpSpeed = 0.3;
        this.facingRight = true;
        this.health = this.maxHealth = 5;
        this.fire = this.maxFire = 2500;
        this.fireCost = 200;
        this.fireBallDamage = 5;
        this.fireBalls = new ArrayList();
        this.scratchDamage = 8;
        this.scratchRange = 40;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("D:/Assignments/ProjectOOP/Disanour_Tale/src/Resources/Sprites/Player/playersprites.gif"));
            this.sprites = new ArrayList();

            for(int i = 0; i < 7; ++i) {
                BufferedImage[] bi = new BufferedImage[this.numFrames[i]];

                for(int j = 0; j < this.numFrames[i]; ++j) {
                    if (i != 6) {
                        bi[j] = spritesheet.getSubimage(j * this.width, i * this.height, this.width, this.height);
                    } else {
                        bi[j] = spritesheet.getSubimage(j * this.width * 2, i * this.height, this.width * 2, this.height);
                    }
                }

                this.sprites.add(bi);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        this.animation = new Animation();
        this.currentAction = 0;
        this.animation.setFrames((BufferedImage[])this.sprites.get(0));
        this.animation.setDelay(400L);
        this.sfx = new HashMap();
        this.sfx.put("jump", new AudioPlayer(new File("D:/Assignments/ProjectOOP/Disanour_Tale/src/Resources/SFX/jump.wav")));
        this.sfx.put("scratch", new AudioPlayer(new File("D:/Assignments/ProjectOOP/Disanour_Tale/src/Resources/SFX/scratch.wav")));
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getFire() {
        return this.fire;
    }

    public int getMaxFire() {
        return this.maxFire;
    }

    public void setFiring() {
        this.firing = true;
    }

    public void setScratching() {
        this.scratching = true;
    }

    public void setGliding(boolean b) {
        this.gliding = b;
    }

    public void checkAttack(ArrayList<Enemy> enemies) {
        for(int i = 0; i < enemies.size(); ++i) {
            Enemy e = (Enemy)enemies.get(i);
            if (this.scratching) {
                if (this.facingRight) {
                    if ((double)e.getx() > this.x && (double)e.getx() < this.x + (double)this.scratchRange && (double)e.gety() > this.y - (double)(this.height / 2) && (double)e.gety() < this.y + (double)(this.height / 2)) {
                        e.hit(this.scratchDamage);
                    }
                } else if ((double)e.getx() < this.x && (double)e.getx() > this.x - (double)this.scratchRange && (double)e.gety() > this.y - (double)(this.height / 2) && (double)e.gety() < this.y + (double)(this.height / 2)) {
                    e.hit(this.scratchDamage);
                }
            }

            for(int j = 0; j < this.fireBalls.size(); ++j) {
                if (((FireBall)this.fireBalls.get(j)).intersects(e)) {
                    e.hit(this.fireBallDamage);
                    ((FireBall)this.fireBalls.get(j)).setHit();
                    break;
                }
            }

            if (this.intersects(e)) {
                this.hit(e.getDamage());
            }
        }

    }

    public void hit(int damage) {
        if (!this.flinching) {
            this.health -= damage;
            if (this.health < 0) {
                this.health = 0;
            }

            if (this.health == 0) {
                this.dead = true;
            }

            this.flinching = true;
            this.flinchTimer = System.nanoTime();
        }
    }

    private void getNextPosition() {
        if (this.left) {
            this.dx -= this.moveSpeed;
            if (this.dx < -this.maxSpeed) {
                this.dx = -this.maxSpeed;
            }
        } else if (this.right) {
            this.dx += this.moveSpeed;
            if (this.dx > this.maxSpeed) {
                this.dx = this.maxSpeed;
            }
        } else if (this.dx > 0.0) {
            this.dx -= this.stopSpeed;
            if (this.dx < 0.0) {
                this.dx = 0.0;
            }
        } else if (this.dx < 0.0) {
            this.dx += this.stopSpeed;
            if (this.dx > 0.0) {
                this.dx = 0.0;
            }
        }

        if ((this.currentAction == 6 || this.currentAction == 5) && !this.jumping && !this.falling) {
            this.dx = 0.0;
        }

        if (this.jumping && !this.falling) {
            ((AudioPlayer)this.sfx.get("jump")).play();
            this.dy = this.jumpStart;
            this.falling = true;
        }

        if (this.falling) {
            if (this.dy > 0.0 && this.gliding) {
                this.dy += this.fallSpeed * 0.1;
            } else {
                this.dy += this.fallSpeed;
            }

            if (this.dy > 0.0) {
                this.jumping = false;
            }

            if (this.dy < 0.0 && !this.jumping) {
                this.dy += this.stopJumpSpeed;
            }

            if (this.dy > this.maxFallSpeed) {
                this.dy = this.maxFallSpeed;
            }
        }

    }

    public void update() {
        this.getNextPosition();
        this.checkTileMapCollision();
        this.setPosition(this.xtemp, this.ytemp);
        if (this.currentAction == 6 && this.animation.hasPlayedOnce()) {
            this.scratching = false;
        }

        if (this.currentAction == 5 && this.animation.hasPlayedOnce()) {
            this.firing = false;
        }

        ++this.fire;
        if (this.fire > this.maxFire) {
            this.fire = this.maxFire;
        }

        if (this.firing && this.currentAction != 5 && this.fire > this.fireCost) {
            this.fire -= this.fireCost;
            FireBall fb = new FireBall(this.tileMap, this.facingRight);
            fb.setPosition(this.x, this.y);
            this.fireBalls.add(fb);
        }

        for(int i = 0; i < this.fireBalls.size(); ++i) {
            ((FireBall)this.fireBalls.get(i)).update();
            if (((FireBall)this.fireBalls.get(i)).shouldRemove()) {
                this.fireBalls.remove(i);
                --i;
            }
        }

        if (this.flinching) {
            long elapsed = (System.nanoTime() - this.flinchTimer) / 1000000L;
            if (elapsed > 1000L) {
                this.flinching = false;
            }
        }

        if (this.scratching) {
            if (this.currentAction != 6) {
                ((AudioPlayer)this.sfx.get("scratch")).play();
                this.currentAction = 6;
                this.animation.setFrames((BufferedImage[])this.sprites.get(6));
                this.animation.setDelay(50L);
                this.width = 60;
            }
        } else if (this.firing) {
            if (this.currentAction != 5) {
                this.currentAction = 5;
                this.animation.setFrames((BufferedImage[])this.sprites.get(5));
                this.animation.setDelay(100L);
                this.width = 30;
            }
        } else if (this.dy > 0.0) {
            if (this.gliding) {
                if (this.currentAction != 4) {
                    this.currentAction = 4;
                    this.animation.setFrames((BufferedImage[])this.sprites.get(4));
                    this.animation.setDelay(100L);
                    this.width = 30;
                }
            } else if (this.currentAction != 3) {
                this.currentAction = 3;
                this.animation.setFrames((BufferedImage[])this.sprites.get(3));
                this.animation.setDelay(100L);
                this.width = 30;
            }
        } else if (this.dy < 0.0) {
            if (this.currentAction != 2) {
                this.currentAction = 2;
                this.animation.setFrames((BufferedImage[])this.sprites.get(2));
                this.animation.setDelay(-1L);
                this.width = 30;
            }
        } else if (!this.left && !this.right) {
            if (this.currentAction != 0) {
                this.currentAction = 0;
                this.animation.setFrames((BufferedImage[])this.sprites.get(0));
                this.animation.setDelay(400L);
                this.width = 30;
            }
        } else if (this.currentAction != 1) {
            this.currentAction = 1;
            this.animation.setFrames((BufferedImage[])this.sprites.get(1));
            this.animation.setDelay(40L);
            this.width = 30;
        }

        this.animation.update();
        if (this.currentAction != 6 && this.currentAction != 5) {
            if (this.right) {
                this.facingRight = true;
            }

            if (this.left) {
                this.facingRight = false;
            }
        }

    }

    public void draw(Graphics2D g) {
        this.setMapPosition();

        for(int i = 0; i < this.fireBalls.size(); ++i) {
            ((FireBall)this.fireBalls.get(i)).draw(g);
        }

        if (this.flinching) {
            long elapsed = (System.nanoTime() - this.flinchTimer) / 1000000L;
            if (elapsed / 100L % 2L == 0L) {
                return;
            }
        }

        super.draw(g);
    }
}

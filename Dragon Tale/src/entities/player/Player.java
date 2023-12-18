package entities.player;

import audio.AudioPlayer;
import entities.ExplosionFireVenom;
import entities.enemies.Enemy;
import objects.Animation;
import objects.MapObject;
import storage.LoadAudio;
import storage.LoadEntities;
import tilemap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends MapObject {

    private int coinAmount;
    private int score;
    private int fire;
    private final int maxFire;
    private boolean flinching;
    private long flinchTimer;
    private boolean firing;
    private final int fireCost;
    private final int fireBallDamage;
    private boolean fireKill;
    private final ArrayList<FireBall> fireBalls;
    private boolean scratching;
    private final int scratchDamage;
    private final int scratchRange;
    private boolean killScratching;
    private boolean gliding;
    private final ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = new int[]{2, 8, 1, 2, 4, 2, 5};
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIREBALL = 5;
    private static final int SCRATCHING = 6;
    private final HashMap<String, AudioPlayer> sfx;

    private final ArrayList<ExplosionFireVenom> explosionsFireVenom;


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
        this.health = this.maxHealth = 10;
        this.fire = this.maxFire = 2500;
        this.fireCost = 200;
        this.fireBallDamage = 5;
        this.fireBalls = new ArrayList<>();
        this.scratchDamage = 8;
        this.scratchRange = 40;
        this.explosionsFireVenom = new ArrayList<>();

        this.sprites = LoadEntities.loadMatrix(LoadEntities.PLAYER, this.numFrames, this.width, this.height);
        this.animation = new Animation();
        this.currentAction = IDLE;
        this.animation.setFrames(this.sprites.get(IDLE));
        this.animation.setDelay(400L);

        this.sfx = new HashMap<>();
        this.sfx.put(LoadAudio.JUMP, LoadAudio.loadKill(LoadAudio.JUMP));
        this.sfx.put(LoadAudio.SCRATCH, LoadAudio.loadKill(LoadAudio.SCRATCH));
    }


    public double getHealth() {
        return this.health;
    }

    public double getMaxHealth() {
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
        for (Enemy e : enemies) {
            if (this.scratching) {
                if (this.facingRight) {
                    if ((double) e.getx() > this.x &&
                            (double) e.getx() < this.x + (double) this.scratchRange &&
                            (double) e.gety() > this.y - (double) (this.height / 2) &&
                            (double) e.gety() < this.y + (double) (this.height / 2))
                    {
                        e.hit(this.scratchDamage);
                    }
                } else if ((double) e.getx() < this.x &&
                        (double) e.getx() > this.x - (double) this.scratchRange &&
                        (double) e.gety() > this.y - (double) (this.height / 2) &&
                        (double) e.gety() < this.y + (double) (this.height / 2))
                {
                    if ((double) e.getx() > this.x &&
                            (double) e.getx() < this.x + (double) this.scratchRange &&
                            (double) e.gety() > this.y - (double) (this.height / 2) &&
                            (double) e.gety() < this.y + (double) (this.height / 2))
                    {
                        e.hit(this.scratchDamage);
                    }
                } else if ((double) e.getx() < this.x &&
                        (double) e.getx() > this.x - (double) this.scratchRange &&
                        (double) e.gety() > this.y - (double) (this.height / 2) &&
                        (double) e.gety() < this.y + (double) (this.height / 2))
                {
                    e.hit(this.scratchDamage);
                }
                if (e.isDead())
                {
                    killScratching = true;
                }
            }


            for (int j = 0; j < this.fireBalls.size(); j++) {
                if (this.fireBalls.get(j).intersects(e)) {
                    e.hit(this.fireBallDamage);
                    this.fireBalls.get(j).setHit();
                    break;
                } else if (e.checkAttack(this) != -1) {
                    if (this.fireBalls.get(j).intersects(e.getVenoms().get(e.checkAttack(this)))) {
                        this.explosionsFireVenom.add(new ExplosionFireVenom(
                                this.fireBalls.get(j).getx(),
                                this.fireBalls.get(j).gety()));
                        this.fireBalls.remove(this.fireBalls.get(j));
                        e.getVenoms().remove(e.getVenoms().get(e.checkAttack(this)));
                        break;
                    }
                }

            }

            if (e.intersects(this)) {
                this.hit(e.getDamage());
            }

            if (e.isDead()) {
                if (e.getIndex() == Enemy.BIRD ||
                    e.getIndex() == Enemy.MUSHROOM ||
                    e.getIndex() == Enemy.HATMONKEY ||
                    e.getIndex() == Enemy.HATMONKEY) {
                    coinAmount++;
                    score += 200;
                } else if (e.getIndex() == Enemy.BOSS) {
                    coinAmount++;
                    score += 500;
                }
            }
        }


    }

    public int getCoinAmount() {return coinAmount;}


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

        if ((this.currentAction == SCRATCHING || this.currentAction == FIREBALL) &&
                !this.jumping && !this.falling) {
            this.dx = 0.0;
        }

        if (this.jumping && !this.falling) {
            this.sfx.get(LoadAudio.JUMP).play();
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
        if (this.currentAction == SCRATCHING && this.animation.hasPlayedOnce()) {
            this.scratching = false;
        }

        if (this.currentAction == FIREBALL && this.animation.hasPlayedOnce()) {
            this.firing = false;
        }

        ++this.fire;
        if (this.fire > this.maxFire) {
            this.fire = this.maxFire;
        }

        if (this.firing && this.currentAction != FIREBALL && this.fire > this.fireCost) {
            this.fire -= this.fireCost;
            FireBall fb = new FireBall(this.tileMap, this.facingRight);
            fb.setPosition(this.x, this.y);
            this.fireBalls.add(fb);
        }

        for(int i = 0; i < this.fireBalls.size(); ++i) {
            this.fireBalls.get(i).update();
            if (this.fireBalls.get(i).shouldRemove()) {
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
            if (this.currentAction != SCRATCHING) {
                this.sfx.get(LoadAudio.SCRATCH).play();
                this.currentAction = SCRATCHING;
                this.animation.setFrames(this.sprites.get(SCRATCHING));
                this.animation.setDelay(50L);
                this.width = 60;
            }
        } else if (this.firing) {
            if (this.currentAction != FIREBALL) {
                this.currentAction = FIREBALL;
                this.animation.setFrames(this.sprites.get(FIREBALL));
                this.animation.setDelay(100L);
                this.width = 30;
            }
        } else if (this.dy > 0.0) {
            if (this.gliding) {
                if (this.currentAction != GLIDING) {
                    this.currentAction = GLIDING;
                    this.animation.setFrames(this.sprites.get(GLIDING));
                    this.animation.setDelay(100L);
                    this.width = 30;
                }
            } else if (this.currentAction != FALLING) {
                this.currentAction = FIREBALL;
                this.animation.setFrames(this.sprites.get(FALLING));
                this.animation.setDelay(100L);
                this.width = 30;
            }
        } else if (this.dy < 0.0) {
            if (this.currentAction != JUMPING) {
                this.currentAction = JUMPING;
                this.animation.setFrames(this.sprites.get(JUMPING));
                this.animation.setDelay(-1L);
                this.width = 30;
            }
        } else if (!this.left && !this.right) {
            if (this.currentAction != IDLE) {
                this.currentAction = IDLE;
                this.animation.setFrames(this.sprites.get(IDLE));
                this.animation.setDelay(400L);
                this.width = 30;
            }
        } else if (this.currentAction != WALKING) {
            this.currentAction = WALKING;
            this.animation.setFrames(this.sprites.get(WALKING));
            this.animation.setDelay(40L);
            this.width = 30;
        }

        this.animation.update();
        if (this.currentAction != SCRATCHING && this.currentAction != FIREBALL) {
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

        this.setMapPosition(this.tileMap.getx(), this.tileMap.gety());

        for (FireBall fireBall : this.fireBalls) {
            fireBall.draw(g);
        }

        if (this.flinching) {
            long elapsed = (System.nanoTime() - this.flinchTimer) / 1000000L;
            if (elapsed / 100L % 2L == 0L) {
                return;
            }
        }

        super.draw(g);
    }

    public void checkCoins(ArrayList<Collectable> coins) {
        for (Collectable coin : coins) {
            if (this.intersects(coin)) {
                coinAmount++;
                score += 100;
            }
        }

    }

    public int getScore() {
        return score;
    }



    public ArrayList<ExplosionFireVenom> getExplosionsFireVenom() {
        return explosionsFireVenom;
    }

    public boolean isFireKill() {
        return fireKill;
    }


    public void setFireKill(boolean fireKill) {
        this.fireKill = fireKill;
    }

    public boolean isKillScratching() {
        return killScratching;
    }

    public void setKillScratching(boolean killScratching) {
        this.killScratching = killScratching;
    }
}

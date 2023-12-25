package entities.player;

import audio.AudioPlayer;
import entities.ExplosionFireVenom;
import entities.enemies.Enemy;
import entities.collectable.Collectable;
import objects.Animation;
import objects.MapObject;
import ui.LoadAudio;
import ui.LoadEntities;
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
	private final ArrayList<FireBall> fireBalls;
	private boolean scratching;
	private final int scratchDamage;
	private final int scratchRange;
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
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		facingRight = true;
		health = maxHealth = 10;
		fire = maxFire = 2500;
		fireCost = 200;
		fireBallDamage = 5;
		fireBalls = new ArrayList<>();
		scratchDamage = 8;
		scratchRange = 40;
		explosionsFireVenom = new ArrayList<>();

		sprites = LoadEntities.loadMatrix(LoadEntities.PLAYER, numFrames, width, height);
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400L);

		sfx = new HashMap<>();
		sfx.put(LoadAudio.JUMP, LoadAudio.loadKill(LoadAudio.JUMP));
		sfx.put(LoadAudio.SCRATCH, LoadAudio.loadKill(LoadAudio.SCRATCH));
	}


	public double getHealth() {
		return health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public int getFire() {
		return fire;
	}

	public int getMaxFire() {
		return maxFire;
	}

	public void setFiring() {
		firing = true;
	}

	public void setScratching() {
		scratching = true;
	}

	public void setGliding(boolean b) {
		gliding = b;
	}

	public void checkAttack(ArrayList<Enemy> enemies) {
		for (Enemy e : enemies) {
			if (scratching) {
				if (facingRight) {
					if ((double) e.getx() > x &&
							(double) e.getx() < x + (double) scratchRange &&
							(double) e.gety() > y - (double) (height / 2) &&
							(double) e.gety() < y + (double) (height / 2))
					{
						e.hit(scratchDamage);
					}
				} else if ((double) e.getx() < x &&
						(double) e.getx() > x - (double) scratchRange &&
						(double) e.gety() > y - (double) (height / 2) &&
						(double) e.gety() < y + (double) (height / 2))
				{
					if ((double) e.getx() > x &&
							(double) e.getx() < x + (double) scratchRange &&
							(double) e.gety() > y - (double) (height / 2) &&
							(double) e.gety() < y + (double) (height / 2))
					{
						e.hit(scratchDamage);
					}
				} else if ((double) e.getx() < x &&
						(double) e.getx() > x - (double) scratchRange &&
						(double) e.gety() > y - (double) (height / 2) &&
						(double) e.gety() < y + (double) (height / 2))
				{
					e.hit(scratchDamage);
				}

			}


			for (int j = 0; j < fireBalls.size(); j++) {
				if (fireBalls.get(j).intersects(e)) {
					e.hit(fireBallDamage);
					fireBalls.get(j).setHit();
					break;
				} else if (e.checkAttack(this) != -1) {
					if (fireBalls.get(j).intersects(e.getVenoms().get(e.checkAttack(this)))) {
						explosionsFireVenom.add(new ExplosionFireVenom(
								fireBalls.get(j).getx(),
								fireBalls.get(j).gety()));
						fireBalls.remove(fireBalls.get(j));
						e.getVenoms().remove(e.getVenoms().get(e.checkAttack(this)));
						break;
					}
				}

			}

			if (e.intersects(this)) {
				hit(e.getDamage());
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
		if (!flinching) {

			health -= damage;
			if (health < 0) {
				health = 0;
			}

			if (health == 0) {
				dead = true;
			}

			flinching = true;
			flinchTimer = System.nanoTime();
		}
	}





	private void getNextPosition() {
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else if (dx > 0.0) {
			dx -= stopSpeed;
			if (dx < 0.0) {
				dx = 0.0;
			}
		} else if (dx < 0.0) {
			dx += stopSpeed;
			if (dx > 0.0) {
				dx = 0.0;
			}
		}

		if ((currentAction == SCRATCHING || currentAction == FIREBALL) &&
				!jumping && !falling) {
			dx = 0.0;
		}

		if (jumping && !falling) {
			sfx.get(LoadAudio.JUMP).play();
			dy = jumpStart;
			falling = true;
		}

		if (falling) {
			if (dy > 0.0 && gliding) {
				dy += fallSpeed * 0.1;
			} else {
				dy += fallSpeed;
			}

			if (dy > 0.0) {
				jumping = false;
			}

			if (dy < 0.0 && !jumping) {
				dy += stopJumpSpeed;
			}

			if (dy > maxFallSpeed) {
				dy = maxFallSpeed;
			}
		}

	}

	public void update() {
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		if (currentAction == SCRATCHING && animation.hasPlayedOnce()) {
			scratching = false;
		}

		if (currentAction == FIREBALL && animation.hasPlayedOnce()) {
			firing = false;
		}

		++fire;
		if (fire > maxFire) {
			fire = maxFire;
		}

		if (firing && currentAction != FIREBALL && fire > fireCost) {
			fire -= fireCost;
			FireBall fb = new FireBall(tileMap, facingRight);
			fb.setPosition(x, y);
			fireBalls.add(fb);
		}

		for(int i = 0; i < fireBalls.size(); ++i) {
			fireBalls.get(i).update();
			if (fireBalls.get(i).shouldRemove()) {
				fireBalls.remove(i);
				--i;
			}
		}

		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000L;
			if (elapsed > 1000L) {
				flinching = false;
			}
		}

		if (scratching) {
			if (currentAction != SCRATCHING) {
				sfx.get(LoadAudio.SCRATCH).play();
				currentAction = SCRATCHING;
				animation.setFrames(sprites.get(SCRATCHING));
				animation.setDelay(50L);
				width = 60;
			}
		} else if (firing) {
			if (currentAction != FIREBALL) {
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100L);
				width = 30;
			}
		} else if (dy > 0.0) {
			if (gliding) {
				if (currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100L);
					width = 30;
				}
			} else if (currentAction != FALLING) {
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100L);
				width = 30;
			}
		} else if (dy < 0.0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1L);
				width = 30;
			}
		} else if (!left && !right) {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400L);
				width = 30;
			}
		} else if (currentAction != WALKING) {
			currentAction = WALKING;
			animation.setFrames(sprites.get(WALKING));
			animation.setDelay(40L);
			width = 30;
		}

		animation.update();
		if (currentAction != SCRATCHING && currentAction != FIREBALL) {
			if (right) {
				facingRight = true;
			}

			if (left) {
				facingRight = false;
			}
		}

	}

	public void draw(Graphics2D g) {

		setMapPosition();

		setMapPosition(tileMap.getx(), tileMap.gety());

		for (FireBall fireBall : fireBalls) {
			fireBall.draw(g);
		}

		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000L;
			if (elapsed / 100L % 2L == 0L) {
				return;
			}
		}

		super.draw(g);
	}

	public void checkCoins(ArrayList<Collectable> coins) {
		for (Collectable coin : coins) {
			if (intersects(coin)) {
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

}

package gamestate;

import entities.ExplosionFireVenom;
import entities.Teleport;
import entities.collectable.Collectable;
import entities.enemies.Enemy;
import entities.enemies.boss.Boss;
import entities.enemies.die.*;
import entities.enemies.minimonsters.*;
import entities.player.Player;
import objects.HUD;
import ui.LoadBackground;
import ui.LoadKeys;
import ui.LoadTileMap;
import tilemap.Background;
import tilemap.Tile;
import tilemap.TileMap;

import java.awt.*;
import java.util.ArrayList;

public class Level1State extends GameState {
	private ArrayList<Collectable> coins;
	private TileMap tileMap;
	private Background bg;
	private Player player;
	private ArrayList<Enemy> enemies;
	private ArrayList<DieEnemies> dieEnemies;

	private HUD hud;

	private ArrayList<Teleport> teleports;
	private boolean bossDefeated = false;
	public static int endScore;


	private SharedData sharedData;
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		this.sharedData = gsm.getSharedData();
		init();
	}

	public void init() {

		tileMap = new TileMap(Tile.TILESIZE);
		LoadTileMap.loadTileMap(tileMap, LoadTileMap.MAP, LoadTileMap.TILES);

		bg = LoadBackground.loadBackground(LoadBackground.LEVEL1STATE);

		player = new Player(tileMap);
		player.setPosition(100, 100);
//        player.setPosition(2600,150);

		dieEnemies = new ArrayList<>();
		populateEnemies();
		populateCollectables();



		hud = new HUD(player);

	}

	private void populateCollectables() {
		coins = new ArrayList<>();

		Point[] coinPoints = new Point[]{
				new Point(200, 200),
				new Point(560, 55),
				new Point(1020, 170),
				new Point(1440, 80),
		};
		for (Point coinPoint : coinPoints) {
			Collectable c = new Collectable(tileMap);
			c.setPosition(coinPoint.x, coinPoint.y);
			coins.add(c);
		}

	}

	private void populateEnemies() {
		enemies = new ArrayList<>();
		teleports = new ArrayList<>();
		Point[] points1 = new Point[]{
				new Point(200, 100),
				new Point(860, 200),
				new Point(960, 200),
				new Point(1550, 200)
		};
		for (Point value : points1) {
			Slugger s = new Slugger(tileMap);
			s.setPosition( value.x, value.y);
			enemies.add(s);
		}

		Point[] points2 = new Point[]{
				new Point(200, 100),
				new Point(860, 200),
				new Point(1550, 200),
				new Point(1680, 200),
				new Point(1800, 200)};

		for (Point value : points2) {
			HatMonkey h = new HatMonkey(tileMap);
			h.setPosition(value.x, value.y);
			enemies.add(h);
		}

		Point[] points3 = new Point[]{
				new Point(100, 50),
				new Point(860, 155),
				new Point(960, 100),
				new Point(2500, 70)
		};
		for (Point point : points3) {
			Bird b = new Bird(tileMap);
			b.setPosition(point.x, point.y);
			enemies.add(b);
		}

		Point[] points4 = new Point[]{
				new Point(1000, 200),
				new Point(1400, 65),
				new Point(1625, 200),
				new Point(2400, 200)
		};
		for (Point point : points4) {
			Arachnik a = new Arachnik(tileMap);
			a.setPosition(point.x, point.y);
			enemies.add(a);
		}

		Point points5 = new Point(3000,198);

		Boss boss = new Boss(tileMap);
		boss.setPosition(points5.x, points5.y);
		enemies.add(boss);

		//teleport
		Point point6 = new Point(3140, 190);

		Teleport teleport = new Teleport(tileMap);
		teleport.setPosition(point6.x, point6.y);
		teleports.add(teleport);

		Point[] points7 = new Point[]{
				new Point(400, 150),
				new Point(720, 150),
				new Point(1920, 112),
				new Point(2270, 150)
		};
		for (Point point : points7) {
			Hero hero = new Hero(tileMap);
			hero.setPosition(point.x, point.y);
			enemies.add(hero);
		}
	}

	public void update() {
		if (player.isDead()) {
			gsm.setState(0);
		}

		player.update();
		tileMap.setPosition(160.0 - (double) player.getx(), 120.0 - (double) player.gety());
		bg.setPosition( tileMap.getx(), tileMap.gety());
		player.checkAttack(enemies);


		for (Enemy enemy : enemies) {
			enemy.checkAttack(player);
		}

		for (int i = 0; i < enemies.size(); ++i) {
			Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				--i;
				dieEnemies.add(getDie(e));
				//check bossdie
				if (e instanceof Boss) {
					bossDefeated = true;
				}
			}
		}

		for (int i = 0; i < dieEnemies.size(); i++) {
			dieEnemies.get(i).update();
			if (dieEnemies.get(i).shouldRemove()) {
				dieEnemies.remove(i);
				i--;
			}
		}

		// update explosion fire and venom
		for (int i = 0; i < player.getExplosionsFireVenom().size(); ++i) {
			player.getExplosionsFireVenom().get(i).update();
			if ((player.getExplosionsFireVenom().get(i).shouldRemove())) {
				player.getExplosionsFireVenom().remove(i);
				i--;
			}
		}

		for (int j = 0; j < coins.size(); j++) {
			Collectable c = coins.get(j);
			c.update();
			if (coins.get(j).intersects(player)) {
				player.checkCoins(coins);
				coins.remove(j);
				j--;
			}
		}

		//if player is dead or enters portal
		if (player.getHealth() <= 0 || player.notOnScreen() || player.intersectsTeleports(teleports)) {
			endScore = player.getScore();
			player.isDead();
			gsm.setState(GameStateManager.GAMEOVERSTATE);
		}

		for (Teleport teleport: teleports) {
			if (bossDefeated) {
				teleport.update();
			}
		}


	}


	public void draw(Graphics2D g) {
		bg.draw(g);
		tileMap.draw(g);
		player.draw(g);


		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}

		for (DieEnemies dieEnemy : dieEnemies) {
			dieEnemy.setMapPosition(tileMap.getx(), tileMap.gety());
			dieEnemy.draw(g);
		}

		for (ExplosionFireVenom explosionFireVenom : player.getExplosionsFireVenom()) {
			explosionFireVenom.setMapPosition(tileMap.getx(), tileMap.gety());
			explosionFireVenom.draw(g);
		}

		for (Collectable coin : coins) {
			coin.draw(g);
		}
		if (bossDefeated) {
			for (Teleport teleport: teleports) {
				teleport.draw(g);
			}
		}

		hud.draw(g);
	}

	public void keyPressed(int k) {
		LoadKeys.ControllingPlayer(k, player, LoadKeys.PRESSED);
		LoadKeys.ControllingMusic(k);
	}

	public void keyReleased(int k) {
		LoadKeys.ControllingPlayer(k, player, LoadKeys.RELEASED);



	}
	private DieEnemies getDie(Enemy e){
		DieEnemies die = null;
		if (e.getIndex() == Enemy.BIRD) {
			die = new DieBird(e.getx(), e.gety());
		} else if (e.getIndex() == Enemy.HATMONKEY) {
			die = new DieHatMonkey(e.getx(), e.gety());
		} else if (e.getIndex() == Enemy.SLUGGER) {
			die = new DieSlugger(e.getx(), e.gety());
		} else if (e.getIndex() == Enemy.BOSS) {
			die = new DieBoss(e.getx(), e.gety());
		} else if (e.getIndex() == Enemy.ARACHNIK) {
			die = new DieArachnik(e.getx(), e.gety());
		} else if (e.getIndex() == Enemy.HERO) {
			die = new DieHero(e.getx(), e.gety());
		}

		return die;
	}




}

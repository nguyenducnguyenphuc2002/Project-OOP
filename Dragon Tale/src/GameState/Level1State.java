package GameState;

import Entity.Collectable.Collectable;
import Main.GamePanel;
import TileMap.*;
import Entity.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import Audio.AudioPlayer;



public class Level1State extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;


	//private ArrayList<Enemy> enemySluggers;
	private ArrayList<Enemy> enemyArachniks;
	private ArrayList<Enemy> enemyMonkey;
	private ArrayList<BurningSlugger> burningS;
	private ArrayList<BurningArachnik> burningA;
	private ArrayList<Collectable> coins;
	public static int endScore;
	private HatMonkey hatMonkey;


	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;

	private HUD hud;
	private Teleport teleport;
	private AudioPlayer bgMusic;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
 
	
	@Override
	public void init() {

		tileMap = new TileMap(30); // bản đồ ô xếp 
		tileMap.loadTiles(new File("Resources/Tilesets/grasstileset.gif"));
		tileMap.loadMap(new File("Resources/Maps/level1-1.map"));
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		bg = new Background(new File("Resources/Backgrounds/grassbg1.gif"), 0.1);
		
		player = new Player(tileMap);
		player.setPosition(100, 100);


		hatMonkey = new HatMonkey(tileMap);
		hatMonkey.setPosition(200, 200);
		
		populateEnemies();
		populateCollectables();

		burningS = new ArrayList<BurningSlugger>();
		burningA = new ArrayList<BurningArachnik>();
		explosions = new ArrayList<Explosion>();

		hud = new HUD(player);

		// teleport
		teleport = new Teleport(tileMap);
		teleport.setPosition(3140, 190);

		bgMusic = new AudioPlayer(new File("Resources/Music/level1-1.wav"));
//		bgMusic.play();
	}

	private void populateCollectables() {
		coins = new ArrayList<Collectable>();

		Collectable c;

		Point[] coinPoints = new Point[]{
				new Point(200, 200),
				new Point(560, 55),
				new Point(1020, 170),
				new Point(1440, 80),
		};

		for (int i = 0; i < coinPoints.length; i++) {
			c = new Collectable(tileMap);
			c.setPosition(coinPoints[i].x, coinPoints[i].y);
			coins.add(c);
		}

	}


	private void populateEnemies() {
		
		enemies = new ArrayList<Enemy>();
		
		Slugger s;
		Point[] points1 = new Point[] {
			new Point(200, 100),
			new Point(860, 200),
			new Point(960, 200),
			new Point(1525, 200),
			new Point(1680, 200),
			new Point(1800, 200)
		};
		
		for(int i = 0; i < points1.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points1[i].x, points1[i].y);
			enemies.add(s);
		}

		Arachnik a;
		enemyArachniks = new ArrayList<Enemy>();
		Point[] points2 = new Point[] {
				new Point(400, 50),
				new Point(860, 200),
				new Point(960, 200),
				new Point(1525, 200),
				new Point(1680, 200),
				new Point(1800, 200)
		};

		for(int i = 0; i < points2.length; i++) {
			a = new Arachnik(tileMap);
			a.setPosition(points2[i].x, points2[i].y);
			enemyArachniks.add(a);
		}

		enemyMonkey= new ArrayList<Enemy>();
		HatMonkey monkey1;
		Point[] pointmonkey1 = new Point[]{
				new Point(200, 200),//note
		};
		for (int i = 0; i < pointmonkey1.length; i++) {
			monkey1 = new HatMonkey(tileMap);
			monkey1.setPosition(pointmonkey1[i].x, pointmonkey1[i].y);
			enemyMonkey.add(monkey1);
		}
	}

	@Override
	public void update() {
//		 update player
		if (player.isDead()) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
		player.update();

		//update hatmonkey
		hatMonkey.update();

		tileMap.setPosition(
			(double) GamePanel.WIDTH / 2 - player.getx(),
			(double) GamePanel.HEIGHT / 2 - player.gety()
		);
		
		
		// set background
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		// attack enemies
		player.checkAttack(enemies);

		//note
		//player.checkAttack(enemySluggers);
		player.checkAttack(enemyArachniks);
		player.checkCoins(coins);
		player.checkAttack(enemyMonkey);


		// update all enemies
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) {
				enemies.remove(i);
				i--;
				//explosions.add(
				//	new Explosion(e.getx(), e.gety()));
				if (player.fireKill) {
					burningS.add(new BurningSlugger(e.getx(), e.gety()));
					player.fireKill = false;
				} else if (player.scratchKill) {
					explosions.add(new Explosion(e.getx(), e.gety()));
					player.scratchKill = false;
				}
			}
		}

		//mpte
		//update all enemies
		/*
		for (int i = 0; i < enemySluggers.size(); i++) {
			Enemy es = enemySluggers.get(i);
			es.update();
			if (enemySluggers.get(i).isDead()) {
				enemySluggers.remove(i);
				i--;
				if (player.fireKill) {
					burningS.add(new BurningSlugger(es.getx(), es.gety()));
					player.fireKill = false;
				} else if (player.scratchKill) {
					explosions.add(new Explosion(es.getx(), es.gety()));
					player.scratchKill = false;
				}
			}
		}
		 */

		for (int j = 0; j < enemyArachniks.size(); j++) {
			Enemy ea = enemyArachniks.get(j);
			ea.update();
			if (enemyArachniks.get(j).isDead()) {
				enemyArachniks.remove(j);
				j--;
				if (player.fireKill) {
					burningA.add(new BurningArachnik(ea.getx(), ea.gety()));
					player.fireKill = false;
				} else if (player.scratchKill) {
					explosions.add(new Explosion(ea.getx(), ea.gety()));
					player.scratchKill = false;
				}
			}
		}

		for (int j = 0; j < enemyMonkey.size(); j++) {
			Enemy eSh = enemyMonkey.get(j);
			eSh.update();
			if (enemyMonkey.get(j).isDead()) {
				enemyMonkey.remove(j);
				j--;
				explosions.add(
							new Explosion(eSh.getx(), eSh.gety()));

			}
		}

		//update explosions
		for (int i = 0; i < burningS.size(); i++) {
			burningS.get(i).update();
			if (burningS.get(i).shouldRemove()) {
				burningS.remove(i);
				i--;
			}
		}
		for (int i = 0; i < burningA.size(); i++) {
			burningA.get(i).update();
			if (burningA.get(i).shouldRemove()) {
				burningA.remove(i);
				i--;
			}
		}



		// update explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}

		//update coins
		for (int j = 0; j < coins.size(); j++) {
			Collectable c = coins.get(j);
			c.update();
			if (coins.get(j).intersects(player)) {
				coins.remove(j);
				j--;
			}
		}

		//if player is dead or enters portal
		if (player.getHealth() <= 0 || player.notOnScreen() || player.intersects(teleport)) {
			endScore = player.getScore();
			player.isDead();
			gsm.setState(GameStateManager.GAMEOVERSTATE);
		}

		//update teleporting
		teleport.update();
	}		
	

	@Override
	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw enemies
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		//draw hatmonkey
		if(enemyMonkey.size() > 0)
			hatMonkey.draw(g);

		//draw enemies
		//for (int i = 0; i < enemySluggers.size(); i++) {
		//	enemySluggers.get(i).draw(g);
		//}
		for (int i = 0; i < enemyArachniks.size(); i++) {
			enemyArachniks.get(i).draw(g);
		}

		for (int i = 0; i < enemyMonkey.size(); i++) {
			enemyMonkey.get(i).draw(g);
		}


		//draw explosions
		for (int i = 0; i < burningS.size(); i++) {
			burningS.get(i).setMapPosition((int)tileMap.getx(), (int)tileMap.gety());
			burningS.get(i).draw(g);
		}
		for (int i = 0; i < burningA.size(); i++) {
			burningA.get(i).setMapPosition((int)tileMap.getx(), (int)tileMap.gety());
			burningA.get(i).draw(g);
		}


		// draw explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition(
					(int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}

		//draw coins
		for (int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(g);
		}

		// draw teleport
		teleport.draw(g);
		
		// draw hud
		hud.draw(g);
		
	}



	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setGliding(true);
		if(k == KeyEvent.VK_R) player.setScratching();
		if(k == KeyEvent.VK_F) player.setFiring();		
	}
	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_E) player.setGliding(false);		
	}
}

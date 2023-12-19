//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package gamestate;

import audio.AudioPlayer;

import entities.ExplosionFireVenom;
import entities.enemies.Enemy;
import entities.enemies.Teleport;
import entities.enemies.boss.Boss;
import entities.enemies.die.*;
import entities.enemies.mimimonsters.Bird;
import entities.enemies.mimimonsters.HatMonkey;
import entities.player.Collectable;
import objects.HUD;
import entities.player.Player;
import entities.enemies.mimimonsters.Slugger;

import storage.LoadAudio;
import storage.LoadBackground;
import storage.LoadKeys;
import storage.LoadTileMap;
import tilemap.Background;
import tilemap.Tile;
import tilemap.TileMap;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
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

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        this.init();
    }

    public void init() {

        this.tileMap = new TileMap(Tile.TILESIZE);
        LoadTileMap.loadTileMap(tileMap, LoadTileMap.MAP, LoadTileMap.TILES);

        bg = LoadBackground.loadBackground(LoadBackground.LEVEL1STATE);

        player = new Player(tileMap);
        player.setPosition(100, 100);
        player.setPosition(2600,150);
        this.populateEnemies();
        this.populateCollectables();


        this.dieEnemies = new ArrayList<DieEnemies>();
        this.hud = new HUD(this.player);

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
        this.enemies = new ArrayList<>();
        teleports = new ArrayList<>();
        Point[] points1 = new Point[]{
                new Point(200, 100),
                new Point(860, 200),
                new Point(960, 200),
                new Point(1550, 200),
                new Point(1680, 200),
                new Point(1800, 200)};

        for (Point value : points1) {
            Slugger s = new Slugger(this.tileMap);
            s.setPosition( value.x, value.y);
            this.enemies.add(s);
        }

        Point[] points2 = new Point[]{
                new Point(200, 100),
                new Point(860, 200),
                new Point(960, 200),
                new Point(1550, 200),
                new Point(1680, 200),
                new Point(1800, 200)};

        for (Point value : points2) {
            HatMonkey h = new HatMonkey(this.tileMap);
            h.setPosition(value.x, value.y);
            this.enemies.add(h);
        }

        Point[] points3 = new Point[]{
                new Point(100, 50),
                new Point(860, 155),
                new Point(960, 100),
                new Point(2500, 150)
        };
        for (Point point : points3) {
            Bird b = new Bird(this.tileMap);
            b.setPosition(point.x, point.y);
            this.enemies.add(b);
        }

        Point points4 = new Point(3000,198);

            Boss boss = new Boss(this.tileMap);
            boss.setPosition(points4.x, points4.y);
            this.enemies.add(boss);

        Point point5 = new Point(3150, 200);

        Teleport teleport = new Teleport(this.tileMap);
        teleport.setPosition(point5.x, point5.y);
        teleports.add(teleport);
    }

    public void update() {
        if (this.player.isDead()) {
            this.gsm.setState(0);
        }

        this.player.update();
        this.tileMap.setPosition(160.0 - (double) this.player.getx(), 120.0 - (double) this.player.gety());
        this.bg.setPosition( this.tileMap.getx(), this.tileMap.gety());
        this.player.checkAttack(this.enemies);


        for (Enemy enemy : this.enemies) {
            enemy.checkAttack(this.player);
        }

        for (int i = 0; i < this.enemies.size(); ++i) {
            Enemy e = this.enemies.get(i);
            e.update();
            if (e.isDead()) {
                this.enemies.remove(i);
                --i;
                dieEnemies.add(getDie(e));
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
                this.player.checkCoins(coins);
                coins.remove(j);
                j--;
            }
        }


        for (Teleport teleport: teleports) {
            if (enemies.isEmpty()) {
                teleport.update();
            }
        }

    }

    public void draw(Graphics2D g) {
        this.bg.draw(g);
        this.tileMap.draw(g);
        this.player.draw(g);


        for (Enemy enemy : this.enemies) {
            enemy.draw(g);
        }

        for (DieEnemies dieEnemy : dieEnemies) {
            dieEnemy.setMapPosition(tileMap.getx(), tileMap.gety());
            dieEnemy.draw(g);
        }

        for (ExplosionFireVenom explosionFireVenom : player.getExplosionsFireVenom()) {
            explosionFireVenom.setMapPosition(this.tileMap.getx(), this.tileMap.gety());
            explosionFireVenom.draw(g);
        }

        for (Collectable coin : coins) {
            coin.draw(g);
        }
        if (enemies.isEmpty()) {
            for (Teleport teleport: teleports) {
                teleport.draw(g);
            }
        }

        this.hud.draw(g);
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
            die = new entities.enemies.die.DieHatMonkey(e.getx(), e.gety());
        } else if (e.getIndex() == Enemy.SLUGGER) {
            die = new DieSlugger(e.getx(), e.gety());
        } else if (e.getIndex() == Enemy.MUSHROOM) {
//                        die = new BurningMushroom(e.getx(), e.gety()); sẽ là con mushroom của N nha
        } else if (e.getIndex() == Enemy.BOSS) {
            die = new DieBoss(e.getx(), e.gety());
        }

        return die;
    }




}

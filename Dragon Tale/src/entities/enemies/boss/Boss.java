package entities.enemies.boss;

<<<<<<< HEAD
import objects.Animation;
import entities.enemies.Enemy;
import entities.player.Player;
=======
import entities.enemies.Enemy;
import entities.player.Player;
import objects.Animation;
>>>>>>> NguyenPhuc
import storage.LoadEntities;
import tilemap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

<<<<<<< HEAD
public class Boss extends Enemy {
    private final BufferedImage[] sprites;
    private int venomDamage;
    private int stepcount;

=======
public class Boss extends Enemy{
    private final BufferedImage[] sprites;
    private final int[] numFrames = new int[]{8,6};
    private int venomDamage;
    private int stepcount;

    private static final int WALKING = 1;
    private static final int ATTACKING = 2;
>>>>>>> NguyenPhuc


    public Boss(TileMap tm) {
        super(tm);

        moveSpeed = 0.8;
        maxSpeed = 0.8;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;
        stepcount = 0;

        width = 100;
        height = 51;
        cwidth = 20;
        cheight = 20;



        health = maxHealth = 100;
        damage = 5;
        venomDamage = 3;


        sprites = LoadEntities.loadLine(LoadEntities.BOSS, width, height);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(100L);
        right = true;
        facingRight = true;
    }

    private void  getNextPosition() {
        //movement
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
        }

//        if (falling) {
//            dy += fallSpeed;
//        }
    }

    public void update() {

        //update position
        getNextPosition();
        checkTileMapCollision();
        //slugger turns around if it hits a wall or is about to fall off a cliff
        setPosition(xtemp, ytemp);
        stepcount++;
        if (stepcount == 35) {
<<<<<<< HEAD
=======
//            dy -= 5;
>>>>>>> NguyenPhuc
            Venom venom = new Venom(tileMap, facingRight);
            venom.setPosition(x, y);
            venoms.add(venom);
            stepcount = 0;
        }



        if(dx == 0) {
            left = !left;
            right = !right;
            facingRight = !facingRight;
        }

        for(int i = 0; i < venoms.size(); ++i) {
            venoms.get(i).update();
            if (venoms.get(i).shouldRemove()) {
                venoms.remove(i);
                --i;
            }
        }

        //check flinching
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000L;
            if (elapsed > 400) {
                flinching = false;
            }
        }

        //update animation
        animation.update();


    }

    public void draw(Graphics2D g) {
        //if (notOnScreen()) return;

        setMapPosition();
        setMapPosition(tileMap.getx(), tileMap.gety());
        for (Venom venom : venoms) {
            venom.draw(g);
        }
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000L;
            if (elapsed / 100L % 2L == 0L) {
                return;
            }
        }

        super.draw(g);
    }

    @Override
    public int checkAttack(Player player){
        for(int j = 0; j < venoms.size(); ++j) {
            if (venoms.get(j).intersects(player)) {
                player.hit(venomDamage);
                venoms.get(j).setHit();
                break;
            } else{
                return j;
            }
        }
        return -1;
    }
    public int getIndex(){
        return 5;
    }
}

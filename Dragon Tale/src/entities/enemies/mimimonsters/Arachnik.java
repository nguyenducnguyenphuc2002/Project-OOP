package entities.enemies.mimimonsters;


import entities.enemies.Enemy;
import objects.Animation;
import storage.LoadEntities;
import tilemap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Arachnik extends Enemy {
    private BufferedImage[] sprites; //lưu trữ hình ảnh sprites

    public Arachnik(TileMap tm) {
        super(tm);

        moveSpeed = 1.2;
        maxSpeed = 1.2;

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        health = maxHealth = 2;
        damage = 1;

        //load sprites
        sprites = LoadEntities.loadLine(LoadEntities.ARACHNIK, width, height); //lấy bộ ảnh đầu tiên
        animation = new Animation();
        animation.setFrames(sprites); //animation duyệt qua các hình ảnh trong Sprites để tạo hoạt hình cho Arachnik
        animation.setDelay(300); //thiết lập thgian trễ giữa các khung hình: 300milliseconds=0.3s

        up = true; //di chuyển lên trên từ trạng thái ban đầu
    }

    private void getNextPosition() { //xác định vị trí kế tiếp
        //movement
        if(up) dy = -moveSpeed;
        else if(down) dy = moveSpeed;
        else dy = 0;
    }

    public void update() {
        //update position
        getNextPosition(); //tính toán vị trí kế tiếp của Arachnik
        checkTileMapCollision(); //ktr va chạm vs TileMap -> đảm bảo Arachnik ko xuyên qua các vật cản

        //arcahanik movement
        calculateCorners(x, ydest + 1); //ktra góc dưới cùng của nó -> xd sẽ đi lên hoặc xuống
        if(!topLeft && topRight) {
            up = false;
        }
        if(!bottomLeft && bottomRight) {
            up = true;
        }

        setPosition(xtemp, ytemp); //vị trí mới dc đặt thành xtemp và ytemp -> cập nhật vị trí thực sự Arachnik trên màn hình

        if(dy == 0) { // v theo trục Oy -> đứng yên
            up = !up;
            down = !down;
        }

        //check flinching
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000; //tính thgian đã trôi qua
            if (elapsed > 400) {
                flinching = false;
            }
        }

        //update animation
        animation.update();
    }

    public void draw(Graphics2D g) {

        setMapPosition();
        setMapPosition(tileMap.getx(), tileMap.gety());
        super.draw(g);
    }

    @Override
    public int getIndex(){
        return 6;
    }
}

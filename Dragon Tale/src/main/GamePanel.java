 package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gamestate.GameStateManager;
public class GamePanel extends JPanel 
	implements Runnable, KeyListener{
	
	// dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 3; // chia theo tỷ lệ 
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60; // frames-per-second: là một thuật ngữ chỉ số khung hình hiển thị trên mỗi giây
	private long targetTime = 1000 / FPS; // chỉ chip đồ họa
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	
	// game state manager
	private GameStateManager gsm;
	// điều khiển toàn chương trình
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); // xét độ dài của Panel
		setFocusable(true); // Đặt trạng thái có thể tập trung của 
							// Thành phần này thành giá trị được chỉ định. 
							// Giá trị này ghi đè lên khả năng lấy nét mặc định của Thành phần.
		requestFocus();
	}
	
	public void addNotify() { // để thêm addNotify được có sẵn trong JPanel
		super.addNotify();
		if(thread == null) { // kiểm tra thread có giá trị không, nếu chưa có sẽ gán cho nó giá của GamePanel
			thread = new Thread(this);
			addKeyListener(this); // GamePanel vào KeyListener
			thread.start(); // và bắt đầu thread
		}
	}

	
	private void init() { // hàm khởi tạo
		
		image = new BufferedImage(
				WIDTH, HEIGHT, 
				BufferedImage.TYPE_INT_RGB
			); // độ dài ảnh là loại hình ảnh được cung cấp int RGB
		
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager();
	}
	
	@Override
	public void run() {
		init();
		
		long start;
		long elapsed; // thời gian trôi qua
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			
			if(wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait); // thời gian nghỉ
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void drawToScreen() {
		Graphics2D g2 = (Graphics2D) getGraphics();
		g2.drawImage(image, 0, 0,
					WIDTH * SCALE, HEIGHT * SCALE,
					null); // hình để vẽ lên screen
		g2.dispose(); // 
	}

	private void draw() {
		gsm.draw(g);
		
	}
	
	

	private void update() {
		gsm.update();
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
		
	}

	
}

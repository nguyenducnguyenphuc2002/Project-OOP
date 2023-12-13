package Entity.Enemies;

import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] frames; // khung hình
	private int currentFrame; // khung hình hiện tại
	
	private long startTime; // bộ hiện giờ bắt đầu
	private long delay; // thời gian giữa 2 khung hình
	
	private boolean playedOnce; // cho mình biết những frames ảnh đã phát hay chưa, nếu lặp thì thì rất hữu ích, dạng như kiểm tra cách animation đã tạo thành vòng lặp chưa
	
	public Animation() {
		playedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames) { // set frames cho animation
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime(); // thời gian bắt đầu
		playedOnce = false;
	}
	
	public void setDelay(long d) { 
		delay = d; 
	}
	
	public void setFrame(int i) { 
		currentFrame = i; 
	}
	
	public void update() { // giúp chuyển qua một frames khác
		
		if(delay == -1) 
			return;
		
		long elapsed = (System.nanoTime() - startTime) / 1000000; // lấy thời gian hiện tại trừ cho thời gian bắt đầu, đây gọi là thời 1 frame ảnh hiện ra
		if(elapsed > delay) { // nếu thời gian delta( thời gian chệch giữa hiện thời gian bắt đầu) lớn hơn thời gian giữa 2 khung hình
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length) { // khi tới frame ảnh cuối cùng
			currentFrame = 0;
			playedOnce = true; // tắt vòng lặp
		}
		
	}
	
	public int getFrame() { return currentFrame; }
	public BufferedImage getImage() { return frames[currentFrame]; }
	public boolean hasPlayedOnce() { return playedOnce; }
	
}

















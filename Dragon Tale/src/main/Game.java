package main;

import storage.LoadEntities;

<<<<<<< HEAD
import javax.swing.JFrame;

public class Game{
	
	public static void main(String[] args) {

		JFrame window = new JFrame("Dragon Tale"); // tên cửa sổ
		window.setContentPane(new GamePanel()); // nội dung bảng điều khiển trò chơi
		window.setIconImage(LoadEntities.LoadIcon(LoadEntities.ICON));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // khi đóng cửa sổ tắt chương trình
		window.setResizable(false);  // false có thể thay đổi kích thước 
		window.pack(); 
		window.setVisible(true); // hiển thị ra
		
		
	}
	
	
	
		
	}

=======
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Game {
    public Game() {
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Dragon Tale");
        window.setContentPane(new GamePanel());
        window.setIconImage(LoadEntities.LoadIcon(LoadEntities.ICON));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);

    }
}
>>>>>>> NguyenPhuc

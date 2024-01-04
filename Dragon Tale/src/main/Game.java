package main;

import ui.LoadEntities;

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


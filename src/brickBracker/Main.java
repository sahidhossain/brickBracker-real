package brickBracker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gameplay gameplay=new Gameplay();
		JFrame obj=new JFrame();
		obj.setBounds(100, 100, 700, 600);
		obj.setTitle("Sahid BRICK bracker");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
		

	}

}

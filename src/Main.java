import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Hunting Demo");
		Rect rect = new Rect();
//		JPanel menu = new JPanel();
//		JButton btn1 = new JButton("Reset/Reload");
//		JButton btn2 = new JButton("Start/Resume");
//		JButton btn3 = new JButton("Pause");
//		menu.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
//		menu.add(btn1);
//		menu.add(btn2);
//		menu.add(btn3);
//		frame.setLayout(new GridLayout(2,0,5,5));
//		frame.add(menu);
		frame.add(rect);
		frame.setBackground(Color.WHITE);
		frame.setSize(500, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}

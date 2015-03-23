import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Rect extends JPanel{
	Rect(){
		
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 50, 50);
	}
}

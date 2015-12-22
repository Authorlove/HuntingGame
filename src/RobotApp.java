import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class RobotApp extends JFrame { 
    private RobotWorld			world; 
    private Timer				timer;
    private RobotWorldPanel		worldPanel;

    // Default constructor 
    public RobotApp(RobotWorld w) {
    	super("Robot Hunting Demo");
        world = w;
        worldPanel = new RobotWorldPanel(w);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        getContentPane().add(worldPanel);
        pack();
        setResizable(false);
        timer = new Timer(1000/RobotWorld.UPDATE_RATE, new Ticker());
        timer.start();
    } 

    private class Ticker implements ActionListener { 
        public void actionPerformed(ActionEvent e) { 
            world.update();
           	update();
        } 
    } 

	private void update() {
		worldPanel.repaint();
	}
		
    public static void main(String args[]) { 
        JFrame frame = new RobotApp(new RobotWorld()); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
    } 
    

} 
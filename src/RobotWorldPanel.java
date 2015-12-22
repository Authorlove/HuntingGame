import java.awt.*; 
import java.awt.event.*; 

import javax.swing.*; 
public class RobotWorldPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener  { 
    private RobotWorld	world;
    private Point TargetPoint;
    
  //  private  MouseEvent e;
	
	// Menu that allows Robots to be added
	private JPopupMenu popupMenu = new JPopupMenu();
	
    // Default constructor 
    public RobotWorldPanel(RobotWorld w) { 
        world = w;    
        setSize(world.getWidth(), world.getHeight()); 
        setPreferredSize(new Dimension(world.getWidth(), world.getHeight())); 
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2)); 
        addMouseListener(this); 
        addMouseMotionListener(this); 
        				        	      		        
        // Now add the items to the popup menu
		JMenuItem  item = new JMenuItem("Add Obstacle");
		item.addActionListener(this);
		popupMenu.add(item);	
//		popupMenu.add(new JSeparator());
//		item = new JMenuItem("Restart");
//		item.addActionListener(this);
		popupMenu.add(item);	
    } 
	
    public void paintComponent(Graphics aPen) {
        super.paintComponent(aPen); 
        world.drawWith(aPen);
    } 

	public void actionPerformed(ActionEvent e) {
		JMenuItem Item=(JMenuItem)e.getSource();	

		if (Item.getText()=="Add Police Robot") 		
			world.addRobot(new PoliceRobot(TargetPoint));

		if (Item.getText()=="Add Obstacle")	
			world.addRec(new Obstacle(TargetPoint));
//		
//		if (Item.getText()=="Restart")			
//			world = world.reset();
//		   	update();
	}


    public void mouseReleased(MouseEvent event) {
    	if (event.isPopupTrigger()) 
    		{
    			TargetPoint=event.getPoint();
    			popupMenu.show(event.getComponent(), (int)event.getX(), (int)event.getY());
    			return;  			
    		}       
    	} 
    private void update() {
    	repaint();
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}

 
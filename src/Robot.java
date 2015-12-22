import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.lang.Math;

public abstract class Robot {
	public static final int		RADIUS = 8;
	public static final int		SQUARE_OF_RADIUS = RADIUS * RADIUS;
	public static final int		TURN_UNIT = 15; //turn unit: 15 degrees
		
	// Instance variables that outsiders need to know about
	protected Point 	location;
	protected int		direction;
	protected int		speed;
	public boolean collisionObstacle;
	public boolean collisionRobot;
	public boolean collisionWall;
	public Robot(Point loc) {
		location = loc;
		direction = 0;
		speed = RADIUS;
	}
	// All standard robots are green */
	public Color getColor() { return Color.CYAN; }
	// Usual get/set methods
	public Point getLocation() { return location; }
	public int   getDirection() { return direction; }
	public int   getSpeed() { return speed; }
	public void  setLocation(Point p) { location = p; }
	public void  setSpeed(int s) { speed = s; }

	// Draw the robot using the given graphics object
	public void drawWith(Graphics aPen) {
		int   endX;
		int   endY;
		aPen.setColor(this.getColor());
	    aPen.fillOval(location.x - RADIUS,location.y - RADIUS,2 * RADIUS,2 * RADIUS);
	    aPen.setColor(Color.BLACK);
	    aPen.drawOval(location.x - RADIUS,location.y - RADIUS,2 * RADIUS,2 * RADIUS);
		
	    endX = location.x +  (int)(RADIUS * Math.cos(direction * Math.PI / 180.0));
	    endY = location.y -  (int)(RADIUS * Math.sin(direction * Math.PI / 180.0));
	    
	    aPen.setColor(Color.BLACK);
	    aPen.drawLine(location.x,location.y,endX,endY);	
	}
	
	// Find a target to head towards.  Thief robot 
	// have nowhere in particular to head
	abstract protected Point findTarget(RobotWorld world);

	// Compute a direction in which to head
	abstract protected int computeDesiredDirection(Point target);
	     
	// Make a Move 
	protected void makeMoveIn(RobotWorld world) {
		// Turn the robot in an appropriate direction
		Point middle = new Point();	
		Point target = this.findTarget(world);
		direction = this.computeDesiredDirection(target);
	    middle.x = (int )(location.x + speed * Math.cos((double )direction * Math.PI / 180.0));
		middle.y = (int )(location.y - speed * Math.sin((double )direction * Math.PI / 180.0));	
		if(checkForCollision(middle.x,middle.y,world) == false){			   		 					
				setLocation(middle);	
		}
		else{
			//if(collisionObstacle){
				middle.x = (int )(location.x + speed * Math.cos((double )direction * Math.PI / 180.0));
				middle.y = (int )(location.y - speed * Math.sin((double )direction * Math.PI / 180.0));
				setLocation(middle);
			//}
		}
	}
	// Update the robot by determining how it should move
	public void update(RobotWorld world){
		makeMoveIn(world);
	}
	
	abstract protected void followwall(int x,int y);
	
	private boolean checkForCollision(int x, int y, RobotWorld world) {
		int sym = 0;
	    for(int i=0; i<world.getRecs().size();i++){
	    	int x1 = world.getRecs().get(i).getLocation().x ;
	    	int y1 = world.getRecs().get(i).getLocation().y ; 
	    	if(Point.distance(x1, y1, x, y)<Obstacle.RADIUS+Robot.RADIUS){ 
	    		this.followwall(x1, y1);
	    		collisionObstacle = true;
	    		return true;
	    	}
	    }
	    //collision with the wall
		if(x <= Robot.RADIUS|| y <= Robot.RADIUS|| x >= world.getWidth()-Robot.RADIUS || y >= world.getHeight()-Robot.RADIUS){
			if(this.getColor()!=Color.BLUE)//thief robot
				this.followwall(x, y);
			else{
				
			}
			collisionObstacle = false;
			return true	;
		}
		if(this.getColor()==Color.BLUE){//robot is police
		   while(sym < world.getRobots().size()){//collision with other police robot 
			   if(location == world.getRobot(sym).getLocation())
		   			sym++;
			   else {
				   if(Point.distanceSq((double)x,(double )y,(double)world.getRobot(sym).getLocation().x,(double)world.getRobot(sym).getLocation().y) <= 4 * RADIUS * RADIUS){ 
					   this.followwall(world.getRobot(sym).getLocation().x, world.getRobot(sym).getLocation().y);
					   collisionObstacle = true;//deal with it as a dynamic obstacle. use bug2 algorithm
					   return true;
				   	}
				   else 
					   sym++;
			   }
		   }
		   if(Point.distanceSq((double)x,(double )y,(double)world.getThief().getLocation().x,(double)world.getThief().getLocation().y) <= 4 * RADIUS * RADIUS){
			   collisionObstacle = false;
			   return true;
		   }
		}
		else{
			while(sym < world.getRobots().size()){ 
			    if(Point.distanceSq((double)x,(double )y,(double)world.getRobot(sym).getLocation().x,(double)world.getRobot(sym).getLocation().y) <= 4 * RADIUS * RADIUS){ 
			    	 this.followwall(x, y);
			    	 collisionObstacle = false;
			    	 return true;
					}
					else 
				      sym++;
				   }
			   			
		}
		return false;	
	}
}
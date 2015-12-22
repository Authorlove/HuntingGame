import java.awt.Point;
import java.awt.Graphics;
import java.util.ArrayList;

public class RobotWorld {
	// The rate (times per second) at which the world is updated
	public static final int UPDATE_RATE = 20;
	private ArrayList<Point> targets = new ArrayList<Point>();
	private ArrayList<Robot> polices; 
	private ArrayList<Obstacle> recs;
	private int	width, height;
	private Robot thief;
	public RobotWorld() {
		polices = new ArrayList<Robot>();
		recs   = new ArrayList<Obstacle>();
		thief = new ThiefRobot(new Point(350,270));
		targets.add(new Point(thief.location.x,thief.location.y+2*Robot.RADIUS));
		targets.add(new Point(thief.location.x,thief.location.y-2*Robot.RADIUS));
		targets.add(new Point(thief.location.x-2*Robot.RADIUS,thief.location.y));
		targets.add(new Point(thief.location.x+2*Robot.RADIUS,thief.location.y));
		addRobot(new PoliceRobot(new Point(50,50))); 
        addRobot(new PoliceRobot(new Point(50,450)));
        addRobot(new PoliceRobot(new Point(450,50)));
        addRobot(new PoliceRobot(new Point(450,450)));
        addRec(new Obstacle(new Point(300,250)));
        addRec(new Obstacle(new Point(100,200)));
        addRec(new Obstacle(new Point(400,300)));
        width = 500;
        height = 500;
	}
	private boolean reachGoal(){
		boolean goal = true;
		
		int size = polices.size();
		int j;
		for(j=0; j<size;j++){
			int x = polices.get(j).findTarget(this).x;
			int y = polices.get(j).findTarget(this).y;
			if(x <= Robot.RADIUS|| y <= Robot.RADIUS|| x >= this.getWidth()-Robot.RADIUS || y >= this.getHeight()-Robot.RADIUS){
				break;
			}
			int i;
			for(i=0; i<getRecs().size();i++){
		    	int x1 = getRecs().get(i).getLocation().x ;
		    	int y1 = getRecs().get(i).getLocation().y ; 
		    	if(Point.distance(x1, y1, x, y)<Obstacle.RADIUS+Robot.RADIUS){ 
		    		break;
		    	}
		    }
			if(i<size)
				break;
		}
		for(int i=0; i<polices.size();i++){
			if(i==j)
				continue;
			int x1 = polices.get(i).location.x;
			int y1 = polices.get(i).location.y;
			if(Point.distance(x1, y1, polices.get(i).findTarget(this).getX(), polices.get(i).findTarget(this).getY())<=Robot.RADIUS)
				continue;
			else{
				goal = false;
				return goal;
			}
		}
		return goal;
	}
	private void updateTarget(){
		targets.clear();
		targets.add(new Point(thief.location.x,thief.location.y+2*Robot.RADIUS));
		targets.add(new Point(thief.location.x,thief.location.y-2*Robot.RADIUS));
		targets.add(new Point(thief.location.x-2*Robot.RADIUS,thief.location.y));
		targets.add(new Point(thief.location.x+2*Robot.RADIUS,thief.location.y));
	}
	public Point getTarget(Robot police){
		return targets.get(polices.indexOf(police));
	}
	public ArrayList<Robot> getRobots() { return polices; }
    Robot getRobot(int i) { return (Robot)polices.get(i); }
	public int getWidth() { return width; }
	public int getHeight() { return height; }

   public  ArrayList<Obstacle> getRecs() { return recs;}
   Obstacle getRec(int i){return (Obstacle)recs.get(i);}

	public void addRobot(PoliceRobot r) { polices.add(r); }
	public Robot getThief(){ return thief;}
	public void addRec(Obstacle r){recs.add(r);}
	
	public void drawWith(Graphics aPen) {
	
		for (int i=0;i<recs.size();i++)
			((Obstacle)recs.get(i)).drawWith(aPen);
		
	   for (int i=0; i<polices.size(); i++) 
			((Robot)polices.get(i)).drawWith(aPen);
	   ((Robot)thief).drawWith(aPen);
	} 
	
	public void update() {
		if(reachGoal()) return;
		for (int i=0; i<polices.size(); i++) 
			polices.get(i).update(this);
		
		thief.update(this);
		 updateTarget();
	}

	public RobotWorld reset(){
		return new RobotWorld();
	}
}
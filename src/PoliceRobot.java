import java.awt.Color;
import java.awt.Point;
public class PoliceRobot extends Robot {
	private Point target;
	boolean reach;
	public PoliceRobot(Point loc) {
		super(loc);
	}
	public Color getColor() {
		return Color.BLUE;
	}
	protected Point findTarget(RobotWorld world) {
		target = world.getTarget(this);
		return target;
	}
	protected int computeDesiredDirection(Point target){
    	int xdiff,ydiff,d;
		double hdiff;
		xdiff = target.x - location.x;
		ydiff = target.y - location.y;
		hdiff = Math.sqrt((double )(xdiff * xdiff + ydiff * ydiff));
		d = (int )(Math.acos(xdiff / hdiff)  * 180 / Math.PI);
		if( ydiff > 0)
			d = -d;
		if (d > 180)
			d -= 360;
		if (d <= -180)
			d += 360;
		return d;			
    }
	@Override
	protected void followwall(int x, int y) {
		// TODO Auto-generated method stub
		int xdiff,ydiff,d;
	    double hdiff;
	    xdiff = -this.location.x + x;
	    ydiff = -this.location.y + y;
	    hdiff = Math.sqrt((double )(xdiff * xdiff + ydiff * ydiff));
	    d = (int )(Math.acos(xdiff / hdiff)  * 180 / Math.PI);
	    if( ydiff > 0)
			d = -d;
	    d+=90;
	    if (d > 180)
			d -= 360;
		if (d <= -180)
			d += 360;
		this.direction =  d;
	}
}
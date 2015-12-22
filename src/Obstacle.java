import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
public class Obstacle {
	public static final int	RADIUS = 25;
	private Point 	location;
	// Constructor
	public Obstacle(Point loc) {
		location = loc;
	}
	// Usual get methods
	public Point getLocation() { return location; }

	// Set methods.
	public void setLocation(Point p) { location = p; }
	
	// Draw the station using the given graphics object
	public void drawWith(Graphics aPen) {
		aPen.setColor(Color.LIGHT_GRAY);
		aPen.fillOval(location.x - RADIUS,location.y - RADIUS,2 * RADIUS,2 * RADIUS);
	}		
}
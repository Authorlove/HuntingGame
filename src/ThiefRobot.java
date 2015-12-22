import java.awt.Point;


public class ThiefRobot extends Robot{

	public ThiefRobot(Point loc) {
		super(loc);
		// TODO Auto-generated constructor stub
	}
	public int computeRandomDirectionChange(){
	    int  decision = (int )(Math.random() * 10);
		int  amount   = (int )(Math.random() * 90 / TURN_UNIT);
		if(decision  < 0){}//desiredDirection = desiredDirection;
		else if(decision >= 0){
			if(decision  == 6 || decision  == 7)
				direction = direction - TURN_UNIT * amount ;
			else
				direction = direction + TURN_UNIT * amount ;			
		}					
		 if (direction > 180) 		
			 direction -= 360;
         if (direction <= -180) 
        	 direction += 360;
      //   System.out.println(desiredDirection);
        return direction;
	}
	protected void followwall(int x,int y){
		this.direction +=15;
	}
	@Override
	protected Point findTarget(RobotWorld world) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected int computeDesiredDirection(Point target) {
		// TODO Auto-generated method stub
		return computeRandomDirectionChange();
	}
}

package columbusGame;

import java.awt.Point;
import java.util.Observable;

public class PirateShip2 extends PirateShip {


	
	public PirateShip2(Observable observable) {
		super(observable);
		// TODO Auto-generated constructor stub
	}

	public Point setPiratelocation(){
		pirateLocation = new Point(0,19);
		return pirateLocation;
	}

}

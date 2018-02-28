package columbusGame;

import java.awt.Point;
import java.util.Observable;

public class PirateShip1 extends PirateShip {


	public PirateShip1(Observable observable) {
		super(observable);
		// TODO Auto-generated constructor stub
	}

	public Point setPiratelocation(){
		pirateLocation = new Point(19,19);
		return pirateLocation;
	}
}

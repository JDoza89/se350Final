package columbusGame;

import java.awt.Point;

public class MoveDynamic implements MoveStrategy {

	RandomPoints r;

	
	public void movement(PirateShip ship,  Point loc) {
		loc = new Point();
		loc = ship.getLocation();
		
		
		if (loc.y+1 <= ship.getLocation().y) {
			ship.moveUp();
		}
		else if (loc.y >= ship.getLocation().y+1) {
			ship.moveDown();
		}
		else if (loc.x+1 <= ship.getLocation().x) {
			ship.moveLeft();
		}
		else if (loc.x >= ship.getLocation().x+1) {
			ship.moveRight();	
		}
		
	}


}

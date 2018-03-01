package columbusGame;

import java.awt.Point;
import java.util.Random;


//Some of the pirate ships will be going towards the player ship
//while others will be using the other movement methods 
//At least, thats the hope


public class MoveTowards implements MoveStrategy  {
	

	
	public void movement(PirateShip ship, Point loc ) {
		
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
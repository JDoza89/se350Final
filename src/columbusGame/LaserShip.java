package columbusGame;

import java.awt.Point;
/**
 * Concrete decorator Laser class for ship.
 *
 */
public class LaserShip extends ShipDecorator{
	Vessel navigable;
	
	public LaserShip(Vessel navigable) {
		this.navigable = navigable;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return navigable.getDescription() + " with laser power up.";
	}

	@Override
	void moveRight() {
		// TODO Auto-generated method stub
		navigable.moveRight();
	}

	@Override
	void moveLeft() {
		// TODO Auto-generated method stub
		navigable.moveLeft();
	}

	@Override
	void moveUp() {
		// TODO Auto-generated method stub
		navigable.moveUp();
	}

	@Override
	void moveDown() {
		// TODO Auto-generated method stub
		navigable.moveDown();
	}

	@Override
	Point getLocation() {
		return navigable.getLocation();
	}
}

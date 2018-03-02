package columbusGame;

import java.awt.Point;
/**
 * Concrete decorator
 * @author juliolama
 *
 */
public class PausableShip extends ShipDecorator{
	Vessel navigable;
	
	public PausableShip(Vessel navigable) {
		this.navigable = navigable;
	}

	@Override
	void moveRight() {
		navigable.moveRight();
	}

	@Override
	void moveLeft() {
		navigable.moveLeft();
	}

	@Override
	void moveUp() {
		navigable.moveUp();
	}

	@Override
	void moveDown() {
		navigable.moveDown();
	}

	@Override
	Point getLocation() {
		return navigable.getLocation();
	}

	@Override
	public String getDescription() {
		return navigable.getDescription() + " with Pausable power up.";
	}
}

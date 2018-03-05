package columbusGame;

import java.awt.Point;
import java.util.Observable;
/**
 * A Vessel can be any state of our ship, with or without power ups.
 */
public abstract class Vessel extends Observable{
	String description = "Unknown Vessel";
	abstract void moveRight();
	abstract void moveLeft();
	abstract void moveUp();
	abstract void moveDown();
	abstract Point getLocation();
	public String getDescription() {
		return description;
	}
}

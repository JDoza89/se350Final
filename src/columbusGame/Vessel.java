package columbusGame;

import java.awt.Point;
import java.util.Observable;

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

package columbusGame;

import java.awt.Point;

public abstract class ShipDecorator implements PowerUp {
	PowerUp power;

	public ShipDecorator(Ship ship) {
		power = ((Ship) ship);
	}

	public abstract void levelUp();

	public abstract String getPowerUp(); // returns what power up has been activated.

	public abstract Point getLocation();
}

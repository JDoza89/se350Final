package columbusGame;

import java.awt.Point;
/**
 * Concrete decorator Laser class for ship.
 *
 */
public class Lasers extends ShipDecorator{
	Ship cShip;
	Point location;
	/**
	 * Decorates Columbus's ship with laser power up.
	 * @param ship
	 */
	public Lasers(Ship ship) {
		super(ship);
		this.cShip = ship;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Levels up ship.
	 */
	public void levelUp() {
		// TODO Auto-generated method stub
		if(cShip.touchedCoins()) {
			
		}
	}

	/**
	 * Returns the name of the power up.
	 */
	public String getPowerUp() {
		return "Laser power up";
	}

	/**
	 * Returns the location where the ship touched the red coin.
	 */
	public Point getLocation() {
		location = cShip.getLocation();
		return location;
	}
}

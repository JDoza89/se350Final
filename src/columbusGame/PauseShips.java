package columbusGame;

import java.awt.Point;
/**
 * PauseShips concrete decorator.
 */
public class PauseShips extends ShipDecorator {
	OceanMap ocean;
	YellowCoin pauseCoin;
	Point location;
	Ship cShip;

	/**
	 * Decorates Columbus's ship with a "Pause" power up.
	 */
	public PauseShips(Ship ship) {
		super(ship);
		this.cShip = ship;
	}

	/**
	 * Stops all pirate ships from chasing Columbus ship.
	 */
	public void levelUp() {
		if(cShip.touchedCoins()) {
			cShip.deleteObservers();
			pauseCoin.resetCoinImage();
		}
	}

	/**
	 * Returns the current location where the Ship touched the coin.
	 */
	public Point getLocation() {
		location = cShip.getLocation();
		return location;
	}
	
	/**
	 * Returns the name of the power up.
	 */
	public String getPowerUp() {
		return "Paused Ships";
	}
}

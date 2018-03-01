package columbusGame;

import java.awt.Point;
import java.util.Observable;

public class Ship extends Observable implements OceanObjects {// extends the Observable built in, so that the
																// pirateSHips can observe the ship's movement and react
	int xCell;
	int yCell;
	int unitSize;
	OceanMap ocean;
	Ship ship;
	PirateShip pirate;
	
	
	YellowCoin coin;	//ship needs to know about coin?
	RedCoin laserCoin;

	/* New: coin parameter.*/
	public Ship(int xCell, int yCell, int unitSize, OceanMap ocean, YellowCoin coin, RedCoin laserCoin) {
		this.xCell = xCell;
		this.yCell = yCell;
		this.unitSize = unitSize;
		this.ocean = ocean;
		this.coin = coin;	// this is new
		this.laserCoin = laserCoin;
	}

	public void movementChanged() {
		setChanged();
		notifyObservers();
	}

	// Checks to see if the next index is 0 so that the ship can move, cannot move
	// if the index == 2 or 3, (pirateShip or island) and catches the
	// outBoundsException
	// the ship cannot go off the grid and calls the movementChanged() method after
	// every change
	public void moveRight() {
		try {
			if (ocean.getMap()[xCell + 1][yCell] == 0) {
				xCell++;	
			}
			touchedCoins();
		} 
		catch (IndexOutOfBoundsException e) {

		}
		movementChanged();

	}

	public void moveLeft() {
		try {
			if (ocean.getMap()[xCell - 1][yCell] == 0) {
				xCell--;
			}
			touchedCoins();
		} catch (IndexOutOfBoundsException e) {

		}
		movementChanged();
	}

	public void moveUp() {
		try {
			if (ocean.getMap()[xCell][yCell - 1] == 0) {
				yCell--;
			}
			touchedCoins();
		} catch (IndexOutOfBoundsException e) {

		}
		movementChanged();

	}

	public void moveDown() {
		try {
			if(ocean.getMap()[xCell][yCell + 1] == 0) {
				yCell++;
			}
			touchedCoins();
			
		} catch (IndexOutOfBoundsException e) {

		}
		movementChanged();
	}

	public Point getLocation() {
		// returns the ship's current location
		return new Point(xCell, yCell);
	}
	
	
	
	/**
	 * Helper function to see after touched.
	 */
	private void touchedCoins() {
		if (this.getLocation().equals(coin.getLocation())) {
			coin.levelUp();
		} else if (this.getLocation().equals(laserCoin.getLocation())) {
			laserCoin.levelUp();
		}
	}
}

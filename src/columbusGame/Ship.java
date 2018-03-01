package columbusGame;

import java.awt.Point;
import java.util.Observable;

public class Ship extends Observable implements OceanObjects, PowerUp {
	int xCell;
	int yCell;
	int unitSize;
	OceanMap ocean;
	Ship ship;
	PirateShip pirate;
	PauseShips bomb;

	YellowCoin coin;
	RedCoin laserCoin;

	/**
	 * Ship knows about these:
	 * @param xCell
	 * @param yCell
	 * @param unitSize
	 * @param ocean
	 * @param coin
	 * @param laserCoin
	 */
	public Ship(int xCell, int yCell, int unitSize, OceanMap ocean, YellowCoin coin, RedCoin laserCoin) {
		this.xCell = xCell;
		this.yCell = yCell;
		this.unitSize = unitSize;
		this.ocean = ocean;
		this.coin = coin;
		this.laserCoin = laserCoin;
	}

	/**
	 * Lets the observers know that the ship moved.
	 */
	public void movementChanged() {
		setChanged();
		notifyObservers();
	}

	// Checks to see if the next index is 0 so that the ship can move, cannot move
	// if the index == 2 or 3, (pirateShip or island) and catches the
	// outBoundsException
	// the ship cannot go off the grid and calls the movementChanged() method after
	// every change
	/**
	 * Ship moves right.
	 */
	public void moveRight() {
		try {
			if (ocean.getMap()[xCell + 1][yCell] == 0) {
				xCell++;
			}
			touchedCoins();
		} catch (IndexOutOfBoundsException e) {

		}
		movementChanged();

	}

	/**
	 * Ships move left.
	 */
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

	/**
	 * Ships move up.
	 */
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

	/**
	 * Ships move down.
	 */
	public void moveDown() {
		try {
			if (ocean.getMap()[xCell][yCell + 1] == 0) {
				yCell++;
			}
			touchedCoins();

		} catch (IndexOutOfBoundsException e) {

		}
		movementChanged();
	}

	/**
	 * Returns the current location of the ship.
	 */
	public Point getLocation() {
		return new Point(xCell, yCell);
	}

	/**
	 * Function that checks if the ship has touched either a red or yellow coin.
	 */
	public boolean touchedCoins() {
		if (this.getLocation().equals(coin.getLocation())) {
			this.deleteObservers();
			coin.resetCoinImage();
			coin.createYellowCoins();
			return true;
		} else if (this.getLocation().equals(laserCoin.getLocation())) {
			this.deleteObservers();
			laserCoin.resetCoinImage();
			laserCoin.createRedCoins();
			return true;
		}
		return false;
	}
}

package columbusGame;

import java.awt.Point;

/**
 * Concrete decorator
 */
public class PausableShip extends ShipDecorator {
	Vessel navigable;
	YellowCoin coin;
	OceanExplorer OE;

	public PausableShip(Vessel navigable, OceanExplorer oceanE) {
		this.navigable = navigable;
		this.OE = oceanE;
		coin = (YellowCoin) OE.yellowCoin;
	}

	@Override
	void moveRight() {
		navigable.moveRight();
		if (coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
		}
	}

	@Override
	void moveLeft() {
		navigable.moveLeft();
		if (coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
		}
	}

	@Override
	void moveUp() {
		navigable.moveUp();
		if (coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
		}
	}

	@Override
	void moveDown() {
		navigable.moveDown();
		if (coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
		}
	}

	@Override
	Point getLocation() {
		return navigable.getLocation();
	}

	@Override
	public String getDescription() {
		return navigable.getDescription() + " freezes PirateShips.";
	}
}

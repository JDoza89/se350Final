package columbusGame;

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Concrete decorator<br/>
 * Ship can interact with the YellowCoin and can freeze PirateShips.
 */
public class FreezerShip extends ShipDecorator {
	Vessel navigable;
	YellowCoin coin;
	OceanExplorer OE;
	Image ice, ice2;
	ImageView iceV, iceV2;

	/**
	 * Constructs a Ship that can freeze PirateShips.
	 * @param navigable
	 * @param oceanE
	 */
	public FreezerShip(Vessel navigable, OceanExplorer oceanE) {
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
			loadFrozenShip();
		}
	}

	@Override
	void moveLeft() {
		navigable.moveLeft();
		if (coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
			loadFrozenShip();
		}
	}

	@Override
	void moveUp() {
		navigable.moveUp();
		if (coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
			loadFrozenShip();
		}
	}

	@Override
	void moveDown() {
		navigable.moveDown();
		if (coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
			loadFrozenShip();
		}
	}

	/**
	 * Returns the current location of FreezeShip.
	 */
	@Override
	Point getLocation() {
		return navigable.getLocation();
	}
	
	/**
	 * Returns the new state of the Ship as a FreezerShip.
	 */
	@Override
	public String getDescription() {
		return navigable.getDescription() + " freezes PirateShips.";
	}
	
	/**
	 * Loads an ice image for each PirateShip.
	 */
	private void loadFrozenShip() {
		ice = new Image("File:src/columbusGame/ice.jpeg", OE.scale, OE.scale, true, true);
		iceV = new ImageView(ice);
		iceV.setX(OE.pirate.getLocation().getX() * OE.scale);
		iceV.setY(OE.pirate.getLocation().getY() * OE.scale);
		OE.root.getChildren().add(iceV);
		
		ice2 = new Image("File:src/columbusGame/ice.jpeg", OE.scale, OE.scale, true, true);
		iceV2 = new ImageView(ice2);
		iceV2.setX(OE.pirate2.getLocation().getX() * OE.scale);
		iceV2.setY(OE.pirate2.getLocation().getY() * OE.scale);
		OE.root.getChildren().add(iceV2);
	}
}

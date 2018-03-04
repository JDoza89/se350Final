package columbusGame;

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Concrete decorator class.<br/>
 * Ship can interact with the RedCoin and get additional stuff.
 */
public class BombardierShip extends ShipDecorator{
	Vessel navigable;
	RedCoin coin;
	OceanExplorer OE;
	Image explosion, explosion2;
	ImageView explosionV, explosionV2;

	
	/**
	 * Ship is now powered up with a laser.
	 * @param navigable
	 * @param oceanE
	 */
	public BombardierShip(Vessel navigable, OceanExplorer oceanE) {
		this.navigable = navigable;
		this.OE = oceanE;
		coin = (RedCoin) OE.redCoin;

	}
	
	/**
	 * Returns the current state of the Ship (with a laser).
	 */
	@Override
	public String getDescription() {
		return navigable.getDescription() + " with rockets.";
	}

	@Override
	void moveRight() {
		navigable.moveRight();
		if(coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
			explodeShip();
		}
	}

	@Override
	void moveLeft() {
		navigable.moveLeft();
		if(coin.getLocationOfCoin().equals(this.getLocation())){
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
			explodeShip();
		}
	}

	@Override
	void moveUp() {
		navigable.moveUp();
		if(coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
			explodeShip();
		}
	}

	@Override
	void moveDown() {
		navigable.moveDown();
		if(coin.getLocationOfCoin().equals(this.getLocation())) {
			navigable.deleteObservers();
			coin.resetCoinImage();
			coin.createCoin();
			explodeShip();
		}
		
	}
	/**
	 * Returns the current location of the powered up ship.
	 */
	@Override
	Point getLocation() {
		return navigable.getLocation();
	}
	
	/**
	 * Loads explosion image for a pirate.
	 */
	private void explodeShip() {
		explosion = new Image("File:src/columbusGame/boom.png", this.OE.scale, this.OE.scale, true, true);
		explosionV = new ImageView(explosion);
		explosionV.setX(this.OE.pirate.getLocation().getX() * this.OE.scale);
		explosionV.setY(this.OE.pirate.getLocation().getY() * this.OE.scale);
		this.OE.root.getChildren().add(explosionV);
		
		explosion2 = new Image("File:src/columbusGame/boom.png", this.OE.scale, this.OE.scale, true, true);
		explosionV2 = new ImageView(explosion2);
		explosionV2.setX(this.OE.pirate2.getLocation().getX() * this.OE.scale);
		explosionV2.setY(this.OE.pirate2.getLocation().getY() * this.OE.scale);
		this.OE.root.getChildren().add(explosionV2);
	}
}

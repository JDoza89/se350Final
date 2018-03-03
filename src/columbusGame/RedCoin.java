package columbusGame;

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RedCoin extends CoinCreator {
	ImageView coinImageview;
	Image coinImage;
	OceanExplorer ocean;
	RandomPoints random;
	Point rCoinLocation;
	int xCell, yCell;
	Islands island;
	PirateShip pirate1, pirate2;

	/**
	 * Creates one RedCoin on the grid.
	 * @param oceanE
	 */

	 //This constructor does not check the location of red coin because yellow is
	 //being created face so based off of this it will create itself somewhere else ??
	public RedCoin(OceanExplorer oceanE) {
		ocean = oceanE;
		island = ocean.island;
		pirate1 = ocean.pirate;
		pirate2 = ocean.pirate2;
		random = new RandomPoints(ocean.oceanGrid);
		xCell = random.generatePoints().x;
		yCell = random.generatePoints().y;
		rCoinLocation = new Point(xCell, yCell);
		if ((rCoinLocation.equals(ocean.ship.getLocation()) || rCoinLocation.equals(island.getLocation())
				|| rCoinLocation.equals(pirate1.getLocation()) || rCoinLocation.equals(pirate2.getLocation()))){
			xCell = random.generatePoints().x;
			yCell = random.generatePoints().y;
			rCoinLocation = new Point(xCell, yCell);
		}
	}

	@Override
	void createCoin() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(100);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		xCell = random.generatePoints().x;
		yCell = random.generatePoints().y;
		rCoinLocation = new Point(xCell, yCell);
		if (rCoinLocation.equals(ocean.ship.getLocation()) || rCoinLocation.equals(ocean.island.getLocation())
				|| rCoinLocation.equals(ocean.pirate.getLocation())
				|| rCoinLocation.equals(ocean.pirate2.getLocation())) {
			xCell = random.generatePoints().x;
			yCell = random.generatePoints().y;
			rCoinLocation = new Point(xCell, yCell);
		}
		loadCoinImage();
	}

	@Override
	Point getLocationOfCoin() {
		// TODO Auto-generated method stub
		return rCoinLocation;
	}

	@Override
	void resetCoinImage() {
		// TODO Auto-generated method stub
		coinImageview.setImage(null);
	}

	@Override
	void loadCoinImage() {
		// TODO Auto-generated method stub
		coinImage = new Image("File:src/columbusGame/redCoin.png", ocean.scale, ocean.scale, true, true);
		coinImageview = new ImageView(coinImage);
		coinImageview.setX(this.getLocationOfCoin().getX() * ocean.scale);
		coinImageview.setY(this.getLocationOfCoin().getY() * ocean.scale);
		ocean.root.getChildren().add(coinImageview);
	}

	public String getDescription() {
		coinDescription = "RedCoin:\nProvides the Ship the ability to stop and destroy ships.";
		return coinDescription;
	}
}

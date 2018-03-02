package columbusGame;

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Coin object
 */
public class YellowCoin extends CoinCreator {
	ImageView coinImageview;
	Image coinImage;
	OceanExplorer ocean;
	RandomPoints random;
	Point yCoinLocation;
	int xCell, yCell;
	/**
	 * Creates one coin on the grid.
	 * @param oceanE
	 */
	public YellowCoin(OceanExplorer oceanE) {
		ocean = oceanE;
		random = new RandomPoints(ocean.oceanGrid);
		xCell = random.generatePoints().x;
		yCell = random.generatePoints().y;
		yCoinLocation = new Point(xCell, yCell);
		if(yCoinLocation.equals(ocean.ship.getLocation()) || yCoinLocation.equals(ocean.island.getLocation()) 
				|| yCoinLocation.equals(ocean.pirate.getLocation()) || yCoinLocation.equals(ocean.pirate2.getLocation())) {
			xCell = random.generatePoints().x;
			yCell = random.generatePoints().y;
			yCoinLocation = new Point(xCell,yCell);
		}
	}
	
	/**
	 * Creates multiple coins on the grid.
	 */
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
		yCoinLocation = new Point(xCell, yCell);
		if (yCoinLocation.equals(ocean.ship.getLocation()) || yCoinLocation.equals(ocean.island.getLocation())
				|| yCoinLocation.equals(ocean.pirate.getLocation())
				|| yCoinLocation.equals(ocean.pirate2.getLocation())) {
			xCell = random.generatePoints().x;
			yCell = random.generatePoints().y;
			yCoinLocation = new Point(xCell, yCell);
		}
		loadCoinImage();
	}

	/**
	 * Returns the location of the yellow coin.
	 */
	@Override
	Point getLocationOfCoin() {
		return yCoinLocation;
	}

	/**
	 * Disappears the coin image.
	 */
	@Override
	void resetCoinImage() {
		// TODO Auto-generated method stub
		coinImageview.setImage(null);
	}
	
	/**
	 * Returns the description of the current coin.
	 */
	public String getDescription() {
		coinDescription = "YellowCoin:\nProvides the Ship the ability to stop being chased.";
		return coinDescription;
	}

	/**
	 * Loads the coin image on the grid.
	 */
	@Override
	void loadCoinImage() {
		// TODO Auto-generated method stub
		coinImage = new Image("File:src/columbusGame/yellowCoin.png", ocean.scale, ocean.scale, true, true);
		coinImageview = new ImageView(coinImage);
		coinImageview.setX(this.getLocationOfCoin().getX() * ocean.scale);
		coinImageview.setY(this.getLocationOfCoin().getY() * ocean.scale);
		ocean.root.getChildren().add(coinImageview);	
	}

}

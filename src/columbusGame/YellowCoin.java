package columbusGame;

import java.awt.Point;
import java.util.HashSet;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * YellowCoin:just stops the PirateShips from chasing Ship.
 */
public class YellowCoin implements OceanObjects, PowerUp {
	OceanMap ocean;
	private int xCell, yCell;
	private RandomPoints rand; // for random points
	
	/** YellowCoin needs to know about these.*/
	Ship cShip;
	OceanExplorer OE;
	Islands island;
	PirateShip pirate;
	PirateShip pirate2;
	RedCoin laserCoin;
	
	/**
	 * Empty constructor.
	 */
	public YellowCoin() {
		
	}
	/**
	 * YellowCoin needs to know about the location of all these:
	 * @param points
	 * @param ocean
	 * @param cShip
	 * @param oceanExplorer
	 * @param island
	 * @param pirate1
	 * @param pirate2
	 * @param laserCoin
	 */
	public YellowCoin(HashSet<Point> points, OceanMap ocean, Ship cShip, OceanExplorer oceanExplorer, Islands island,
			PirateShip pirate1, PirateShip pirate2, RedCoin laserCoin) {
		this.laserCoin = laserCoin;
		this.pirate = pirate1;
		this.pirate2 = pirate2;
		this.island = island;
		this.OE = oceanExplorer;
		this.ocean = ocean;
		this.cShip = cShip; // CC ship.
		rand = new RandomPoints(ocean);
		if (points.contains(rand.generatePoints()) == true) {
			rand = new RandomPoints(ocean);
		} else {
			xCell = rand.generatePoints().x;
			yCell = rand.generatePoints().y;
		}
	}
	
	

	/**
	 * This is called when the ship touches the coin and after some time it
	 * creates another yellow coin in another cell on the grid.
	 */
	private void createYellowCoins() {
		try {
			Thread.sleep(100);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Point islandLoc = island.getLocation();
		Point redCoin = laserCoin.getLocation();
		xCell = rand.generatePoints().x;
		yCell = rand.generatePoints().y;
		this.getLocation().setLocation(xCell, yCell);
		
		if(this.getLocation().equals(islandLoc) || this.getLocation().equals(redCoin)) {
			xCell = rand.generatePoints().x;
			yCell = rand.generatePoints().y;
			this.getLocation().setLocation(xCell, yCell);
		} else {
			this.getLocation().setLocation(xCell, yCell);
		}
		
		OE.yellowCoinImage = new Image("File:src/columbusGame/yellowCoin.png", OE.scale, OE.scale, true, true);
		OE.yellowCoinImageView = new ImageView(OE.yellowCoinImage);
		OE.yellowCoinImageView.setX(this.getLocation().getX() * OE.scale);
		OE.yellowCoinImageView.setY(this.getLocation().getY() * OE.scale);
		OE.root.getChildren().add(OE.yellowCoinImageView);
	}

	/**
	 * Returns the current location of the YellowCoin.
	 */
	public Point getLocation() {
		return new Point(xCell, yCell);
	}

	/**
	 * LevelUp: PirateShips stop chasing Ship.
	 */
	@Override
	public void levelUp() {
		if (resetCoinImage()) {
			stopPirates();
		}
		createYellowCoins(); // after leveling up this is called
	}

	/**
	 * Resets the coin's image.
	 */
	private boolean resetCoinImage() {
		OE.yellowCoinImageView.setImage(null); // deletes the coin image.
		return true;
	}
	
	
	/**
	 * Function that deletes the observers/stop pirate ships.
	 */
	private void stopPirates() {
		OE.ship.deleteObservers();
	}
}
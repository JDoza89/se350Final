package columbusGame;

import java.awt.Point;
import java.util.HashSet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * The RedCoin object for laser power up.
 * Crates lasers around a pirate ship; freezes the pirate ship or explode it.
 */
public class RedCoin implements OceanObjects, PowerUp{
	OceanMap ocean;
	private int xCell, yCell;
	private RandomPoints rand;	/* For random points.*/ 
	
	/** Coin needs to know about these.*/
	Ship cShip;
	OceanExplorer OE;
	Islands island;
	PirateShip pirate;
	PirateShip pirate2;
	
	/**
	 * RedCoin object needs to know the location of all these:
	 * @param points
	 * @param ocean
	 * @param cShip
	 * @param oceanExplorer
	 * @param island
	 * @param pirate
	 * @param pirate2
	 */
	public RedCoin(HashSet<Point> points, OceanMap ocean, Ship cShip, OceanExplorer oceanExplorer, Islands island,
			PirateShip pirate, PirateShip pirate2) {
		this.ocean = ocean;
		this.cShip = cShip;
		this.OE = oceanExplorer;
		this.island = island;
		rand = new RandomPoints(ocean);
		/* If set contains this points, generate new ones.*/
		if(points.contains(rand.generatePoints())) {
			rand = new RandomPoints(ocean);
		} else {
			/* Set the x,y */
			xCell = rand.generatePoints().x;
			yCell = rand.generatePoints().y;
		}
	}
	/**
	 * Empty constructor
	 */
	public RedCoin() {
		
	}
	
	/**
	 * Create RedCoins on the grid.
	 */
	private void createRedCoins() {
		try {
			Thread.sleep(100);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Point islandLoc = island.getLocation();
		xCell = rand.generatePoints().x;
		yCell = rand.generatePoints().y;
		this.getLocation().setLocation(xCell, yCell);
		if (this.getLocation().equals(islandLoc)) {
			xCell = rand.generatePoints().x;
			yCell = rand.generatePoints().y;
			this.getLocation().setLocation(xCell, yCell);
		} else {
			this.getLocation().setLocation(xCell, yCell);
		}
		
		OE.redCoinImage = new Image("File:src/columbusGame/redCoin.png", OE.scale, OE.scale, true, true);
		OE.redCoinImageView = new ImageView(OE.redCoinImage);
		OE.redCoinImageView.setX(this.getLocation().getX() * OE.scale);
		OE.redCoinImageView.setY(this.getLocation().getY() * OE.scale);
		OE.root.getChildren().add(OE.redCoinImageView);
	}
	
	/**
	 * Ship, levels up, resets the RedCoin's image and then activates laser?
	 */
	@Override
	public void levelUp() {
		if (resetCoinImage()) {
			
		}
		createRedCoins();
	}

	
	/**
	 * Returns the current location of the RedCoin
	 */
	public Point getLocation() {
		return new Point(xCell, yCell);
	}
	
	

	/**
	 * Resets the red coin image
	 */
	private boolean resetCoinImage() {
		OE.redCoinImageView.setImage(null); // deletes the coin image.
		return true;
	}
}

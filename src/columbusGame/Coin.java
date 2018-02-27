package columbusGame;

import java.awt.Point;

import java.util.HashSet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Coin Object
 */
public class Coin implements OceanObjects, PowerUp {
	private OceanMap ocean;
	private int xCell, yCell;
	private RandomPoints rand; // for random points
	
	 Ship cShip; // coin needs to know about the ship
	 
	private OceanExplorer OE;
	private Islands island; // coin needs to know about islands??
	PirateShip pirate;
	PirateShip pirate2;

	/**
	 * Constructs a coin on the ocean.
	 */
	public Coin(HashSet<Point> points, OceanMap ocean, Ship cShip, OceanExplorer oceanExplorer,  Islands island,
			PirateShip pirate1, PirateShip pirate2) {
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
		System.out.println("Coin constructed at:" + xCell + "," + yCell); // this was just to see where it was being created
	}

	/**
	 * This is called when the ship touches the coin and after 1 second it should
	 * create a coin in another cell within the grid.
	 */
	private void createCoins() {
		try {
			Thread.sleep(100);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// creates new coin somewhere in the grid here
		rand = new RandomPoints(ocean);
		xCell = rand.generatePoints().x;
		yCell = rand.generatePoints().y;
		Point islandLocation = island.getLocation();
		this.getLocation().setLocation(xCell, yCell); // set new locations of the coin to some x,y
		if (islandLocation.equals(this.getLocation())) { // if equal to an island's, generate new x,y
			xCell = rand.generatePoints().x;
			yCell = rand.generatePoints().y;
			this.getLocation().setLocation(xCell, yCell);
		} else {
			this.getLocation().setLocation(xCell, yCell);
		}
		System.out.println("New coin created here: " + this.getLocation().getX() + "," + this.getLocation().getY());
		OE.coinImage = new Image("File:src/columbusGame/coin.jpg", OE.scale, OE.scale, true, true);
		OE.coinImageView = new ImageView(OE.coinImage);
		OE.coinImageView.setX(this.getLocation().getX() * OE.scale);
		OE.coinImageView.setY(this.getLocation().getY() * OE.scale);
		OE.root.getChildren().add(OE.coinImageView);
	}

	/**
	 * Returns the current location of the Coin.
	 */
	public Point getLocation() {
		return new Point(xCell, yCell);
	}

	@Override
	/**
	 * Bomb level up, explodes all pirate ships.
	 */
	public void levelUp() {
		if (resetCoinImage()) {
			bombPirates();
		}
		createCoins(); // after leveling up this is called
	}

	/**
	 * Resets the coin's image.
	 * @return
	 */
	private boolean resetCoinImage() {
		OE.coinImageView.setImage(null); // deletes the coin image.
		return true;
	}
	
	/**
	 * Resets a pirate's ship image.
	 */
	private void resetPirateImage() {
		OE.pirateImageView.setImage(null);
		OE.pirate2ImageView.setImage(null);
		this.ocean.getMap()[OE.pirate.getLocation().x][OE.pirate.getLocation().y] = 0;
		this.ocean.getMap()[OE.pirate2.getLocation().x][OE.pirate2.getLocation().y] = 0;
	}
	
	
	/**
	 * Funtion that destroys pirate ships.
	 * remove them as observers
	 */
	private void bombPirates() {
//		System.out.println(
//				"Pirate1 location: " + this.pirate.getLocation().getX() + "," + this.pirate.getLocation().getY());
//		System.out.println(
//				"Pirate2 location: " + this.pirate2.getLocation().getX() + "," + this.pirate2.getLocation().getY());
		
		resetPirateImage();
	}

}


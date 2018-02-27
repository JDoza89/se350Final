package columbusGame;
import java.awt.Point;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
public class Coin implements OceanObjects{
	Point coinLocation;	// location of the coint
	Point shipLoc, pirateShipLoc, pirate2ShipLoc, islandLoc;
	OceanMap ocean;
	int xCell, yCell;
	RandomPoints rand;	// for random points
	Timer timer;
	TimerTask timerTask;
	Islands island;
	
	
	public Coin(HashSet<Point> points, OceanMap ocean) {
		this.ocean = ocean;
		rand = new RandomPoints(ocean);
		if(points.contains(rand.generatePoints()) == true) {
//			rand = new RandomPoints();	//>DD
		}
	}
	
	
	

	
	/*
	 * Returns location of coin.
	 */
	public Point getLocation() {
		coinLocation = new Point(xCell, yCell);
		return coinLocation;
	}
	
	
	
	
}

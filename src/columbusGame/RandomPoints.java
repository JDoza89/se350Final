package columbusGame;

import java.awt.Point;
import java.util.Random;

public class RandomPoints {
	
	Random x = new Random();
	Random y = new Random();
	int xRand;                           //creating random ints
	int yRand;
	Point rand = new Point(0,0);
	OceanMap ocean;
	int[][] islandMap;

	
	public RandomPoints() {
	    this.ocean = ocean.getInstance();
		
	}
	
	public Point generatePoints() {
		/* Will return random Points that can be used for islands or pirateShips
		 */
		islandMap = ocean.getMap(); 
			/* Creating random ints that are less than the map size, so that we can randomly
			 * place islands on the map
			 */
			xRand = x.nextInt(islandMap.length);      
			yRand = y.nextInt(islandMap.length);
			
				rand = new Point(xRand, yRand);
				return rand;
		}
	
}

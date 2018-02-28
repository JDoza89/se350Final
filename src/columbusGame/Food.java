package columbusGame;

import java.awt.Point;

public class Food implements OceanObjects{
	RandomPoints rand;
	Point point;
	OceanMap ocean;
	
	public Food() {
	ocean = ocean.getInstance();
	rand = new RandomPoints();
	point = rand.generatePoints();
	}
	@Override
	public Point getLocation() {
		return point;
	}

}

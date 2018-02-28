package columbusGame;

import java.awt.Point;
import java.util.ArrayList;

public class SnakeHead implements BadGuys, OceanObjects{
	ArrayList<SnakeBody> body;
	int x, y;
	OceanMap ocean;
	RandomPoints rand;
	Point point;
	public SnakeHead() {
		rand = new RandomPoints();
		point = rand.generatePoints();
		x = point.x;
		y = point.y;
	}
	
	@Override
	public Boolean contains(double x, double y) {
		if(x == this.x && y == this.y)
			return true;
		for(SnakeBody m : body) {
			if(x == m.getLocation().x  && y == m.getLocation().y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Point getLocation() {
		return new Point(x,y);
	}

}

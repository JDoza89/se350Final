package columbusGame;

import java.awt.Point;

public class Monster implements BadGuys, OceanObjects{
	int x;
	int y;
	
	@Override
	public Boolean contains(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	public Point move() {
		// TODO Auto-generated method stub
		return null;
	}

	public Point getLocation() {
		return new Point(this.x, this.y);
	}
	
	
}

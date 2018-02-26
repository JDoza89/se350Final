package columbusGame;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MonsterArea implements BadGuys, OceanObjects{
	ArrayList<Monster> monsters;
	double x, y, width, height;
	Rectangle area;
	Color color;
	OceanMap ocean;
	
	public MonsterArea(double x, double y) {
		this(x, y, 5, 5);
	}
	
	public MonsterArea(double x, double y, double width, double height){
		ocean = ocean.getInstance();
	    monsters = new ArrayList<>();
	    RandomPoints p = new RandomPoints(ocean);
	    Point point = p.generatePoints();
	    x = point.getX();
	    y = point.getY();
	    
	    for(int i = (int)x; x< 10; x++) {
	    	for(int j = (int)y; y < 5; y++) {
	    	ocean.setPoint(i, j, 2, Color.RED);
	    	}
	    }
	    }


	@Override
	public Boolean contains(double x, double y) {
		if(x == this.x && y == this.y)
			return true;
		for(Monster m : monsters) {
			if(x == m.getLocation().x  && y == m.getLocation().y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	public Rectangle getArea() {
		return area;
	}

}

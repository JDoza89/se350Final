package columbusGame;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MonsterArea implements BadGuys{
	ArrayList<Monster> monsters;
	double x, y, width, height;
	Rectangle area;
	Color color;
	OceanMap ocean;
	
	/*public MonsterArea(double x, double y) {
		this(x,y,ocean);
	}*/
	
	public MonsterArea(double x, double y, OceanMap ocean) {
		// TODO Auto-generated method stub
		
		ocean = ocean.getInstance();
	    monsters = new ArrayList<>();
	    
	    
	    for(int i = (int)x; x< 10; x++) {
	    	for(int j = (int)y; y < 10; y++) {
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

	public void add(Monster monster) {
		monsters.add(monster);
		ocean.getInstance().setPoint(monster.getX(), monster.getY(), 2);
		
	}

	public void remove() {
		monsters.remove(monsters.get(monsters.size()-1));
		
	}

	
	

}

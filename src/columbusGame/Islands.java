package columbusGame;
 
import java.util.HashSet;
import java.awt.Point;
public class Islands implements OceanObjects{
	int xCell;
	int yCell;
	int unitSize;
	OceanMap ocean;
	Islands island;
	RandomPoints rand;
	
	
	public Islands(HashSet<Point> p) {
		ocean = ocean.getInstance();
		//creates new Points (islands) based on the passed HashSet
		//makes sure that the points are not already in use by another object
		
		rand = new RandomPoints();
			if(p.contains(rand.generatePoints()) == true) {
				rand = new RandomPoints();
			}
			else {
				xCell = rand.generatePoints().x; 
				yCell = rand.generatePoints().y;
				setPoints();
			}
			
		}	
	
	public void setPoints() {
		    		//setting the coordinate to 2 which will symbolize islands
			ocean.setPoint(xCell, yCell, 2);
		   	 
	}
	
	public Point getLocation() {
		//returns the location of the island
		 return new Point(xCell, yCell);
		}

	
		
		
	}



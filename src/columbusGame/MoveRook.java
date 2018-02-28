package columbusGame;

import java.awt.Point;

public class MoveRook implements MoveStrategy{
	//This should move Pirate ships 3 places 
	
	OceanMap ocean;
	int xCell;
	int yCell;
	int unitSize;
	Point shipLocation;
	Point pirateLocation;
	RandomPoints rand;

	
	public void moveRight() {
		try {
			if(ocean.getMap()[xCell+1][yCell] == 0) {
				xCell++;
					}
			}
		catch(IndexOutOfBoundsException e) {

					}
			}
	
	
	
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	
	public void moveUp() {
		// TODO Auto-generated method stub
		
	}

	
	public void moveDown() {
		// TODO Auto-generated method stub
		
	}

	

}

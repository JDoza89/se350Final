package columbusGame;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

//Basically we are encapsulating the observer movement method
//Some of the pirate ships will be going towards the player ship
//while others will be using the other movement methods 


public class MoveTowards implements MoveStrategy, Observer {
	
	OceanMap ocean;
	int xCell;
	int yCell;
	int unitSize;
	Point shipLocation;
	Point pirateLocation;
	RandomPoints rand;

	public void setPoints() {
	ocean.setPoint(xCell, yCell, 3);
		}
	
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
			try {
				if(ocean.getMap()[xCell-1][yCell] == 0) {
					xCell--;
						}
				}
			
			
				catch(IndexOutOfBoundsException e) {
						}
				}

	public void moveUp() {
			try{
				if(ocean.getMap()[xCell][yCell-1] == 0) {
					yCell--;
					}
				}
				catch(IndexOutOfBoundsException e) {

					}

				}
	
	
	public void moveDown() {

			try {
				if(ocean.getMap()[xCell][yCell+1] == 0) {
					yCell++;
					}
				}
			catch(IndexOutOfBoundsException e) {
	
					}
	
				}

	public Point getLocation() {
		//returns the pirateShip's location
			return new Point(xCell, yCell);
				}


	public void update(Observable obs, Object arg) {
//Checks if the Observable is of type Ship and gets the ship location, sets the old pirateLocation back to 0, so the point on the grid is not blocked and calls movePirate()
		if(obs instanceof Ship) {
			shipLocation = ((Ship)obs).getLocation();
			ocean.setPoint(xCell, yCell, 0);
			movePirate();
		}

	}



	public void movePirate(){

		Random r = new Random();
	
		if(r.nextInt(2) == 1) {//Slows down the movement of the pirateShip

			//Moves the pirate Ship based on its location in terms of the ship's location, calls the move methods from above based on this
			pirateLocation = this.getLocation();
			if (shipLocation.x > pirateLocation.x) {
				moveRight();
			}
			else {
				moveLeft();
			}


			if (shipLocation.y > yCell) {
				moveDown();
			}
			else {
				moveUp();
			}

		}

//sets the point on the grid to 3 
		ocean.setPoint(xCell, yCell, 3);
}
	
	
	

}

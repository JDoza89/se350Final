package columbusGame;

import java.awt.Point;

public class Ship extends Vessel{
	int xCell;
	int yCell;
	int unitSize;
	OceanMap ocean;
	
	public Ship(int xCell, int yCell, int unitSize, OceanMap ocean) {
		this.xCell = xCell;
		this.yCell = yCell;
		this.unitSize = unitSize;
		this.ocean = ocean;
	}
	
	
	@Override
	void moveRight() {
		try {
			if (ocean.getMap()[xCell + 1][yCell] == 0) {
				xCell++;
			}
		} catch (IndexOutOfBoundsException e) {

		}
		
		movementChanged();
	}

	@Override
	void moveLeft() {
		try {
			if (ocean.getMap()[xCell - 1][yCell] == 0) {
				xCell--;
			}
		} catch (IndexOutOfBoundsException e) {

		}
		
		movementChanged();
	}

	@Override
	void moveUp() {
		try {
			if (ocean.getMap()[xCell][yCell - 1] == 0) {
				yCell--;
			}
		} catch (IndexOutOfBoundsException e) {

		}
		
		movementChanged();
	}

	@Override
	void moveDown() {
		try {
			if (ocean.getMap()[xCell][yCell + 1] == 0) {
				yCell++;
			}

		} catch (IndexOutOfBoundsException e) {

		}
		movementChanged();
	}
	
	public void movementChanged() {
		setChanged();
		notifyObservers();
	}


	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return new Point(xCell, yCell);
	}


	public String getDescription() {
		description = "Columbus ship";	// description is already in vessel.
		return description;
	}
}

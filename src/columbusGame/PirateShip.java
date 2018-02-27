package columbusGame;

import java.util.Random;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements Observer, OceanObjects, MoveStrategy {
	OceanMap ocean;
	int xCell;
	int yCell;
	int unitSize;
	Point shipLocation;
	Point pirateLocation;
	RandomPoints rand;
	Observable observable;

	public PirateShip(OceanMap ocean, Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
		this.ocean = ocean;

		rand = new RandomPoints(ocean);
		// creates new random points and checks to make sure that they are not the same
		// as the location of ship
		if (rand.generatePoints() == ((Ship) (observable)).getLocation()) {
			rand = new RandomPoints(ocean);
		} else {
			xCell = rand.generatePoints().x;
			yCell = rand.generatePoints().y;

		}
		setPoints();
	}

	public void setPoints() {
		// setting the coordinate to 3 which will symbolize islands
		ocean.setPoint(xCell, yCell, 3);

	}

	// Checks to see if the next index is 0 so that the ship can move, cannot move
	// if the index == 2 or 3, (pirateShip or island) and catches the
	// outBoundsException
	// the ship cannot go off the grid
	public void moveRight() {
		try {
			if (ocean.getMap()[xCell + 1][yCell] == 0) {

				xCell++;
			}
		} catch (IndexOutOfBoundsException e) {

		}

	}

	public void moveLeft() {
		try {
			if (ocean.getMap()[xCell - 1][yCell] == 0) {
				xCell--;
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void moveUp() {
		try {
			if (ocean.getMap()[xCell][yCell - 1] == 0) {
				yCell--;
			}
		} catch (IndexOutOfBoundsException e) {

		}

	}

	public void moveDown() {

		try {
			if (ocean.getMap()[xCell][yCell + 1] == 0) {
				yCell++;
			}
		} catch (IndexOutOfBoundsException e) {

		}

	}

	public Point getLocation() {
		// returns the pirateShip's location
		return new Point(xCell, yCell);
	}

	@Override
	public void update(Observable obs, Object arg) {
		// Checks if the Observable is of type Ship and gets the ship location, sets the
		// old pirateLocation back to 0, so the point on the grid is not blocked and
		// calls movePirate()
		if (obs instanceof Ship) {
			shipLocation = ((Ship) obs).getLocation();
			ocean.setPoint(xCell, yCell, 0);
			movePirate();
		}

	}

	public void movePirate() {

		Random r = new Random();

		if (r.nextInt(2) == 1) {// Slows down the movement of the pirateShip

			// Moves the pirate Ship based on its location in terms of the ship's location,
			// calls the move methods from above based on this
			pirateLocation = this.getLocation();
			if (shipLocation.x > pirateLocation.x) {
				moveRight();
			} else {
				moveLeft();
			}

			if (shipLocation.y > yCell) {
				moveDown();
			} else {
				moveUp();
			}

		}

		// sets the point on the grid to 3
		ocean.setPoint(xCell, yCell, 3);
	}
}

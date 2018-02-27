package columbusGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class OceanMap {
	private static OceanMap isUnique;

	int size;
	int[][] ocean;

	// construct instance of a map with size.
	public static OceanMap setInstance(int size) {
		if (isUnique == null) {
			isUnique = new OceanMap(size);
		}
		return isUnique;
	}

	private OceanMap(int size) {
		// sets the given size to the size of the map and calls the method that
		// initializes the grid to a sizeXsize grid
		this.size = size;
		initGrid();

	}

	private void initGrid() {
		// initializes grid to a sizeXsize int[][] (grid)
		ocean = new int[size][size];

	}

	public void setPoint(int x, int y, int k) {
		// This is used by the islands and pirateShips to set points to 2 or 3, so that
		// the ship cannot collide with them
		ocean[x][y] = k;

	}

	public void setPoint(int x, int y, int k, Color color) {
		ocean[x][y] = k;
		Rectangle rect = new Rectangle(x, y);
		rect.setFill(color);
	}

	public Point getStartPoint() {
		// returns the start point
		return new Point(1, 1);
	}

	public int[][] getMap() {
		return ocean;
	}

	public static OceanMap getInstance() {
		return isUnique;
	}
}
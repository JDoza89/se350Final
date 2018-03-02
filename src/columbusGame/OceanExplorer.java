package columbusGame;

import java.awt.Point;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.HashSet;

public class OceanExplorer extends Application {

	OceanMap oceanGrid;
	int[][] islandMap;
	final int dimension = 20; // We are creating 10X10 maps
	final int scale = 35; // Scale everything by 50
	Scene scene; // Instantiating all variables that will be used in this class
	AnchorPane root;
	ImageView shipImageView, islandImageView, pirateImageView, pirate2ImageView, explosionImageView;
	Image shipImage, islandImage, pirateImage, pirate2Image, explosionImage;

	Vessel ship;
	CoinCreator yellowCoin;
	CoinCreator redCoin;

	Islands island;
	PirateShip pirate, pirate2;
	RandomPoints rand;
	Button button;
	Point r;
	MonsterArea area;

	HashSet<Point> points;

	@Override
	public void start(Stage oceanStage) throws Exception {
		oceanGrid = OceanMap.setInstance(dimension);
		Point startPoint = oceanGrid.getStartPoint();
		points = new HashSet<Point>();
		points.add(new Point(startPoint.x, startPoint.y));
		root = new AnchorPane();
		drawGrid();

		/** Creates 10 islands. **/
		int counter = 0;
		while (counter < 10) {
			island = new Islands(points, oceanGrid);
			if (points.contains(island.getLocation()) == false) {
				island.setPoints();
				points.add(island.getLocation());
				loadIslandImages();
				counter++;
			} else {
				island = new Islands(points, oceanGrid);
			}
		}


		// create a cc ship.
		ship = new Ship(startPoint.x, startPoint.y, scale, oceanGrid);
		loadShipImages();

		/** Pirates **/
		pirate = new PirateShip(ship);
		points.add(pirate.getLocation());
		pirate2 = new PirateShip(ship);
		points.add(pirate2.getLocation());
		ship.addObserver(pirate);
		ship.addObserver(pirate2);
		loadPirateImages();
		loadPirate2Images();

		/** Create coins. */
		yellowCoin = new YellowCoin(this);
		yellowCoin.loadCoinImage();
		System.out.println(yellowCoin.getDescription());
		redCoin = new RedCoin(this);
		redCoin.loadCoinImage();
		System.out.println(redCoin.getDescription());

		/**
		 * Decorate the ship to interact with the coins and obtain additional
		 * functionality.
		 **/
		ship = new PausableShip(ship, this);
		ship = new LaserShip(ship, this);
		

		scene = new Scene(root, 700, 700);
		oceanStage.setTitle("Columbus Game");
		oceanStage.setScene(scene);
		oceanStage.show();
		startSailing();
	}

	// Draws the grid and uses Paleturquiose as hte color of the ocean (water)
	public void drawGrid() {
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect); // Add to the node tree in the pane
			}
		}
	}

	// loads ship image and scales it to size
	private void loadShipImages() {
		shipImage = new Image("File:src/columbusGame/ship.png", scale, scale, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getLocation().x * scale);
		shipImageView.setY(ship.getLocation().y * scale);
		root.getChildren().add(shipImageView);
	}

	// loads island image and scales it to size
	private void loadIslandImages() {
		islandImage = new Image("File:src/columbusGame/island.jpg", scale, scale, true, true);
		islandImageView = new ImageView(islandImage);
		islandImageView.setX((island.getLocation().x * scale));
		islandImageView.setY((island.getLocation().y * scale));
		root.getChildren().add(islandImageView);

	}

	// loads pirate ship image and scales it to size
	private void loadPirateImages() {
		pirateImage = new Image("File:src/columbusGame/pirateShip.png", scale, scale, true, true);
		pirateImageView = new ImageView(pirateImage);
		pirateImageView.setX((pirate.getLocation().x * scale));
		pirateImageView.setY((pirate.getLocation().y * scale));
		root.getChildren().add(pirateImageView);

	}

	// loads Pirate ship image and scales it to size (2 were created because they
	// move in different directions)
	private void loadPirate2Images() {
		pirate2Image = new Image("File:src/columbusGame/pirateShip.png", scale, scale, true, true);
		pirate2ImageView = new ImageView(pirate2Image);
		pirate2ImageView.setX((pirate2.getLocation().x * scale));
		pirate2ImageView.setY((pirate2.getLocation().y * scale));
		root.getChildren().add(pirate2ImageView);

	}

	// loads the explosion image (2 were created because there are 2 possible
	// collisions with 2 different objects)
	private void loadExplosionImages() {
		explosionImage = new Image("File:src/columbusGame/explosion.jpg", scale, scale, true, true);
		shipImageView = new ImageView(explosionImage);
		shipImageView.setX(ship.getLocation().x * scale);
		shipImageView.setY(ship.getLocation().y * scale);
		root.getChildren().add(shipImageView);
		pirateImageView = new ImageView(explosionImage);
		pirateImageView.setX(pirate.getLocation().x * scale);
		pirateImageView.setY(pirate.getLocation().y * scale);
		root.getChildren().add(pirateImageView);

	}

	private void loadExplosionImages2() {
		explosionImage = new Image("File:src/columbusGame/explosion.jpg", scale, scale, true, true);
		shipImageView = new ImageView(explosionImage);
		shipImageView.setX(ship.getLocation().x * scale);
		shipImageView.setY(ship.getLocation().y * scale);
		root.getChildren().add(shipImageView);
		pirate2ImageView = new ImageView(explosionImage);
		pirate2ImageView.setX(pirate2.getLocation().x * scale);
		pirate2ImageView.setY(pirate2.getLocation().y * scale);
		root.getChildren().add(pirate2ImageView);

	}

	private void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				if (ship.getLocation().equals(pirate.getLocation()) == false
						&& ship.getLocation().equals(pirate2.getLocation()) == false) {
					switch (ke.getCode()) {
					case RIGHT:
						ship.moveRight();
						break;
					case LEFT:
						ship.moveLeft();
						break;
					case DOWN:
						ship.moveDown();
						break;
					case UP:
						ship.moveUp();
						break;
					default:
						break;
					}

					shipImageView.setX(ship.getLocation().x * scale);
					shipImageView.setY(ship.getLocation().y * scale);

					pirateImageView.setX(pirate.getLocation().x * scale);
					pirateImageView.setY(pirate.getLocation().y * scale);

					pirate2ImageView.setX(pirate2.getLocation().x * scale);
					pirate2ImageView.setY(pirate2.getLocation().y * scale);

				}
				/**
				 * Here we check to see if there has been a collision. If there has been, we
				 * load the ExplosionImages, which changes the shipImageView and
				 * pirateShipImageView to an explosion
				 */
				if (ship.getLocation().equals(pirate.getLocation())) {
					loadExplosionImages();

				}
				if (ship.getLocation().equals(pirate2.getLocation())) {
					loadExplosionImages2();
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}

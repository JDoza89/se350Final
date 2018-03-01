package columbusGame;


import java.util.Observable;
import java.util.Observer;


import javafx.scene.text.Font;
import java.awt.Point;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Iterator;

//added this for cams branch

public class OceanExplorer extends Application{
	 
	OceanMap oceanGrid;
	int[][] islandMap;
	final int dimension = 20; // We are creating 10X10 maps
	final int scale = 50; // Scale everything by 50
	Scene scene;                                                                     //Instantiating all variables that will be used in this class
	AnchorPane root;
	ImageView shipImageView, islandImageView, pirateImageView, pirate2ImageView, explosionImageView, foodImageView, snakeImageView, bodyImageView;
	Image shipImage, islandImage, pirateImage, pirate2Image, explosionImage, foodImage, snakeImage, bodyImage;
	Ship ship;
	Islands island;
	PirateShip pirate, pirate2;
	RandomPoints rand;
	Button button;
	Point r;
	MonsterArea area;
	//Food food;
	SnakeHead snakeHead;
	MoveStrategy searchStrategy;
	PirateShipFactory pirateShipFactory;
	
	    @Override
	 	public void start(Stage oceanStage) throws Exception {
	    	
	    	/** Creating the initial grid (What you first see)
	    	 * 
	    	 */
	    	oceanGrid = OceanMap.setInstance(dimension);
	    	Point startPoint = oceanGrid.getStartPoint();
	    	area = new MonsterArea(1,2,oceanGrid);
	    	
	    
	    	// Creating a hashSet to keep track of random points that are used for the islands and pirateShips, so they are not reused
	    	HashSet<Point> points = new HashSet<Point>();
	    	//adding the startPoints of the ship to the HashSet so it is not reused
	    	points.add(new Point(startPoint.x, startPoint.y));
	    	int counter = 0;
	    	root = new AnchorPane();
	    	//drawing the grid and creating the ship 
	    	drawGrid();
	    	
	    	ship = new Ship(startPoint.x, startPoint.y, scale, oceanGrid);
	    	loadShipImages();
	    	//creating pirateShips and adding their locations to the hashSet, so they are not reused

	    	pirate = pirateShipFactory.getPirateShip("pirate1");

	      	ship.addObserver(pirate);    //adding the pirateShip as an Observer of the ship
	      	points.add(pirate.getLocation());
	    	loadPirateImages();       //Loads the image of the pirateShip

	    	pirate2 =pirateShipFactory.getPirateShip("pirate2");

	      	
	    	//pirate2 = new PirateShip(ship);

	      	//pirate = PirateShipFactory.getPirateShip("pirate1");

	    
	      	ship.addObserver(pirate2);    //adding the pirateShip as an Observer of the ship
	      	points.add(pirate.getLocation());
	    	loadPirateImages();       //Loads the image of the pirateShip
	    	
	    	//pirate2 = PirateShipFactory.getPirateShip("pirate2");
	    	

	      	
	      	
	      	points.add(pirate2.getLocation());
	    	loadPirate2Images();
	    	
	    	while(counter < 10) {  //Creating 10 islands at different random points, using the hashSet to make sure that they are all at different points
	    		island = new Islands(points);
	    		if(points.contains(island.getLocation()) == false) {
	    			points.add(island.getLocation());
	    			loadIslandImages();
	    			counter++;
	    	}
	    		else {
	    			island = new Islands(points);
	    			
	    		}
	    			
	    		}
	
	    	//Creating the Scene and calling the startSailing method which controls the ships movements
    		
	    	scene = new Scene(root, 1000, 1000);

	    	oceanStage.setTitle("~~Columbus Sails the Ocean Blue~~");
	    	oceanStage.setScene(scene);
	    	oceanStage.show();
	    	
	    	startSailing();
	    	
	    	
	    	
	    }
	    //Draws the grid and uses Paleturquiose as hte color of the ocean (water)
	 public void drawGrid() {
	   	    for(int x = 0; x < dimension; x++){
			  for(int y = 0; y < dimension; y++){
			 Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
			 rect.setStroke(Color.BLACK); 
			 rect.setFill(Color.PALETURQUOISE); 
			 root.getChildren().add(rect); // Add to the node tree in the pane
			  }
	   	    }
	   	    }
	 //loads ship image and scales it to size
	 /*
	 private void loadFishImages(){
		 foodImage = new Image("\\fish.jpg", scale, scale, true, true);
		 foodImageView = new ImageView(foodImage);
		 foodImageView.setX(food.getLocation().x * scale); 
		 foodImageView.setY(food.getLocation().y * scale); 
		 root.getChildren().add(foodImageView);
	 }
	 */
	 private void loadSnakeImages(){
		 snakeImage = new Image("\\snakeHead.PNG", scale, scale, true, true);
		 snakeImageView = new ImageView(snakeImage);
		 snakeImageView.setX(snakeHead.getLocation().x * scale); 
		 snakeImageView.setY(snakeHead.getLocation().y * scale); 
		 root.getChildren().add(snakeImageView);
	 }
	 private void loadShipImages() {
		 shipImage = new Image("\\ship.png", scale, scale, true, true);
		 shipImageView = new ImageView(shipImage);
		 shipImageView.setX(ship.getLocation().x * scale); 
		 shipImageView.setY(ship.getLocation().y * scale); 
		 root.getChildren().add(shipImageView);
	 }
	 //loads island image and scales it to size
	 private void loadIslandImages() {
		 islandImage = new Image("\\island.jpg", scale, scale, true, true);
		 islandImageView = new ImageView(islandImage);
		 islandImageView.setX((island.getLocation().x * scale)); 
		 islandImageView.setY((island.getLocation().y * scale)); 
		 root.getChildren().add(islandImageView);
		 
	 }
	 //loads pirate ship image and scales it to size
	 private void loadPirateImages() {
		 pirateImage = new Image("\\pirateShip.png", scale, scale, true, true);
		 pirateImageView = new ImageView(pirateImage);
		 pirateImageView.setX((pirate.getLocation().x * scale)); 
		 pirateImageView.setY((pirate.getLocation().y * scale)); 
		 root.getChildren().add(pirateImageView);
		 
	 }
	 //loads Pirate ship image and scales it to size  (2 were created because they move in different directions)
	 private void loadPirate2Images() {
		 pirate2Image = new Image("\\pirateShip.png", scale, scale, true, true);
		 pirate2ImageView = new ImageView(pirate2Image);
		 pirate2ImageView.setX((pirate2.getLocation().x * scale)); 
		 pirate2ImageView.setY((pirate2.getLocation().y * scale)); 
		 root.getChildren().add(pirate2ImageView);
		 
	 }
	 
	 //loads the explosion image (2 were created because there are 2 possible collisions with 2 different objects)
	 private void loadExplosionImages() {
		 explosionImage = new Image("\\explosion.jpg", scale, scale, true, true);
			shipImageView = new ImageView(explosionImage);
			shipImageView.setX(ship.getLocation().x*scale);
	    	shipImageView.setY(ship.getLocation().y*scale);
	    	root.getChildren().add(shipImageView);
			pirateImageView = new ImageView(explosionImage);
			pirateImageView.setX(pirate.getLocation().x*scale);
	    	pirateImageView.setY(pirate.getLocation().y*scale);
	    	root.getChildren().add(pirateImageView);
		     
	 }
	 private void loadExplosionImages2() {
		 explosionImage = new Image("\\explosion.jpg", scale, scale, true, true);
			shipImageView = new ImageView(explosionImage);
			shipImageView.setX(ship.getLocation().x*scale);
	    	shipImageView.setY(ship.getLocation().y*scale);
	    	root.getChildren().add(shipImageView);
			pirate2ImageView = new ImageView(explosionImage);
			pirate2ImageView.setX(pirate2.getLocation().x*scale);
	    	pirate2ImageView.setY(pirate2.getLocation().y*scale);
	    	root.getChildren().add(pirate2ImageView);
		     
	 }
	 
	
	 private void startSailing() { 
		 scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		
			 /** This handles the key pressing (cases right, left, down, up == pressing the right, left, down, up keypad arrows
			  * 
			  */
		public void handle(KeyEvent ke) {
			/** We are only allowing the player to play the game(or press keys with a reaction) if there have been no collisions
			 * 
			 */
			if(ship.getLocation().equals(pirate.getLocation()) == false && ship.getLocation().equals(pirate2.getLocation()) == false) {
			switch(ke.getCode()){
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
	/**Updating the ImageView of the Columbus ship and pirateShips based on the movement of the player
	 * the ships locations are changing because of movement by the player, the pirateShips are being notified and move as well.
	 * we need to update the ImageView, where we just set the x and y coordinates to the new locations of the ships 
	 */
    	shipImageView.setX(ship.getLocation().x*scale);
    	shipImageView.setY(ship.getLocation().y*scale);
    	

    	pirateImageView.setX(pirate.getLocation().x*scale);
    	pirateImageView.setY(pirate.getLocation().y*scale);
    	

    	pirate2ImageView.setX(pirate2.getLocation().x*scale);
    	pirate2ImageView.setY(pirate2.getLocation().y*scale);
    	
			}

			
			
			
			
			
			
			
			
			
			/**Here we check to see if there has been a collision.
			 * If there has been, we load the ExplosionImages, which changes the shipImageView and pirateShipImageView to an explosion
			 */
    	if(ship.getLocation().equals(pirate.getLocation())) {
			loadExplosionImages();
			
		}
    	if(ship.getLocation().equals(pirate2.getLocation())) {
    		loadExplosionImages2();
    	}
		}
    	});
		 
	    	
	 }

	 
	 
	public static void main(String[] args) {
		launch(args);
	}

}

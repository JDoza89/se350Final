package columbusGame;

import java.awt.Point;

public interface PowerUp {
//	public abstract void levelUp();// changed to this
	public default void levelUp() { }	/* So that ship doesnt need to implement this.*/
	public abstract Point getLocation();	
	public default String getPowerUp() { return "No power up";}	/* So that ship doesnt need to implement this.*/
}
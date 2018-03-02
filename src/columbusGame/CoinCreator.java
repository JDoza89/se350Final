package columbusGame;

import java.awt.Point;

public abstract class CoinCreator {
	String coinDescription = "unknown coin.";

	abstract void createCoin();

	public String getDescription() {
		return coinDescription;
	}

	abstract Point getLocationOfCoin();

	abstract void resetCoinImage();
	
	abstract void loadCoinImage();
}

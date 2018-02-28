package columbusGame;



public class PirateShipFactory {

	final static int dimension= 20;
	
	public static PirateShip getPirateShip(String criteria) {
		
		if(criteria.equals("pirate1")) {
			return new PirateShip1();
		}
		if(criteria.equals("pirate2")) {
			return new PirateShip2();
		}
	
		return null;
	}

}

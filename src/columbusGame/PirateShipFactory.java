package columbusGame;



public class PirateShipFactory {

	final static int dimension= 20;
	
	public static PirateShip getPirateShip(String criteria) {
		
		if(criteria.equals("pirate1")) {
			return new PirateShip1(null);
		}
		if(criteria.equals("pirate2")) {
			return new PirateShip2(null);
		}
	
		return null;
	}

}

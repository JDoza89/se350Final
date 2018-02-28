package columbusGame;
import java.awt.Point;

public class SnakeBody implements BadGuys, OceanObjects{
		int x;
		int y;
		OceanMap ocean;
		
		@Override
		public Boolean contains(double x, double y) {
			return (this.x == x && this.y == y);
		}

		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}

		public Point move() {
			
			return new Point(this.x, this.y);
		}

		public Point getLocation() {
			return new Point(this.x, this.y);
		}
		


}

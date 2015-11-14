import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class InvisibleSlime extends Slime {

	public InvisibleSlime(double x, double y, double width, double height, int player) {
		super(null, null, x, y, width, height, player);
	}
	
	public ArrayList<ShapeColor> drawObject() {
		ArrayList<ShapeColor> array = new ArrayList<ShapeColor>();
		if(player == 1){
			array.add(new ShapeColor(Color.WHITE, new Ellipse2D.Double(getX() + getWidth() * 0.7, getY() + getHeight() * 0.1, getHeight() / 5, getWidth() / 5)));
			array.add(new ShapeColor(Color.BLACK, new Ellipse2D.Double(getX() + getWidth() * 0.8, getY() + getHeight() * 0.15, getHeight() / 10, getWidth() / 10)));
		} else {
			array.add(new ShapeColor(Color.WHITE, new Ellipse2D.Double(getX() + getWidth() * 0.1, getY() + getHeight() * 0.1, getHeight() / 5, getWidth() / 5)));
			array.add(new ShapeColor(Color.BLACK, new Ellipse2D.Double(getX() + getWidth() * 0.1, getY() + getHeight() * 0.15, getHeight() / 10, getWidth() / 10)));
		}
		return array;
	}
}

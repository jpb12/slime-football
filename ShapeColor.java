import java.awt.Color;
import java.awt.Shape;

public class ShapeColor {
	Color colour;
	Shape shape;
	
	//Class to allow me to pass both a colour and a shape together
	public ShapeColor(Color colour, Shape shape){
		this.colour = colour;
		this.shape = shape;
	}
}

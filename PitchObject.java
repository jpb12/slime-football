import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;

public abstract class PitchObject {
	private Color color;
	private double x;
	private double y;
	private double width;
	private double height;
	
	public void setColor(Color color) {
		this.color = color;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public PitchObject(Color color, double x, double y, double width, double height){
		this.color = color;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Color getColor(){
		return color;
	}
	
	public double getX(){
		return x;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getWidth(){
		return width;
	}
	
	public double getHeight(){
		return height;
	}
	
	public abstract Shape getShape();
	
	public abstract void updateLocation();
	
	public abstract ArrayList<ShapeColor> drawObject();
}

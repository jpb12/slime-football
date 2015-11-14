import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Slime extends PitchObject{
	protected int player;
	private Color colorTwo;
	private boolean Left = false;
	private boolean Right = false;
	private boolean Up = false;
	private double Velocity = 0;
	
	public boolean isUp() {
		return Up;
	}

	public boolean isLeft() {
		return Left;
	}

	public boolean isRight() {
		return Right;
	}

	public double getyVelocity() {
		return Velocity;
	}
	
	public double getxVelocity() {
		if(Right && !Left){
			return 0.8;
		} else if (Left && !Right){
			return -0.8;
		} else {
			return 0;
		}
	}

	public Shape getShape(){
		return new Arc2D.Double(getX(), getY(), getWidth(), getHeight(), 0, 180, 1);
	}
	
	public void setLeft(boolean left) {
		Left = left;
	}

	public void setRight(boolean right) {
		Right = right;
	}

	public void setUp(boolean up) {
		Up = up;
	}

	public void setVelocity(double velocity) {
		Velocity = velocity;
	}

	public Slime(Color color, Color colorTwo, double x, double y, double width, double height, int player){
		super(color, x, y, width, height);
		this.colorTwo = colorTwo;
		this.player = player;
	}
	
	public void updateLocation(){
		if(isLeft() == true){
    		if(getX() > 0){
    			setX(getX() - 0.8);
    		}
    	}
    	if(isRight() == true){
    		if(getX() < 650){
    			setX(getX() + 0.8);
    		}
    	}
    	if(isUp() == true){
    		if(getY() == 257.5){
    			setVelocity(1.8);
    		}
    	}
    	if(getShape().intersects(0, 295, 725, 65) && getyVelocity() < 0){
    		setVelocity(0);
    		setY(257.5);
    	} else {
    		setVelocity(getyVelocity() - 0.02);
    		setY(getY() - getyVelocity());
    	}
	}
	
	public ArrayList<ShapeColor> drawObject() {
		ArrayList<ShapeColor> array = new ArrayList<ShapeColor>();
		array.add(new ShapeColor(getColor(), new Arc2D.Double(getX(), getY(), getWidth(), getHeight(), 0, 180, 1)));
		if(player == 1){
			array.add(new ShapeColor(colorTwo, new Polygon(new int[] {(int) (getX() + getWidth()/2), (int) (getX() + getWidth() * 0.1), (int) (getX() + getWidth()/2), (int) (getX() + getWidth() * 0.4)}, new int[] {(int) getY(), (int) (getY() + getHeight()/4), (int) (getY() + getHeight()/2), (int) (getY() + getHeight()/4)}, 4)));
			array.add(new ShapeColor(Color.WHITE, new Ellipse2D.Double(getX() + getWidth() * 0.7, getY() + getHeight() * 0.1, getHeight() / 5, getWidth() / 5)));
			array.add(new ShapeColor(Color.BLACK, new Ellipse2D.Double(getX() + getWidth() * 0.8, getY() + getHeight() * 0.15, getHeight() / 10, getWidth() / 10)));
		} else {
			array.add(new ShapeColor(colorTwo, new Polygon(new int[] {(int) (getX() + getWidth()/2), (int) (getX() + getWidth() * 0.9), (int) (getX() + getWidth()/2), (int) (getX() + getWidth() * 0.6)}, new int[] {(int) getY(), (int) (getY() + getHeight()/4), (int) (getY() + getHeight()/2), (int) (getY() + getHeight()/4)}, 4)));
			array.add(new ShapeColor(Color.WHITE, new Ellipse2D.Double(getX() + getWidth() * 0.1, getY() + getHeight() * 0.1, getHeight() / 5, getWidth() / 5)));
			array.add(new ShapeColor(Color.BLACK, new Ellipse2D.Double(getX() + getWidth() * 0.1, getY() + getHeight() * 0.15, getHeight() / 10, getWidth() / 10)));
		}
		return array;
	}

}

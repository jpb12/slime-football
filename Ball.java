import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Ball extends PitchObject{
	private double xVelocity = 0;
	private double yVelocity = 0;
	
	public double getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	public double getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}

	public Shape getShape(){
		//Returns the shape for collision detection
		return new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight());
	}
	
	public Ball(Color color, double x, double y, double width, double height){
		super(color, x, y, width, height);
	}
	
	public void updateLocation() {
    	//Updates location using velocity
    	setY(getY() - getyVelocity());
    	setX(getX() + getxVelocity());
    	//Gravity
    	setyVelocity(getyVelocity() - 0.01);
	}
	
	public void detectCollison(Slime SlimeOne, Slime SlimeTwo){
		//Centre of each object used for collision detection
		double centreBallX = getX() + 10;
		double centreBallY = getY() + 10;
		double centreSlimeOneX = SlimeOne.getX() + 37.5;
		double centreSlimeOneY = SlimeOne.getY() + 37.5;
		double centreSlimeTwoX = SlimeTwo.getX() + 37.5;
		double centreSlimeTwoY = SlimeTwo.getY() + 37.5;
    	
    	//Checks if the ball collides with one of the slimes
    	if(Math.pow(Math.max(centreBallX, centreSlimeOneX) - Math.min(centreBallX, centreSlimeOneX), 2) + Math.pow(Math.max(centreBallY, centreSlimeOneY) - Math.min(centreBallY, centreSlimeOneY), 2) < Math.pow(47.5, 2)){
			//Makes sure the collision isn't with the non-existent half of the slime semi-circle
			if(centreBallY < centreSlimeOneY + 10){
				//Adjust velocity TODO figure out want the hell I put here
				double angle;
				if(centreBallX > centreSlimeOneX){
					//Angle of the ball to the centre of the slime
					angle = Math.atan((centreSlimeOneY - centreBallY) / (centreBallX - centreSlimeOneX));
					setX(centreSlimeOneX + Math.cos(angle) * 48 - 10);
				} else if (centreBallX < centreSlimeOneX){
					angle = Math.atan((centreSlimeOneY - centreBallY) / (centreSlimeOneX - centreBallX));
	    			setX(centreSlimeOneX - Math.cos(angle) * 48 - 10);
					//remove divide by zero error
				} else {
					angle = Math.PI / 2;
				}
    			setY(centreSlimeOneY - Math.sin(angle) * 48 - 10);
    			//Sets velocity
    			double xDiff = (getX() - SlimeOne.getX() * 2);
    			double yDiff = getY() - SlimeOne.getY();
    			double xVDiff = getxVelocity() - SlimeOne.getxVelocity();
    			double yVDiff = getyVelocity() - (SlimeOne.getyVelocity());
    			double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    			double temp = (xDiff * xVDiff + yDiff * yVDiff) / distance;
    			setyVelocity((getyVelocity() + SlimeOne.getyVelocity() - (2 * yDiff * temp) / distance) * 1);
    			setxVelocity((getxVelocity() + SlimeOne.getxVelocity() - (2 * xDiff * temp) / distance) * 0.7);
    			if(getxVelocity() > 2.25){
    				setxVelocity(2.25);
    			} else if (getxVelocity() < -2.25){
    				setxVelocity(-2.25);
    			}
    			if(getyVelocity() > 1.4){
    				setyVelocity(1.4);
    			} else if (getyVelocity() < -1.4){
    				setyVelocity(-1.4);
    			}
			}
		}
    	
    	//Repeats for other slime
    	if(Math.pow(Math.max(centreBallX, centreSlimeTwoX) - Math.min(centreBallX, centreSlimeTwoX), 2) + Math.pow(Math.max(centreBallY, centreSlimeTwoY) - Math.min(centreBallY, centreSlimeTwoY), 2) < Math.pow(47.5, 2)){
			if(centreBallY < centreSlimeTwoY + 10){
				double angle;
				if(centreBallX > centreSlimeTwoX){
					angle = Math.atan((centreSlimeTwoY - centreBallY) / (centreBallX - centreSlimeTwoX));
	    			setX(centreSlimeTwoX + Math.cos(angle) * 48 - 10);
				} else if (centreBallX < centreSlimeTwoX){
					angle = Math.atan((centreSlimeTwoY - centreBallY) / (centreSlimeTwoX - centreBallX));
	    			setX(centreSlimeTwoX - Math.cos(angle) * 48 - 10);
				} else {
					angle = Math.PI / 2;
				}
    			setY(centreSlimeTwoY - Math.sin(angle) * 48 - 10);
    			double xDiff = (getX() - SlimeTwo.getX() * 2);
    			double yDiff = getY() - SlimeTwo.getY();
    			double xVDiff = getxVelocity() - SlimeTwo.getxVelocity();
    			double yVDiff = getyVelocity() - (SlimeTwo.getyVelocity());
    			double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    			double temp = (xDiff * xVDiff + yDiff * yVDiff) / distance;
    			setyVelocity((getyVelocity() + SlimeTwo.getyVelocity() - (2 * yDiff * temp) / distance) * 1);
    			setxVelocity((getxVelocity() + SlimeTwo.getxVelocity() - (2 * xDiff * temp) / distance) * 0.7);
    			if(getxVelocity() > 2.25){
    				setxVelocity(2.25);
    			} else if (getxVelocity() < -2.25){
    				setxVelocity(-2.25);
    			}
    			if(getyVelocity() > 1.4){
    				setyVelocity(1.4);
    			} else if (getyVelocity() < -1.4){
    				setyVelocity(-1.4);
    			}
			}
		}
    	
		//Checks if the ball has hit the floor
    	if(getShape().intersects(0, 295, 725, 65) && getyVelocity() < 0){
    		//Reverses y directions and slows down
    		setyVelocity(getyVelocity() * -0.75);
    		setxVelocity(getxVelocity() * 0.75);
    		setY(275);
    		//Checks if the ball has hit the left wall
    	} else if (getX() <= 0) {
    		setxVelocity(getxVelocity() * -0.75);
    		setX(0);
    		//Checks if the ball has hit the right wall
    	} else if (getX() >= 705) {
    		setxVelocity(getxVelocity() * -0.75);
    		setX(705);
    		//Checks if the ball has hits the left crossbar
    	} else if (getShape().intersects(0, 222, 40, 1)){
    		//Collision from above
    		if(centreBallX <= 40 && centreBallY < 222){
    			setyVelocity(getyVelocity() * -0.75);
    			setxVelocity(getxVelocity() * 0.75);
    			setY(202);
    			//Collision from below
    		} else if(centreBallX <= 40 && centreBallY >= 222){
    			setyVelocity(getyVelocity() * -0.75);
    			setxVelocity(getxVelocity() * 0.75);
    			setY(223);
    			//Collision on edge
    		} else {
    			double angle = Math.atan((222 - centreBallY) / (centreBallX - 40));
    			double velocity = Math.sqrt(Math.pow(getyVelocity(), 2) * Math.pow(getxVelocity(), 2));
    			setyVelocity(velocity * Math.sin(angle));
    			setxVelocity(velocity * Math.cos(angle));
    			setY(212 - 11 * Math.sin(angle));
    			setX(30 + 11 * Math.cos(angle));
    		}	
    		//Checks if the ball hits the right crossbar
    	} else if (getShape().intersects(685, 222, 40, 1)){
    		if(centreBallX >= 685 && centreBallY < 222){
    			setyVelocity(getyVelocity() * -0.75);
    			setxVelocity(getxVelocity() * 0.75);
    			setY(202);
    			//Collision from below
    		} else if(centreBallX >= 685 && centreBallY >= 222){
    			setyVelocity(getyVelocity() * -0.75);
    			setxVelocity(getxVelocity() * 0.75);
    			setY(223);
    			//Collision on edge
    		} else {
    			double angle = Math.atan((222 - centreBallY) / (685 - centreBallX));
    			double velocity = Math.sqrt(Math.pow(getyVelocity(), 2) * Math.pow(getxVelocity(), 2));
    			setyVelocity(velocity * Math.sin(angle));
    			setxVelocity(-velocity * Math.cos(angle));
    			setY(212 - 11 * Math.sin(angle));
    			setX(675 - 11 * Math.cos(angle));
    		}
    		//Slows down ball if in the net
    	} else if ((getX() <= 20 && getY() >= 222) | (getX() >= 685 && getY() >= 222)){
    		setxVelocity(getxVelocity() * 0.98);
    		setyVelocity(getyVelocity() * 0.98);
    	}
	}
	
	public ArrayList<ShapeColor> drawObject() {
		//Passes information to draw the ball to the pitch
		ArrayList<ShapeColor> array = new ArrayList<ShapeColor>();
		array.add(new ShapeColor(getColor(), new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight())));
		return array;
	}
}

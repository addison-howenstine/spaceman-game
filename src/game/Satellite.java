// This entire file is part of my masterpiece.
// Addison Howenstine

package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Satellite extends ImageView{
	public static final int IMAGE_DIMS = Main.WIDTH / 13;
	public static final int ROTATION_DEGREE = 5;
	public static final int ACCELERATION = 10;
	public static final int MARGIN_BUFFER = 10;
	
	protected int randX = (int) (Math.random() * Main.WIDTH);
	protected int randY = (int) (Math.random() * Main.HEIGHT);
	protected int randAngle = (int) (Math.random() * 360);
	
	private int x_vel, y_vel;
	private String name;
	
	/**
	 * constructor for Satellite calls
	 * generic ImageView constructor
	 * subclass constructors will call
	 * setAllVals to initialize Satellite parameters
	 */
	public Satellite(){
		super();
	}
	
	/**
	 * initializes all relevant values that
	 * apply for any Satellite object
	 */
	public void setAllVals(int x, int y, int initialVelocity, int angle0, String n, String fileName){
		setX(x - IMAGE_DIMS / 2);
		setY(y - IMAGE_DIMS / 2);
		setXVel((int) (Math.sin(Math.toRadians(angle0)) * initialVelocity));
		setYVel((int) (- Math.cos(Math.toRadians(angle0)) * initialVelocity));
		setRotate(angle0);
		setName(n);
		setImageFromFileName(fileName);
	}
	
	public int getXCenter(){
		return (int) (getX() - IMAGE_DIMS / 2); 
	}
	public int getYCenter(){
		return (int) (getY() - IMAGE_DIMS / 2); 
	}
	public int getXVel(){
		return x_vel;
	}
	public int getYVel(){
		return y_vel;
	}
	public void setXVel(int xv){
		x_vel = xv;
	}
	public void setYVel(int yv){
		y_vel = yv;
	}
	public String getName(){
		return name;
	}
	public void setName(String n){
		name = n;
	}
	
	/**
	 * sets image representation of satellite
	 * to the image stored in a file and resized
	 * to consistent dimensions IMAGE_DIMS
	 */
	public void setImageFromFileName(String fileName){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(fileName), IMAGE_DIMS, IMAGE_DIMS,true,true);
		setImage(image);
	}
	
	/**
	 * updates current position based on
	 * velocity in each direction and time passed
	 * 
	 * includes conditions for if Satellite
	 * is about to go off screen (comes out other end)
	 * 
	 * @param elapsedTime
	 */
	public void move(double elapsedTime){
		setX(getX() + getXVel() * elapsedTime);
		setY(getY() + getYVel() * elapsedTime);
		
		if (getX() <= 0){
			setX(Main.WIDTH - MARGIN_BUFFER);
		}
		if (getX() >= Main.WIDTH){
			setX(MARGIN_BUFFER);
		}
		if (getY() <= 0){
			setY(Main.HEIGHT - MARGIN_BUFFER);
		}
		if (getY() >= Main.HEIGHT){
			setY(MARGIN_BUFFER);
		}
	}
	
	public void rotateCW(){
    	setRotate(getRotate() + ROTATION_DEGREE);
    }
	public void rotateCCW(){
    	setRotate(getRotate() - ROTATION_DEGREE);
    }
	public void accelerateFW(){
		x_vel += ACCELERATION * Math.sin(Math.toRadians(getRotate()));
		y_vel -= ACCELERATION * Math.cos(Math.toRadians(getRotate()));
	}
	public void accelerateBW(){
		x_vel -= ACCELERATION * Math.sin(Math.toRadians(getRotate()));
		y_vel += ACCELERATION * Math.cos(Math.toRadians(getRotate()));
	}
	
	/**
	 * returns distance from this Satellite
	 * to the other Satellite passed in
	 * 
	 * @param other
	 */
	public double distanceToOther(Satellite other){
		return Math.sqrt( Math.pow( (this.getXCenter() - other.getXCenter()) ,2) +  
				Math.pow((this.getYCenter() - other.getYCenter()) ,2) );
	}
}
package game;

import javafx.scene.image.ImageView;

class Satellite extends ImageView{
	private int x_vel, y_vel;
	private String name;
	
	public static final int IMAGE_DIMS = Main.WIDTH / 13;
	public static final int ROTATION_DEGREE = 5;
	public static final int ACCELERATION = 10;

	
	/**
	 * constructor for Satellite calls
	 * generic ImageView constructor
	 * subclass constructors will build on this
	 */
	public Satellite(){
		super();
	}
	
	/**
	 * constructs Satellite with initial positions,
	 * velocities, rotation angle, and name
	 * @param x
	 * @param y
	 * @param xv
	 * @param yv
	 * @param a
	 * @param n
	 */
	public Satellite(int x, int y, int xv, int yv, int a, String n){
		super();
		setX(x - IMAGE_DIMS / 2);
		setY(y - IMAGE_DIMS / 2);
		setXVel(xv);
		setYVel(yv);
		setRotate(a);
		setName("@Astro_" + n);
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
			setX(Main.WIDTH - 10);
		}
		if (getX() >= Main.WIDTH){
			setX(10);
		}
		if (getY() <= 0){
			setY(Main.HEIGHT - 10);
		}
		if (getY() >= Main.HEIGHT){
			setY(10);
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
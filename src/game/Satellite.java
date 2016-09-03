package game;

import javafx.scene.image.ImageView;

class Satellite extends ImageView{
	int x_vel, y_vel;
	int angle;
	String name;
	
	private static final int ROTATION_DEGREE = 5;
	private static final int ACCELERATION = 10;

	
	public Satellite(){
		super();
	}
	
	public int getXVel(){
		return x_vel;
	}
	public int getYVel(){
		return y_vel;
	}
	public String getName(){
		return name;
	}
	
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
	

}
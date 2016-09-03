package game;

import javafx.scene.image.Image;

public class Spacecraft extends Satellite{
	
	public static final String FILE_NAME = "spacecraft.gif";
	public static final int X = (int) (Math.random() * Main.WIDTH);
	public static final int Y = (int) (Math.random() * Main.HEIGHT);
	public static final int INITIAL_VELOCITY = 50 + (int) (Math.random() * 100);
	public static final int ANGLE_0 = (int) (Math.random() * 360);


	
	public Spacecraft(String n){
		super();
		setImage(new Image(getClass().getClassLoader().getResourceAsStream(FILE_NAME), 100,100,true,true));
		setX(X);
		setY(Y);
		x_vel = (int) (Math.sin(Math.toRadians(ANGLE_0)) * INITIAL_VELOCITY);
		y_vel = - (int) (Math.cos(Math.toRadians(ANGLE_0)) * INITIAL_VELOCITY);
		name = n;
		setRotate(ANGLE_0);
	}
}
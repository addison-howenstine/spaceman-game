package game;

import javafx.scene.image.Image;

public class Spacecraft extends Satellite{
	
	private static final String FILE_NAME = "spacecraft.gif";
	private int X = (int) (Math.random() * Main.WIDTH);
	private int Y = (int) (Math.random() * Main.HEIGHT);
	private int initialVelocity = 50 + (int) (Math.random() * 100);
	private int angle0 = (int) (Math.random() * 360);


	/**
	 * initializes new Spacecraft with random
	 * position, angle, and velocity
	 */
	public Spacecraft(String n){
		super();
		setImage(new Image(getClass().getClassLoader().getResourceAsStream(FILE_NAME), IMAGE_DIMS,IMAGE_DIMS,true,true));
		setX(X);
		setY(Y);
		setXVel((int) (Math.sin(Math.toRadians(angle0)) * initialVelocity));
		setYVel((int) (- Math.cos(Math.toRadians(angle0)) * initialVelocity));
		setName(n);
		setRotate(angle0);
	}
}
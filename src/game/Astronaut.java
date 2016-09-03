package game;

import javafx.scene.image.Image;

public class Astronaut extends Satellite{
	
	public static final String FILE_NAME = "astronaut.gif";
	public static final int X = Main.WIDTH /2;
	public static final int Y = Main.HEIGHT /2;
	public static final int X_V0 = 0;
	public static final int Y_V0 = 0;
	public static final int ANGLE_0 = 0;

	public Astronaut(String n){
		super();
		setImage(new Image(getClass().getClassLoader().getResourceAsStream(FILE_NAME), 100,100,true,true));
		setX(X);
		setY(Y);
		x_vel = X_V0;
		y_vel = Y_V0;
		name = "@Astro_" + n;
		setRotate(ANGLE_0);
	}
}
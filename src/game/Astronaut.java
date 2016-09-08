package game;

import javafx.scene.image.Image;

public class Astronaut extends Satellite{
	
	private static final String FILE_NAME = "astronaut.gif";
	private static final int X = Main.WIDTH /2;
	private static final int Y = Main.HEIGHT /2;
	private static final int X_V0 = 0;
	private static final int Y_V0 = 0;
	private static final int ANGLE_0 = 0;

	public Astronaut(String n){
		super(X,Y,X_V0, Y_V0, ANGLE_0, n);
		setImage(new Image(getClass().getClassLoader().getResourceAsStream(FILE_NAME), IMAGE_DIMS,IMAGE_DIMS,true,true));
	}
}
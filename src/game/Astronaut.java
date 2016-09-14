// This entire file is part of my masterpiece.
// Addison Howenstine

package game;

public class Astronaut extends Satellite{

	private static final String ASTRO_FILE_NAME = "astronaut.gif";
	private static final String ASRO_FILE_NAME_GLOW = "astronautGlow.png";
	private static final int X = Main.WIDTH /2;
	private static final int Y = Main.HEIGHT /2;
	private static final int INITIAL_VELOCITY = 0;
	private static final int ANGLE_0 = 0;

	/**
	 * initializes new Astronaut at zero velocity
	 * at center of the screen pointing straight up
	 */
	public Astronaut(String n){
		super();
		setAllVals(X,Y, INITIAL_VELOCITY, ANGLE_0, n, ASTRO_FILE_NAME);
	}

	/**
	 * changes icon of Astronaut to 
	 * glowing yellow icon
	 */
	public void highlight(){
		setImageFromFileName(ASRO_FILE_NAME_GLOW);
	}

	/**
	 * returns Astronaut icon to
	 * regular white color icon
	 */
	public void unHighlight(){
		setImageFromFileName(ASTRO_FILE_NAME);
	}
}
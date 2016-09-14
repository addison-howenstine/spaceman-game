// This entire file is part of my masterpiece.
// Addison Howenstine

package game;

import java.time.LocalDateTime;

public class Asteroid extends Satellite{
	
	private static final String ASTEROID_FILE_NAME = "asteroid.gif";
	private static final String ASTEROID_FILE_NAME_DARK = "dark.png";

	private int initialVelocity = 10 + (int) (Math.random() * 50);
	
	/**
	 * initializes new Asteroid
	 * at random position and velocity
	 * with random name based on time stamp
	 */
	public Asteroid(){
		super();
		String name = "C" + LocalDateTime.now().toString();
		setAllVals(randX, randY, initialVelocity, randAngle, name, ASTEROID_FILE_NAME);
	}
	
	/**
	 * replaces image of Asteroid with clear
	 * image when Asteroid is destroyed
	 */
	public void goDark(){
		setImageFromFileName(ASTEROID_FILE_NAME_DARK);
	}
}
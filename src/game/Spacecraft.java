// This entire file is part of my masterpiece.
// Addison Howenstine

package game;

public class Spacecraft extends Satellite{
	
	private static final String SPACECRAFT_FILE_NAME = "spacecraft.gif";
	private int initialVelocity = 50 + (int) (Math.random() * 100);


	/**
	 * initializes new Spacecraft with random
	 * position, angle, and velocity
	 */
	public Spacecraft(String n){
		super();
		setAllVals(randX, randY, initialVelocity, randAngle, n, SPACECRAFT_FILE_NAME);
	}
}
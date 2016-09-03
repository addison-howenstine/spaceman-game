package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;


public class MarkWatneyGame {

	public static final String TITLE = "SAVE MARK WATNEY!";
	private static final double DOCK_DISTANCE = 100.0;
	private static final double WIN_TIME = 5.0;

	private Scene myScene;
	private Astronaut astro;
	private Spacecraft shuttle;
	private double secondsDocked = 0;

	/**
	 * Returns name of the game.
	 */
	public String getTitle () {
		return TITLE;
	}

	public Scene init (int width, int height) {
		// create a scene graph to organize the scene
		Group root = new Group();
		// create a place to see the shapes
		myScene = new Scene(root, width, height, Color.BLACK);
		// make some shapes and set their properties
		astro = new Astronaut("Addison");
		// x and y represent the top left corner, so center it
		root.getChildren().add(astro);

		shuttle = new Spacecraft("Endeavor");
		root.getChildren().add(shuttle);


		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return myScene;
	}

	public void step (double elapsedTime) {
		astro.move(elapsedTime);
		shuttle.move(elapsedTime);
		countDockTime();
		isWin();
	}

	private void handleKeyInput (KeyCode code){
		switch(code){
		case RIGHT:
			astro.rotateCW();
			break;
		case LEFT:
			astro.rotateCCW();
			break;
		case UP:
			astro.accelerateFW();
			break;
		case DOWN:
			astro.accelerateBW();
			break;
		default:
		}
	}

	private double distance(Satellite sat1, Satellite sat2){
		return Math.sqrt( Math.pow( (sat1.getX() - sat2.getX()) ,2) +  
				Math.pow((sat1.getY() - sat2.getY()) ,2) );
	}

	private boolean isDocked(){
		double astroDist = distance(astro, shuttle);
		if (astroDist <= DOCK_DISTANCE)
			return true;
		else return false;
	}

	private void countDockTime(){
		if (isDocked())
			secondsDocked += ( (double) 1 / Main.FRAMES_PER_SECOND);
		else secondsDocked = 0;
	}

	private boolean isWin(){
		if( secondsDocked >= WIN_TIME){
			System.out.println("YOU WON!");
			return true;
		}
		else return false;
	}

}

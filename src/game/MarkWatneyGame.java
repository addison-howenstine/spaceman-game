package game;

import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class MarkWatneyGame {

	protected static final double DOCK_DISTANCE = Main.WIDTH / 13;
	private static final double WIN_TIME = 5.0;

	private Scene myScene;
	private Astronaut astro;
	private Spacecraft shuttle;
	private Satellite satelliteToMove;
	private ArrayList<Asteroid> asteroidField = new ArrayList<Asteroid>();
	private double secondsDocked = 0;
	private Timeline currentAnimation;

	public Scene getMyScene() {
		return myScene;
	}
	public void setMyScene(Scene myScene) {
		this.myScene = myScene;
	}
	public Astronaut getAstro() {
		return astro;
	}
	public void setAstro(Astronaut astro) {
		this.astro = astro;
	}
	public Spacecraft getShuttle() {
		return shuttle;
	}
	public void setShuttle(Spacecraft shuttle) {
		this.shuttle = shuttle;
	}
	public Satellite getSatelliteToMove() {
		return satelliteToMove;
	}
	public void setSatelliteToMove(Satellite satelliteToMove) {
		this.satelliteToMove = satelliteToMove;
	}
	public double getSecondsDocked() {
		return secondsDocked;
	}
	public void setSecondsDocked(double secondsDocked) {
		this.secondsDocked = secondsDocked;
	}
	public ArrayList<Asteroid> getAsteroidField() {
		return asteroidField;
	}
	public static double getDockDistance() {
		return DOCK_DISTANCE;
	}
	public static double getWinTime() {
		return WIN_TIME;
	}
	public Timeline getAnimation(){
		return currentAnimation;
	}
	public void setAnimation(Timeline animation){
		currentAnimation = animation;
	}

	/**
	 * initializes new scene for a generic
	 * Level 1 or Level 2 MarkWatneyGame
	 */
	public Scene init (int width, int height) {
		Group root = new Group();
		setMyScene(new Scene(root, width, height, Color.BLACK));
		setAstro(new Astronaut("Addison"));
		root.getChildren().add(getAstro());
		setShuttle(new Spacecraft("Endeavor"));
		root.getChildren().add(getShuttle());
		
		satelliteToMove = getAstro();

		// creates a random number of asteroids between 3 and 6
		// new Asteroids can not be placed near current Astronaut
		for (int i = 0; i <= (int) (3 + Math.random()*3); i++){
			Asteroid a = new Asteroid();
			if (a.distanceToOther(getAstro()) >= DOCK_DISTANCE *3){
				root.getChildren().add(a);
				asteroidField.add(a);
			}
		}

		getMyScene().setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return getMyScene();
	}

	/**
	 * move all pieces on the screen according
	 * to their current positions and velocities
	 */
	public void step (double elapsedTime, Timeline animation) {
		getAstro().move(elapsedTime);
		getShuttle().move(elapsedTime);
		setAnimation(animation);
		for(Asteroid each: getAsteroidField()){
			each.move(elapsedTime);
		}
		countDockTime();
	}

	/**
	 * handle all keyboard inputs
	 * that apply to any game level
	 */
	public void handleKeyInput (KeyCode code){
		switch(code){
		case RIGHT:
			satelliteToMove.rotateCW();
			break;
		case LEFT:
			satelliteToMove.rotateCCW();
			break;
		case UP:
			satelliteToMove.accelerateFW();
			break;
		case DOWN:
			satelliteToMove.accelerateBW();
			break;
		case E:
			Platform.exit();
			break;
		case R:
			Main.gotoSplashScreen();
		default:
		}
	}

	/**
	 * check whether astro and shuttle 
	 * are in within DOCK_DISTANCE of each other
	 */
	private boolean isDocked(){
		double astroDist = astro.distanceToOther(shuttle);
		return (astroDist <= DOCK_DISTANCE);
	}

	/**
	 * update SecondsDocked based on whether
	 * isDocked is true or false
	 */
	public void countDockTime(){
		if (isDocked()){
			secondsDocked += ( (double) 1 / Main.FRAMES_PER_SECOND);
			astro.highlight();
		}
		else{
			secondsDocked = 0;
			astro.unHighlight();
		}
	}

	/**
	 * returns true if astro has been
	 * docked long enough to win
	 */
	public boolean isWon(){
		return (secondsDocked >= WIN_TIME);
	}

	/**
	 * adds new Asteroid to asteroidField
	 */
	public Asteroid createNewAsteroid(){
		Asteroid asteroid = new Asteroid();
		asteroidField.add(asteroid);
		return asteroid;
	}
	
	/**
	 * removes asteroid from asteroidField
	 */
	public void destroyAsteroid(Asteroid a){
		asteroidField.remove(a);
	}

	/**
	 * generates a new Scene pulled from an
	 * image file path name and returns new Scene
	 */
	public Scene newScene(String img){
		Group root = new Group();
		Scene scene = new Scene(root, Main.WIDTH, Main.HEIGHT, Color.BLACK);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(img), Main.WIDTH, Main.HEIGHT,true,true);
		ImageView imageView = new ImageView(image);
		root.getChildren().add(imageView);
		return scene;
	}
}
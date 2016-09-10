package game;

import java.util.ArrayList;

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
	private double secondsDocked = 0;

	private ArrayList<Asteroid> asteroidField = new ArrayList<Asteroid>();

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
	
	public Scene init (int width, int height) {
		Group root = new Group();
		setMyScene(new Scene(root, width, height, Color.BLACK));
		setAstro(new Astronaut("Addison"));
		root.getChildren().add(getAstro());
		setShuttle(new Spacecraft("Endeavor"));
		root.getChildren().add(getShuttle());
		
		for (int i = 0; i <= (int) (2 + Math.random()*3); i++){
			Asteroid a = new Asteroid();
			if (a.distanceToOther(getAstro()) >= DOCK_DISTANCE *3){
				root.getChildren().add(a);
				asteroidField.add(a);
			}
		}

		getMyScene().setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return getMyScene();
	}

	public void handleKeyInput (KeyCode code){
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
//		case W:
//			won = true;
//			break;
		case E:
			Platform.exit();
			break;
		case R:
			Main.gotoSplashScreen();
		default:
		}
	}

	private boolean isDocked(){
		double astroDist = astro.distanceToOther(shuttle);
		return (astroDist <= DOCK_DISTANCE);
	}

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

	public boolean isWon(){
		return ( secondsDocked >= WIN_TIME);
		}
	
	public Asteroid createNewAsteroid(){
		Asteroid asteroid = new Asteroid();
		asteroidField.add(asteroid);
		return asteroid;
	}
	public void destroyAsteroid(Asteroid a){
		asteroidField.remove(a);
	}
	
	public Scene newScene(String img){
		Group root = new Group();
		Scene scene = new Scene(root, Main.WIDTH, Main.HEIGHT, Color.BLACK);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(img), Main.WIDTH, Main.HEIGHT,true,true);
		ImageView imageView = new ImageView(image);
		root.getChildren().add(imageView);
		return scene;
	}


}
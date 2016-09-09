package game;

import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class MarkWatneyGame {

	private static final double DOCK_DISTANCE = Main.WIDTH / 13;
	private static final double WIN_TIME = 5.0;

	private Scene myScene;
	private Astronaut astro;
	private Spacecraft shuttle;
	private double secondsDocked = 0;
	private boolean won;

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
	public boolean isWon() {
		return won;
	}
	public void setWon(boolean won) {
		this.won = won;
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

	public void step (double elapsedTime, Timeline animation) {
		astro.move(elapsedTime);
		shuttle.move(elapsedTime);
		for(Asteroid each: asteroidField){
			each.move(elapsedTime);
		}
		countDockTime();
		checkWin();
		if(won){
			won = false;
			showWinScreen();
			animation.stop();
		}
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
		case W:
			won = true;
			break;
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

	private void countDockTime(){
		if (isDocked()){
			secondsDocked += ( (double) 1 / Main.FRAMES_PER_SECOND);
			astro.highlight();
		}
		else{
			secondsDocked = 0;
			astro.unHighlight();
		}
	}

	private void checkWin(){
		if( secondsDocked >= WIN_TIME){
			setWon(true);
		}
	}

	public Asteroid createNewAsteroid(){
		Asteroid asteroid = new Asteroid();
		asteroidField.add(asteroid);
		return asteroid;
	}

	private void showWinScreen(){
		Group root = new Group();
		Scene winScene = new Scene(root, Main.WIDTH, Main.HEIGHT, Color.BLACK);
		Image winScreen = new Image(getClass().getClassLoader().getResourceAsStream("WinScreen.png"), Main.WIDTH, Main.HEIGHT,true,true);
		ImageView winScreneImg = new ImageView(winScreen);
		root.getChildren().add(winScreneImg);
		winScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(winScene);
	}
}
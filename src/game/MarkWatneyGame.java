package game;

import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

public class MarkWatneyGame {

	private static final double DOCK_DISTANCE = 100.0;
	private static final double WIN_TIME = 5.0;

	private Scene myScene;
	private Astronaut astro;
	private Spacecraft shuttle;
	private double secondsDocked = 0;
	private boolean won;

	private ArrayList<Asteroid> asteroidField = new ArrayList<Asteroid>();
	

	public Scene init (int width, int height) {
		won = false;
		Group root = new Group();
		myScene = new Scene(root, width, height, Color.BLACK);
		astro = new Astronaut("Addison");
		root.getChildren().add(astro);
		shuttle = new Spacecraft("Endeavor");
		root.getChildren().add(shuttle);
		root.getChildren().add(createNewAsteroid());	
		root.getChildren().add(createNewAsteroid());	
		root.getChildren().add(createNewAsteroid());	

		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return myScene;
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

	private double distance(Satellite sat1, Satellite sat2){
		return Math.sqrt( Math.pow( (sat1.getXCenter() - sat2.getXCenter()) ,2) +  
				Math.pow((sat1.getYCenter() - sat2.getYCenter()) ,2) );
	}

	private boolean isDocked(){
		double astroDist = distance(astro, shuttle);
		return (astroDist <= DOCK_DISTANCE);
	}

	private void countDockTime(){
		if (isDocked())
			secondsDocked += ( (double) 1 / Main.FRAMES_PER_SECOND);
		else secondsDocked = 0;
	}

	private void checkWin(){
		if( secondsDocked >= WIN_TIME){
			System.out.println("YOU WON!");
			won = true;
		}
	}

	private Asteroid createNewAsteroid(){
		Asteroid asteroid = new Asteroid();
		asteroidField.add(asteroid);
		return asteroid;
	}

	private void showWinScreen(){
		Group root = new Group();
		Scene winScene = new Scene(root, Main.WIDTH, Main.HEIGHT, Color.BLACK);
		Text winText = new Text(Main.WIDTH / 2 - 300, Main.HEIGHT / 2 - 50, 
				"YOU WIN!");
		winText.setFill(Color.rgb(255, 255, 255, .99));
		winText.setFont(new Font(160));
		root.getChildren().add(winText);
		winScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(winScene);
	}
}
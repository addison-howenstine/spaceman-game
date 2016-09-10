package game;

import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class MarkWatneyGameLevel2 extends MarkWatneyGame{

	/**
	 * Overrides MarkWatneyGame generic init
	 * by calling super.init() and then giving
	 * astro an initial velocity to make things
	 * more interesting...
	 */
	public Scene init(int width, int height){
		Scene scene = super.init(width, height);
		getAstro().setYVel(-100);
		return scene;
	}

	/**
	 * Overrides MarkWatneyGame generic step
	 * by calling super.init() and then calling
	 * Level2 specific checks for winning
	 * and losing (you can lose in Level2
	 * 
	 * destroys asteroids impacted by Spacecraft
	 */
	public void step(double elapsedTime, Timeline animation){
		super.step(elapsedTime, animation);
		destroyOtherAsteroids(getShuttle(), DOCK_DISTANCE);
		if(isWon())
			showWinScreen(animation);
		if(isLost())
			showLoseScreen(animation);
	}

	/**
	 * shows Level2 specific win screen,
	 * stops animation from Level2
	 */
	private void showWinScreen(Timeline animation){
		animation.stop();
		Scene winScene = newScene("WinScreenLevel2.png");
		winScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(winScene);
	}

	/**
	 * shows Level2 specific lose screen,
	 * stops animation from Level2
	 */
	private void showLoseScreen(Timeline animation){
		animation.stop();
		Scene loseScene = newScene("LoseScreen.png");
		loseScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(loseScene);
	}

	/**
	 * uses same handleKeyInput as super
	 * but also adds commands for level
	 * specific win, continue, and start
	 */
	public void handleKeyInput (KeyCode code){
		super.handleKeyInput(code);
		switch(code){
		case SPACE:
			if(getSatelliteToMove() instanceof Astronaut){
				setSatelliteToMove(getShuttle());
			}
			else if(getSatelliteToMove() instanceof Spacecraft){
				setSatelliteToMove(getAstro());
			}
			break;
		case W:
			showWinScreen(getAnimation());
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
	 * Overrides super's isWon to include
	 * previous winning conditions and condition
	 * that all asteroids have been destroyed
	 */
	public boolean isWon(){
		return (super.isWon() && getAsteroidField().size() == 0);
	}

	/**
	 * checks if loser has been killed
	 * by passing asteroid
	 */
	private boolean isLost(){
		boolean lost = false;
		for(Asteroid eachA: getAsteroidField()){
			double astroDist = getAstro().distanceToOther(eachA);
			if(astroDist <= DOCK_DISTANCE)
				lost = true;
		}
		return lost;
	}

	/**
	 * destroys any asteroid within
	 * distToDestroy of sat passed in
	 * 
	 * @param sat
	 * @param distToDestroy
	 */
	private void destroyOtherAsteroids(Satellite sat, double distToDestroy){
		ArrayList<Asteroid> toDestroy = new ArrayList<Asteroid>();
		for(Asteroid eachA: getAsteroidField()){
			double shuttleDist = sat.distanceToOther(eachA);
			if(shuttleDist <= distToDestroy){
				eachA.goDark();
				toDestroy.add(eachA);
			}
		}
		for(Asteroid eachA: toDestroy)
			destroyAsteroid(eachA);
	}
}
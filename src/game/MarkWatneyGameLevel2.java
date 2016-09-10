package game;

import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class MarkWatneyGameLevel2 extends MarkWatneyGame{

	private Satellite satelliteToMove;

	public Scene init(int width, int height){
		Scene scene = super.init(width, height);
		satelliteToMove = getAstro();
		getAstro().setYVel(-100);
		return scene;
	}

	public void step(double elapsedTime, Timeline animation){
		getAstro().move(elapsedTime);
		getShuttle().move(elapsedTime);
		for(Asteroid each: getAsteroidField()){
			each.move(elapsedTime);
		}
		countDockTime();
		destroyOtherAsteroids(getShuttle(), DOCK_DISTANCE);
		if(isWon())
			showWinScreen(animation);
		if(isLost())
			showLoseScreen(animation);
	}

	private void showWinScreen(Timeline animation){
		animation.stop();
		Scene winScene = newScene("WinScreen.png");
		winScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(winScene);
	}

	private void showLoseScreen(Timeline animation){
		animation.stop();
		Scene loseScene = newScene("LoseScreen.png");
		loseScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(loseScene);
	}

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
		case SPACE:
			if(satelliteToMove instanceof Astronaut){
				satelliteToMove = getShuttle();
			}
			else if(satelliteToMove instanceof Spacecraft){
				satelliteToMove = getAstro();
			}
			break;
			//		case W:
			//			setWon(true);
			//			break;
		case E:
			Platform.exit();
			break;
		case R:
			Main.gotoSplashScreen();
		default:
		}
	}
	
	public boolean isWon(){
		return (super.isWon() && getAsteroidField().size() == 0);
	}

	private boolean isLost(){
		boolean lost = false;
		for(Asteroid eachA: getAsteroidField()){
			double astroDist = getAstro().distanceToOther(eachA);
			if(astroDist <= DOCK_DISTANCE)
				lost = true;
		}
		return lost;
	}

	private void destroyOtherAsteroids(Satellite sat, double distToDestroy){
		ArrayList<Asteroid> toDestroy = new ArrayList<Asteroid>();
		for(Asteroid eachA: getAsteroidField()){
			double shuttleDist = sat.distanceToOther(eachA);
			if(shuttleDist <= distToDestroy){
				eachA.goDark();
				toDestroy.add(eachA);
			}
		}
		for(Asteroid eachA: toDestroy){
			destroyAsteroid(eachA);
		}
	}

}

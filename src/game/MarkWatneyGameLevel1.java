package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class MarkWatneyGameLevel1 extends MarkWatneyGame{

	/**
	 * Overrides MarkWatneyGame generic init
	 * by calling super.init() and then making
	 * all asteroids dark because they are not
	 * necessary in Level 1
	 */
	public Scene init (int width, int height) {
		Scene scene = super.init(width, height);
		for(Asteroid eachA : getAsteroidField())
			eachA.goDark();
		return scene;
	}

	/**
	 * Overrides MarkWatneyGame generic step
	 * by calling super.init() and then calling
	 * Level1 specific showWinScreen procedure
	 * (you cannot lose in Level1)
	 */
	public void step (double elapsedTime, Timeline animation) {
		super.step(elapsedTime, animation);
		if(isWon()){
			showWinScreen(animation);
		}
	}

	/**
	 * shows Level1 win screen,
	 * stops animation from Level1

	 */
	private void showWinScreen(Timeline animation){
		animation.stop();
		Scene winScene = newScene("WinScreenLevel1.png");
		winScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(winScene);
	}

	/**
	 * uses same handleKeyInput as super
	 * but also adds commands for level
	 * specific win, continue, and start
	 */
	public void handleKeyInput (KeyCode code){
		super.handleKeyInput(code);
		switch(code){
		case W:
			showWinScreen(getAnimation());
			break;
		case C:
			goToLevel2Rules();
			break;
		case S:
			goToLevel2();
			break;
		default:
		}
	}

	/**
	 * brings user to rules and start
	 * screen for Level2
	 */
	public void goToLevel2Rules(){
		Scene levelTwoScene = newScene("LevelTwoScreen.png");
		levelTwoScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(levelTwoScene);
	}


	/**
	 * starts new Level2 game
	 */
	public static void goToLevel2(){
		MarkWatneyGameLevel2 myGame = new MarkWatneyGameLevel2();
		Scene markScene = myGame.init(Main.WIDTH, Main.HEIGHT);
		Timeline animation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(Main.MILLISECOND_DELAY),
				e -> myGame.step(Main.SECOND_DELAY, animation));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		Main.updateGameScreen(markScene);
	}
}
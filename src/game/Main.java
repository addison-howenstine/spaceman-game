package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Begins new Mark Watney Game
 * starts at splash screen and progresses to Level 1
 *
 * @author Addison Howenstine
 */
public class Main extends Application{

	// Set height and width to be 90% of screen height constrained to standard 1.5 x 1 proportion
	public static final int HEIGHT = (int) (Screen.getPrimary().getVisualBounds().getMaxY() * (.9));
	public static final int WIDTH = (int) (1.5 * HEIGHT);
	
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private static Stage gameStage;

	/**
	 * called to instantiate new Stage
	 * and open new Scene
	 * initially is SplashScreen to begin new game
	 */
	@Override
	public void start (final Stage s) {
		gameStage = s;
		gameStage.setTitle("SAVE MARK WATNEY");

		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
		gameStage.setScene(scene);
		gameStage.getScene();
		gotoSplashScreen();
	}

	/**
	 * displays SplashScreen
	 */
	public static void gotoSplashScreen(){
		SplashScreen splash = new SplashScreen();
		Scene splashScene = splash.init(WIDTH, HEIGHT);
		updateGameScreen(splashScene);
		gameStage.getScene().setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	}

	/**
	 * Sets keyboard input commands for SplashScreen
	 */
	private static void handleKeyInput (KeyCode code){
		switch(code){
		case S:
			startNewMarkWatneyGame();
			break;
		case E:
			Platform.exit();
			break;
		default:
		}
	}

	/**
	 * Loads new Level 1 of Mark Watney Game
	 * updates the gameScreen
	 */
	public static void startNewMarkWatneyGame(){
		MarkWatneyGameLevel1 myGame = new MarkWatneyGameLevel1();
		Scene markScene = myGame.init(WIDTH, HEIGHT);
		Timeline animation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> myGame.step(SECOND_DELAY, animation));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		updateGameScreen(markScene);
	}

	/**
	 * changes scene assigned to main gameStage
	 * 
	 * @param newScene
	 */
	public static void updateGameScreen(Scene newScene){
		gameStage.setScene(newScene);
		gameStage.show();
	}

	/**
	 * launch the program
	 */
	public static void main (String[] args) {
		launch(args);
	}

}

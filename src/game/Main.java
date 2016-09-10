package game;

import java.net.URL;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application{

	//public static final int WIDTH = (int) Screen.getPrimary().getVisualBounds().getMaxX();
	public static final int HEIGHT = (int) Screen.getPrimary().getVisualBounds().getMaxY() - 100;
	public static final int WIDTH = (int) (1.5 * HEIGHT);

	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private static Stage gameStage;

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * TODO: ADD JAVADOC COMMENT FOR EVERY PUBLIC METHOD
	 */
	@Override
	public void start (final Stage s) {
		gameStage = s;
		gameStage.setTitle("SAVE MARK WATNEY");

		//	    final Media media = new Media(getClass().getClassLoader().getResourceAsStream("WorkTheProblem.mp3").toString());
		//	    final MediaPlayer mediaPlayer = new MediaPlayer(media);
		//	    mediaPlayer.play();


		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
		gameStage.setScene(scene);
		gameStage.getScene();
		gotoSplashScreen();
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	}

	public static void gotoSplashScreen(){
		SplashScreen splash = new SplashScreen();
		Scene splashScene = splash.init(WIDTH, HEIGHT);
		updateGameScreen(splashScene);
		gameStage.getScene().setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	}

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

	public static void updateGameScreen(Scene newScene){
		gameStage.setScene(newScene);
		gameStage.show();
	}

	public static Stage getGameStage(){
		return gameStage;
	}


	public static void main (String[] args) {
		launch(args);
	}

}

package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MarkWatneyGameLevel1 extends MarkWatneyGame{
	
	public Scene init (int width, int height) {
		Scene ret = super.init(width, height);
		for(Asteroid eachA : getAsteroidField())
			eachA.goDark();
		return ret;
	}
	
	public void step (double elapsedTime, Timeline animation) {
		getAstro().move(elapsedTime);
		getShuttle().move(elapsedTime);
		for(Asteroid each: getAsteroidField()){
			each.move(elapsedTime);
		}
		countDockTime();
		if(isWon()){
			showWinScreen(animation);
		}
	}
	
	private void showWinScreen(Timeline animation){
		animation.stop();
		Group root = new Group();
		Scene winScene = new Scene(root, Main.WIDTH, Main.HEIGHT, Color.BLACK);
		Image winScreen = new Image(getClass().getClassLoader().getResourceAsStream("WinScreenLevel1.png"), Main.WIDTH, Main.HEIGHT,true,true);
		ImageView winScreneImg = new ImageView(winScreen);
		root.getChildren().add(winScreneImg);
		winScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(winScene);
	}

	public void handleKeyInput (KeyCode code){
		super.handleKeyInput(code);
		switch(code){
		case C:
			goToWinLevel1();
			break;
		case S:
			goToLevel2();
			break;
		default:
		}
	}
	
	public void goToWinLevel1(){
		Scene levelTwoScene = newScene("LevelTwoScreen.png");
		levelTwoScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		Main.updateGameScreen(levelTwoScene);
	}


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

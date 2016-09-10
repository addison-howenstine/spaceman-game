package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class SplashScreen {

	private Scene myScene;
	
	/**
	 * initializes a new SplashScreen to begin
	 * a new MarkWatneyGame
	 */
	public Scene init (int width, int height) {
		Group root = new Group();
		myScene = new Scene(root, width, height, Color.BLACK);
		Image splash = new Image(getClass().getClassLoader().getResourceAsStream("SplashScreen.png"), width, height,true,true);
		ImageView splashImg = new ImageView(splash);
		root.getChildren().add(splashImg);
        return myScene;
	}

}

package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class MarkWatneyGameLevel1 extends MarkWatneyGame{
	
	public Scene init (int width, int height) {
		setWon(false);
		Group root = new Group();
		setMyScene(new Scene(root, width, height, Color.BLACK));
		setAstro(new Astronaut("Addison"));
		root.getChildren().add(getAstro());
		setShuttle(new Spacecraft("Endeavor"));
		root.getChildren().add(getShuttle());
		root.getChildren().add(createNewAsteroid());	
		root.getChildren().add(createNewAsteroid());	
		root.getChildren().add(createNewAsteroid());	

		getMyScene().setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return getMyScene();
	}

}

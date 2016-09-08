package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SplashScreen {

	private Scene myScene;
	
	public Scene init (int width, int height) {
		Group root = new Group();
		myScene = new Scene(root, width, height, Color.BLACK);
		Text startText = new Text(width / 2 - 300, height / 2 - 50, 
				"SAVE MARK WATNEY\n\nPRESS [S] TO START");
        startText.setFill(Color.rgb(255, 255, 255, .99));
        startText.setFont(new Font(60));
        root.getChildren().add(startText);
        return myScene;
	}

}

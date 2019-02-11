package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 650;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Game game = new Game("gra", WINDOW_WIDTH, WINDOW_HEIGHT);
		/*Parent root = FXMLLoader.load(getClass().getResource("/fight/FightWindow.fxml"));
		
		primaryStage.setTitle("Fight");
		primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
		primaryStage.show();*/
	}

}

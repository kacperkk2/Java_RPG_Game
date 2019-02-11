package game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Display {

	private GraphicsContext gc;
	private Stage stage;
	private Canvas canvas;
	private Pane root;
	private Scene scene;
	
	public Display(String title, int width, int height) {
		stage = new Stage();
		stage.setTitle(title);
		stage.setResizable(false);
		stage.setWidth(width);
		stage.setHeight(height);	
		
		canvas = new Canvas(width, height);
		root = new Pane();
		root.getChildren().add(canvas);
		scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
		
		gc = canvas.getGraphicsContext2D();
	}
	
	GraphicsContext getGraphicsContext() {
		return gc;
	}
	
	public Scene getScene() {
		return scene;
	}
}

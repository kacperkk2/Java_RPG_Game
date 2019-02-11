package worlds;

import java.io.IOException;

import characters.Character;
import characters.Player;
import characters.Spook;
import characters.Tradesman;
import fight.FightWindowController;
import game.Camera;
import game.Game;
import game.Launcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tiles.Tile;

public class Village extends WorldMap{

	private Spook spook;
	private Tradesman tradesman;
	private Game game;
	
	public Village(Game game, Player player, Camera camera, int pixelWindowWidth, int pixelWindowHeight, String path) {
		super(player, camera, pixelWindowWidth, pixelWindowHeight, path);
		this.game = game;
		spook = new Spook(camera, 0 * Tile.TILE_WIDTH, 0 * Tile.TILE_HEIGHT, Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT);
		spook.setWorldMap(this);
		spook.setTilePosition(2, 2);
		tradesman = new Tradesman(camera, 0 * Tile.TILE_WIDTH, 0 * Tile.TILE_HEIGHT, Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT);
		tradesman.setWorldMap(this);
		tradesman.setTilePosition(2, 12);
		
		
		// TYMCZASOWO WLACZENIE OKNA WALKI
		fight(player, spook);
	}
	
	public void fight(Player player, Character opponent) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fight/FightWindow.fxml"));
		
		Parent root = null;
		try {
			root = (Parent)loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = new Stage();
		
		FightWindowController controller = loader.getController();
		controller.setCharacters(player, opponent);
		
		// JESLI CHCE ZEBY POTWORKI SAME PRZYBIEGALY TO STOPOWAC TIMER W PRZYSZLOSCI
		stage.setTitle("Fight");
		//stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner( game.getDisplay().getScene().getWindow() );
		stage.setScene(new Scene(root)); // (root, Launcher.WINDOW_WIDTH, Launcher.WINDOW_HEIGHT)
		stage.show();
	}

	@Override
	public void update() {
		spook.update();
		tradesman.update();
	}

	@Override
	public void render(GraphicsContext gc) {
		renderTiles(gc);

		spook.render(gc);
		tradesman.render(gc);
	}
	
}

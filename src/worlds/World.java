package worlds;

import characters.Character;
import characters.Player;
import characters.UserInput;
import fight.FightWindowController;
import game.Camera;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import tiles.Tile;

public class World {

	private Player player;
	private WorldMap currentMap;
	private WorldMap village, forest, desert;
	private WorldMap[][] worldMapGrid;
	private int currentMapCoordX, currentMapCoordY;
	private UserInput input;
	private Camera camera;
	private Game game;
	int width, height;
	FightWindowController fight;

	public World(Game game, UserInput input, Camera camera, int width, int height) {
		this.width = width;
		this.height = height;
		this.game = game;
		this.camera = camera;
		this.input = input;
		
		player = new Player(input, camera, 0 * Tile.TILE_WIDTH, 0 * Tile.TILE_HEIGHT, 
				Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT);
		
		//setting maps
		village = new Village(game, player, camera, width, height, "res/maps/village.txt");
		forest = new WorldMap(player, camera, width, height, "res/maps/forest.txt");
		desert = new WorldMap(player, camera, width, height, "res/maps/desert.txt");
		assignMapsToGrid(); // assigns maps to grid and sets current map
	}
	
	private void assignMapsToGrid() {
		//[x][y] x is how many to right, y is how many to bottom
		worldMapGrid = new WorldMap[10][10];
		worldMapGrid[5][5] = village;
		worldMapGrid[6][5] = forest;
		worldMapGrid[4][5] = forest;
		worldMapGrid[5][4] = desert;
		worldMapGrid[5][6] = desert;
		
		currentMapCoordX = 5; // setting village coords
		currentMapCoordY = 5;
		currentMap = worldMapGrid[currentMapCoordX][currentMapCoordY];
		
		player.setWorldMap(currentMap);
		player.setTilePosition(currentMap.getMapWidth()/2, currentMap.getMapHeight()/2);
	}
	
	// trzeba zrobic ze nie ptrzy na ktorym jest kafelku tylko po wspolrzedncyh konca collision
	// dziala ale przy up jest marginez +1, wiec wchodzac z dolnej na gore wyrzuca mnie 1 w gore wiecej, trzeba zmienic na wspolrzedne boxa
	public void maybeChangeMap() {
		if(player.getTileXPosition() > currentMap.getMapWidth()-2) { // moving right
			if(worldMapGrid[currentMapCoordX + 1][currentMapCoordY] != null) { // if there is map then switch
				currentMapCoordX++;
				currentMap = worldMapGrid[currentMapCoordX][currentMapCoordY];
				player.setWorldMap(currentMap);
				System.out.println(currentMap.getMapHeight()/2);
				player.setTilePosition(0, currentMap.getMapHeight()/2);
			}
		}
		else if(player.getTileXPosition() < 0) { // moving left
			if(worldMapGrid[currentMapCoordX - 1][currentMapCoordY] != null) { // if there is map then switch
				currentMapCoordX--;
				currentMap = worldMapGrid[currentMapCoordX][currentMapCoordY];
				player.setWorldMap(currentMap);
				player.setTilePosition(currentMap.getMapWidth()-1, currentMap.getMapHeight()/2);
			}
		}
		else if(player.getTileYPosition() < 0) { // moving up
			if(worldMapGrid[currentMapCoordX][currentMapCoordY - 1] != null) { // if there is map then switch
				currentMapCoordY--;
				currentMap = worldMapGrid[currentMapCoordX][currentMapCoordY];
				player.setWorldMap(currentMap);
				player.setTilePosition(currentMap.getMapWidth()/2, currentMap.getMapHeight() - 2);
			}
		}
		else if(player.getTileYPosition() > currentMap.getMapHeight()-3) { // moving down
			if(worldMapGrid[currentMapCoordX][currentMapCoordY + 1] != null) { // if there is map then switch
				currentMapCoordY++;
				currentMap = worldMapGrid[currentMapCoordX][currentMapCoordY];
				player.setWorldMap(currentMap);
				player.setTilePosition(currentMap.getMapWidth()/2, 0);
			}
		}
	}
	
	public void update() {
		currentMap.update();
		player.update();
		
		maybeChangeMap();
	}
	
	public void render(GraphicsContext gc) {
		currentMap.render(gc);
		player.render(gc);
	}
	
	public WorldMap getWorldMap() {
		return currentMap;
	}
}

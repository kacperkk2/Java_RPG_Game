package worlds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import characters.Player;
import game.Camera;
import javafx.scene.canvas.GraphicsContext;
import tiles.Tile;

public class WorldMap {
	
	private int mapWidth, mapHeight;
	private int[][] mapTiles;
	private int pixelWindowWidth, pixelWindowHeight;

	private Camera camera;
	private Player player;

	public WorldMap(Player player, Camera camera, int pixelWindowWidth, int pixelWindowHeight, String path) {
		this.pixelWindowWidth = pixelWindowWidth;
		this.pixelWindowHeight = pixelWindowHeight;
		this.camera = camera;
		this.player = player;
		
		loadMapFromFile(path);
	}

	public void update() {
	}

	public void render(GraphicsContext gc) {
		renderTiles(gc);
	}

	public void renderTiles(GraphicsContext gc) {
		// render only tiles that are on camera
		int upCamera = (int) Math.max(0, camera.getYoff() / Tile.TILE_HEIGHT);
		int downCamera = (int) Math.min(mapHeight, (camera.getYoff() + pixelWindowHeight) / Tile.TILE_HEIGHT + 1);
		int leftCamera = (int) Math.max(0, camera.getXoff() / Tile.TILE_WIDTH);
		int rightCamera = (int) Math.min(mapWidth, (camera.getXoff() + pixelWindowWidth) / Tile.TILE_WIDTH + 1);

		for (int x = leftCamera; x < rightCamera; x++) { // reversed order to avoid bugs
			for (int y = upCamera; y < downCamera; y++) {
				getTile(y, x).render(gc, (int) (x * Tile.TILE_WIDTH - camera.getXoff()),
						(int) (y * Tile.TILE_HEIGHT - camera.getYoff()));
			}
		}
	}

	// x i y sa jakos pokickane
	public Tile getTile(int x, int y) {
		if (y < 0 || y >= mapWidth || x < 0 || x >= mapHeight)
			return Tile.tiles[2];

		Tile t = Tile.tiles[mapTiles[x][y]];
		if (t == null)
			t = Tile.tiles[0];

		return t;
	}

	private void loadMapFromFile(String path) {
		String file = loadFileToString(path);
		String[] numbers = file.split("\\s+");

		mapTiles = new int[mapHeight][mapWidth];

		for (int x = 0; x < mapHeight; x++) {
			for (int y = 0; y < mapWidth; y++) {
				mapTiles[x][y] = toInteger(numbers[(y + x * mapWidth)]);
			}
		}
	}

	private String loadFileToString(String path) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;

			line = reader.readLine();
			String[] numbers = line.split("\\s+");
			mapWidth = numbers.length;
			builder.append(line + "\n");

			mapHeight = 1; // because 1 line is already red
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
				mapHeight++;
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	private int toInteger(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}
}

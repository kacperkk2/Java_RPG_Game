package tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tile {
	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	
	// ALL TEXTURES OF WORLDS
	public static Tile[] tiles = new Tile[256];
	public static Tile grass = new Grass(0);
	public static Tile rock = new Rock(1);
	public static Tile rockTop = new RockTop(2);
	public static Tile woodFloor = new WoodFloor(3);
	public static Tile blueFlowerGrass = new BlueFlowerGrass(4);
	public static Tile redFlowerGrass = new RedFlowerGrass(5);
	public static Tile whiteFlowerGrass = new WhiteFlowerGrass(6);
	public static Tile sand = new Sand(7);
	public static Tile sandBricks = new SandBricks(8);
	public static Tile mud = new Mud(9);
	public static Tile waterClear = new WaterClear(10);
	
	protected Image texture;
	protected final int id;
	
	public Tile(Image texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void update() {
		
	}
	
	public void render(GraphicsContext gc, int x, int y) {
		gc.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT);
	}
	
	public boolean walkable() {
		return true;
	}
	
	public int getId() {
		return id;
	}
}

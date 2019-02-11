package characters;

import game.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import tiles.Tile;
import worlds.WorldMap;

public abstract class Character {
	
	public static final int DEFAULT_HP = 100, DEFAULT_MP = 20, DEFAULT_STAMINA = 50,
							DEFAULT_ATTACK = 5, DEFAULT_DEFENCE = 1, DEFAULT_EFFORT = 2;
	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_WIDTH = 64, DEFAULT_HEIGHT = 96;

	protected WorldMap map;
	protected Image fightImage;
	protected Camera camera;
	
	// character position and size of texture
	protected float x, y;
	protected int width, height;
	
	// character attributes
	protected int hp, mp, stamina, currentHp, currentMp, currentStamina, lvl;
	protected int attack, defence, effort; // effort means stamina needed to attack other character
	protected float speed;
	protected float dx, dy;
	protected String name;
	
	protected Rectangle collision; // by default collision box is on all character surface, i change it in player class
	
	public Character(Camera camera, float x, float y, int width, int height) {
		name = "NOTSET";
		this.camera = camera;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collision = new Rectangle(0, 0, width, height);
		hp = currentHp = DEFAULT_HP;
		mp = currentMp = DEFAULT_MP;
		stamina = currentStamina = DEFAULT_STAMINA;
		attack = DEFAULT_ATTACK;
		defence = DEFAULT_DEFENCE;
		effort = DEFAULT_EFFORT;
		lvl = 1;
		speed = DEFAULT_SPEED;
		dx = 0;
		dy = 0;
		fightImage = new Image("tex/grass.png"); // to avoid null, just in case
	}
	
	public void move() {
		xAxis();
		yAxis();
	}
	
	public void xAxis() {
		// find y top and bottom of collision box
		int yTopCollision = (int) (y + collision.getY()) / Tile.TILE_HEIGHT;
		int yBottomCollision = (int) (y + collision.getY() + collision.getHeight()) / Tile.TILE_HEIGHT;
		
		if(dx > 0) { // goes right
			int tileX = (int) (x + dx + collision.getX() + collision.getWidth()) / Tile.TILE_WIDTH;
			
			if(map.getTile(yTopCollision, tileX).walkable() &&
			   map.getTile(yBottomCollision, tileX).walkable()) {
				x += dx;
			}
			else { // if there is collision swipe player to tile
				x = (int) (tileX * Tile.TILE_WIDTH - collision.getX() - collision.getWidth() - 1);
			}
		}
		else if(dx < 0) { // goes left
			int tileX = (int) (x + dx + collision.getX()) / Tile.TILE_WIDTH;
			
			if(map.getTile(yTopCollision, tileX).walkable() &&
			   map.getTile(yBottomCollision, tileX).walkable()) {
				x += dx;
			}
			else { // if there is collision swipe player to tile
				x = (int) (tileX * Tile.TILE_WIDTH + Tile.TILE_WIDTH - collision.getX());
			}
		}
	}
	
	public void yAxis() {
		int xLeftCollsion = (int) (x + collision.getX()) / Tile.TILE_WIDTH;
		int xRightCollsion = (int) (x + collision.getX() + collision.getWidth()) / Tile.TILE_WIDTH;
		
		if(dy > 0) { // goes down
			int tileY = (int) (y + dy + collision.getY() + collision.getHeight()) / Tile.TILE_HEIGHT;
			
			if(map.getTile(tileY, xLeftCollsion).walkable() &&
			   map.getTile(tileY, xRightCollsion).walkable()) {
				y += dy;
			}
			else { // if there is collision swipe player to tile
				y = (int) (tileY * Tile.TILE_HEIGHT - collision.getY() - collision.getHeight() - 1);
			}
		}
		else if(dy < 0) { // goes up
			int tileY = (int) (y + dy + collision.getY()) / Tile.TILE_HEIGHT;
			
			if(map.getTile(tileY, xLeftCollsion).walkable() &&
			   map.getTile(tileY, xRightCollsion).walkable()) {
				y += dy;
			}
			else { // if there is collision swipe player to tile
				y = (int) (tileY * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - collision.getY());
			}
		}
	}

	public abstract void update();
	public abstract void render(GraphicsContext gc);
	
	// gets x tile and y tile, and sets there character
	public void setTilePosition(int x, int y) {
		this.x = x * Tile.TILE_WIDTH;
		this.y = y * Tile.TILE_HEIGHT;
	}
	
	public int getTileXPosition() {
		return (int) (x / Tile.TILE_WIDTH);
	}
	
	public int getTileYPosition() {
		return (int) (y / Tile.TILE_HEIGHT);
	}
	
	public void showLvlAndName(GraphicsContext gc) {	
		gc.setFont(new Font("Verdana", 17));
		gc.setFill(Color.YELLOW);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Lv." + lvl + " " + name, x - camera.getXoff() + Character.DEFAULT_WIDTH/2, 
				y - camera.getYoff() - 8);
	}
	
	//GETERS AND SETTERS
	
	public void setFightImage(Image fightImage) {
		this.fightImage = fightImage;
	}
	
	public Image getFightImage() {
		return fightImage;
	}
	
	public void setWorldMap(WorldMap map) {
		this.map = map;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getHp() {
		return hp;
	}

	public void setp(int hp) {
		this.hp = hp;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public int getCurrentMp() {
		return currentMp;
	}

	public void setCurrentMp(int currentMp) {
		this.currentMp = currentMp;
	}

	public int getCurrentStamina() {
		return currentStamina;
	}

	public void setCurrentStamina(int currentStamina) {
		this.currentStamina = currentStamina;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}
	
}

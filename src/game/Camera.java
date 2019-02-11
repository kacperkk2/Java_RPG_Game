package game;

import characters.Character;
import tiles.Tile;

public class Camera {

	private float xoff, yoff;
	private Game game;
	
	public Camera(Game game, float xoff, float yoff) {
		this.game = game;
		this.xoff = xoff;
		this.yoff = yoff;
	}
	
	public void checkMapBounds() {
		if(xoff < 0) {
			xoff = 0;
		}
		else if(xoff > game.getWorld().getWorldMap().getMapWidth() * Tile.TILE_WIDTH - game.getWidth()) {
			xoff = game.getWorld().getWorldMap().getMapWidth() * Tile.TILE_WIDTH - game.getWidth();
		}
		
		if(yoff < 0) {
			yoff = 0;
		}												// +30 cause ubuntu calculates height window with that black strip on top
		else if(yoff > game.getWorld().getWorldMap().getMapHeight() * Tile.TILE_HEIGHT - game.getHeight() +30) {
			yoff = game.getWorld().getWorldMap().getMapHeight() * Tile.TILE_HEIGHT - game.getHeight() +30;
		}
	}
	
	public void centerOnPlayer(Character ch) {
		xoff = ch.getX() - game.getWidth() / 2 + ch.getWidth() / 2;
		yoff = ch.getY() - game.getHeight() /2 + ch.getHeight() / 2;
		checkMapBounds();
	}
	
	public void moveCamera(float x, float y) {
		xoff += x;
		yoff += y;
		checkMapBounds();
	}
	
	public float getXoff() {
		return xoff;
	}

	public void setXoff(float xoff) {
		this.xoff = xoff;
		checkMapBounds();
	}

	public float getYoff() {
		return yoff;
	}

	public void setYoff(float yoff) {
		this.yoff = yoff;
		checkMapBounds();
	}
}

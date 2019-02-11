package characters;

import game.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import worlds.WorldMap;

public class Tradesman extends Character{

	private WorldMap map;
	//private Camera camera;
	// teraz nie daje tradesmanFrames bo mam tylko 1 obrazek, w przyszlosci dodac trzeba jakas animacje
	
	public Tradesman(Camera camera, float x, float y, int width, int height) {
		super(camera, x, y, width, height);
		name = "Tradesman";
		this.map = map;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(new Image("tex/tradesman/tradesman.png"), x - camera.getXoff(), y - camera.getYoff(), width, height);
		showLvlAndName(gc);
	}

}

package characters;

import animation.FramesSpook;
import game.Camera;
import javafx.scene.canvas.GraphicsContext;
import worlds.WorldMap;

public class Spook extends Character{
	
	//private Camera camera;
	private FramesSpook framesSpook;
	
	public Spook(Camera camera, float x, float y, int width, int height) {
		super(camera, x, y, width, height);
		name = "Spook";
		framesSpook = new FramesSpook();
		setFightImage(framesSpook.getFight());
	}
	
	@Override
	public void update() {
		framesSpook.update();
	}

	@Override
	public void render(GraphicsContext gc) {
		/*if(y > camera.getYoff() && y  < camera.getYoff() + Launcher.WINDOW_HEIGHT &&
				x > camera.getXoff() && x < camera.getXoff() + Launcher.WINDOW_WIDTH)*/
		// renders spook always, even when he is off the camera
		gc.drawImage(framesSpook.getCurrentStaticFrame(), x - camera.getXoff(), y - camera.getYoff(), width, height);
		showLvlAndName(gc);
	}
}

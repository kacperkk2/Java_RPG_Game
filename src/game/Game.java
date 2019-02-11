package game;

import characters.UserInput;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import worlds.World;

public class Game {

	private Display display;
	private int width, height;
	private GraphicsContext gc;
	
	private World world;
	private UserInput input;
	private Camera camera;
	
	private AnimationTimer timer;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;

		display = new Display(title, width, height);
		gc = display.getGraphicsContext();
		
		input = new UserInput(display.getScene());
		camera = new Camera(this, 0, 0);
		world = new World(this, input, camera, width, height);
		
		draw();
	}
	
	private void update() {
		world.update();
	}
	
	private void render() {
		gc.clearRect(0, 0, width, height);
		
		world.render(gc);
	}

	private void draw() {
		timer = new AnimationTimer() {
			int fps = 60;
			double timePerTick = 1000000000 / fps;
			double delta = 0;
			long now;
			long lastTime = System.nanoTime();
			long timer = 0;
			int ticks = 0;
			
		    @Override
		    public void handle(long timestamp) {
		    	now = timestamp;
		    	delta += (now - lastTime) / timePerTick;
		    	timer += now - lastTime;
		    	lastTime = now;
		    	
		        if (delta >= 1) {

		            update();
		            render();
		            
		            delta--;
		            ticks++;
		        }
		        
		        if(timer >= 1000000000) {
		        	System.out.println("FPS " + ticks);
		        	ticks = 0;
		        	timer = 0;
		        }
		    }
		};
		timer.start();
	}
	
	public AnimationTimer getTimer() {
		return timer;
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void startTimer() {
		timer.start();
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
	
	public World getWorld() {
		return world;
	}
	
	public Display getDisplay() {
		return display;
	}
}

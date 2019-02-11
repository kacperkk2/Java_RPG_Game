package animation;

import javafx.scene.image.Image;

public class Animation {
	
	public static final int DEFAULT_ANIMATION_INTERVAL = 200;

	private int interval, framesIndex;
	private Image[] frames;
	private long lastTime, timer;
	
	public Animation(int interval, Image[] frames) {
		this.interval = interval;
		this.frames = frames;
		framesIndex = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void update() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > interval) {
			framesIndex = (framesIndex + 1) % frames.length;
			timer = 0;
		}
	}
	
	public Image getCurrentFrame() {
		return frames[framesIndex];
	}
}

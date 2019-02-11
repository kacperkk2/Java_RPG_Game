package animation;

import javafx.scene.image.Image;

public class FramesSpook {

	private Image[] stat;
	private Image fight;
	
	private Animation staticAnimation;
	
	public FramesSpook() {
		prepareFrames();
		staticAnimation = new Animation(Animation.DEFAULT_ANIMATION_INTERVAL, stat);
	}
	
	private void prepareFrames() { // zmienic na tekstury spooka
		fight = new Image("tex/spook/R_100opacity.png");
		stat = new Image[] {new Image("tex/spook/R_0opacity.png"), new Image("tex/spook/R_20opacity.png"), 
				new Image("tex/spook/R_40opacity.png"), new Image("tex/spook/R_60opacity.png"),
				new Image("tex/spook/R_80opacity.png"), new Image("tex/spook/R_100opacity.png"),
				new Image("tex/spook/R_100opacity.png"), new Image("tex/spook/R_100opacity.png"),
				new Image("tex/spook/R_80opacity.png"), new Image("tex/spook/R_60opacity.png"),
				new Image("tex/spook/R_40opacity.png"), new Image("tex/spook/R_20opacity.png"),};
	}
	
	public void update() {
		staticAnimation.update();
	}
	
	public Image getFight() {
		return fight;
	}
	
	public Image getCurrentStaticFrame() {
		return staticAnimation.getCurrentFrame();
	}
}

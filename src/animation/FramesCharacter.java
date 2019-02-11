package animation;

import javafx.scene.image.Image;

public class FramesCharacter {

	private Image[] down, up, left, right;
	private Image standDown, standUp, standRight, standLeft, fight;
	
	private Animation downAnimation;
	private Animation upAnimation;
	private Animation leftAnimation;
	private Animation rightAnimation;
	
	public FramesCharacter() {
		prepareFrames();
		downAnimation = new Animation(Animation.DEFAULT_ANIMATION_INTERVAL, down);
		upAnimation = new Animation(Animation.DEFAULT_ANIMATION_INTERVAL, up);
		leftAnimation = new Animation(Animation.DEFAULT_ANIMATION_INTERVAL, left);
		rightAnimation = new Animation(Animation.DEFAULT_ANIMATION_INTERVAL, right);
	}
	
	private void prepareFrames() {
		fight = new Image("tex/player/D_stand.png");
		standDown = new Image("tex/player/D_stand.png");
		standUp = new Image("tex/player/U_stand.png");
		standRight = new Image("tex/player/R_stand.png");
		standLeft = new Image("tex/player/L_stand.png");
		
		down = new Image[] {new Image("tex/player/D_right_leg.png"), new Image("tex/player/D_stand.png"), 
				new Image("tex/player/D_left_leg.png"), new Image("tex/player/D_stand.png")};
		
		up = new Image[] {new Image("tex/player/U_right_leg.png"), new Image("tex/player/U_stand.png"), 
				new Image("tex/player/U_left_leg.png"), new Image("tex/player/U_stand.png")};
		
		left = new Image[] {new Image("tex/player/L_right_leg.png"), new Image("tex/player/L_stand.png"), 
				new Image("tex/player/L_left_leg.png"), new Image("tex/player/L_stand.png")};
		
		right = new Image[] {new Image("tex/player/R_right_leg.png"), new Image("tex/player/R_stand.png"), 
				new Image("tex/player/R_left_leg.png"), new Image("tex/player/R_stand.png")};
	}
	
	public void update() {
		downAnimation.update();
		upAnimation.update();
		leftAnimation.update();
		rightAnimation.update();
	}
	
	public Image getCurrentDownFrame() {
		return downAnimation.getCurrentFrame();
	}
	
	public Image getCurrentUpFrame() {
		return upAnimation.getCurrentFrame();
	}
	
	public Image getCurrentLeftFrame() {
		return leftAnimation.getCurrentFrame();
	}
	
	public Image getCurrentRightFrame() {
		return rightAnimation.getCurrentFrame();
	}

	public Image getStandDown() {
		return standDown;
	}
	
	public Image getFight() {
		return fight;
	}

	public Image getStandUp() {
		return standUp;
	}

	public Image getStandRight() {
		return standRight;
	}

	public Image getStandLeft() {
		return standLeft;
	}
}

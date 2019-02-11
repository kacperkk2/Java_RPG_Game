package characters;

import animation.FramesCharacter;
import game.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import worlds.WorldMap;

public class Player extends Character{

	private FramesCharacter framesPlayer;
	private UserInput input; // mozna tez zrobic tak ze nie przekazywac przez konstuktor, tylko tu trzymac game i przed game brac input, ale narazie nie ma potrzeby calego game trrzymac
	//private Camera camera;
	private String direction;
	
	public Player(UserInput input, Camera camera, float x, float y, int width, int height) {
		super(camera, x, y, width, height);
		name = "Player";
		this.input = input;
		direction = "down";
		
		// redefine collison box size
		collision.setX(16);
		collision.setY(48);
		collision.setWidth(32);
		collision.setHeight(48);
		
		framesPlayer = new FramesCharacter(); // stores info of player animation (textures can be changed within thsi class)
		setFightImage(framesPlayer.getFight());
	}

	@Override
	public void update() {
		// updates player animation frames
		framesPlayer.update();
		
		setDirection();
		move();
		camera.centerOnPlayer(this);
	}
	
	private void setDirection() {
		dy = 0; // to prevent sliding without typing wsad
		dx = 0;
		
		if(input.up) {
			dy = -speed;
			direction = "up";
		}
		if(input.down) {
			dy = speed;
			direction = "down";
		}
		if(input.left) {
			dx = -speed;
			direction = "left";
		}
		if(input.right) {
			dx = speed;
			direction = "right";
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(getCurrentImage(), (int) (x - camera.getXoff()), (int) (y - camera.getYoff()), width, height);
	
		//gc.fillRect(x + collision.getX() - camera.getXoff(), y + collision.getY() - camera.getYoff(), collision.getWidth(), collision.getHeight());
		showLvlAndName(gc);
		
		showStats(gc);
	}
	
	private void showStats(GraphicsContext gc) {
		// to mozna pozniej przeniesc do jakiejs klasy nowej i odpalac tylko metode z tej klasy
		int width = 250;
		int height = 150;
		float xWindow = x - camera.getXoff() - (width - Character.DEFAULT_WIDTH)/2;
		float yWindow = y - camera.getYoff() - height - 5;
		
		if(input.stats) {
			gc.setFill(Color.BLACK);
			gc.fillRect(xWindow, yWindow, width, height);
			
			gc.setFill(Color.WHITE);
			gc.setFont(new Font("Verdana", 18));
			gc.setTextAlign(TextAlignment.CENTER);
			gc.fillText("HEALTH", xWindow + width/4, yWindow + 20);
			gc.fillText("2000/2000", xWindow + 3*width/4, yWindow + 20);
			gc.fillText("MANA", xWindow + width/4, yWindow + 50);
			gc.fillText("500/500", xWindow + 3*width/4, yWindow + 50);
			gc.fillText("STAMINA", xWindow + width/4, yWindow + 80);
			gc.fillText("800/800", xWindow + 3*width/4, yWindow + 80);
		}
	}
	
	private Image getCurrentImage() {
		if(dx < 0) { // goes left
			return framesPlayer.getCurrentLeftFrame();
		}
		else if(dx > 0) { // goes right
			return framesPlayer.getCurrentRightFrame();
		}
		else if(dy > 0) { // goes down
			return framesPlayer.getCurrentDownFrame();
		}
		else if(dy < 0) { // goes up
			return framesPlayer.getCurrentUpFrame();
		}
		else { // stands
			switch(direction) {
			case "up" : return framesPlayer.getStandUp();
			case "down" : return framesPlayer.getStandDown();
			case "left" : return framesPlayer.getStandLeft();
			case "right" : return framesPlayer.getStandRight();
			default : return framesPlayer.getStandDown();
			}
		}
	}
}

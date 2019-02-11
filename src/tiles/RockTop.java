package tiles;

import javafx.scene.image.Image;

public class RockTop extends Tile{
	
	public RockTop(int id) {
		super(new Image("tex/rock_top.png"), id);
	}
	
	@Override
	public boolean walkable() {
		return false;
	}
}

package tiles;

import javafx.scene.image.Image;

public class Rock extends Tile{

	public Rock(int id) {
		super(new Image("tex/rock.png"), id);
	}
	
	@Override
	public boolean walkable() {
		return false;
	}
}

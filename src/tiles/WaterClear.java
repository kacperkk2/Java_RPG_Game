package tiles;

import javafx.scene.image.Image;

public class WaterClear extends Tile{

	public WaterClear(int id) {
		super(new Image("tex/water_clear.png"), id);
	}

	@Override
	public boolean walkable() {
		return false;
	}
}

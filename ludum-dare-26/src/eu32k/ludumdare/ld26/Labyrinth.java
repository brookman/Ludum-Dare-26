package eu32k.ludumdare.ld26;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {

	private int xSize;
	private int ySize;
	private List<Tile> tiles;

	public Labyrinth(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		tiles = new ArrayList<Tile>();
	}
}

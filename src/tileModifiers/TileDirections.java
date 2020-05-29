package tileModifiers;

public enum TileDirections {
	NORTH(0),
	SOUTH(1),
	EAST(2),
	WEST(3),
	NORTHWEST(4),
	NORTHEAST(5),
	SOUTHWEST(6),
	SOUTHEAST(7);

	TileDirections(int index) {
		this.index=index;
	}
	
	public final int index;

}

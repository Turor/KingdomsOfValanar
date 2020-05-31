package utilities;

import entities.Tile;

public class DijkstraTileContainer implements Comparable<DijkstraTileContainer> {
	
	public Tile tile;
	
	public int distance;
	
	public DijkstraTileContainer(Tile t, int cost) {
		this.tile = t;
		this.distance = cost;
	}
	
	public int getCost(HasVision type) {
		return tile.costFor(type);
	}

	@Override
	public int compareTo(DijkstraTileContainer arg0) {
		if(this.distance<arg0.distance)
			return -1;
		else if(this.distance>arg0.distance) {
			return 1;
		}
		return 0;
	}
	
	public boolean equals(DijkstraTileContainer other) {
		return this.tile.equals(other.tile);
	}
	
	public int hashCode() {
		return tile.hashCode();
	}

}

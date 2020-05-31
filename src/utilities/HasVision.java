package utilities;

import java.util.HashSet;
import java.util.Set;

import entities.Tile;
import unitModifiers.MovementClass;

public abstract class HasVision {
	protected int range;
	protected MovementClass movementType;
	protected Tile location;
	protected Set<Tile> visionRange;
	protected Set<Tile> movementRange;
	
	public HasVision(MovementClass movementType, Tile location, int range) {
		this.movementType = movementType;
		this.range = range;
		this.location = location;
		visionRange = null;
	}
	
	public Tile getLocation() {
		return location;
	}

	public MovementClass getMovementClass() {
		return movementType;
	}
	
	public int getRange() {
		return range;
	}
	
	public void updateVision() {
		visionRange = StaticFunctions.dijkstra(this,range/2);	
	}
	
	public Set<Tile> getVision(){
		updateVision();
		return visionRange;
	}
	
	public Set<Tile> getMovementRange(){
		movementRange = StaticFunctions.dijkstra(this, range);
		return movementRange;
	}
	
}

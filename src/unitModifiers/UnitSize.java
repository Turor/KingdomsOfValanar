package unitModifiers;

public enum UnitSize {
	D4 (.66,4),
	D6 (1,6),
	D8 (1.33,8),
	D10 (1.66,10),
	D12 (2,12);
	
	private final double costFactor;
	private final int health;
	
	UnitSize(double costFactor, int health){
		this.costFactor = costFactor;
		this.health = health;
	}
	
	public double costFactor() {return costFactor;}
	public int health() {return health;}
}

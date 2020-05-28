package unitModifiers;

import utilities.UnitStatPackage;

public enum UnitExperience {
	GREEN(1,0,0,100,1),
	REGULAR(1,1,1,150,2),
	SEASONED(1,1,2,200,4),
	VETERAN(1,1,3,250,8),
	ELITE(2,2,4,350,16),
	SUPER_ELITE(2,2,5,400,32);

	private UnitExperience(int attack, int toughness, int morale, int cost,int battlesToLevel) {
		this.attack=attack;
		this.toughness=toughness;
		this.morale=morale;
		this.cost=cost;
		this.battlesToLevel = battlesToLevel;
	}
	
	private final int attack;
	private final int toughness;
	private final int morale;
	private final int cost;
	private final int battlesToLevel;
	
	public int attack() {
		return attack;
	}
	
	public int toughness() {
		return toughness;
	}
	
	public int morale() {
		return morale;
	}
	
	public int cost() {
		return cost;
	}
	
	public int getBattlesToLevel() {
		return battlesToLevel;
	}
	
	public String toString() {
		switch(this) {
		case GREEN:
			return "Green";
		case REGULAR:
			return "Regular";
		case SEASONED:
			return "Seasoned";
		case VETERAN:
			return "Veteran";
		case ELITE:
			return "Elite";
		case SUPER_ELITE:
			return "Super-Elite";
		default:
			return "";
		}
	}
	
	public UnitStatPackage getStatPackage() {
		return new UnitStatPackage(attack,0.,0.,toughness,morale, 0);
	}

}

package unitModifiers;

import utilities.PackageType;
import utilities.ResourcePackage;
import utilities.ResourceTypes;
import utilities.UnitStatPackage;

public enum UnitEquipment {
	LIGHT(0,1,1,0,0,25,75),
	MEDIUM(0,2,2,0,0,50,125),
	HEAVY(0,4,4,0,0,100,200),
	SUPER_HEAVY(0,6,6,0,0,150,300);
	
	private UnitEquipment(int attack, int power, int defense, int toughness, int morale,  double metalCost,
			double woodCost) {
		this.attack = attack;
		this.power = power;
		this.defense = defense;
		this.toughness = toughness;
		this.morale = morale;
		this.metalCost = metalCost;
		this.woodCost = woodCost;
	}
	
	private final int attack;
	private final int power;
	private final int defense;
	private final int toughness;
	private final int morale;
	private final double metalCost;
	private final double woodCost;
	
	public UnitStatPackage getStats() {
		return new UnitStatPackage(attack,power,defense,toughness,morale,0);
	}
	
	public ResourcePackage getCost() {
		ResourcePackage temp = new ResourcePackage(PackageType.flat);
		temp.add(ResourceTypes.Metal, metalCost);
		temp.add(ResourceTypes.Wood, woodCost);
		return temp;
	}
	
	public String toString() {
		switch(this) {
		case LIGHT:
			return "Light";
		case MEDIUM:
			return "Medium";
		case HEAVY:
			return "Heavy";
		case SUPER_HEAVY:
			return "Super Heavy";
		default:
			return "";
		}
	}
}

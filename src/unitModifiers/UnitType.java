package unitModifiers;

import utilities.PackageType;
import utilities.ResourcePackage;
import utilities.ResourceTypes;
import utilities.UnitStatPackage;

public enum UnitType{
	FLYING(0	,	0,	0,	0,	3,	16,	1,	1,	2,MovementClass.FLYING),
	ARCHER(0,1,0,0,1,8,1,2,1.25,MovementClass.FOOT),
	CAVALRY(1,1,0,0,2,16,1.5,1.5,1.5,MovementClass.MOUNTED),
	LEVY(0,0,0,0,-1,8,0.75,0.75,0.75,MovementClass.FOOT),
	INFANTRY(0,0,1,1,0,8,1,1,1,MovementClass.FOOT),
	SIEGE_ENGINE(1,1,0,1,0,4,2,2,1.25,MovementClass.SIEGE),
	NAVY(1,1,0,2,0,16,1,3,1,MovementClass.SAIL);
	
	private UnitType(int attack, int power, int defense, int toughness, int morale, int movement, double metalCost,
			double woodCost, double goldCost,MovementClass style) {
		this.attack=attack;
		this.power=power;
		this.defense=defense;
		this.toughness=toughness;
		this.morale=morale;
		this.movement=movement;
		this.metalCost=metalCost;
		this.woodCost=woodCost;
		this.goldCost=goldCost;
		movementStyle = style;
	}
	
	public final int attack;
	public final int power;
	public final int defense;
	public final int toughness;
	public final int morale;
	public final int movement;
	private final double metalCost;
	private final double woodCost;
	private final double goldCost;
	private final MovementClass movementStyle;
	
	public UnitStatPackage getStats() {
		return new UnitStatPackage(attack,power,defense,toughness,morale,movement);
	}
	
	public ResourcePackage getCost() {
		ResourcePackage temp = new ResourcePackage();
		temp.add(ResourceTypes.Metal, 1);
		temp.add(ResourceTypes.Wood,1);
		temp.add(ResourceTypes.Gold, 1);
		temp.multiply(ResourceTypes.Metal, metalCost);
		temp.multiply(ResourceTypes.Wood, woodCost);
		temp.multiply(ResourceTypes.Gold, goldCost);
		return temp;
	}
	
	public MovementClass movementClass() {
		return movementStyle;
	}
	
	public String toString() {
		switch(this) {
		case FLYING:
			return "Flying";
		case CAVALRY:
			return "Cavalry";
		case LEVY:
			return "Levy";
		case ARCHER:
			return "Archer";
		case INFANTRY:
			return "Infantry";
		case SIEGE_ENGINE:
			return "Siege Engine";
		case NAVY:
			return "Navy";
		default:
			return "";
		}
	}
}

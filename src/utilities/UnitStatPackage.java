package utilities;

public class UnitStatPackage {
	
	public double attack;
	public double power;
	public double defense;
	public double toughness;
	public double morale;
	public double movement;
	
	
	public UnitStatPackage(double attack, double power, double defense, double toughness, double morale, double movement) {
		this.attack = attack;
		this.power = power;
		this.defense = defense;
		this.toughness = toughness;
		this.morale = morale;
		this.movement = movement;
	}
	
	public UnitStatPackage(UnitStatPackage stats) {
		attack = stats.attack;
		power = stats.power;
		defense = stats.defense;
		toughness = stats.toughness;
		morale = stats.morale;
		movement = stats.movement;
	}
	
	public UnitStatPackage() {
		attack = 0;
		power = 0;
		defense = 0;
		toughness = 0;
		morale = 0;
		movement = 0;
	}
	
	public void add(UnitStatPackage stats) {
		attack +=stats.attack;
		power += stats.power;
		defense += stats.defense;
		toughness += stats.toughness;
		morale += stats.morale;
		movement += stats.movement;
	}
	
	public void multiply(UnitStatPackage stats) {
		attack *= stats.attack;
		power *= stats.power;
		defense *= stats.defense;
		toughness *= stats.toughness;
		morale *= stats.morale;
		movement *= stats.movement;
	}

}

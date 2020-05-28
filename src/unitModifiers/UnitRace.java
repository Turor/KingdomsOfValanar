package unitModifiers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import utilities.UnitStatPackage;

public enum UnitRace {
	BUGBEAR(2,0,0,0,1,50, 
			new UnitTraits[] {UnitTraits.MARTIAL}),
	DRAGONBORN(2,2,1,1,1,175,
			new UnitTraits[] {UnitTraits.COURAGEOUS}),
	DWARF(3,1,1,2,1,225,
			new UnitTraits[] {UnitTraits.STALWART}),
	ELF(2,0,0,0,1,50,
			new UnitTraits[] {UnitTraits.ETERNAL}),
	ELF_WINGED(1,1,0,0,1,50,
			new UnitTraits[] {UnitTraits.ETERNAL}),
	GHOUL(-1,0,2,2,0,125,
			new UnitTraits[] {UnitTraits.UNDEAD,UnitTraits.HORRIFYING,UnitTraits.RAVENOUS}),
	GNOLL(2,0,0,0,1,50,
			new UnitTraits[] {UnitTraits.RAVENOUS}),
	GNOME(1,-1,1,-1,1,-25,
			new UnitTraits[] {}),
	GOBLIN(-1,-1,1,-1,0,-75,
			new UnitTraits[] {}),
	HOBGOBLIN(2,0,0,0,1,50,
			new UnitTraits[] {UnitTraits.BRED_FOR_WAR,UnitTraits.MARTIAL}),
	HUMAN(2,0,0,0,1,50,
			new UnitTraits[] {UnitTraits.COURAGEOUS}),
	KOBOLD(-1,-1,1,-1,-1,-75,
			new UnitTraits[] {}),
	LIZARDFOLK(2,1,-1,1,1,100,
			new UnitTraits[] {UnitTraits.AMPHIBIOUS}),
	OGRE(0,2,0,2,1,150,
			new UnitTraits[] {UnitTraits.BRUTAL}),
	ORC(2,1,1,1,2,150,
			new UnitTraits[] {UnitTraits.SAVAGE}),
	SKELETON(-2,-1,1,1,1,0,
			new UnitTraits[] {UnitTraits.UNDEAD,UnitTraits.MINDLESS}),
	TREANT(0,2,0,2,0,150,
			new UnitTraits[] {UnitTraits.TWISTING_ROOTS,UnitTraits.ROCK_HURLER}),
	TROLL(0,2,0,2,0,150,
			new UnitTraits[] {UnitTraits.REGENERATE}),
	ZOMBIE(-2,0,2,2,2,100,
			new UnitTraits[] {UnitTraits.UNDEAD,UnitTraits.MINDLESS}),
	;

	private UnitRace(int attack, int power, int defense, int toughness, int morale, int cost,
			UnitTraits[] traits) {
		this.attack=attack;
		this.power=power;
		this.defense=defense;
		this.toughness=toughness;
		this.morale=morale;
		this.cost=cost;
		traitsProvided = new HashSet<UnitTraits>();
		for(int i = 0; i < traits.length;i++) {
			traitsProvided.add(traits[i]);
		}
	}
	
	private Set<UnitTraits> traitsProvided;
	private final int attack;
	private final int power;
	private final int defense;
	private final int toughness;
	private final int morale;
	public final int cost;
	
	public UnitStatPackage getStats() {
		return new UnitStatPackage(attack,power,defense,toughness,morale,0);
	}
	
	
	public String toString() {
		switch(this) {
		case BUGBEAR:
			return "Bugbear";
		case DRAGONBORN:
			return "Dragonborn";
		case DWARF:
			return "Dwarf";
		case ELF:
			return "Elf";
		case ELF_WINGED:
			return "Winged Elf";
		case GHOUL:
			return "Ghoul";
		case GNOLL:
			return "Gnoll";
		case GNOME:
			return "Gnome";
		case GOBLIN:
			return "Goblin";
		case HOBGOBLIN:
			return "Hobgoblin";
		case HUMAN:
			return "Human";
		case KOBOLD:
			return "Kobold";
		case LIZARDFOLK:
			return "Lizardfolk";
		case OGRE:
			return "Ogre";
		case ORC:
			return "Orc";
		case SKELETON:
			return "Skeleton";
		case TREANT:
			return "Treant";
		case TROLL:
			return "Troll";
		case ZOMBIE:
			return "Zombie";
		default:
			return "";
		}
	}
	

	public Set<UnitTraits> getTraits(){
		return new HashSet<UnitTraits>(traitsProvided);
	}
	

}

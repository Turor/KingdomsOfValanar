package unitModifiers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import technology.Technology;
import utilities.Description;
import utilities.ResourcePackage;
import utilities.UnitStatPackage;

public class UnitModifiers {
	private Description desc;

	private UnitStatPackage flatBonuses;
	private UnitStatPackage multiBonuses;

	private Set<Technology> prerequisites;

	private ResourcePackage buildCost;
	private ResourcePackage multiCost;
	private ResourcePackage upkeepCost;

	private Set<UnitEquipment> equip;
	private Set<UnitExperience> exp;
	private Set<UnitRace> race;
	private Set<UnitSize> size;
	private Set<UnitType> type;
	private Set<UnitTraits> traits;

	public UnitModifiers(Description desc, UnitStatPackage flatStatBonuses,
			UnitStatPackage multiplicativeStatBonuses,
			ArrayList<Technology> prerequisites, ResourcePackage buildCost, ResourcePackage upkeepCost,
			Set<UnitEquipment> applicableEquip,Set<UnitExperience> applicableExp,Set<UnitRace> applicableRace,
			Set<UnitSize> applicableSize, Set<UnitType> applicableType,
			Set<UnitTraits> extraTraits) {
		this.desc = desc;
		this.flatBonuses = new UnitStatPackage(flatStatBonuses);
		this.multiBonuses = new UnitStatPackage(multiplicativeStatBonuses);

		this.prerequisites = new HashSet<Technology>();
		this.prerequisites.addAll(prerequisites);

		this.buildCost = new ResourcePackage(buildCost);
		this.upkeepCost = new ResourcePackage(upkeepCost);

		equip= applicableEquip;
		exp =applicableExp;
		race = applicableRace;
		size = applicableSize;
		type = applicableType;
		traits = extraTraits;
	}

	public ResourcePackage getCost() {
		return new ResourcePackage(buildCost);
	}
	
	public ResourcePackage getMultiCost() {
		return new ResourcePackage(multiCost);
	}

	public ResourcePackage getUpkeep() {
			return new ResourcePackage(upkeepCost);
	}

	public UnitStatPackage getFlatBonus() {
		return new UnitStatPackage(flatBonuses);
	}

	public UnitStatPackage getMultiBonuses() {
		return new UnitStatPackage(multiBonuses);

	}

	public Set<UnitTraits> getTraits(){
		return new HashSet<UnitTraits>(traits);
	}

	public Description getDescription() {
		return new Description(desc);
	}

	public boolean applies(UnitEquipment equip, UnitExperience exp, UnitRace race, UnitSize size, UnitType type) {
		return this.equip.contains(equip)|| this.exp.contains(exp) || this.race.contains(race)||this.size.contains(size)
				||this.type.contains(type);
	}

	public Set<Technology> getTechnology(){
		Set<Technology> requiredTech = new HashSet<Technology>();
		requiredTech.addAll(prerequisites);
		return requiredTech;
	}





}

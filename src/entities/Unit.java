package entities;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import technology.Technology;
import unitModifiers.*;
import utilities.UnitSize;
import utilities.UnitStatPackage;
import utilities.MovementClass;
import utilities.ResourcePackage;
import utilities.ResourceTypes;
import utilities.StaticFunctions;

public class Unit {
	
	private String unitDescription;
	
	private UnitStatPackage stats;
	private UnitEquipment equipment;
	private UnitExperience experience;
	private UnitRace race;
	private Set<UnitTraits> traits;
	private UnitType type;
	
	
	private Kingdom owner;
	private LinkedList<Kingdom> secondaryControllers;
	private Tile location;
		
	private int health;
	private MovementClass movementClass;
	private UnitSize size;
	
	private Set<Technology> requiredTech;
	
	private ResourcePackage cost;
	private ResourcePackage upkeep;
	
	private Set<Tile> vision;
	
	
	public Unit(UnitEquipment equipment, UnitRace race, UnitType type, LinkedList<UnitTraits> traits, 
			UnitExperience experience, UnitSize size) throws RuntimeException {
		this.equipment = equipment;
		this.race = race;
		this.type = type;
		this.traits = new HashSet<UnitTraits>();
		traits.addAll(traits);
		this.experience = experience;
		this.size = size;
		this.initializeCost();
		this.initializeUpkeep();
		this.generateStats();
		this.populateTechnologyRequired();
	}
	
	public void buyUnit(Kingdom owner, Tile location) throws Exception {
		if(StaticFunctions.isAffordable(owner,cost))
			if(StaticFunctions.isValidRecruitmentLocation(owner,location))
				if(StaticFunctions.hasRequiredTechnology(owner,requiredTech)) {
					this.owner = owner;
					this.location = location;
				}
	}
	
	private void initializeCost() {
		cost = new ResourcePackage();
		cost.addPackage(equipment.getCost());
		cost.addPackage(race.getCost());
		cost.addPackage(experience.getCost());
		cost.multiplication(type.getCost());
		cost.scalarMultiplication(size.costFactor());
	}
	
	private void initializeUpkeep() {
		upkeep = new ResourcePackage();
		upkeep.add(ResourceTypes.Food,15);
		upkeep.add(ResourceTypes.Gold, cost.get(ResourceTypes.Gold)/10);
	}
	
	private void generateStats() {
		stats = new UnitStatPackage();
		stats.add(equipment.getStats());
		stats.add(race.getStats());
		stats.add(type.getStats());
		stats.add(experience.getStats());
		movementClass = type.getMovementClass();
		for(UnitTraits trait : traits) {
			stats.add(trait.getStats());
		}
		health = size.health();
	}
	
	private void populateTechnologyRequired() {
		requiredTech = new HashSet<Technology>();
		requiredTech.addAll(equipment.getTechnology());
		requiredTech.addAll(race.getTechnology());
		requiredTech.addAll(type.getTechnology());
		requiredTech.addAll(experience.getTechnology());
		for(UnitTraits trait : traits) {
			requiredTech.addAll(trait.getTechnology());
		}
	}
	
	
	public String toString() {
		return "Owner: " + owner + ", Location: " + location + ", " + unitDescription;
	}
	
	
	

}

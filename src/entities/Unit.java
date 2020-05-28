package entities;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import technology.Technology;
import unitModifiers.*;
import utilities.UnitSize;
import utilities.UnitStatPackage;
import utilities.ResourcePackage;
import utilities.ResourceTypes;
import utilities.StaticFunctions;

public class Unit {
	
	private String unitDescription;
	
	private UnitStatPackage stats;
	private UnitEquipment equipment;
	private UnitExperience experience;
	private UnitRace race;
	private Set<UnitTraits> traits; //TODO trait effect design
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
		this.traits.addAll(traits);
		this.traits.addAll(race.getTraits());
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
		cost.add(ResourceTypes.Gold,race.cost);
		cost.add(ResourceTypes.Gold,experience.cost());
		for(UnitTraits t : traits) {
			cost.add(ResourceTypes.Gold, t.cost);
		}
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
		stats.add(experience.getStatPackage());
		movementClass = type.movementClass();
		health = size.health();
	}
	
	private void populateTechnologyRequired() {
		requiredTech = new HashSet<Technology>();
		//TODO
	}
	
	
	public String toString() {
		return "Owner: " + owner + ", Location: " + location + ", " + unitDescription;
	}
	
	
	

}

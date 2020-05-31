package entities;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import technology.Technology;
import unitModifiers.*;
import utilities.UnitStatPackage;
import utilities.HasVision;
import utilities.ResourcePackage;
import utilities.ResourceTypes;
import utilities.StaticFunctions;

public class Unit extends HasVision {

	private String unitDescription;

	private UnitStatPackage stats;
	private UnitEquipment equipment;
	private UnitExperience experience;
	private UnitRace race;
	private Set<UnitTraits> traits; //TODO trait effect design
	private UnitType type;

	private Set<UnitModifiers> mods;

	private Kingdom owner;
	private Set<Kingdom> secondaryControllers;

	private int health;
	private UnitSize size;

	private Set<Technology> requiredTech;

	private ResourcePackage cost;
	private ResourcePackage upkeep;

	private Set<Tile> vision;

	private PropertyChangeSupport pcs;


	public Unit(UnitEquipment equipment, UnitRace race, UnitType type, 
			UnitExperience experience, UnitSize size,
			Set<UnitModifiers> uniqueBonuses, Tile location) throws RuntimeException {
		super(type.movementClass(),location,type.movement);
		this.equipment = equipment;
		this.race = race;
		this.type = type;
		this.traits = new HashSet<UnitTraits>();
		this.traits.addAll(traits);
		this.traits.addAll(race.getTraits());
		this.experience = experience;
		this.size = size;
		if(uniqueBonuses == null)
			mods = new HashSet<UnitModifiers>();
		else
			mods = new HashSet<UnitModifiers>(uniqueBonuses);
		this.initializeCost();
		this.initializeUpkeep();
		this.generateStats();
		this.populateTechnologyRequired();
		this.initializeDataStructures();
		this.pcs = new PropertyChangeSupport(this);
		unitDescription = size.toString()+" " + experience.toString() +" "+ equipment.toString() + " " 
				+race.toString()+" " + type.toString();


	}

	private void initializeDataStructures() {
		vision = new HashSet<Tile>();
		secondaryControllers = new HashSet<Kingdom>();
	}

	public void buyUnit(Kingdom owner, Tile location) throws Exception {
		if(StaticFunctions.isAffordable(owner,cost))
			if(StaticFunctions.isValidRecruitmentLocation(owner,location))
				if(StaticFunctions.hasRequiredTechnology(owner,requiredTech)) {
					this.owner = owner;
					this.location = location;
					this.pcs.addPropertyChangeListener("unitlocation", location);
					this.pcs.addPropertyChangeListener("location",owner);
					this.pcs.addPropertyChangeListener("vision", owner);

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
		for(UnitModifiers m : mods) {
			cost.addPackage(m.getCost());
		}
		for(UnitModifiers m:mods) {
			cost.multiplication(m.getMultiCost());
		}
		cost.multiplication(type.getCost());
		cost.scalarMultiplication(size.costFactor());
	}

	public void moveUnit(Tile destination) {
		if(super.movementRange == null) {
			super.getMovementRange();
			if(super.movementRange.contains(destination)) {
				destination.addUnit(this);
				pcs.firePropertyChange("unitlocation", location, destination);
				Set<Tile> temp = super.visionRange;
				super.updateVision();
				pcs.firePropertyChange("vision", temp,super.visionRange);
			}
		}
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
		health = size.health();
	}

	public void unsubscribe(PropertyChangeListener obj) {
		pcs.removePropertyChangeListener(obj);
	}

	public ResourcePackage getCost() {
		return new ResourcePackage(cost);
	}

	private void populateTechnologyRequired() { //Requires some parameters
		requiredTech = new HashSet<Technology>();
		//TODO
	}


	public String toString() {
		return "" + unitDescription + stats.printStats();
	}








}

package entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import technology.Technology;
import utilities.ResourcePackage;

public class Kingdom {
	
	String name;
	String description;
	
	
	ResourcePackage resources; //Resources available in this turn
	
	ResourcePackage availableResources; //Resources available after proposed actions
	
	//Public action
	//Private Action
	//Public Spending Action
	//Private Spending Action
	//Arcane Magic Action
	//Divine Magic Action
	//Nature Magic Action
	//Alchemy Magic Action
	//Enchanting Magic Action
	//Court Magic Action
	
	private Set<Technology> unlockedTechnologies;
	
	private LinkedList<Unit> units;
	
	private Set<Tile> vision;
	
	private LinkedList<Building> buildings;
	
	
	/**
	 * Initialize a Kingdom
	 * @param name
	 * @param description
	 * @param startingResources
	 * @param kingdomTechnology - Set of technology the kingdom starts with
	 */
	public Kingdom(String name, String description, ResourcePackage startingResources, Set<Technology> kingdomTechnology) {
		unlockedTechnologies = new HashSet<Technology>();
		unlockedTechnologies.addAll(kingdomTechnology);
		resources = new ResourcePackage(startingResources);
		availableResources = new ResourcePackage(resources);
		this.name = "" + name;
		this.description = ""+description;
		units = new LinkedList<Unit>();
		vision = new HashSet<Tile>();
	}
	
	public ResourcePackage getAvailableResources() {
		return new ResourcePackage(availableResources);
	}
	
	public Set<Technology> getUnlockedTechnologies(){
		return new HashSet<Technology>(unlockedTechnologies);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
	//Triggered when some vision changes, recomputation of the whole line of sight map required
	public void updateVision() {
		
		vision = new HashSet<Tile>();
	
		for(Unit u : units) {//For each unit in units
			
			
		}
		
		for(Building b: buildings) {//For each building in buildings
			if(b.providesVision()) {
				
			}	
		}
	}
	
	

}

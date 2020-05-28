package entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import technology.Technology;
import tileModifiers.TerrainTypes;
import utilities.Description;
import utilities.ResourcePackage;

public class Kingdom implements PropertyChangeListener, Comparable<Kingdom> {
	
	private Description desc;
	
	private ResourcePackage resources; //Resources available in this turn
	
	private ResourcePackage availableResources; //Resources available after proposed actions
	
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
	
	private Set<Tile> operatedTiles;
	
	private Set<Tile> claimedTiles;
	
	private int[] tileCounts;
	
	//Policies
	
	
	/**
	 * Initialize a Kingdom
	 * @param name
	 * @param description
	 * @param startingResources
	 * @param kingdomTechnology - Set of technology the kingdom starts with
	 */
	public Kingdom(Description description, ResourcePackage startingResources, Set<Technology> kingdomTechnology) {
		unlockedTechnologies = new HashSet<Technology>();
		unlockedTechnologies.addAll(kingdomTechnology);
		resources = new ResourcePackage(startingResources);
		availableResources = new ResourcePackage(resources);
		desc = new Description(description);
		units = new LinkedList<Unit>();
		vision = new HashSet<Tile>();
		operatedTiles = new HashSet<Tile>();
		claimedTiles = new HashSet<Tile>();
		tileCounts = new int[TerrainTypes.count()];
	}
	
	public ResourcePackage getAvailableResources() {
		return new ResourcePackage(availableResources);
	}
	
	public Set<Technology> getUnlockedTechnologies(){
		return new HashSet<Technology>(unlockedTechnologies);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return desc.name;
	}
	
	public void addOperatedTile(Tile newTile) {
		operatedTiles.add(newTile);
	}
	
	public void addClaimedTile(Tile newTile) {
		claimedTiles.add(newTile);
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public Description getDescription() {
		return desc;
	}
	
	@Override
	public String toString() {
		return desc.toString();
	}
	
	public boolean equals(String other) {
		return this.getName().equals(other);
	}
	
	
	public int compareTo(Kingdom other) {
		return this.getName().compareTo(other.getName());
	}
	
	public String printOperatedTiles(){
		String s = "";
		for(Tile t : operatedTiles) {
			s += t.toString();
		}
		return s;
	}
	
	public int totalTilesOperated() {
		return operatedTiles.size();
	}
	
	public ResourcePackage initializeIncome() {
		ResourcePackage tileProduction = recountResourcesFromTiles();
		return tileProduction;
	}
	
	private ResourcePackage recountResourcesFromTiles() {
		ResourcePackage results = new ResourcePackage();
		for(int i = 0; i < tileCounts.length;i++) {
			tileCounts[i] = 0;
		}
		for(Tile t : operatedTiles) {
			tileCounts[t.getTerrain().getDbValue()]++;
		}
		for(int i = 0; i < tileCounts.length;i++) {
			ResourcePackage temp = TerrainTypes.fromDbValue(i).getProduction();
			temp.scalarMultiplication(tileCounts[i]);
			results.addPackage(temp);			
		}
		return results;
	}
	
	

}

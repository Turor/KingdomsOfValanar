package unitModifiers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import technology.Technology;
import utilities.ResourcePackage;
import utilities.UnitStatPackage;

public class UnitModifiers {
	private String name;
	private String tooltip;
	private String description;
	
	private UnitStatPackage statBonuses;
	
	private Set<Technology> prerequisites;
	
	private ResourcePackage buildCost;
	private ResourcePackage upkeepCost;
	
	public UnitModifiers(String name, String tooltip, String description, UnitStatPackage flatStatBonuses,
			UnitStatPackage multiplicativeStatBonuses,
			ArrayList<Technology> prerequisites, ResourcePackage buildCost, ResourcePackage upkeepCost) {
		this.name = "" + name;
		this.tooltip = "" + tooltip;
		this.description = "" + description;
		
		this.statBonuses = new UnitStatPackage(statBonuses);
		
		this.prerequisites = new HashSet<Technology>();
		this.prerequisites.addAll(prerequisites);
		
		this.buildCost = new ResourcePackage(buildCost);
		this.upkeepCost = new ResourcePackage(upkeepCost);
	}
	
	public ResourcePackage getCost() {
		return new ResourcePackage(buildCost);
	}
	
	public ResourcePackage getUpkeep() {
		return new ResourcePackage(upkeepCost);
	}
	
	public UnitStatPackage getStats() {
		return new UnitStatPackage(statBonuses);
	}
	
	public Set<Technology> getTechnology(){
		Set<Technology> requiredTech = new HashSet<Technology>();
		requiredTech.addAll(prerequisites);
		return requiredTech;
	}
	
	
	

	
}

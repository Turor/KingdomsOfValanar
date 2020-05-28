package buildingModifiers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import technology.Technology;
import utilities.Description;
import utilities.ResourcePackage;

public class BuildingType {
	
	private Description text;
	
	private ResourcePackage costToBuild;
	
	private Set<Technology> techProvided;
	
	private ResourcePackage resourcesProduced;
	
	private boolean providesVision;
	
	private List<LocalBonus> localBonus;
	
	public BuildingType(Description text, ResourcePackage costToBuild, ResourcePackage resourcesProvided, Set<Technology> techProvided) {
		this.text = text;
		this.costToBuild = costToBuild;
		this.resourcesProduced = resourcesProvided;
		this.techProvided = techProvided;
	}
	
	
	/**
	 * @return A clone of the building type's description
	 */
	public Description getDescription() {
		return new Description(text);
	}
	
	public String toString() {
		return text.toString();
	}
	
	public ResourcePackage getCost() {
		return new ResourcePackage(costToBuild);
	}
	
	public ResourcePackage getProductionTotals() {
		return new ResourcePackage(resourcesProduced);
	}
	
	public Set<Technology> getTechProvided(){
		return new HashSet<Technology>(techProvided);
	}
	
	
	
	

}

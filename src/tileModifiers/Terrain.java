package tileModifiers;

import utilities.ResourcePackage;

public class Terrain {
	
	public final String name;
	
	public final int terrainCost;
	
	public final int importID;
	
	private ResourcePackage developmentCost;
	
	private ResourcePackage resourceProduction;
	
	public Terrain(String name, int terrainCost, ResourcePackage developmentCost, ResourcePackage resourceProduction
			,int importID) {
		this.name = name;
		this.terrainCost = terrainCost;
		this.importID = importID;
		this.developmentCost = new ResourcePackage(developmentCost);
		this.resourceProduction = new ResourcePackage(developmentCost);
	}
	
	public ResourcePackage getDevelopmentCost() {
		return new ResourcePackage(developmentCost);
	}
	
	public ResourcePackage getProduction() {
		return new ResourcePackage(resourceProduction);
	}
	
	
	public String toString() {
		return name;
	}
	
	public String printDevelopmentCost() {
		return developmentCost.toString();
	}
	
	public String printProduction() {
		return resourceProduction.toString();
	}
	
	public boolean equals(int key) {
		return key==importID;
	}
	

}

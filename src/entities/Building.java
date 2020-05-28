package entities;

import java.beans.PropertyChangeSupport;
import java.util.Set;

import buildingModifiers.BuildingType;
import technology.Technology;
import utilities.Description;
import utilities.ResourcePackage;

public class Building {
	
	/**
	 * Provides a notification means for informing Tiles and Kingdoms that ownership was changed
	 * or its status as enabled or disabled was changed
	 */
	private PropertyChangeSupport pcs;
	
	private Description playerFlavor;
	
	private BuildingType type;

	private Tile location;
	
	private boolean active;
	
	private Kingdom owner;
	
	private Kingdom operator;
	
	/**
	 * Initialize the property change support as well as initialize the first listeners
	 * @param owner - building ownership change events
	 * @param operator - building operator change events
	 */
	private void initpcs(Kingdom owner, Kingdom operator) {
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener("Bow", owner);
		pcs.addPropertyChangeListener("Bop", operator);
		
	}
	
	public Building(BuildingType type, Kingdom owner, Kingdom operator, Description customFlavor, Tile location ) {
		initpcs(owner,operator);
		this.type = type;
		this.owner = owner;
		this.operator = operator;
		if(customFlavor == null)
			this.playerFlavor = new Description(type.getDescription());
		else
			this.playerFlavor = customFlavor;
		active = true;
		this.location = location;
	}

	public boolean providesVision() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}

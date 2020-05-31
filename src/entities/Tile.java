package entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tileModifiers.Connection;
import tileModifiers.TerrainTypes;
import tileModifiers.TileDirections;
import utilities.HasVision;
import utilities.ResourcePackage;

public class Tile implements PropertyChangeListener, Comparable<Tile> {

	private int row;
	private int column;


	//Distance Calculations
	private boolean hasRoad;
	private boolean finished;
	private TerrainTypes dominantTerrain;
	private int distance;

	private Kingdom owner;

	private Set<Kingdom> claimants;

	private Kingdom operator;

	private Tile[] defaultConnections;

	//TODO: Advanced connections like permanent portals

	private Set<Kingdom> recruitmentRights;

	private Set<Kingdom> buildingRights;

	private List<Building> buildingsPresent;

	private Set<Unit> units;

	private PropertyChangeSupport pcs;

	//Potentially the kingdoms with some claim to this tile

	public Set<Kingdom> getRecruitmentRights() {
		// TODO Auto-generated method stub
		return new HashSet<Kingdom>(recruitmentRights);
	}

	public Kingdom getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}

	public Kingdom getOperator() {
		return operator;
	}

	public Set<Kingdom> getBuildingRights() {
		// TODO Auto-generated method stub
		return new HashSet<Kingdom>(buildingRights);
	}

	/**
	 * Initialize a tile with no owner but a location on the map and with a given terrain type.
	 * @param mapCoordinates - The geographical location of this tile, used to place the tile on a map
	 * @param dominantTerrain - The terrain class to which this tile belongs
	 */
	public Tile(TerrainTypes dominantTerrain, int row, int column) {
		init();
		owner = null;
		operator = null;	
		this.dominantTerrain = dominantTerrain;
		this.row = row;
		this.column = column;
	}

	private void init() {
		defaultConnections = new Tile[8];
		recruitmentRights = new HashSet<Kingdom>();
		buildingRights = new HashSet<Kingdom>();
		buildingsPresent = new LinkedList<Building>();
		claimants = new HashSet<Kingdom>();
		units = new HashSet<Unit>();
		pcs = new PropertyChangeSupport(this);
		hasRoad = false;
		distance = 999;
		finished = false;
	}

	/**
	 * Form a new connection to a different tile
	 * @param newConnection
	 * @param dir The enumerator direction
	 */
	public void addConnection(Tile other, TileDirections dir) {
		defaultConnections[dir.index] = other;
	}

	public void changeTerrain(TerrainTypes newTerrain) {
		dominantTerrain = newTerrain;
	}

	public void changeOwner(Kingdom newOwner) {
		owner = newOwner;
	}

	public void changeOperator(Kingdom newOperator) {
		operator = newOperator;
	}

	public void addUnit(Unit unit) {
		units.add(unit);
	}

	public void grantRecruitmentRights(Kingdom king) {
		recruitmentRights.add(king);
		pcs.addPropertyChangeListener("recruitment", king);
	}

	public boolean revokeRecruitmentRights(Kingdom king) {
		if(recruitmentRights.remove(king)) {
			pcs.firePropertyChange("recruitment", null, king);
			return true;
		}
		return false;

	}

	/**
	 * To be implemented
	 * @param conqueror
	 * @return
	 */
	public ResourcePackage raid(Kingdom raider) {
		//launch event that gives
		return null;
	}

	public void conquer(Kingdom conqueror) {

	}

	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public TerrainTypes getTerrain() {
		return dominantTerrain;
	}

	public String toString() {
		return String.format("%9s[%3d,%3d]\t", dominantTerrain.toString(),row,column);
	}

	public String printConnections() {
		String s = "";
		for(int i = 0; i < defaultConnections.length;i++) {
			s+=defaultConnections[i].toString()+"\n";
		}
		return s;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("unitlocation")) {
			units.remove(evt.getSource());
			((Unit) evt.getSource()).unsubscribe(this);
		}

	}

	public int costFor(HasVision type) {
		switch(type.getMovementClass()) {
		case FLYING:{
			//unaffected by roads or terrain
			return 1;
		}
		case FOOT:{
			//affected by roads and terrain equally
			if(this.hasRoad) {
				return dominantTerrain.getMovementCost()/2;
			}else {
				return dominantTerrain.getMovementCost();
			}
		}
		case MOUNTED:{
			//roads have double the affect
			if(this.hasRoad) {
				return dominantTerrain.getMovementCost()/4;
			}else {
				return dominantTerrain.getMovementCost();
			}
		}
		case SAIL:{
			//unaffected by roads and terrain
			return 1;
		}
		case SIEGE:{
			//affected by roads and doubly affected by terrain
			if(this.hasRoad) {
				return dominantTerrain.getMovementCost();
			}else {
				return dominantTerrain.getMovementCost()*2;
			}
		}
		default:
			return dominantTerrain.getMovementCost();
		}
	}

	public void finish() {
		finished = true;
	}

	public void reset() {
		finished = false;
		distance = 999;
	}

	public boolean isFinished() {
		return finished;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int newDistance) {
		distance = newDistance;
	}

	public List<Tile> getConnections(){
		List<Tile> temp = new LinkedList<Tile>();
		for(int i = 0; i < defaultConnections.length;i++) {
			if(defaultConnections[i]!=null)
				temp.add(defaultConnections[i]);
		}
		return temp;
	}

	@Override
	public int compareTo(Tile arg0) {
		if(this.getDistance()<arg0.getDistance())
			return -1;
		else if(this.getDistance()>arg0.getDistance()) {
			return 1;
		}
		return 0;
	}




}

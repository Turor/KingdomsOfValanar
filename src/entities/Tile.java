package entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tileModifiers.Connection;
import tileModifiers.TerrainTypes;
import tileModifiers.TileDirections;
import utilities.ResourcePackage;

public class Tile {
	
	private int row;
	private int column;
	
	private Kingdom owner;
	
	private Set<Kingdom> claimants;
	
	private Kingdom operator;
	
	private Tile[] defaultConnections;
	
	//TODO: Advanced connections like permanent portals
	
	private Set<Kingdom> recruitmentRights;
	
	private Set<Kingdom> buildingRights;
	
	private List<Building> buildingsPresent;
	
	private TerrainTypes dominantTerrain;
	
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
	



	
	
	
	
	
	

}

package tileModifiers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Kingdom;
import entities.Tile;
import utilities.AccessClass;

public class Connection {
	

	private Set<Kingdom> accessible;
	
	private Set<Tile> connection;
	
	private AccessClass accessClass;
	
	//In the future, might add bidirectional and unidirectional type connections
	
	/**
	 * Presumed global access by default
	 * @param originTile
	 * @param destinationTile
	 */
	public Connection(Tile originTile, Tile destinationTile) {
		initConnection(originTile,destinationTile);
		accessClass = AccessClass.global;
	}
	
	//When the criteria for accessibility changes, an update to this connection should be called.
	/**
	 * Restricted access connection, requires membership in the set of kingdoms to utilize or alternatively is a
	 * longform method of initializing the standard global connection style, in which case accessible may be NULL
	 * @param originTile
	 * @param destinationTile
	 * @param access
	 * @param accessible - List of kingdoms which can use this connection
	 */
	public Connection(Tile originTile, Tile destinationTile, AccessClass access, Set<Kingdom> accessible) {
		initConnection(originTile,destinationTile);
		accessClass = access;
		initAccessibility(accessible);
	}
	
	private void initConnection(Tile originTile, Tile destinationTile) {
		connection = new HashSet<Tile>();
		connection.add(originTile);
		connection.add(destinationTile);
	}
	
	private void initAccessibility(Set<Kingdom> accessible) {
		this.accessible = new HashSet<Kingdom>(accessible);
	}
	
	public boolean isAccessibleBy(Kingdom kingdom) {
		if(accessClass == AccessClass.global)
			return true;
		else
			return accessible.contains(kingdom);
	}
	
	//Might add error feedback to this method
	public void removeKingdomsAccess(Kingdom kingdom) {
		if(accessClass == AccessClass.limited)
			accessible.remove(kingdom);
	}
	
	//And this one
	public void addKingdomAccess(Kingdom kingdom) {
		if(accessClass == AccessClass.limited)
			accessible.add(kingdom);
	}
	
	
	public Set<Tile> tilesConnected(){
		return new HashSet<Tile>(connection);
	}
	
	public String toString() {
		String s = "";
		for(Tile c : connection) {
			s+= c.toString();
		}
		return s;
	}
	

	
	
	

}

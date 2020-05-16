package entities;

import java.util.HashSet;
import java.util.Set;

import technology.Technology;

public class Tile {
	
	private Kingdom owner;
	
	private Kingdom operator;
	
	private Set<Connection> adjacencies;
	
	private Set<Kingdom> recruitmentRights;
	
	private Set<Kingdom> buildingRights;

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
	
	public void addAdjacency(Tile newAdjacency,)
	
	

}

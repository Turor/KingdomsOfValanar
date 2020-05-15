package utilities;

import java.util.HashSet;
import java.util.Set;

import entities.Kingdom;
import entities.Tile;
import technology.Technology;

public class StaticFunctions {
	
	public static boolean isAffordable(Kingdom owner, ResourcePackage cost) {
		return owner.getAvailableResources().sufficientResources(cost);
	}
	
	public static boolean isValidBuildLocation(Kingdom owner, Tile location) throws Exception {
		if(owner.equals(location.getOwner()))
			return true;
		else {
			if(location.getBuildingRights().contains(owner))
				return true;
			else
				throw new Exception(owner.getName() + " does not have building rights for " + location);
		}
		
	}
	
	public static boolean isValidRecruitmentLocation(Kingdom owner, Tile location) throws Exception {
		if(owner.equals(location.getOwner()))
			return true;
		else {
			if(location.getRecruitmentRights().contains(owner)) {
				return true;
			}
			else {
				throw new Exception(owner.getName() + " does not have recruitment rights for " + location);
			}
		}
		
	}
	
	public static boolean hasRequiredTechnology(Kingdom owner, Set<Technology> requiredTech) throws Exception {
		HashSet<Technology> kingdomTech = new HashSet<Technology>(owner.getUnlockedTechnologies());
		if(kingdomTech.containsAll(requiredTech))
			return true;
		else {
			HashSet<Technology> overlappingSet = new HashSet<Technology>(kingdomTech);
			overlappingSet.retainAll(requiredTech);
			HashSet<Technology> missingTechnology = new HashSet<Technology>(requiredTech);
			missingTechnology.removeAll(overlappingSet);
			String missingTech = "{";
			for(Technology tech : missingTechnology) {
				missingTech += tech.toString()+", ";
			}
			missingTech+="}";
			throw new Exception("Missing Technology: "+missingTech);
		}
	}

}

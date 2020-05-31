package utilities;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import entities.Kingdom;
import entities.Tile;
import technology.Technology;
import unitModifiers.UnitEquipment;
import unitModifiers.UnitExperience;
import unitModifiers.UnitModifiers;
import unitModifiers.UnitRace;
import unitModifiers.UnitSize;
import unitModifiers.UnitTraits;
import unitModifiers.UnitType;

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

	public static ResourcePackage calculateUnitCost(UnitEquipment equip, UnitRace race,
			UnitExperience exp, UnitSize size, UnitType type, Set<UnitModifiers> mods) {
		//Flat cost modifiers
		HashSet<UnitTraits> trait = new HashSet<UnitTraits>();
		ResourcePackage cost = new ResourcePackage();
		cost.addPackage(equip.getCost());
		cost.add(ResourceTypes.Gold,race.cost);
		cost.add(ResourceTypes.Gold,exp.cost());

		trait.addAll(race.getTraits());
		for(UnitTraits t : trait) {
			cost.add(ResourceTypes.Gold, t.cost);
		}
		
		if(mods != null) 
			for(UnitModifiers m : mods) {
				if(m.applies(equip, exp, race, size, type)) {
					cost.addPackage(m.getCost());
				}
			}
		

		//Multiplicative Cost Modifiers
		cost.multiplication(type.getCost());
		if(mods !=null)
			for(UnitModifiers m : mods) {
				if(m.applies(equip, exp, race, size, type))
					cost.multiplication(m.getMultiCost());
			}
		cost.scalarMultiplication(size.costFactor());
		return cost;
	}
	
	public static Set<Tile> dijkstra(HasVision source, int range){
		PriorityQueue<Tile> pq = new PriorityQueue<Tile>();
		source.getLocation().setDistance(0);;
		pq.add(source.getLocation());
		Stack<Tile> clean = new Stack<Tile>();
		Set<Tile> result = new HashSet<Tile>();
		
		while(!pq.isEmpty()) {
			Tile t = pq.poll();
			List<Tile> connections = t.getConnections();
			t.isFinished();
			for(Tile p : connections) {
				if(!p.isFinished()) {
					int distance = t.getDistance() + p.costFor(source);
					if(distance < p.getDistance() && distance < range) {
						p.setDistance(distance);
						pq.add(p);
					}
				}
			}
			result.add(t);
			t.isFinished();
			clean.add(t);
		}
		for(Tile t : clean) {
			t.reset();
		}
		return result;
	}
	
	/**
	 * This variant of Dijkstra computes the set of tiles within a supplied range of source in a way
	 * that doesn't modify any shared resources. This means that one can freely spawn threads on this so
	 * long as this is done exclusively before or after tiles have been modified via terraforming, having a
	 * portal constructed, or roads constructed. I favor pathfinding and LoS being the last calculated update
	 * in a given turn.
	 * @param source
	 * @param range
	 * @return
	 */
	public static Set<Tile> concurrentDijkstra(HasVision source, int range){
		Set<Tile> finished = new HashSet<Tile>();
		Hashtable<Tile,Integer> enqueued = new Hashtable<Tile,Integer>(); 
		PriorityQueue<DijkstraTileContainer> pq = new PriorityQueue<DijkstraTileContainer>();
		DijkstraTileContainer src = new DijkstraTileContainer(source.getLocation(),0);
		pq.add(src);
		enqueued.put(src.tile, 0);
		
		while(!pq.isEmpty()) {
			DijkstraTileContainer t = pq.poll();
			finished.add(t.tile);
			List<Tile> connections = t.tile.getConnections();
			for(Tile p : connections) {
				if(!finished.contains(p)) {
					if(!enqueued.contains(p)) {
						enqueued.put(p,999);
					}
					int distance = t.distance + p.costFor(source);	
					if(distance < enqueued.get(p) && distance < range) {
						enqueued.put(p, distance);
						pq.add(new DijkstraTileContainer(p,distance));
					}
				}
			}
		}
		
		return finished;
		
	}
	
//	public static boolean isAffordable(ResourcePackage availableResources,Set<UnitModifiers> mods, UnitEquipment equip, UnitRace race,
//			UnitExperience exp, UnitSize size, UnitType type, Set<UnitTraits> extraTraits ) {
//		ResourcePackage cost = calculateUnitCost(mods,equip,race,exp,size,type,extraTraits);
//		return availableResources.sufficientResources(cost);
//	}

}

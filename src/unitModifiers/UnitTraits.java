package unitModifiers;

import java.util.ArrayList;
import java.util.Set;

import technology.Technology;
import tileModifiers.Terrain;
import utilities.ResourcePackage;
import utilities.UnitStatPackage;

public class UnitTraits extends UnitModifiers {

	public UnitTraits(String name, String tooltip, String description, UnitStatPackage statBonuses,
			ArrayList<Technology> prerequisites, ResourcePackage buildCost, ResourcePackage upkeepCost,
			Set<Terrain> applicableTerrain) {
		super(name, tooltip, description, statBonuses, statBonuses, prerequisites, buildCost, upkeepCost);
		// TODO Auto-generated constructor stub
	}

}

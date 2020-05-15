package unitModifiers;

import java.util.ArrayList;

import technology.Technology;
import utilities.MovementClass;
import utilities.ResourcePackage;
import utilities.UnitStatPackage;

public class UnitType extends UnitModifiers {
	
	private MovementClass movementClass;

	public UnitType(String name, String tooltip, String description, UnitStatPackage statBonuses,
			UnitStatPackage multiplicativeStatBonuses,
			ArrayList<Technology> prerequisites, ResourcePackage buildCost, ResourcePackage upkeepCost, MovementClass movementClass
			) {
		super(name, tooltip, description, statBonuses, multiplicativeStatBonuses, prerequisites, buildCost, upkeepCost);
		// TODO Auto-generated constructor stub
		this.movementClass = movementClass;
	}
	
	public MovementClass getMovementClass() {
		return movementClass;
	}
	

}

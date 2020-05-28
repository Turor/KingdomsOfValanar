package utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.platform.commons.logging.LoggerFactory;

/**
 * When adding a new resource type, append a shorthand of the resource to the list, add a new case of the same name with the resources full name, 
 * and then update the count method to include the new resource.
 * 
 * To get the index of each resource, call the ordinal method on an instance of the enumerator. This tracks the 'id' of the resource and where
 * corresponding resources are found in the resource array.
 * @author Matthew
 *
 */
public enum ResourceTypes {
	Population(0),
	Food(1),
	Metal(2),
	Wood(3),
	Gold(4),
	Arcane(5),
	Divine(6),
	Nature(7),
	Enchanting(8),
	Alchemy(9),
	Court(10),
	Darkiron(11),
	Mythril(12),
	Happiness(13),
	HappyCounter(14),
	//Append new resources here in a shorthand form with only a single word
	;
	
	private ResourceTypes(int i) {
		this.dbValue = i;
	}
	/**
	 * Whenever adding a new resource, add a new case with the same name that translates the full name
	 */
	public String toString() {
		switch(this) {
		case Darkiron:
			return "Dark Iron";
		case Arcane:
			return "Arcane Magic";
		case Divine:
			return "Divine Magic";
		case Enchanting:
			return "Enchanting Magic";
		case Alchemy:
			return "Alchemical Magic";
		case Court:
			return "Court Magic";
		case Nature:
			return "Nature Magic";
		case Mythril:
			return "Mythril";
		case Wood:
			return "Wood";
		case Population:
			return "Population";
		case Metal:
			return "Metal";
		case Gold:
			return "Gold";
		case Food:
			return "Food";
		case Happiness:
			return "Happiness";
		case HappyCounter:
			return "Happiness Counter";
			/* case NEWRESOURCE:
			return "NEW RESOUCE FULL NAME"; */
		default:
			return "";
		}
	}

	private static Logger log = Logger.getGlobal(); 
	private static Map<Integer,ResourceTypes> lookup;
	private int dbValue;
	private static int count;

	static {
		try {
			ResourceTypes[] values = ResourceTypes.values();
			lookup = new HashMap<Integer,ResourceTypes>(values.length);
			for(ResourceTypes type:values) {
				lookup.put(type.getDbValue(),type);
			}
			count = values.length;
		}catch(Exception e) {
			log.log(Level.SEVERE, "Unexpected exception initialization" + ResourceTypes.class,e);
		}
	}


	public static ResourceTypes fromDbValue(Integer dbValue) {
		return lookup.get(dbValue);
	}
	
	public static int count() {
		return count;
	}

	public int getDbValue() {
		return this.dbValue;
	}
}

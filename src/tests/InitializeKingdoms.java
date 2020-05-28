package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entities.Kingdom;
import technology.Technology;
import utilities.Description;
import utilities.PackageType;
import utilities.ResourcePackage;
import utilities.ResourcePair;
import utilities.ResourceTypes;

class InitializeKingdoms {
	private static Kingdom test;
	
	private static ResourcePackage testResources;
	
	private static Description kingdomDescription;
	
	
	@BeforeAll
	static void initAll() {
		LinkedList<ResourcePair> dragon = new LinkedList<ResourcePair>();
		dragon.add(new ResourcePair(ResourceTypes.Metal,1000));
		dragon.add(new ResourcePair(ResourceTypes.Wood,1000));
		dragon.add(new ResourcePair(ResourceTypes.Gold,1000));
		dragon.add(new ResourcePair(ResourceTypes.Food,1000));
		dragon.add(new ResourcePair(ResourceTypes.Happiness,5));
		dragon.add(new ResourcePair(ResourceTypes.HappyCounter,5));
		ResourcePackage testResources = new ResourcePackage(PackageType.flat, dragon);
		String name = "Dragon's Reach";
		String tooltip = "Player controlled faction";
		String flavor = "Faction born of the chilling north, the land is broken and scarred by years of the dragon's conflict with"
				+ " the giantfolk. It is said that the last dragon's bide their time in wait for the time the giant's tyranny comes"
				+ " to a close.";
		kingdomDescription = new Description(name,tooltip,flavor);
		Set<Technology> techs = new HashSet<Technology>();
		test = new Kingdom(kingdomDescription, testResources, techs);
		
	}

	@Test
	void checkResources() {
		System.out.println(test.getAvailableResources());
		assertTrue(testResources.toString().equals(test.getAvailableResources().toString()));
	}
	
	@Test
	void checkDescription() {
		System.out.println(test.toString());
		assertTrue(test.toString().equals(kingdomDescription.toString()));
	}
	
}

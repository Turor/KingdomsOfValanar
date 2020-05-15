package utilities;

public class ResourcePackage {

	public double population;
	public double food;
	public double wood;
	public double metal;
	public double gold;
	
	public double arcane;
	public double divine;
	public double nature;
	public double alchemy;
	public double enchanting;
	public double courtMagic;
	
	public double happinessCounter;
	public double happiness;
	
	/**
	 * First initialization
	 * @param population
	 * @param food
	 * @param metal
	 * @param wood
	 * @param gold
	 * @param arcane
	 * @param divine
	 * @param nature
	 * @param alchemy
	 * @param enchanting
	 * @param courtMagic
	 * @param happinessCounter
	 * @param happiness
	 */
	public ResourcePackage(double population, double food, double metal, double wood, double gold, double arcane, double divine, 
							double nature, double alchemy, double enchanting, double courtMagic, double happinessCounter, double happiness) {
		this.population = population;
		this.food = food;
		this.metal = metal;
		this.wood = wood;
		this.gold = gold;
		this.arcane = arcane;
		this.divine = divine;
		this.nature = nature;
		this.alchemy = alchemy;
		this.enchanting = enchanting;
		this.courtMagic = courtMagic;
		this.happinessCounter = happinessCounter;
		this.happiness = happiness;
	}
	
	public ResourcePackage() {
		population = 0;
		food = 0;
		metal = 0;
		wood = 0;
		gold = 0;
		arcane = 0;
		divine = 0;
		nature = 0;
		alchemy = 0;
		enchanting = 0;
		courtMagic = 0;
		happinessCounter = 0;
		happiness = 0;
	}
	
	public ResourcePackage(ResourcePackage resources) {
		population = resources.population;
		food = resources.food;
		metal = resources.metal;
		wood = resources.wood;
		gold = resources.gold;
		arcane = resources.arcane;
		divine = resources.divine;
		nature = resources.nature;
		alchemy = resources.alchemy;
		enchanting = resources.enchanting;
		courtMagic = resources.courtMagic;
		happinessCounter = resources.happinessCounter;
		happiness = resources.happiness;
	}
	
	
	public void add(ResourcePackage resources) {
		population += resources.population;
		food += resources.food;
		metal += resources.metal;
		wood += resources.wood;
		gold += resources.gold;
		arcane += resources.arcane;
		divine += resources.divine;
		nature += resources.nature;
		alchemy += resources.alchemy;
		enchanting += resources.enchanting;
		courtMagic += resources.courtMagic;
		happinessCounter += resources.happinessCounter;
		happiness += resources.happiness;
	}
	
	public void scalarMultiplication(double scalar) {
		population *= scalar;
		food *= scalar;
		metal *= scalar;
		wood *= scalar;
		gold *= scalar;
		arcane *= scalar;
		divine *= scalar;
		nature *= scalar;
		alchemy *= scalar;
		enchanting *= scalar;
		courtMagic *= scalar;
		happinessCounter *= scalar;
		happiness *= scalar;
	}
	
	public void multiplication(ResourcePackage scalar) {
		population *= scalar.population;
		food *= scalar.food;
		metal *= scalar.metal;
		wood *= scalar.wood;
		gold *= scalar.gold;
		arcane *= scalar.arcane;
		divine *= scalar.divine;
		nature *= scalar.nature;
		alchemy *= scalar.alchemy;
		enchanting *= scalar.enchanting;
		courtMagic *= scalar.courtMagic;
		happinessCounter *= scalar.happinessCounter;
		happiness *= scalar.happiness;
	}
	
	public boolean sufficientResources(ResourcePackage spending) {
		if(population-spending.population <0)
			return false;
		else if(food-spending.food < 0)
			return false;
		else if(metal-spending.metal < 0)
			return false;
		else if(wood-spending.wood < 0)
			return false;
		else if(gold-spending.gold < 0)
			return false;
		else if(arcane-spending.arcane<0)
			return false;
		else if(divine-spending.divine<0)
			return false;
		else if(nature-spending.nature<0)
			return false;
		else if(alchemy-spending.alchemy<0)
			return false;
		else if(enchanting-spending.enchanting<0)
			return false;
		else if(courtMagic-spending.courtMagic<0)
			return false;
		else
			return true;
	}
	
	public String toString() {
		return "Food: "+(int)food+" Metal: " +(int)metal +" Gold: "+ (int) gold+ " Arcane: "+ (int) arcane + " Divine: " + (int) divine
				+" Nature: " + (int) nature + " Alchemy: " + (int) alchemy + " Enchanting: " + (int) enchanting + " Court Magic: " + (int) courtMagic
				+" Happiness Counter: " + happinessCounter +" Happiness: " + happiness;
	}
}

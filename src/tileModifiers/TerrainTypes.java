package tileModifiers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import utilities.ResourcePackage;
import utilities.ResourceTypes;

public enum TerrainTypes {
	Mountains(4,0,2,0,0.25,10,5,10,1,14,Color.SADDLEBROWN,"mountain"),
	Hills(2,1,0.25,0.25,0.25,0,10,5,1,13,Color.PERU,"hill"),
	Plains(1,2,0,0.1,0.25,3,3,3,1,12,Color.GREEN,"plains"),
	Safari(1,1,0,0.25,0.25,2,3,2,1,11,Color.GOLD,"safari"),
	Forest(2,1,0,2.5,0.25,5,3,3,1,10,Color.FORESTGREEN,"forest"),
	Jungle(3,1,0,2,0.25,7,4,4,1,9,Color.DARKGREEN,"jungle"),
	Taiga(2,1,0,1,0.25,0,10,5,1,8,Color.LAVENDERBLUSH,"taiga"),
	Tundra(1,0.5,0,0,0.25,1,1,1,1,7,Color.ALICEBLUE,"tundra"),
	Swamp(4,0.5,0,0.5,0.25,1,6,1,1,6,Color.MEDIUMSEAGREEN,"swamp"),
	River(2,1,0.25,0.25,2,1,20,10,1,5,Color.DEEPSKYBLUE,"river"),
	Lake(2,2,0.25,0.25,2,1,20,10,1,4,Color.CORNFLOWERBLUE,"lake"),
	Sea(2,3,0.25,0.25,2,2,30,15,1,3,Color.ROYALBLUE,"sea"),
	Desert(1,0,0,0,0.25,0,10,5,1,2,Color.MOCCASIN,"desert"),
	Snow(2,0,0,0,0.25,0,10,5,1,1,Color.WHITE,"snow"),
	City(1,0,0,0,6,25,50,25,1,0,Color.BLACK,"city"),
	;


	private ResourcePackage developmentCost;

	private ResourcePackage resourceProduction;
	private final int movementCost;
	private int dbkey;
	private static int count;
	
	private static Logger log = Logger.getGlobal(); 
	private static Map<Integer,TerrainTypes> lookup;

	public Background defaultTexture;
	public Background snowTexture;


	TerrainTypes(int movementCost, double food, double metal, double wood, double gold, double metalC, double woodC, 
			double goldC, int population, int dbkey, Color defaultFill, String terrainKey) {
		this.movementCost = movementCost;
		developmentCost = new ResourcePackage();
		resourceProduction = new ResourcePackage();
		developmentCost.add(ResourceTypes.Metal, metalC);
		developmentCost.add(ResourceTypes.Wood, woodC);
		developmentCost.add(ResourceTypes.Gold, goldC);
		resourceProduction.add(ResourceTypes.Gold, gold);
		resourceProduction.add(ResourceTypes.Wood, wood);
		resourceProduction.add(ResourceTypes.Food, food);
		resourceProduction.add(ResourceTypes.Metal, metal);
		developmentCost.add(ResourceTypes.Population, population);
		this.dbkey = dbkey;
		
		
		try {
			Image def = new Image(getClass().getResource("../resources/images/biome/kov_"+terrainKey+".jpg").toExternalForm());
			defaultTexture = new Background(new BackgroundImage(def,null,null,null,null));
			Image snow;
			try {
				snow = new Image(getClass().getResource("../resources/images/biome/kov_"+terrainKey+"_snow.jpg").toExternalForm());
				snowTexture = new Background(new BackgroundImage(snow,null,null,null,null));
			}catch(Exception e) {
				snow = def;
				snowTexture = defaultTexture;
			}
					
		}catch(Exception e) {
			defaultTexture = new Background(new BackgroundFill(defaultFill, null, null));
			snowTexture = new Background(new BackgroundFill(defaultFill,null,null));
		}
		
		
		
	}

	public ResourcePackage getDevelopmentCost() {
		return new ResourcePackage(developmentCost);
	}

	public ResourcePackage getProduction() {
		return new ResourcePackage(resourceProduction);
	}
	
	public int getMovementCost() {
		return movementCost;
	}
	
	static {
		try {
			TerrainTypes[] values = TerrainTypes.values();
			lookup = new HashMap<Integer,TerrainTypes>(values.length);
			for(TerrainTypes type:values) {
				lookup.put(type.getDbValue(),type);
			}
			count = values.length;
		}catch(Exception e) {
			log.log(Level.SEVERE, "Unexpected exception initialization" + TerrainTypes.class,e);
		}
	}
	
	public static TerrainTypes fromDbValue(Integer dbValue) {
		return lookup.get(dbValue);
	}
	
	public static int count() {
		return count;
	}

	public int getDbValue() {
		return this.dbkey;
	}


	public String toString() {
		switch(this) {
		case City:
			return "City";
		case Desert:
			return "Desert";
		case Forest:
			return "Forest";
		case Hills:
			return "Hills";
		case Jungle:
			return "Jungle";
		case Lake:
			return "Lake";
		case Mountains:
			return "Mountains";
		case Plains:
			return "Plains";
		case River:
			return "River";
		case Safari:
			return "Safari";
		case Sea:
			return "Sea";
		case Snow:
			return "Snow";
		case Swamp:
			return "Swamp";
		case Taiga:
			return "Taiga";
		case Tundra:
			return "Tundra";
		default:
			return "";
		}
	}

	public String printDevelopmentCost() {
		return developmentCost.toString();
	}

	public String printProduction() {
		return resourceProduction.toString();
	}




}

package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import entities.Kingdom;
import entities.Tile;
import entities.Unit;
import technology.Technology;
import tileModifiers.Connection;
import tileModifiers.TerrainTypes;
import tileModifiers.TileDirections;
import unitModifiers.UnitEquipment;
import unitModifiers.UnitExperience;
import unitModifiers.UnitRace;
import unitModifiers.UnitSize;
import unitModifiers.UnitType;
import utilities.Description;
import utilities.PackageType;
import utilities.ResourcePackage;
import utilities.ResourcePair;
import utilities.ResourceTypes;
import utilities.StaticFunctions;

import java.util.Scanner;

public class KoVModel {
	
	private static Tile[][] tiles;

	public static void main(String[] args) {

		Kingdom[] kingdoms =loadKingdoms();

		//		for(Kingdom k : kingdoms) {
		//			System.out.println(k.getDescription().toString() + k.getAvailableResources().toString());
		//		}



		//		Terrain[] terrainTypes = loadTerrainTypes();
		//		for(Terrain t : terrainTypes) {
		//			System.out.println(t.toString() + " cost " + t.getDevelopmentCost() + " to develop and "
		//					+ "produces " + t.getProduction() + " per turn once developed.");
		//		}


		loadTiles(kingdoms);
		formTileConnections();
		//		for(int i = 0; i < tiles.length;i++) {
		//			for(int j = 0; j < tiles[i].length;j++) {
		//				System.out.print(tiles[i][j].toString());
		//			}
		//			System.out.println();
		//		}

//		for(Kingdom k : kingdoms) {
//			System.out.println(k.getName()+ ":\t" +k.initializeIncome());
//		}
//
		ResourcePackage unitCost= StaticFunctions.calculateUnitCost(UnitEquipment.MEDIUM, 
				UnitRace.DRAGONBORN, UnitExperience.REGULAR, 
				UnitSize.D6, UnitType.NAVY, null);
//
//		Unit yolo = new Unit(UnitEquipment.MEDIUM, 
//				UnitRace.DRAGONBORN, UnitType.NAVY,UnitExperience.REGULAR, 
//				UnitSize.D6,null);
//		System.out.println(yolo.getCost());
//		System.out.println(unitCost);
		

	}

	private static Kingdom[] loadKingdoms() {
		try {
			LinkedList<ResourcePair> dragon = new LinkedList<ResourcePair>();
			dragon.add(new ResourcePair(ResourceTypes.Metal,1000));
			dragon.add(new ResourcePair(ResourceTypes.Wood,1000));
			dragon.add(new ResourcePair(ResourceTypes.Gold,1000));
			dragon.add(new ResourcePair(ResourceTypes.Food,1000));
			dragon.add(new ResourcePair(ResourceTypes.Happiness,5));
			dragon.add(new ResourcePair(ResourceTypes.HappyCounter,5));
			ResourcePackage defaultPackage = new ResourcePackage(PackageType.flat, dragon);

			File kingdomData = new File("../KingdomsOfValanar/initializationResources/Kingdoms.txt");
			FileReader fh = new FileReader(kingdomData);
			BufferedReader fileScanner = new BufferedReader(fh);

			LinkedList<Kingdom> tempList = new LinkedList<Kingdom>();

			Set<Technology> techs = new HashSet<Technology>();
			String row;
			while((row = fileScanner.readLine())!= null) {
				String[] data = row.split("`");
				Kingdom temp = new Kingdom(new Description(data[0],data[1],data[2]),defaultPackage,techs);
				tempList.add(temp);
			}

			Kingdom[] kingdoms = new Kingdom[tempList.size()];
			int i = 0;
			for(Kingdom t : tempList) {
				kingdoms[i] = t;
				i++;
			}
			Arrays.sort(kingdoms);
			fileScanner.close();
			return kingdoms;			
		}catch(Exception e) {
			System.err.println("woo bad form:" + e.getMessage());
		}
		return null;
	}


	//	private static Terrain[] loadTerrainTypes() {
	//		try {
	//			File terrainData = new File("../KingdomsOfValanar/initializationResources/TileTypes.csv");
	//			FileReader fh = new FileReader(terrainData);
	//			BufferedReader fileScanner = new BufferedReader(fh);
	//
	//			String row;
	//			LinkedList<Terrain> tempList = new LinkedList<Terrain>();
	//			while((row = fileScanner.readLine())!= null) {
	//				String[] data = row.split(",");
	//				String terrainName = data[0];
	//				int movementCost = Integer.parseInt(data[1]);
	//				ResourcePackage production = new ResourcePackage();
	//				ResourcePackage cost = new ResourcePackage();
	//				production.add(ResourceTypes.Food, Double.parseDouble(data[2]));
	//				production.add(ResourceTypes.Metal,Double.parseDouble(data[3]));
	//				production.add(ResourceTypes.Wood,Double.parseDouble(data[4]));
	//				production.add(ResourceTypes.Gold,Double.parseDouble(data[5]));
	//				cost.add(ResourceTypes.Metal,Double.parseDouble(data[6]));
	//				cost.add(ResourceTypes.Wood,Double.parseDouble(data[7]));
	//				cost.add(ResourceTypes.Gold,Double.parseDouble(data[8]));
	//				cost.add(ResourceTypes.Population, Double.parseDouble(data[9]));
	//				int importID = Integer.parseInt(data[10]);
	//				Terrain t = new Terrain(terrainName,movementCost,cost,production,importID);
	//				tempList.add(t);
	//			}
	//			fileScanner.close();
	//			int length = tempList.size();
	//			Terrain[] terrain = new Terrain[length];
	//			for(Terrain t : tempList) {
	//				terrain[t.importID] = t;
	//			}
	//			return terrain;
	//
	//		}catch(Exception e) {
	//			System.err.println(e.getMessage());
	//		}
	//		return null;
	//	}

	private static Tile[][] loadTiles(Kingdom[] kingdoms) {
		try {		
			File tileData = new File("../KingdomsOfValanar/initializationResources/TileData.csv");
			FileReader fh = new FileReader(tileData);
			BufferedReader fileScanner = new BufferedReader(fh);

			String line;
			int maxCol = 0;
			int row = 0;
			LinkedList<Tile> tiles2 = new LinkedList<Tile>();
			while((line = fileScanner.readLine())!= null) {
				String[] data = line.split(",");
				if(data.length > 1) {
					for(int column = 0; column < data.length;column++) {
						String temp = data[column].substring(1, data[column].length()-1);
						String[] values = temp.split("`");

						//Match kingdoms with the tiles they operate
						int i = 0;
						for(; i < kingdoms.length;i++) {
							if(kingdoms[i].equals(values[3]))
								break;
						}
						Tile t = new Tile(TerrainTypes.valueOf(values[0]),row,column);
						if(i < kingdoms.length) {
							t.changeOperator(kingdoms[i]);
							kingdoms[i].addOperatedTile(t);
						}else
							t.changeOperator(null);
						tiles2.add(t);
						if(column > maxCol)
							maxCol = column;
					}
					row++;
				}
			}
			fileScanner.close();
			tiles = new Tile[row][maxCol+1];
			for(Tile t : tiles2) {
				tiles[t.getRow()][t.getColumn()] = t;			
			}
			return tiles;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static void formTileConnections() {
		for(int row = 0; row < tiles.length;row++) {
			for(int col = 0; col < tiles[row].length;col++) {
				if(row < tiles.length-1) {//S connections are valid
					newConnection(row,col,row+1,col,TileDirections.SOUTH);
				}
				if(row > 0) {//N connections are valid
					newConnection(row,col,row-1,col,TileDirections.NORTH);
				}
				if(col > 0) {//W connections are valid
					newConnection(row,col,row,col-1,TileDirections.WEST);
				}
				if(col < tiles[row].length-1) {//E connections are valid
					newConnection(row,col,row,col+1,TileDirections.EAST);
				}
				if(col > 0 && row > 0) {//NW connections are valid
					newConnection(row,col,row-1,col-1,TileDirections.NORTHWEST);
				}
				if(col > 0 && row <tiles.length-1) {//SW connections are valid
					newConnection(row,col,row+1,col-1,TileDirections.SOUTHWEST);
				}
				if(row > 0 && col < tiles[row].length-1) {//NE connections are valid
					newConnection(row,col,row-1,col+1,TileDirections.NORTHEAST);
				}
				if(row < tiles.length-1 && col < tiles[row].length-1) {//SE connections are valid
					newConnection(row,col,row+1,col+1,TileDirections.SOUTHEAST);
				}	
			}
		}
	}
	
	private static void newConnection(int row1, int col1, int row2, int col2, TileDirections dir) {
		tiles[row1][col1].addConnection(tiles[row2][col2], dir);
	}
}





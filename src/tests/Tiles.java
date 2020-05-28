package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entities.Tile;
import javafx.scene.shape.Rectangle;
import tileModifiers.Connection;

class Tiles {

	public static Tile n;
	public static Tile s;
	public static Tile e;
	public static Tile w;
	public static Tile c;
	public static Tile rightfield;

	@BeforeAll
	static void init() {
		Rectangle[] rect = new Rectangle[9];
		for(int iter = 0; iter < 9; iter++) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					rect[iter] = new Rectangle(i,j,1,1);
				}
			}
		}
		Rectangle rightField = new Rectangle(5,5,1,1);

		
	}


	@Test
	void test() {
		fail("Not yet implemented");
	}

}

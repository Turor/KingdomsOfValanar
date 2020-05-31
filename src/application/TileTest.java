package application;

import entities.Kingdom;
import entities.Tile;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tileModifiers.Connection;

public class TileTest extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Group root = new Group();
	
		Kingdom[] kingdoms = KoVModel.loadKingdoms();
		Tile[][] tiles = KoVModel.loadTiles(kingdoms);


	}
}

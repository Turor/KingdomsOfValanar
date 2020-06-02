package application;

import entities.Kingdom;
import entities.Tile;
import gui.TileButton;
import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
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
		ScrollPane scr = new ScrollPane();
		Scene scene = new Scene(scr,800,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		TileButton[][] board = new TileButton[tiles.length][];
		GridPane grid = new GridPane();
		for(int row = 0; row < tiles.length;row++) {
			board[row] = new TileButton[tiles[row].length]; 
			for(int col = 0; col < tiles[row].length;col++) {
				board[row][col] = new TileButton(tiles[row][col],row);
				tiles[row][col].registerGraphicsListener(board[row][col]);
				grid.add(board[row][col], col, row);
			}
		}
		
		scr.setContent(grid);
		
		scr.setPannable(true);
		
		
		stage.setScene(scene);
		
		stage.show();
	}
	
	private void scale(GridPane grid, double factor) {
		grid.setScaleX(factor);
		grid.setScaleY(factor);
	}
}

package application;

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
		Rectangle[][] rect = new Rectangle[3][3];

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				rect[i][j] = new Rectangle(i*20+i,j*20+j,20,20);
				root.getChildren().add(rect[i][j]);
				System.out.println(rect[i][j]);
			}
		}

		Rectangle rightField = new Rectangle(100,100,20,20);
		root.getChildren().add(rightField);
		


	}
}

package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import entities.Kingdom;
import entities.Tile;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tileModifiers.TerrainTypes;

public class TileButton extends Label implements PropertyChangeListener {
	
	private TerrainTypes terrain;
	
	private Tile tile;

	public TileButton(Tile t) {
		this.setMinSize(32, 32);
		this.setMaxSize(32, 32);
		this.getStyleClass().add("terrain");
		this.tile = t;
		
	}
	
	public void update() {
		//this.setText(terrain.toString());
		this.setId(terrain.toString());
		this.setText(tile.getOperatorName());
		this.applyCss();
	}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("terrain")) {
			terrain = (TerrainTypes)evt.getNewValue();
			update();
		}else if(evt.getPropertyName().equals("operator")) {
			this.setText(tile.getOperator().getName());
		}
	}
	

}

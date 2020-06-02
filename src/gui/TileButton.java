package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import entities.Kingdom;
import entities.Tile;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import tileModifiers.TerrainTypes;

public class TileButton extends Label implements PropertyChangeListener {
	
	private TerrainTypes terrain;
	
	private int height;
	
	private Tile tile;

	public TileButton(Tile t,int height) {
		this.setMinSize(32, 32);
		this.setMaxSize(32, 32);
		this.tile = t;
		this.height = height;
	}
	
	public void update() {
		
	}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("terrain")) {
			terrain = (TerrainTypes)evt.getNewValue();
			if(height < 30) {
				this.setBackground(terrain.snowTexture);
			}else {
				this.setBackground(terrain.defaultTexture);
			}
		}else if(evt.getPropertyName().equals("operator")) {
			this.setText(tile.getOperator().getName());
		}
	}
	

}

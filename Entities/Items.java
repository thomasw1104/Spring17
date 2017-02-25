package entities;

import java.util.ArrayList;

public abstract class Items extends Entity {

	public String name;
	
	public boolean walkOver;
	public int x;
	public int y;
	protected boolean pickup;
	
	
	
	// Constructor
	public Items(String imageName, int xSquare, int ySquare, int width, int height) {

		super(imageName, xSquare, ySquare, width, height);
		x = xSquare;
		y = ySquare;
		
		walkOver = true;

	}

	public boolean isConsumable() {//????
		return false;
	}

	public boolean pickUp(Boolean b){
		return b;
	}

}

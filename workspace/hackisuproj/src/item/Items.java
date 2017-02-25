package item;

import java.util.ArrayList;

public abstract class Items extends Entity {

	public String name;
	
	public boolean walkOver;
	public int x;
	public int y;
	protected boolean pickup;
	
	
	protected ArrayList<Items> bag;
	// Constructor
	public Items(String imageName, int xSquare, int ySquare, int width, int height) {

		super(imageName, xSquare, ySquare, width, height);
		x = xSquare;
		y = ySquare;
		
		bag = new ArrayList<Items>();
		
		walkOver = true;

	}

	public boolean isConsumable() {//????
		return false;
	}

	public boolean pickUp(Boolean b){
		return b;
	}
	public void addToBag() {
		bag.add(this);
	}
	public void addChest(ArrayList<Items> c) {
		for(int i = 0; i < c.size(); i++) {
			bag.add(c.get(i));
		}
	}
}

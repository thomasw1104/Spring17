package item;

public abstract class Items extends Entity {

	public String name;
	public int color;
	public char symbol;
	public int weight;
	public int value;
	public boolean walkOver;
	public int x;
	public int y;
	private boolean pickup;

	// Constructor
	public Items(String imageName, int xSquare, int ySquare, int width, int height) {

		super(imageName, xSquare, ySquare, width, height);
		x = xSquare;
		y = ySquare;

		walkOver = true;

	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setSize(int w, int n) {

	}

	public boolean isConsumable() {
		return false;
	}

	public void pickUp(Boolean b){
		pickup = b;
	}
}

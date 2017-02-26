package application;

import java.util.ArrayList;

import javafx.stage.Stage;

public class Items extends Entity {

	public String name;

	public boolean walkOver;
	protected boolean pickup;

	// Constructor
	public Items(String imageName, int xSquare, int ySquare, int width, int height, int gl1x, int gl1y) {

		super(imageName, xSquare, ySquare, width, height, gl1x, gl1y);
		walkOver = true;

	}

	public boolean isConsumable() {// ????
		return false;
	}

	public boolean pickUp(Boolean b) {
		return b;
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

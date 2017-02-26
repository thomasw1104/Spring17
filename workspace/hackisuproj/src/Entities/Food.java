package Entities;

import item.Items;
import javafx.stage.Stage;

public class Food extends Items {

	private static final int HEALTH_ADD = 5;

	public Food(String imageName, int xSquare, int ySquare, int width, int height) {
		super(imageName, xSquare, ySquare, width, height);
	}

	// add conditional for picking up food
	public void addHealth() {
		MovableObjects.health += HEALTH_ADD;
	}

	@Override
	public void interact() {
		boolean picked = super.pickUp(null);
		if(picked == true && MovableObjects.health < 100) addHealth();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}

}
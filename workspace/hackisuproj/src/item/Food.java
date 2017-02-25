package item;

import javafx.stage.Stage;

public class Food extends Items {

	
	public int health; // should probably go in character class????
	private static final int HEALTH_ADD = 5;

	public Food(String imageName, int xSquare, int ySquare, int width, int height) {
		super(imageName, xSquare, ySquare, width, height);
	}

	// add conditional for picking up food
	public void addHealth() {
		health += HEALTH_ADD;
	}

	@Override
	public void interact() {
		if(pickup == true) addHealth();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}

}
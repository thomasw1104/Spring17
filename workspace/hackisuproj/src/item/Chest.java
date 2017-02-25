package item;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

public class Chest extends Items {
	public ArrayList<Items> chest;
	
	public Chest(String imageName, int xSquare, int ySquare, int width, int height) {
		super(imageName, xSquare, ySquare, width, height);
		
		chest = new ArrayList<Items>();
	}

	
	@Override
	public void start(Stage primaryStage) {
		
	}

	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void interact() {
		boolean picked = pickUp(null); //find out if picked up
		
		if(picked) {
			super.addChest(chest); //if yes add
		}
	}
}

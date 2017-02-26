package Entities;

import java.util.ArrayList;
import java.util.Random;

import item.Items;
import javafx.application.Application;
import javafx.stage.Stage;

public class Chest extends Items {
	public ArrayList<Items> chest;
	private String imageName;
	private int xSquare;
	private int ySquare;
	private int width;
	private int height;
	
	public Chest(String imageName, int xSquare, int ySquare, int width, int height) {
		super(imageName, xSquare, ySquare, width, height);
		
		chest = new ArrayList<Items>();
		Random rand = new Random();
		int numChestItems = rand.nextInt(3) + 1;
	}

	public void fillChest() {
		//Items one = new Food();
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

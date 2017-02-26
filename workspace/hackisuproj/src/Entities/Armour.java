package Entities;

import item.Items;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.stage.Stage;

public class Armour extends Items{

	private static final int DAMAGE_RESISTANCE = 1;
	
	public Armour(String imageName, int xSquare, int ySquare, int width, int height) {
		super(imageName, xSquare, ySquare, width, height);
		// TODO Auto-generated constructor stub
	}

	public void addResis() {
		MovableObjects.damageTaken -= DAMAGE_RESISTANCE;
	}

	@Override
	public void interact() {
		boolean picked = super.pickUp(null);
		if(picked == true) addResis();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
package item;

import javafx.stage.Stage;

public class Weapon extends Items{

	public Weapon(String imageName, int xSquare, int ySquare, int width, int height) {
		super(imageName, xSquare, ySquare, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void interact() {
		boolean picked = super.pickUp(null);
		if(picked) {
			super.addToBag();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

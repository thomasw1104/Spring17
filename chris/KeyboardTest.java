package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KeyboardTest extends MovableObjects implements EventHandler<KeyEvent> {
	public ArrayList<Items> bag = new ArrayList<Items>();

	public boolean bagFull() {
		if (bag.size() > 15) {
			return true;
		}
		return false;
	}

	public boolean pickUp(Boolean b) {
		if (!bagFull())
			return true;
		return false;
	}

	public KeyboardTest(String imageName, int xSquare, int ySquare, int xSize, int ySize, int gl1x, int gl1y) {
		super(imageName, xSquare, ySquare, xSize, ySize, gl1x, gl1y);

	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub

	}

	public void handle(KeyEvent event) {
		// if(event.getCharacter().equals("W"))
		//
		// {
		// //this.iv.setX(this.iv.getX());
		// this.iv.setY(this.iv.getY() + (Main1.getXMovement()));
		// }else if(event.getCharacter().equals("S"))
		// {
		//
		// }else if(event.getCharacter().equals("A"))
		// {
		//
		// }else if(event.getCharacter().equals("D"))
		// {
		//
		// }

	}

}

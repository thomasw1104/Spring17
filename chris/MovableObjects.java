package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class MovableObjects extends Entity implements EventHandler<KeyEvent> {

	public MovableObjects(String imageName, int xSquare, int ySquare, int xSize, int ySize) {
		super(imageName, xSquare, ySquare, xSize, ySize);
		x = xSquare;
		y = ySquare;
	}

	public double x, y;

	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub

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

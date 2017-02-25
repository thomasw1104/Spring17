package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity extends Application {

	public Image sprite;
	public ImageView iv;
	public int gl1x;
	public int gl1y;
	public int xSquare;
	public int ySquare;
	// public int xSquare;
	// public int ySquare;
	// public int xSize;
	// public int ySize;

	public Entity(String imageName, int xSquare, int ySquare, int xSize, int ySize, int gameLayer1ValX,
			int gameLayer1ValY) {

		// Create image and scale
		gl1x = gameLayer1ValX;
		gl1y = gameLayer1ValY;
		sprite = new Image(imageName);
		iv = new ImageView(sprite);
		iv.setPreserveRatio(true);
		iv.setImage(sprite);
		iv.setFitHeight(xSize);
		iv.setFitWidth(ySize);
		iv.setX(xSquare*gl1x);
		iv.setY(ySquare*gl1y);
		this.xSquare = xSquare * gl1x;
		this.ySquare = ySquare * gl1y;
	}

	public abstract void interact();

}

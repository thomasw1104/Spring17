package item;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity extends Application {

	private Image sprite;
	public ImageView iv;
	
	public Entity(String imageName, int xSquare, int ySquare, int width, int height){
		
		//Create image and scale
		sprite = new Image(imageName);
		iv = new ImageView(sprite);
		iv.setPreserveRatio(true);
		iv.setFitHeight(width);
		iv.setFitWidth(height);
		iv.setX(xSquare);
		iv.setY(ySquare);
		
	}
	
	public abstract void interact();
	
	
	
}
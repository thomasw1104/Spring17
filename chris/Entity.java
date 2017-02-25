package application;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity extends Application {

	public Image sprite;
	public ImageView iv;
//	public int xSquare;
//	public int ySquare;
//	public int xSize;
//	public int ySize;
	
	public Entity(String imageName, int xSquare, int ySquare, int xSize, int ySize){
		
		//Create image and scale
		sprite = new Image(imageName);
		iv = new ImageView(sprite);
		iv.setPreserveRatio(true);
		iv.setImage(sprite);
		iv.setFitHeight(xSize);
		iv.setFitWidth(ySize);
		iv.setX(xSquare);
		iv.setY(ySquare);
		
	}
	
	public abstract void interact();
	
}

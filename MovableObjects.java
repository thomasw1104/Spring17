package entities;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class MovableObjects extends Entity implements ActionListener{

	Timer timer = new Timer(5, this);
	public double x, y;
	
	public MovableObjects(String imageName, int xSquare, int ySquare, int width, int height){
		
		super(imageName, xSquare, ySquare, width, height);
		x = xSquare;
		y = ySquare ;
		
	}
	
	public abstract void interact();
	
	public void move(KeyEvent e){
		
		if (e.getCode() == KeyCode.UP)
			this.y =- 15;
		else if (e.getCode() == KeyCode.DOWN)
			this.y =+ 15;
		else if (e.getCode() == KeyCode.LEFT)
			this.x =- 15;
		else if (e.getCode() == KeyCode.RIGHT)
			this.x =+ 15;
		
		redraw();
		
	}
	
	private void redraw(){
		
		this.iv.setX(x);
		this.iv.setY(y);
		
	}
	
	
	
	
}

package Entities;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import javafx.scene.input.KeyCode;

public abstract class MovableObjects extends Entity implements ActionListener{

	Timer timer = new Timer(5, this);
	public double x, y;
	public static int health;
	public static int damageTaken;
	public static int damageGiven;
	
	public MovableObjects(String imageName, int xSquare, int ySquare, int width, int height){
		
		super(imageName, xSquare, ySquare, width, height);
		x = xSquare;
		y = ySquare ;
		
	}
	
	public abstract void interact(final Entity object);
	
	public void move(KeyCode e){
		
		if (e == KeyCode.UP)
			this.y =- 15;
		else if (e == KeyCode.DOWN)
			this.y =+ 15;
		else if (e == KeyCode.LEFT)
			this.x =- 15;
		else if (e == KeyCode.RIGHT)
			this.x =+ 15;
		
		redraw();
		
	}
	
	private void redraw(){
		
		this.iv.setX(x);
		this.iv.setY(y);
		
	}
	
	public void takeDamage(int damage){
		
		this.health =- damage;
		
	}
	
	
}

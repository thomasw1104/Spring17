package Entities;

import javafx.scene.input.KeyCode;

public abstract class Enemy extends MovableObjects {

	public Enemy(String imageName, int xSquare, int ySquare, int width, int height){
		
		super(imageName, xSquare, ySquare, width, height);
		
	}
	
	public void trackPlayer(double playerX, double playerY, final MovableObjects player){
		
		double xDistance = playerX - this.x;
		double yDistance = playerY - this.y;
		
		if (Math.abs(xDistance) < 20 && Math.abs(yDistance) < 20)
			this.interact(player);
			
		if (Math.abs(xDistance) > Math.abs(yDistance)){
			if (xDistance > 0)
				this.move(KeyCode.RIGHT);
			else if (xDistance < 0)
				this.move(KeyCode.LEFT);
		}
		else if (Math.abs(xDistance) < Math.abs(yDistance)){
			if (yDistance > 0)
				this.move(KeyCode.DOWN);
			else if (yDistance < 0)
				this.move(KeyCode.UP);
		}
		
	}
	
	public void interact(final MovableObjects player){
		attack(player);
	}
	
	public abstract void attack(final MovableObjects player);
	
}

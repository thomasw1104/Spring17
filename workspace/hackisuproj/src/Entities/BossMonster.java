package Entities;

import java.awt.event.ActionEvent;

import javafx.stage.Stage;

public class BossMonster extends Enemy{

	public BossMonster(int xSquare, int ySquare, int width, int height){
		
		super("EnemyBoss.png", xSquare, ySquare, width, height);
		this.health = 50;
		
	}

	@Override
	public void attack(final Entity player) {
		
		MovableObjects playerObject = (MovableObjects) player;
		playerObject.takeDamage(5);
		
	}

	@Override 
	public void interact(final Entity player) {}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(MovableObjects player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}
	
}

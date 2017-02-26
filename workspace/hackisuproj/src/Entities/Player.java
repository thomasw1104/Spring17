package Entities;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javafx.stage.Stage;

public class Player extends MovableObjects{

	protected static ArrayList<Items> bag = new ArrayList<Items>();
	
	public Player(int xSquare, int ySquare, int width, int height){
		
		super("King.PNG", xSquare, ySquare, width, height);
		this.health = 100;
		this.damageGiven = 10;
		this.damageTaken = 10;
		
		
	}

	public void interact(Enemy fighting) {		
		this.attack(fighting);
	}
	
	public void interact (Items item){
		if (item.pickup)
			bag.add(item);
	}
	
	public void attack(Enemy fighting){
		fighting.takeDamage(damageTaken);
	}
	
	public void addChest(ArrayList<Items> c) {
		for(int i = 0; i < c.size(); i++) {
			bag.add(c.get(i));
		}
	}
	
	public boolean bagFull() {
		if(bag.size() > 15) {
			return true;
		} return false;
	}
	public boolean pickUp(Boolean b){
		if(bagFull() == false) return true;
		return false;
	}

	@Override
	public void interact(Entity object) {}

	@Override
	public void actionPerformed(ActionEvent e) {}
	
	@Override
	public void start(Stage primaryStage) throws Exception {}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}
}

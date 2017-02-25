package entities;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javafx.stage.Stage;

public class Player extends MovableObjects{

	protected ArrayList<Items> bag;
	
	public Player(int xSquare, int ySquare, int width, int height){
		
		super("King.PNG", xSquare, ySquare, width, height);
		this.health = 100;
		bag = new ArrayList<Items>();
		
	}

	public void interact(Enemy fighting) {		
		this.attack(fighting);
	}
	
	public void interact (Items item){
		if (item.pickup)
			bag.add(item);
	}
	
	public void attack(Enemy fighting){
		fighting.takeDamage(10);
	}
	
	public void addChest(ArrayList<Items> c) {
		for(int i = 0; i < c.size(); i++) {
			bag.add(c.get(i));
		}
	}

	@Override
	public void interact(Entity object) {}

	@Override
	public void actionPerformed(ActionEvent e) {}
	
	@Override
	public void start(Stage primaryStage) throws Exception {}
}

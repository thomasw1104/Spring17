package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main1 extends Application implements EventHandler<KeyEvent> {
	public static int MAP_WIDTH = 25;
	public static int MAP_HEIGHT = 25;

	static javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

	public static double WINDOW_WIDTH = screenBounds.getHeight() - 80;
	public static double WINDOW_HEIGHT = screenBounds.getHeight() - 80;

	private static double moveUp = WINDOW_HEIGHT / MAP_HEIGHT;
	private static double moveSideways = WINDOW_WIDTH / MAP_WIDTH;

	public boolean gameLayer[][] = new boolean[MAP_HEIGHT][MAP_WIDTH];
	public Object gameLayer1[][] = new Object[MAP_HEIGHT][MAP_WIDTH];

	public Filler genericFiller = new Filler();

	public KeyboardTest hero;
	public Spider test;
	public Group root;
	ArrayList<ImageView> monsters = new ArrayList<ImageView>();

	public static double getYMovement() {
		return moveUp;
	}

	public static double getXMovement() {
		return moveSideways;
	}

	// (int) WINDOW_WIDTH / MAP_WIDTH
	@Override
	public void start(Stage primaryStage) {
		hero = new KeyboardTest("chara.png", (int) WINDOW_WIDTH / MAP_WIDTH,
				(int) WINDOW_HEIGHT / MAP_HEIGHT, (int) WINDOW_WIDTH / MAP_WIDTH, (int) WINDOW_HEIGHT / MAP_HEIGHT, 1,
				1);

		primaryStage.setTitle("Map Experiment");
		test = new Spider("spider.png", (int) moveSideways, (int) moveUp, (int) moveSideways, (int) moveUp, 5, 5);
		// lets get hero image in there
		drawRandomDungeon(5);
		root = new Group(hero.iv);
		for (int i = 0; i < monsters.size(); i++)
			root.getChildren().add(monsters.get(i));
		
		gameLayer1[hero.gl1x][hero.gl1y] = hero;
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		primaryStage.setScene(scene);

		// drawing dungeon, starting everything up as a boolean for the sake of
		// making walls Filler objects
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		drawMap(graphics);
		root.getChildren().addAll(canvas);
		// uncomment for full screen fucc that tho
		// primaryStage.setFullScreen(true);
		test.iv.toFront();
		hero.iv.toFront();
		for (int i = 0; i < monsters.size(); i++)
			monsters.get(i).toFront();
		scene.setOnKeyTyped(this);
		primaryStage.show();
		String test = "application.Enemy";
		System.out.println(gameLayer1[0][0].getClass().getName());

	}
	
	public void drawRandomDungeon(int numEnemies){
		Random rand = new Random();
		drawOpenDungeon();
		for (int i = 0; i < numEnemies; i++){
			int col = rand.nextInt(MAP_WIDTH - 1) + 1;
			int row = rand.nextInt(MAP_HEIGHT - 1) + 1;
			if (col == 1 && row == 1)
				i--;
			else{
				Spider spider = new Spider("spider.png", (int) moveUp, (int) moveSideways, (int) moveUp, 
						(int) moveSideways, col, row);
				gameLayer1[row][col] = spider;
				monsters.add(spider.iv);
			}
				
		}
	}

	public void drawOpenDungeon() {
		for (int row = 0; row < MAP_WIDTH; row++)
			for (int col = 0; col < MAP_HEIGHT; col++) {
				if (row != 0 && col != 0 && row != MAP_HEIGHT - 1 && col != MAP_WIDTH - 1)
					gameLayer[row][col] = true;
			}

	}

	private void drawMap(GraphicsContext graphics) {
		for (int i = 0; i < MAP_WIDTH; i++) {
			for (int j = 0; j < MAP_HEIGHT; j++) {
				if (gameLayer[i][j]) {
					graphics.setFill(Color.WHITE);
				} else {
					gameLayer1[i][j] = new Filler();
					graphics.drawImage(new Image("wall.png"), i * getXMovement(), j * getYMovement(), getXMovement(),
							getYMovement());
				}
				if (gameLayer[i][j]) {
					graphics.strokeRect(i * (WINDOW_HEIGHT / MAP_HEIGHT), j * (WINDOW_HEIGHT / MAP_HEIGHT),
							WINDOW_HEIGHT / MAP_HEIGHT, WINDOW_HEIGHT / MAP_HEIGHT);
					graphics.fillRect(i * (WINDOW_HEIGHT / MAP_HEIGHT), j * (WINDOW_HEIGHT / MAP_HEIGHT),
							WINDOW_HEIGHT / MAP_HEIGHT, WINDOW_HEIGHT / MAP_HEIGHT);
				}
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(KeyEvent event) {

		if (event.getCharacter().equals("w")
				&& (gameLayer1[hero.gl1y - 1][hero.gl1x] == null || ((gameLayer1[hero.gl1y - 1][hero.gl1x] != null
						&& !gameLayer1[hero.gl1y - 1][hero.gl1x].getClass().getName().equals("application.Filler"))
						&& gameLayer1[hero.gl1y - 1][hero.gl1x].getClass().getSuperclass().getSuperclass().getName()
								.equals("application.MovableObjects"))))

		{
			if ((gameLayer1[hero.gl1y - 1][hero.gl1x] != null) && gameLayer1[hero.gl1y - 1][hero.gl1x].getClass()
					.getSuperclass().getSuperclass().getName().equals("application.MovableObjects")) {
				System.out.println("interact");
			}
			// hero.iv.setX(hero.iv.getX());
			else {
				hero.iv.setY(hero.iv.getY() - (getYMovement()));
				gameLayer1[--hero.gl1y][hero.gl1x] = hero;
				gameLayer1[hero.gl1y + 1][hero.gl1x] = null;
			}
		} else if (event.getCharacter().equals("s")
				&& (gameLayer1[hero.gl1y + 1][hero.gl1x] == null || ((gameLayer1[hero.gl1y + 1][hero.gl1x] != null
						&& !gameLayer1[hero.gl1y + 1][hero.gl1x].getClass().getName().equals("application.Filler"))
						&& gameLayer1[hero.gl1y + 1][hero.gl1x].getClass().getSuperclass().getSuperclass().getName()
								.equals("application.MovableObjects")))) {

			if ((gameLayer1[hero.gl1y + 1][hero.gl1x] != null) && gameLayer1[hero.gl1y + 1][hero.gl1x].getClass()
					.getSuperclass().getSuperclass().getName().equals("application.MovableObjects")) {
				System.out.println("interact");
			}

			else {
				hero.iv.setY(hero.iv.getY() + (getYMovement()));
				gameLayer1[++hero.gl1y][hero.gl1x] = hero;
				gameLayer1[hero.gl1y - 1][hero.gl1x] = null;
			}

		} else if (event.getCharacter().equals("d")
				&& (gameLayer1[hero.gl1y][hero.gl1x + 1] == null || ((gameLayer1[hero.gl1y][hero.gl1x + 1] != null
						&& !gameLayer1[hero.gl1y][hero.gl1x + 1].getClass().getName().equals("application.Filler"))
						&& gameLayer1[hero.gl1y][hero.gl1x + 1].getClass().getSuperclass().getSuperclass().getName()
								.equals("application.MovableObjects")))) {

			if ((gameLayer1[hero.gl1y][hero.gl1x + 1] != null) && gameLayer1[hero.gl1y][hero.gl1x + 1].getClass()
					.getSuperclass().getSuperclass().getName().equals("application.MovableObjects")) {
				System.out.println("interact");
			} else {
				hero.iv.setX(hero.iv.getX() + getXMovement());
				gameLayer1[hero.gl1y][++hero.gl1x] = hero;
				gameLayer1[hero.gl1y][hero.gl1x - 1] = null;
			}

		} else if (event.getCharacter().equals("a")
				&& (gameLayer1[hero.gl1y][hero.gl1x - 1] == null || ((gameLayer1[hero.gl1y][hero.gl1x - 1] != null
						&& !gameLayer1[hero.gl1y][hero.gl1x - 1].getClass().getName().equals("application.Filler"))
						&& gameLayer1[hero.gl1y][hero.gl1x - 1].getClass().getSuperclass().getSuperclass().getName()
								.equals("application.MovableObjects")))) {
			if ((gameLayer1[hero.gl1y][hero.gl1x - 1] != null) && gameLayer1[hero.gl1y][hero.gl1x - 1].getClass()
					.getSuperclass().getSuperclass().getName().equals("application.MovableObjects")) {
				System.out.println("interact");
			}
			else{
			hero.iv.setX(hero.iv.getX() - getXMovement());
			gameLayer1[hero.gl1y][--hero.gl1x] = hero;
			gameLayer1[hero.gl1y][hero.gl1x + 1] = null;
			}
		}

	}

	public void setCharLocation(int x, int y) {

	}
}
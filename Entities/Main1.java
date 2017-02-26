package application;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

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
	public Items[][] gameLayer2 = new Items[(int) moveUp][(int) moveSideways];

	public Filler genericFiller = new Filler();

	public KeyboardTest hero;
	public Spider test;
	public Items itemTest;

	public ArrayList<Entity> monsters = new ArrayList<Entity>();

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
		itemTest = new Items("King.png", (int) moveSideways, (int) moveUp, (int) moveSideways, (int) moveUp, 10, 10);
		// lets get hero image in there
		Group root = new Group(hero.iv, test.iv);
		root.getChildren().addAll(itemTest.iv);
		gameLayer1[hero.gl1x][hero.gl1y] = hero;
		gameLayer1[test.gl1x][test.gl1y] = test;
		gameLayer2[itemTest.gl1x][itemTest.gl1y] = itemTest;
		drawRandomDungeon(8);
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		primaryStage.setScene(scene);

		// drawing dungeon, starting everything up as a boolean for the sake of
		// making walls Filler objects
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		drawMap(graphics);
		root.getChildren().add(canvas);
		addListToGroup(root, monsters);
		// uncomment for full screen fucc that tho
		// primaryStage.setFullScreen(true);
		itemTest.iv.toFront();
		test.iv.toFront();
		hero.iv.toFront();
		toFrontArrList(monsters);

		scene.setOnKeyTyped(this);
		primaryStage.show();

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

	/**
	 * @Override
	 */
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
			} else {
				hero.iv.setX(hero.iv.getX() - getXMovement());
				gameLayer1[hero.gl1y][--hero.gl1x] = hero;
				gameLayer1[hero.gl1y][hero.gl1x + 1] = null;
			}
		} else if (event.getCharacter().equals("e") && gameLayer2[hero.gl1x][hero.gl1y] != null) {
			if (hero.bagFull())
				System.out.println("no mo sht");
			else {
				hero.bag.add(gameLayer2[hero.gl1x][hero.gl1y]);
				gameLayer2[hero.gl1x][hero.gl1y].iv.setImage(null);
				gameLayer2[hero.gl1x][hero.gl1y] = null;

				System.out.println("Just picked up: " + hero.bag.get(0).toString());
			}
		}

	}

	public void drawRandomDungeon(int numEnemies) {
		boolean allGood = false;
		Random rand = new Random();
		drawOpenDungeon();
		while (monsters.size() < numEnemies) {

			while (!allGood) {
				int col = rand.nextInt(MAP_WIDTH - 1) + 1;
				int row = rand.nextInt(MAP_HEIGHT - 1) + 1;
				if (gameLayer[row][col] && gameLayer1[row][col] == null)
					allGood = true;
				if (allGood) {
					Spider spiderName = new Spider("spider.png", (int) moveUp, (int) moveSideways, (int) moveUp,
							(int) moveSideways, 0, 0);
					gameLayer1[row][col] = spiderName;
					spiderName.iv.relocate(col * MAP_WIDTH, row * MAP_HEIGHT);
					System.out.println(spiderName.iv.getImage());
					monsters.add(spiderName);
				}

			}
			allGood = false;
			
//			for (int row = 1; row < MAP_WIDTH - 1; row++){
//				for (int col = 1; col < MAP_HEIGHT; col++){
//					int fill = rand.nextInt(4);
//					if (!(row == 1 && col==1)){
//						if (fill == 0)
//							gameLayer[row][col] = false;
//						else
//							gameLayer[row][col] = true;
//					}
//				}
//			} 
			
			generateRoom();
		}
	}

	public void addListToGroup(Group gr, ArrayList<Entity> arrlist) {
		for (int i = 0; i < arrlist.size(); i++) {
			gr.getChildren().addAll(arrlist.get(i).iv);

		}
	}

	public void toFrontArrList(ArrayList<Entity> arr) {
		for (int i = 0; i < arr.size(); i++) {
			Entity test = arr.get(i);
			test.iv.toFront();
		}
	}

	public void setCharLocation(int x, int y) {

	}
	
	public void generateRoom(){
		
		Random rand = new Random();
		int sideLength = rand.nextInt(MAP_HEIGHT - 10) + 1;
		
		for (int row = 8; row < MAP_WIDTH - 1; row++){
			for (int col = 8; col < MAP_HEIGHT - 1; col++){
				gameLayer[row][col] = false;
			}
		}
		
	}
}
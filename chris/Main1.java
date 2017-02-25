package application;

import java.awt.geom.Rectangle2D;
import javafx.geometry.*;

import java.util.Arrays;
import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

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

	public static double getYMovement() {
		return moveUp;
	}

	public static double getXMovement() {
		return moveSideways;
	}

	// (int) WINDOW_WIDTH / MAP_WIDTH
	@Override
	public void start(Stage primaryStage) {
		hero = new KeyboardTest("file:/home/esc/workspace/spring17/src/chara.png", (int) WINDOW_WIDTH / MAP_WIDTH,
				(int) WINDOW_HEIGHT / MAP_HEIGHT, (int) WINDOW_WIDTH / MAP_WIDTH, (int) WINDOW_HEIGHT / MAP_HEIGHT, 0,
				0);
		hero.gl1x = 1;
		hero.gl1y = 1;
		primaryStage.setTitle("Map Experiment");
		test = new Spider("spider.png", 5*(int) moveSideways, 5*(int) moveUp, (int) moveSideways, (int) moveUp, 5, 5);
		// lets get hero image in there
		Group root = new Group(hero.iv,test.iv);
		gameLayer1[hero.gl1x][hero.gl1y] = hero;
		gameLayer1[test.gl1x][test.gl1y] = test;
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		primaryStage.setScene(scene);

		// drawing dungeon, starting everything up as a boolean for the sake of
		// making walls Filler objects
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		drawOpenDungeon();
		drawMap(graphics);
		root.getChildren().add(canvas);
		// uncomment for full screen fucc that tho
		// primaryStage.setFullScreen(true);
		test.iv.toFront();
		hero.iv.toFront();
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
		Random rand = new Random();
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

		if (event.getCharacter().equals("w") && gameLayer1[hero.gl1y - 1][hero.gl1x] == null)

		{
			// hero.iv.setX(hero.iv.getX());
			hero.iv.setY(hero.iv.getY() - (getYMovement()));

			gameLayer1[hero.gl1y--][hero.gl1x] = hero;
			gameLayer1[hero.gl1y + 1][hero.gl1x] = null;
		} else if (event.getCharacter().equals("s") && gameLayer1[hero.gl1y + 1][hero.gl1x] == null) {
			hero.iv.setY(hero.iv.getY() + (getYMovement()));
			hero.gl1y++;
			gameLayer1[hero.gl1y - 1][hero.gl1x] = null;

		} else if (event.getCharacter().equals("d") && gameLayer1[hero.gl1y][hero.gl1x + 1] == null) {
			hero.iv.setX(hero.iv.getX() + getXMovement());
			hero.gl1x++;
			gameLayer1[hero.gl1y][hero.gl1x - 1] = null;

		} else if (event.getCharacter().equals("a") && gameLayer1[hero.gl1y][hero.gl1x - 1] == null) {
			hero.iv.setX(hero.iv.getX() - getXMovement());
			hero.gl1x--;
			gameLayer1[hero.gl1y][hero.gl1x + 1] = null;
		}

	}

	public void setCharLocation(int x, int y) {

	}
}
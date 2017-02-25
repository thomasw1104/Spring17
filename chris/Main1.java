package application;

import java.awt.geom.Rectangle2D;
import javafx.geometry.*;
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
	public KeyboardTest hero = new KeyboardTest("file:/home/esc/workspace/spring17/src/chara.png",
			(int) WINDOW_WIDTH / MAP_WIDTH, (int) WINDOW_HEIGHT / MAP_HEIGHT, (int) WINDOW_WIDTH / MAP_WIDTH,
			(int) WINDOW_HEIGHT / MAP_HEIGHT);

	public static double getYMovement() {
		return moveUp;
	}

	public static double getXMovement() {
		return moveSideways;
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Map Experiment");

		Group root = new Group(hero.iv);

		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		primaryStage.setScene(scene);
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext graphics = canvas.getGraphicsContext2D();

		drawOpenDungeon();
		drawMap(graphics);
		root.getChildren().add(canvas);
		// primaryStage.setFullScreen(true);
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
					graphics.setFill(Color.BLUE);
				}
				graphics.strokeRect(i * (WINDOW_HEIGHT / MAP_HEIGHT), j * (WINDOW_HEIGHT / MAP_HEIGHT),
						WINDOW_HEIGHT / MAP_HEIGHT, WINDOW_HEIGHT / MAP_HEIGHT);
				graphics.fillRect(i * (WINDOW_HEIGHT / MAP_HEIGHT), j * (WINDOW_HEIGHT / MAP_HEIGHT),
						WINDOW_HEIGHT / MAP_HEIGHT, WINDOW_HEIGHT / MAP_HEIGHT);
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(KeyEvent event) {

		if (event.getCharacter().equals("w"))

		{
			// hero.iv.setX(hero.iv.getX());
			hero.iv.setY(hero.iv.getY() - (getYMovement()));
		} else if (event.getCharacter().equals("s")) {
			hero.iv.setY(hero.iv.getY() + (getYMovement()));

		} else if (event.getCharacter().equals("d")) {
			hero.iv.setX(hero.iv.getX() + getXMovement());

		} else if (event.getCharacter().equals("a")) {
			hero.iv.setX(hero.iv.getX() - getXMovement());

		}

	}

	public void setCharLocation(int x, int y) {

	}
}
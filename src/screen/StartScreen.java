package screen;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.GameController;

public class StartScreen {
	private static final int WIDTH = 1280;
	private static final int HIGHT = 720;

	public StartScreen(Stage primaryStage) {
		StackPane root = new StackPane();

		Scene scene = new Scene(root);

		Canvas canvas = new Canvas(HIGHT, WIDTH);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		root.getChildren().add(canvas);

		primaryStage.setTitle("Angel vs. Devil");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

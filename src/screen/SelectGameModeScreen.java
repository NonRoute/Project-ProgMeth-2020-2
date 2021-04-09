package screen;

import com.sun.prism.paint.Paint;

import gui.ButtonSelectGameMode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.GameController;
import sharedObject.AudioLoader;

public class SelectGameModeScreen {
	private static final int WIDTH = 1280;
	private static final int HIGHT = 720;

	public SelectGameModeScreen(Stage primaryStage) {
		StackPane root = new StackPane();

		Scene scene = new Scene(root);

		ImageView image = new ImageView("picture/Background.png");
		image.setFitWidth(WIDTH);
		image.setFitHeight(HIGHT);


		root.setAlignment(Pos.TOP_RIGHT);

		root.getChildren().addAll(image, new ButtonSelectGameMode(), getExitButton());
		primaryStage.setTitle("Angel vs. Devil");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public Button getExitButton() {
		Button exitButton = new Button("X");
		exitButton.setPrefSize(50, 50);
		exitButton.setBackground(new Background(new BackgroundFill(Color.TOMATO, CornerRadii.EMPTY, Insets.EMPTY)));
		exitButton.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		exitButton.setTextFill(Color.MAROON);
		StackPane.setMargin(exitButton, new Insets(20));
		exitButton.setOnAction((ActionEvent e) -> {
			Platform.exit();
		});
		return exitButton;
	}
}

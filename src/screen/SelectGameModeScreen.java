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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.GameController;
import sharedObject.AudioLoader;
import sharedObject.RenderableHolder;

public class SelectGameModeScreen {
	StackPane root;
	Scene scene;
	ImageView image;

	public SelectGameModeScreen() {
		root = new StackPane();

		scene = new Scene(root);

		image = RenderableHolder.backgroundSelectGameMode;
		image.setFitWidth(GameController.SCREEN_WIDTH);
		image.setFitHeight(GameController.SCREEN_HIGHT);

		root.setAlignment(Pos.TOP_RIGHT);
		root.getChildren().addAll(image, new ButtonSelectGameMode(), getExitButton());
		GameController.primaryStage.setTitle("Angel vs. Devil");
		GameController.primaryStage.setScene(scene);
	}

	public Button getExitButton() {
		Button exitButton = new Button("Exit");
		exitButton.setPrefSize(80, 50);
		exitButton.setBackground(new Background(new BackgroundFill(Color.TOMATO, new CornerRadii(5), Insets.EMPTY)));
		exitButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		exitButton.setTextFill(Color.MAROON);
		exitButton.setBorder(new Border(
				new BorderStroke(Color.MAROON, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));
		StackPane.setMargin(exitButton, new Insets(20));
		exitButton.setOnMouseClicked((MouseEvent e) -> {
			Platform.exit();
		});
		exitButton.setOnMouseEntered((MouseEvent e) -> {
			exitButton.setBackground(
					new Background(new BackgroundFill(Color.SANDYBROWN, new CornerRadii(5), Insets.EMPTY)));
		});

		exitButton.setOnMouseExited((MouseEvent e) -> {
			exitButton.setBackground(new Background(new BackgroundFill(Color.TOMATO, new CornerRadii(5), Insets.EMPTY)));
		});
		return exitButton;
	}
}

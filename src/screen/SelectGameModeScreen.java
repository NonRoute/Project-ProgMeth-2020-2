package screen;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

public class SelectGameModeScreen {
	private StackPane root;
	private Scene scene;
	private ImageView image;

	public SelectGameModeScreen() {
		root = new StackPane();

		scene = new Scene(root);

		image = new ImageView(RenderableHolder.backgroundSelectGameMode);
		image.setFitWidth(GameController.SCREEN_WIDTH);
		image.setFitHeight(GameController.SCREEN_HEIGHT);

		root.setAlignment(Pos.TOP_RIGHT);
		root.getChildren().addAll(image, new SelectGameModeButton(), getExitButton());
		GameController.primaryStage.setTitle("Angel vs. Devil");
		GameController.primaryStage.setScene(scene);
	}

	public Button getExitButton() {
		Button exitButton = new Button("Exit");
		exitButton.setPrefSize(90, 50);
		exitButton.setBackground(new Background(new BackgroundFill(Color.TOMATO, new CornerRadii(5), new Insets(2))));
		exitButton.setFont(FontHolder.getInstance().font24);
		exitButton.setTextFill(Color.MAROON);
		exitButton.setBorder(new Border(
				new BorderStroke(Color.MAROON, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));
		StackPane.setMargin(exitButton, new Insets(20));
		exitButton.setOnMouseClicked((MouseEvent e) -> {
			SoundHolder.getInstance().click.play();
			Platform.exit();
		});
		exitButton.setOnMouseEntered((MouseEvent e) -> {
			exitButton.setBackground(
					new Background(new BackgroundFill(Color.SANDYBROWN, new CornerRadii(5), new Insets(2))));
			exitButton.setEffect(new InnerShadow());
		});

		exitButton.setOnMouseExited((MouseEvent e) -> {
			exitButton
					.setBackground(new Background(new BackgroundFill(Color.TOMATO, new CornerRadii(5), new Insets(2))));
			exitButton.setEffect(null);
		});
		return exitButton;
	}
}

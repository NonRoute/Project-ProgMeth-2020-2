package screen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

public class HowToPlayScreen {
	private Pane root;
	private BorderPane borderPane;
	private Button goBackButton;
	private ImageView background;
	
	public HowToPlayScreen() {
		root = new Pane();
		Scene scene = new Scene(root);
		
		this.background = new ImageView(RenderableHolder.backgroundHowToPlay);
		borderPane = new BorderPane();
		borderPane.setPrefSize(GameController.SCREEN_WIDTH, GameController.SCREEN_HEIGHT);
		goBackButton = getGoBackButton();
		BorderPane.setAlignment(goBackButton, Pos.CENTER_RIGHT);
		BorderPane.setMargin(goBackButton, new Insets(20));
		borderPane.setTop(goBackButton);
		borderPane.setCenter(getScrollPane());
		
		background.setFitWidth(GameController.SCREEN_WIDTH);
		background.setFitHeight(GameController.SCREEN_HEIGHT);
		root.getChildren().addAll(background, borderPane);
		GameController.primaryStage.setScene(scene);
		
	}
	
	public ScrollPane getScrollPane() {
		ScrollPane sp = new ScrollPane();
		ImageView imageView = new ImageView(RenderableHolder.HowToPlayDetail);
		imageView.setFitWidth(984);
		sp.setMaxSize(1000, 510);
		sp.setContent(imageView);
		return sp;
	}
	
	public Button getGoBackButton() {
		Button goBackButton = new Button("Go back");
		goBackButton.setPrefSize(120, 50);
		goBackButton.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), new Insets(2))));
		goBackButton.setFont(FontHolder.getInstance().font24);
		goBackButton.setTextFill(Color.NAVY);
		goBackButton.setBorder(new Border(
				new BorderStroke(Color.NAVY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));
		StackPane.setMargin(goBackButton, new Insets(20));
		goBackButton.setOnMouseClicked((MouseEvent e) -> {
			new SelectGameModeScreen();
			SoundHolder.click.play();
		});
		goBackButton.setOnMouseEntered((MouseEvent e) -> {
			goBackButton.setBackground(
					new Background(new BackgroundFill(Color.POWDERBLUE, new CornerRadii(5), new Insets(2))));
			goBackButton.setEffect(new InnerShadow());
		});

		goBackButton.setOnMouseExited((MouseEvent e) -> {
			goBackButton
					.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), new Insets(2))));
			goBackButton.setEffect(null);
		});
		return goBackButton;
	}
}

package screen;

import entity.Entity;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class EndGame extends StackPane {

	public EndGame() {
		clearEntity();
		this.setAlignment(Pos.CENTER);

		ImageView image = new ImageView(RenderableHolder.backgroundEndGame);
		image.setFitWidth(GameController.SCREEN_WIDTH);
		image.setFitHeight(GameController.SCREEN_HIGHT);

		this.getChildren().addAll(image, getVBox());
		Scene scene = new Scene(this);
		GameController.primaryStage.setScene(scene);
	}

	public void clearEntity() {
		for (IRenderable e : RenderableHolder.getInstance().getEntities()) {
			((Entity) e).setVisible(false);
		}
	}

	public VBox getVBox() {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(50);
		Text text = new Text("The winner is " + GameController.winner + " side!!");
		text.setFont(FontHolder.getInstance().font48);
		vBox.getChildren().addAll(text, getGoBackButton());
		return vBox;
	}

	public Button getGoBackButton() {
		Button goBackButton = new Button("New Game");
		goBackButton.setLayoutY(600);
		goBackButton.setPrefSize(300, 100);
		goBackButton
				.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), new Insets(2))));
		goBackButton.setFont(FontHolder.getInstance().font36);
		goBackButton.setTextFill(Color.NAVY);
		goBackButton.setBorder(new Border(
				new BorderStroke(Color.NAVY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));
		StackPane.setMargin(goBackButton, new Insets(20));
		goBackButton.setOnMouseClicked((MouseEvent e) -> {
			new SelectGameModeScreen();
		});
		goBackButton.setOnMouseEntered((MouseEvent e) -> {
			goBackButton.setBackground(
					new Background(new BackgroundFill(Color.POWDERBLUE, new CornerRadii(5), new Insets(2))));
			goBackButton.setEffect(new InnerShadow());
		});

		goBackButton.setOnMouseExited((MouseEvent e) -> {
			goBackButton.setBackground(
					new Background(new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), new Insets(2))));
			goBackButton.setEffect(null);
		});
		return goBackButton;
	}
}

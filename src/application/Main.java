package application;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.Board;
import logic.GameController;
import screen.SelectGameModeScreen;

public class Main extends Application {
	public static void main(String[] args) {
		//1280x720
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {
		SelectGameModeScreen start = new SelectGameModeScreen(primaryStage);
        primaryStage.show();
	}

}

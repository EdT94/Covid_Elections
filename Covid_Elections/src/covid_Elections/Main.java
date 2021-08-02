package covid_Elections;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ElectionRound round = new ElectionRound(2020, 6);
		round.startElections(round, primaryStage);
	}

}

package covid_Elections;

import java.util.*;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ElectionRound {
	private int year;
	private int month;
	private int numOfCitizens;
	private int numOfBallotBoxes;
	private int numOfParties;
	private int numOfCandidates;
	private int numOfVoted;
	private int[][] numberOfVotes;
	private ArrayList<Citizen> allCitizens;
	private ArrayList<Party> allParties;
	private ArrayList<BallotBox> allBallotBoxes;
	private ArrayList<Candidate> allCandidates;
	public boolean votingDone = false;

	public ElectionRound(int year, int month) {
		this.year = year;
		this.month = month;
		this.allCitizens = new ArrayList<Citizen>();
		this.allParties = new ArrayList<Party>();
		this.allBallotBoxes = new ArrayList<BallotBox>();
		this.allCandidates = new ArrayList<Candidate>();
		this.numOfCitizens = 0;
		this.numOfBallotBoxes = 0;
		this.numOfParties = 0;
		this.numOfCandidates = 0;
	}

	public ElectionRound() {
		this.allCitizens = new ArrayList<Citizen>();
		this.allParties = new ArrayList<Party>();
		this.allBallotBoxes = new ArrayList<BallotBox>();
		this.allCandidates = new ArrayList<Candidate>();
		this.numOfCitizens = 0;
		this.numOfBallotBoxes = 0;
		this.numOfParties = 0;
		this.numOfCandidates = 0;

	}

	private ArrayList<BallotBox> getAllBallotBoxes() {
		return allBallotBoxes;
	}

	private ArrayList<Citizen> getAllCitizens() {
		return allCitizens;
	}

	private ArrayList<Party> getAllParties() {
		return allParties;
	}

	private int getNumOfCitizens() {
		return numOfCitizens;
	}


	private int getNumOfBallotBoxes() {
		return numOfBallotBoxes;
	}

	

	private int getNumOfParties() {
		return numOfParties;
	}

	

	private int getNumOfVoted() {
		return numOfVoted;
	}

	

	private BallotBox getBallotBoxByNumber(int number) {
		for (int i = 0; i < numOfBallotBoxes; i++) {
			if (allBallotBoxes.get(i).getNumber() == number)
				return allBallotBoxes.get(i);
		}
		return null;
	}

	private void setCitizen(Citizen other) {
		allCitizens.add(other);
		numOfCitizens++;
		other.getBallotBox().setAllowedCitizen(other);

	}

	private void setBallotBox(BallotBox other) {
		allBallotBoxes.add(other);
		numOfBallotBoxes++;
	}

	private void setParty(Party other) {
		allParties.add(other);
		numOfParties++;
	}

	private void setCandidate(Candidate other) {
		allCandidates.add(other);
		numOfCandidates++;
		other.getParty().setCandidate(other);
	}


	private void setPartyByName(Candidate other, String name) {
		for (Party party : allParties) {
			if (party.getName().equals(name)) {
				other.setParty(party);
			}
		}
	}

	private boolean checkIfIdIsTaken(int number) {
		for (Citizen cit : allCitizens) {
			if (cit.getId() == number)
				return false;
		}
		for (Candidate can : allCandidates) {
			if (can.getId() == number)
				return false;
		}
		return true;
	}

	// AddBallotBoxHandler 193-338
	private void addBallotBoxHandler(Stage primaryStage, ToggleGroup primaryStageToggleGroup) {
		VBox addBallotBoxVbox = new VBox();
		addBallotBoxVbox.setPadding(new Insets(10));
		addBallotBoxVbox.setSpacing(10);
		addBallotBoxVbox.setAlignment(Pos.CENTER_LEFT);
		Stage addBallotBoxStage = new Stage();
		StackPane addBallotBoxStackPane = new StackPane();
		Text addBallotBoxTitle = new Text("Choose the type of the new BallotBox");
		addBallotBoxTitle.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
		addBallotBoxTitle.setFill(Color.BLACK);
		addBallotBoxStackPane.setAlignment(Pos.TOP_CENTER);
		ToggleGroup addBallotBoxToggleGroup = new ToggleGroup();
		RadioButton regularBallotBoxRadioButton = new RadioButton("Regular");
		regularBallotBoxRadioButton.setToggleGroup(addBallotBoxToggleGroup);
		RadioButton armyBallotBoxButton = new RadioButton("Army");
		armyBallotBoxButton.setToggleGroup(addBallotBoxToggleGroup);
		RadioButton isolatedBallotBoxButton = new RadioButton("For people who are in isolation");
		isolatedBallotBoxButton.setToggleGroup(addBallotBoxToggleGroup);

		regularBallotBoxRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				addRegularBallotBoxHandler(addBallotBoxStage, primaryStage);
			}
		});

		armyBallotBoxButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				addArmyBallotBoxHandler(addBallotBoxStage, primaryStage);
			}

		});

		isolatedBallotBoxButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				addCoronaBallotBoxHandler(addBallotBoxStage, primaryStage);
			}
		});

		addBallotBoxVbox.getChildren().addAll(addBallotBoxTitle, regularBallotBoxRadioButton, armyBallotBoxButton,
				isolatedBallotBoxButton);
		Scene addBallotBoxScene = new Scene(addBallotBoxVbox, 500, 200);
		addBallotBoxStage.setTitle("Add ballot box");
		addBallotBoxStage.setScene(addBallotBoxScene);
		addBallotBoxStage.setAlwaysOnTop(true);
		addBallotBoxStage.show();
		primaryStageToggleGroup.getSelectedToggle().setSelected(false);
	}

	private void addRegularBallotBoxHandler(Stage addBallotBoxStage, Stage primaryStage) {
		addBallotBoxStage.close();
		BallotBox regularBallotBox = new BallotBox();
		TextField regularBallotBoxStreet = new TextField();
		Button addRegularBallotBoxButton = new Button("Add ballot box");
		GridPane regularBallotBoxGridPane = new GridPane();
		Scene regularBallotBoxScene = new Scene(regularBallotBoxGridPane, 700, 150);
		Stage regularBallotBoxStage = new Stage();
		regularBallotBoxGridPane.add(new Label("Type the adress the new ballot box which number is "
				+ regularBallotBox.getNumber() + " will be located"), 0, 0);
		regularBallotBoxGridPane.setMinSize(600, 200);
		regularBallotBoxGridPane.setVgap(10);
		regularBallotBoxGridPane.setHgap(10);
		regularBallotBoxGridPane.setAlignment(Pos.CENTER);
		regularBallotBoxGridPane.add(regularBallotBoxStreet, 1, 0);
		regularBallotBoxGridPane.add(addRegularBallotBoxButton, 1, 2);
		regularBallotBoxGridPane.setPadding(new Insets(10, 10, 10, 10));
		addRegularBallotBoxButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				regularBallotBox.setAdress(regularBallotBoxStreet.getText());
				setBallotBox(regularBallotBox);
				System.out.println("Ballot box added succesfully");
				regularBallotBoxStage.close();
				primaryStage.show();

			}
		});
		regularBallotBoxStage.setTitle("Street name");
		regularBallotBoxStage.setScene(regularBallotBoxScene);
		regularBallotBoxStage.setAlwaysOnTop(true);
		regularBallotBoxStage.show();
	}

	private void addArmyBallotBoxHandler(Stage addBallotBoxStage, Stage primaryStage) {
		addBallotBoxStage.close();
		ArmyBallotBox armyBallotBoxButton = new ArmyBallotBox();
		TextField armyBallotBoxStreet = new TextField();
		Button addArmyBallotBoxButton = new Button("Add ballot box");
		GridPane armyBallotBoxGridPane = new GridPane();
		armyBallotBoxGridPane.add(new Label("Type the adress the new ballot box which number is "
				+ armyBallotBoxButton.getNumber() + " will be located"), 0, 0);
		armyBallotBoxGridPane.setMinSize(600, 200);
		armyBallotBoxGridPane.setVgap(10);
		armyBallotBoxGridPane.setHgap(10);
		armyBallotBoxGridPane.setAlignment(Pos.CENTER);
		armyBallotBoxGridPane.add(armyBallotBoxStreet, 1, 0);
		armyBallotBoxGridPane.add(addArmyBallotBoxButton, 1, 2);
		armyBallotBoxGridPane.setPadding(new Insets(10, 10, 10, 10));
		Scene armyBallotBoxScene = new Scene(armyBallotBoxGridPane, 700, 150);
		Stage armyBallotBoxStage = new Stage();
		addArmyBallotBoxButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				armyBallotBoxButton.setAdress(armyBallotBoxStreet.getText());
				setBallotBox(armyBallotBoxButton);
				System.out.println("Ballot box added succesfully");
				armyBallotBoxStage.close();
				primaryStage.show();

			}
		});
		armyBallotBoxStage.setTitle("Street name");
		armyBallotBoxStage.setScene(armyBallotBoxScene);
		armyBallotBoxStage.setAlwaysOnTop(true);
		armyBallotBoxStage.show();
	}

	private void addCoronaBallotBoxHandler(Stage addBallotBoxStage, Stage primaryStage) {
		addBallotBoxStage.close();
		CoronaBallotBox isolatedBallotBox = new CoronaBallotBox();
		TextField coronaBallotBoxStreet = new TextField();
		Button addCoronaBallotBoxButton = new Button("Add ballot box");
		GridPane coronaBallotBoxGridPane = new GridPane();
		coronaBallotBoxGridPane.add(new Label("Type the adress the new ballot box which number is "
				+ isolatedBallotBox.getNumber() + " will be located"), 0, 0);
		coronaBallotBoxGridPane.setMinSize(600, 200);
		coronaBallotBoxGridPane.setVgap(10);
		coronaBallotBoxGridPane.setHgap(10);
		coronaBallotBoxGridPane.setAlignment(Pos.CENTER);
		coronaBallotBoxGridPane.add(coronaBallotBoxStreet, 1, 0);
		coronaBallotBoxGridPane.add(addCoronaBallotBoxButton, 1, 2);
		coronaBallotBoxGridPane.setPadding(new Insets(10, 10, 10, 10));
		Scene coronaBallotBoxScene = new Scene(coronaBallotBoxGridPane, 700, 150);
		Stage coronaBallotBoxStage = new Stage();
		addCoronaBallotBoxButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				isolatedBallotBox.setAdress(coronaBallotBoxStreet.getText());
				setBallotBox(isolatedBallotBox);
				System.out.println("Ballot box added succesfully");
				coronaBallotBoxStage.close();
				primaryStage.show();

			}
		});
		coronaBallotBoxStage.setTitle("Street name");
		coronaBallotBoxStage.setScene(coronaBallotBoxScene);
		coronaBallotBoxStage.setAlwaysOnTop(true);
		coronaBallotBoxStage.show();
	}

	// AddCitizenHandler 341-648
	private void addCitizenHandler() {
		Citizen citizen = new Citizen();
		TextField citizenNameTextField = new TextField();
		Button addCitizenNextButton = new Button("next");
		GridPane addCitizenGridPane = new GridPane();
		addCitizenGridPane.add(new Label("What is the name of the citizen?"), 0, 0);
		Scene addCitizenScene = new Scene(addCitizenGridPane, 450, 150);
		Stage addCitizenStage = new Stage();
		addCitizenGridPane.setMinSize(600, 200);
		addCitizenGridPane.setVgap(10);
		addCitizenGridPane.setHgap(10);
		addCitizenGridPane.setAlignment(Pos.CENTER);
		addCitizenGridPane.add(citizenNameTextField, 1, 0);
		addCitizenGridPane.add(addCitizenNextButton, 1, 2);
		addCitizenGridPane.setPadding(new Insets(10, 10, 10, 10));
		addCitizenNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				addCitizenIsInQuarantineHandler(addCitizenStage, citizen, citizenNameTextField);
			}
		});
		addCitizenStage.setTitle("Citizen's name");
		addCitizenStage.setScene(addCitizenScene);
		addCitizenStage.setAlwaysOnTop(true);
		addCitizenStage.show();
	}

	private void addCitizenIsInQuarantineHandler(Stage addCitizenStage, Citizen citizen,
			TextField citizenNameTextField) {
		addCitizenStage.close();
		citizen.setName(citizenNameTextField.getText());
		GridPane isInIsolationGridPane = new GridPane();
		isInIsolationGridPane.add(new Label("Does " + citizen.getName() + " is in quarantine?"), 0, 0);
		RadioButton inIsolationRadioButton = new RadioButton("Yes");
		RadioButton isNotInIsolationRadioButton = new RadioButton("No");
		ToggleGroup isIsolation = new ToggleGroup();
		Scene isInIsolationScene = new Scene(isInIsolationGridPane, 350, 150);
		Stage isInIsolationStage = new Stage();
		isInIsolationGridPane.setMinSize(600, 200);
		isInIsolationGridPane.setVgap(10);
		isInIsolationGridPane.setHgap(10);
		isInIsolationGridPane.setAlignment(Pos.CENTER);
		isInIsolationGridPane.add(inIsolationRadioButton, 0, 1);
		isInIsolationGridPane.add(isNotInIsolationRadioButton, 0, 2);
		inIsolationRadioButton.setToggleGroup(isIsolation);
		isNotInIsolationRadioButton.setToggleGroup(isIsolation);
		isInIsolationGridPane.setPadding(new Insets(10, 10, 10, 10));
		inIsolationRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				inIsolationCitizenHandler(isInIsolationStage, citizen);
			}
		});

		isNotInIsolationRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				isNotInIsolationCitizenHandler(isInIsolationStage, citizen);
			}
		});

		isInIsolationStage.setTitle("is in quarantine?");
		isInIsolationStage.setScene(isInIsolationScene);
		isInIsolationStage.setAlwaysOnTop(true);
		isInIsolationStage.show();
	}

	private void inIsolationCitizenHandler(Stage isInIsolationStage, Citizen citizen) {
		isInIsolationStage.close();
		citizen.setIsolate(true);
		Text chooseIsolationText = new Text("Choose a ballot box by it's id, from these available ballot boxes :\n");
		Button inIsolationNextButton = new Button("next");
		VBox availableBallotBoxesDetailsVbox = new VBox();
		VBox availableBallotBoxesComboBoxVbox = new VBox();
		BorderPane inIsolationBorderPane = new BorderPane();
		ComboBox<String> availableBallotBoxesComboBox = new ComboBox<String>();
		availableBallotBoxes(availableBallotBoxesDetailsVbox, availableBallotBoxesComboBox, "CoronaBallotBox");
		FlowPane inIsolationBallotBoxContainer = new FlowPane();
		ScrollPane inIsolationScrollPane = new ScrollPane();
		Scene inIsolationScene = new Scene(inIsolationScrollPane, 800, 400);
		Stage inIsolationStage = new Stage();
		initAvailableBallotBoxes(chooseIsolationText, availableBallotBoxesDetailsVbox, availableBallotBoxesComboBox,
				availableBallotBoxesComboBoxVbox, inIsolationBorderPane, inIsolationBallotBoxContainer,
				inIsolationScrollPane, inIsolationStage, inIsolationScene, inIsolationNextButton);
		inIsolationNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (!comboBoxIsSelected(availableBallotBoxesComboBox)) {
					System.out.println("Choose a ballot box");
				} else
					inIsolationCitizenDobAndIdHandler(inIsolationStage, availableBallotBoxesComboBox, citizen);
			}
		});
	}

	private void initAvailableBallotBoxes(Text chooseIsolationText, VBox availableBallotBoxesDetailsVbox,
			ComboBox<String> availableBallotBoxesComboBox, VBox availableBallotBoxesComboBoxVbox,
			BorderPane isInIsolationBorderPane, FlowPane isInIsolationBallotBoxContainer,
			ScrollPane isInIsolationScrollPane, Stage inIsolationStage, Scene inIsolationScene,
			Button inIsolationNextButton) {
		chooseIsolationText.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
		chooseIsolationText.setFill(Color.BLACK);
		availableBallotBoxesDetailsVbox.setPadding(new Insets(10, 10, 10, 10));
		availableBallotBoxesDetailsVbox.setSpacing(20);
		availableBallotBoxesComboBoxVbox.setPadding(new Insets(10, 10, 10, 10));
		availableBallotBoxesComboBoxVbox.setSpacing(20);
		isInIsolationBorderPane.setTop(chooseIsolationText);
		isInIsolationBorderPane.setLeft(availableBallotBoxesDetailsVbox);
		isInIsolationBorderPane.setRight(availableBallotBoxesComboBoxVbox);
		availableBallotBoxesComboBoxVbox.getChildren().addAll(availableBallotBoxesComboBox, inIsolationNextButton);
		isInIsolationBallotBoxContainer.getChildren().add(isInIsolationBorderPane);
		isInIsolationScrollPane.setContent(isInIsolationBallotBoxContainer);
		isInIsolationScrollPane.setPannable(true);
		inIsolationStage.setTitle("Available ballot boxes");
		inIsolationStage.setScene(inIsolationScene);
		inIsolationStage.setAlwaysOnTop(true);
		inIsolationStage.show();
	}

	private void inIsolationCitizenDobAndIdHandler(Stage inIsolationStage, ComboBox availableBallotBoxesComboBox,
			Citizen citizen) {
		inIsolationStage.close();
		for (int i = 0; i < (Integer) availableBallotBoxesComboBox.getValue(); i++) {
			if (getAllBallotBoxes().get(i).getNumber() == (Integer) availableBallotBoxesComboBox.getValue())
				citizen.setBallotBox(getBallotBoxByNumber((Integer) availableBallotBoxesComboBox.getValue()));
		}
		GridPane inIsolationCitizenGridPane = new GridPane();
		Text inIsolationCitizenDobText = new Text("Type " + citizen.getName() + "'s birth year");
		TextField inIsolationCitizenDobTextField = new TextField();
		Text inIsolationCitizenIdText = new Text("Type " + citizen.getName() + "'s id");
		TextField inIsolationCitizenIdTextField = new TextField();
		Button addInIsolationCitizenButton = new Button("Add citizen");
		Scene inIsolationCitizenDobAndIdScene = new Scene(inIsolationCitizenGridPane, 600, 200);
		Stage inIsolationCitizenDobAndIdStage = new Stage();
		initInIsolationCitizenDobAndIdStyles(inIsolationCitizenGridPane, inIsolationCitizenDobText,
				inIsolationCitizenDobTextField, inIsolationCitizenIdText, inIsolationCitizenIdTextField,
				addInIsolationCitizenButton, inIsolationCitizenDobAndIdScene, inIsolationCitizenDobAndIdStage);
		addInIsolationCitizenButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				try {
					citizen.setBirthYear(Integer.parseInt(inIsolationCitizenDobTextField.getText()));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				citizenIdVerification(inIsolationCitizenDobAndIdStage, inIsolationCitizenIdTextField, citizen);
			}
		});

	}

	private void initInIsolationCitizenDobAndIdStyles(GridPane inIsolationCitizenGridPane,
			Text inIsolationCitizenDobText, TextField inIsolationCitizenDobTextField, Text inIsolationCitizenIdText,
			TextField inIsolationCitizenIdTextField, Button addInIsolationCitizenButton,
			Scene inIsolationCitizenDobAndIdScene, Stage inIsolationCitizenDobAndIdStage) {
		inIsolationCitizenGridPane.setMinSize(300, 200);
		inIsolationCitizenGridPane.setVgap(10);
		inIsolationCitizenGridPane.setHgap(10);
		inIsolationCitizenGridPane.setAlignment(Pos.CENTER);
		inIsolationCitizenGridPane.add(inIsolationCitizenDobText, 0, 0);
		inIsolationCitizenGridPane.add(inIsolationCitizenDobTextField, 1, 0);
		inIsolationCitizenGridPane.add(inIsolationCitizenIdText, 0, 1);
		inIsolationCitizenGridPane.add(inIsolationCitizenIdTextField, 1, 1);
		inIsolationCitizenGridPane.add(addInIsolationCitizenButton, 3, 3);
		inIsolationCitizenDobAndIdStage.setScene(inIsolationCitizenDobAndIdScene);
		inIsolationCitizenDobAndIdStage.setTitle("Birth year and id");
		inIsolationCitizenDobAndIdStage.setAlwaysOnTop(true);
		inIsolationCitizenDobAndIdStage.show();
	}

	private void isNotInIsolationCitizenHandler(Stage isInIsolationStage, Citizen citizen) {
		isInIsolationStage.close();
		citizen.setIsolate(false);
		GridPane isNotInIsolationGridPane = new GridPane();
		Text dobText = new Text("Type " + citizen.getName() + "'s birth year");
		TextField dobTextField = new TextField();
		Button isNotInIsolationNextButton = new Button("next");
		isNotInIsolationGridPane.setMinSize(300, 200);
		isNotInIsolationGridPane.setVgap(10);
		isNotInIsolationGridPane.setHgap(10);
		isNotInIsolationGridPane.setAlignment(Pos.CENTER);
		isNotInIsolationGridPane.add(dobText, 0, 0);
		isNotInIsolationGridPane.add(dobTextField, 1, 0);
		isNotInIsolationGridPane.add(isNotInIsolationNextButton, 2, 2);
		Scene scene2isNotInIsolationScene = new Scene(isNotInIsolationGridPane, 600, 200);
		Stage isNotInIsolationStage = new Stage();
		isNotInIsolationStage.setScene(scene2isNotInIsolationScene);
		isNotInIsolationStage.setTitle("Birth year");
		isNotInIsolationStage.setAlwaysOnTop(true);
		isNotInIsolationStage.show();
		isNotInIsolationNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				notInIsolationBallotBoxHandler(isNotInIsolationStage, dobTextField, citizen);
			}
		});

	}

	private void notInIsolationBallotBoxHandler(Stage isNotInIsolationStage, TextField dobTextField, Citizen citizen) {
		try {
			citizen.setBirthYear(Integer.parseInt(dobTextField.getText()));
			isNotInIsolationStage.close();
			VBox availableBallotBoxesDetailsVbox = new VBox();
			VBox availableBallotBoxesComboBoxVbox = new VBox();
			ComboBox<String> availableBallotBoxesComboBox = new ComboBox<String>();
			initNotInIsolationBallotBoxStyles(availableBallotBoxesDetailsVbox, availableBallotBoxesComboBoxVbox,
					availableBallotBoxesComboBox, citizen);
			if (Integer.parseInt(dobTextField.getText()) >= 1999 && Integer.parseInt(dobTextField.getText()) <= 2002) {
				availableBallotBoxes(availableBallotBoxesDetailsVbox, availableBallotBoxesComboBox, "ArmyBallotBox");

			} else if (Integer.parseInt(dobTextField.getText()) < 1999) {
				availableBallotBoxes(availableBallotBoxesDetailsVbox, availableBallotBoxesComboBox, "BallotBox");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void initNotInIsolationBallotBoxStyles(VBox availableBallotBoxesDetailsVbox,
			VBox availableBallotBoxesComboBoxVbox, ComboBox<String> availableBallotBoxesComboBox, Citizen citizen) {
		Text notInIsolationBallotBoxChoiceText = new Text(
				"Choose a ballot box by it's id, from these available ballot boxes :\n");
		Button notInIsolationBallotBoxButtonNext = new Button("next");
		BorderPane notInIsolationBallotBoxBorderPane = new BorderPane();
		FlowPane isNotInIsolationBallotBoxContainer = new FlowPane();
		ScrollPane isNotInIsolationBallotBoxScrollPane = new ScrollPane();
		Scene isNotInIsolationBallotBoxScene = new Scene(isNotInIsolationBallotBoxScrollPane, 800, 400);
		Stage isNotInIsolationBallotBoxStage = new Stage();
		initAvailableBallotBoxes(notInIsolationBallotBoxChoiceText, availableBallotBoxesDetailsVbox,
				availableBallotBoxesComboBox, availableBallotBoxesComboBoxVbox, notInIsolationBallotBoxBorderPane,
				isNotInIsolationBallotBoxContainer, isNotInIsolationBallotBoxScrollPane, isNotInIsolationBallotBoxStage,
				isNotInIsolationBallotBoxScene, notInIsolationBallotBoxButtonNext);
		notInIsolationBallotBoxButtonNext.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (!comboBoxIsSelected(availableBallotBoxesComboBox)) {
					System.out.println("Choose a ballot box");
				} else
					notInIsolationSoldierIdHandler(isNotInIsolationBallotBoxStage, availableBallotBoxesDetailsVbox,
							availableBallotBoxesComboBox, citizen);
			}
		});
	}

	private void notInIsolationSoldierIdHandler(Stage isNotInIsolationBallotBoxStage,
			VBox availableBallotBoxesDetailsVbox, ComboBox availableBallotBoxesComboBox, Citizen citizen) {
		isNotInIsolationBallotBoxStage.close();
		for (int i = 0; i < (Integer) availableBallotBoxesComboBox.getValue(); i++) {
			if (getAllBallotBoxes().get(i).getNumber() == (Integer) availableBallotBoxesComboBox.getValue())

				citizen.setBallotBox(getBallotBoxByNumber((Integer) availableBallotBoxesComboBox.getValue()));
		}
		availableBallotBoxes(availableBallotBoxesDetailsVbox, availableBallotBoxesComboBox, "ArmyBallotBox");
		GridPane notInIsolationSoldierGridPane = new GridPane();
		Text notInIsolationSoldierIdText = new Text("Type " + citizen.getName() + "'s id");
		TextField notInIsolationSoldierIdTextField = new TextField();
		Button notInIsolationSoldierAddButton = new Button("Add Citizen");
		notInIsolationSoldierGridPane.setMinSize(300, 200);
		notInIsolationSoldierGridPane.setVgap(10);
		notInIsolationSoldierGridPane.setHgap(10);
		notInIsolationSoldierGridPane.setAlignment(Pos.CENTER);
		notInIsolationSoldierGridPane.add(notInIsolationSoldierIdText, 0, 0);
		notInIsolationSoldierGridPane.add(notInIsolationSoldierIdTextField, 1, 0);
		notInIsolationSoldierGridPane.add(notInIsolationSoldierAddButton, 2, 2);
		Scene notInIsolationSoldierScene = new Scene(notInIsolationSoldierGridPane, 600, 200);
		Stage notInIsolationSoldierStage = new Stage();
		notInIsolationSoldierStage.setScene(notInIsolationSoldierScene);
		notInIsolationSoldierStage.setTitle("Id");
		notInIsolationSoldierStage.setAlwaysOnTop(true);
		notInIsolationSoldierStage.show();
		notInIsolationSoldierAddButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				citizenIdVerification(notInIsolationSoldierStage, notInIsolationSoldierIdTextField, citizen);
			}
		});

	}

	private void citizenIdVerification(Stage Stage, TextField idTextField, Citizen citizen) {
		try {
			boolean idIsNotTaken = checkIfIdIsTaken(Integer.parseInt(idTextField.getText()));
			citizen.setId(Integer.parseInt(idTextField.getText()));
			if (idIsNotTaken) {
				setCitizen(citizen);
				System.out.println(citizen.getName() + " added succesfully");
				Stage.close();
			} else
				System.out.println("This id is taken");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void availableBallotBoxes(VBox availableBallotBoxesDetailsVbox, ComboBox availableBallotBoxesComboBox,
			String ballotBoxType) {
		for (int i = 0; i < getNumOfBallotBoxes(); i++) {
			if (getAllBallotBoxes().get(i).getThisClass().equals(ballotBoxType)) {
				Text ballotBoxDescription = new Text(getAllBallotBoxes().get(i).toString());
				ballotBoxDescription.setFont(Font.font("Charter BT", FontWeight.NORMAL, FontPosture.REGULAR, 20));
				availableBallotBoxesComboBox.getItems().add(getAllBallotBoxes().get(i).getNumber());
				availableBallotBoxesDetailsVbox.getChildren().add(ballotBoxDescription);
			}
		}
	}

	// addPartyHandler 651-729
	private void addPartyHandler(Stage primaryStage) {
		VBox partyFactionVbox = new VBox();
		Stage partyFactionStage = new Stage();
		StackPane addPartyStackPane = new StackPane();
		Text selectPartyFactionText = new Text("Select Party political faction:");
		ToggleGroup factionTglGroup = new ToggleGroup();
		RadioButton rightFactionRadioButton = new RadioButton("Right");
		RadioButton centerFactionRadioButton = new RadioButton("Center");
		RadioButton leftFactionRadioButton = new RadioButton("Left");
		initAddPartyStyles(partyFactionVbox, selectPartyFactionText, addPartyStackPane);
		rightFactionRadioButton.setToggleGroup(factionTglGroup);
		centerFactionRadioButton.setToggleGroup(factionTglGroup);
		leftFactionRadioButton.setToggleGroup(factionTglGroup);

		rightFactionRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				partyFactionStage.close();
				partyNameHandler(primaryStage, "Right");
			}
		});
		centerFactionRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				partyFactionStage.close();
				partyNameHandler(primaryStage, "Center");
			}
		});
		leftFactionRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				partyFactionStage.close();
				partyNameHandler(primaryStage, "Left");
			}
		});
		partyFactionVbox.getChildren().addAll(selectPartyFactionText, rightFactionRadioButton, centerFactionRadioButton,
				leftFactionRadioButton);
		Scene partyFactionScene = new Scene(partyFactionVbox, 500, 200);
		partyFactionStage.setTitle("Add Party");
		partyFactionStage.setScene(partyFactionScene);
		partyFactionStage.setAlwaysOnTop(true);
		partyFactionStage.show();

	}

	private void partyNameHandler(Stage primaryStage, String partyFriction) {
		Party party = new Party();
		TextField partyNameTextField = new TextField();
		Button addPartyButton = new Button("Add Party");
		GridPane partyNameGridPane = new GridPane();
		partyNameGridPane.add(new Label("Type the name of the party"), 0, 0);
		partyNameGridPane.setMinSize(600, 200);
		partyNameGridPane.setVgap(10);
		partyNameGridPane.setHgap(10);
		partyNameGridPane.setAlignment(Pos.CENTER);
		partyNameGridPane.add(partyNameTextField, 1, 0);
		partyNameGridPane.add(addPartyButton, 0, 2);
		partyNameGridPane.setPadding(new Insets(10, 10, 10, 10));
		Scene partyNameScene = new Scene(partyNameGridPane, 700, 150);
		Stage partyNameStage = new Stage();
		addPartyButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				party.setName(partyNameTextField.getText());
				party.setFaction(partyFriction);
				setParty(party);
				System.out.println("Party added succesfully");
				partyNameStage.close();
				primaryStage.show();
			}
		});
		partyNameStage.setTitle("Party name");
		partyNameStage.setScene(partyNameScene);
		partyNameStage.setAlwaysOnTop(true);
		partyNameStage.show();
	}

	private void initAddPartyStyles(VBox partyFactionVbox, Text selectPartyFactionText, StackPane addPartyStackPane) {
		partyFactionVbox.setPadding(new Insets(10));
		partyFactionVbox.setSpacing(10);
		partyFactionVbox.setAlignment(Pos.CENTER_LEFT);
		selectPartyFactionText.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
		selectPartyFactionText.setFill(Color.BLACK);
		addPartyStackPane.setAlignment(Pos.TOP_CENTER);
	}

	// addCandidateHandler 732-1065
	private void addCandidateHandler() {
		Candidate candidate = new Candidate();
		TextField candidateNameTextField = new TextField();
		Button addCandidateNextButton = new Button("next");
		GridPane addCandidateGridPane = new GridPane();
		addCandidateGridPane.add(new Label("What is the name of the candidate?"), 0, 0);
		Scene addCandidateScene = new Scene(addCandidateGridPane, 550, 150);
		Stage addCandidateStage = new Stage();
		addCandidateGridPane.setMinSize(600, 200);
		addCandidateGridPane.setVgap(10);
		addCandidateGridPane.setHgap(10);
		addCandidateGridPane.setAlignment(Pos.CENTER);
		addCandidateGridPane.add(candidateNameTextField, 1, 0);
		addCandidateGridPane.add(addCandidateNextButton, 1, 2);
		addCandidateGridPane.setPadding(new Insets(10, 10, 10, 10));
		addCandidateNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				addCandidateIsInQuarantineHandler(addCandidateStage, candidate, candidateNameTextField);
			}
		});
		addCandidateStage.setTitle("Candidate's name");
		addCandidateStage.setScene(addCandidateScene);
		addCandidateStage.setAlwaysOnTop(true);
		addCandidateStage.show();
	}

	private void addCandidateIsInQuarantineHandler(Stage addCandidateStage, Candidate candidate,
			TextField candidateNameTextField) {
		addCandidateStage.close();
		candidate.setName(candidateNameTextField.getText());
		GridPane inIsolationCandidateGridPane = new GridPane();
		inIsolationCandidateGridPane.add(new Label("Does " + candidate.getName() + " is in quarantine?"), 0, 0);
		RadioButton inIsolationCandidate = new RadioButton("Yes");
		RadioButton notInIsolationCandidate = new RadioButton("No");
		ToggleGroup isInIsolationTgl = new ToggleGroup();
		Scene isInIsolationCandidateScene = new Scene(inIsolationCandidateGridPane, 350, 150);
		Stage isInIsolationCandidateStage = new Stage();
		inIsolationCandidate.setToggleGroup(isInIsolationTgl);
		notInIsolationCandidate.setToggleGroup(isInIsolationTgl);
		inIsolationCandidateGridPane.setMinSize(600, 200);
		inIsolationCandidateGridPane.setVgap(10);
		inIsolationCandidateGridPane.setHgap(10);
		inIsolationCandidateGridPane.setAlignment(Pos.CENTER);
		inIsolationCandidateGridPane.add(inIsolationCandidate, 0, 1);
		inIsolationCandidateGridPane.add(notInIsolationCandidate, 0, 2);
		inIsolationCandidateGridPane.setPadding(new Insets(10, 10, 10, 10));
		inIsolationCandidate.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				inIsolationCandidateHandler(isInIsolationCandidateStage, candidate);
			}
		});

		notInIsolationCandidate.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				isNotInIsolationCandidateHandler(isInIsolationCandidateStage, candidate);
			}
		});
		isInIsolationCandidateStage.setTitle("is in quarantine?");
		isInIsolationCandidateStage.setScene(isInIsolationCandidateScene);
		isInIsolationCandidateStage.setAlwaysOnTop(true);
		isInIsolationCandidateStage.show();

	}

	private void inIsolationCandidateHandler(Stage isInIsolationCandidateStage, Candidate candidate) {
		isInIsolationCandidateStage.close();
		candidate.setIsolate(true);
		Text chooseIsolationText = new Text("Choose a ballot box by it's id, from these available ballot boxes :\n");
		Button inIsolationNextButton = new Button("next");
		VBox availableBallotBoxesDetailsVbox = new VBox();
		VBox availableBallotBoxesComboBoxVbox = new VBox();
		BorderPane inIsolationBorderPane = new BorderPane();
		ComboBox<String> availableBallotBoxesComboBox = new ComboBox<String>();
		FlowPane inIsolationBallotBoxContainer = new FlowPane();
		ScrollPane inIsolationScrollPane = new ScrollPane();
		Scene inIsolationBallotBoxCandidateScene = new Scene(inIsolationScrollPane, 800, 400);
		Stage inIsolationBallotBoxCandidateStage = new Stage();
		initAvailableBallotBoxes(chooseIsolationText, availableBallotBoxesDetailsVbox, availableBallotBoxesComboBox,
				availableBallotBoxesComboBoxVbox, inIsolationBorderPane, inIsolationBallotBoxContainer,
				inIsolationScrollPane, inIsolationBallotBoxCandidateStage, inIsolationBallotBoxCandidateScene,
				inIsolationNextButton);
		availableBallotBoxes(availableBallotBoxesDetailsVbox, availableBallotBoxesComboBox, "CoronaBallotBox");
		inIsolationNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (!comboBoxIsSelected(availableBallotBoxesComboBox)) {
					System.out.println("Choose a ballot box");
				} else
					inIsolationCandidateDobAndIdHandler(inIsolationBallotBoxCandidateStage,
							availableBallotBoxesComboBox, candidate);
			}
		});
	}

	private void isNotInIsolationCandidateHandler(Stage isInIsolationCandidateStage, Candidate candidate) {
		isInIsolationCandidateStage.close();
		candidate.setIsolate(false);
		GridPane notInIsolationCandidateGridPane = new GridPane();
		Text notInIsolationCandidateDobText = new Text("Type " + candidate.getName() + "'s birth year");
		TextField notInIsolationCandidateTextField = new TextField();
		Button notInIsolationCandidateNextButton = new Button("next");
		Scene notInIsolationCandidateDobScene = new Scene(notInIsolationCandidateGridPane, 600, 200);
		Stage notInIsolationCandidateDobStage = new Stage();
		initInIsolationCandidateDobStyle(notInIsolationCandidateGridPane, notInIsolationCandidateDobText,
				notInIsolationCandidateTextField, notInIsolationCandidateNextButton, notInIsolationCandidateDobScene,
				notInIsolationCandidateDobStage);
		notInIsolationCandidateNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				notInIsolationCandidateBallotBoxHandler(candidate, notInIsolationCandidateTextField,
						notInIsolationCandidateDobStage);
			}
		});

	}

	private void inIsolationCandidateDobAndIdHandler(Stage inIsolationBallotBoxCandidateStage,
			ComboBox availableBallotBoxesComboBox, Candidate candidate) {
		inIsolationBallotBoxCandidateStage.close();
		for (int i = 0; i < (Integer) availableBallotBoxesComboBox.getValue(); i++) {
			if (getAllBallotBoxes().get(i).getNumber() == (Integer) availableBallotBoxesComboBox.getValue())
				candidate.setBallotBox(getBallotBoxByNumber((Integer) availableBallotBoxesComboBox.getValue()));
		}
		GridPane inIsolationCandidateDobAndIdGridPane = new GridPane();
		Text inIsolationCandidateDobText = new Text("Type " + candidate.getName() + "'s birth year");
		TextField inIsolationCandidateDobTextField = new TextField();
		Text inIsolationCandidateIdText = new Text("Type " + candidate.getName() + "'s id");
		TextField inIsolationCandidateIdTextField = new TextField();
		Button inIsolationCandidateDobAndIdNextButton = new Button("next");
		Scene inIsolationCandidateDobAndIdScene = new Scene(inIsolationCandidateDobAndIdGridPane, 600, 200);
		Stage inIsolationCandidateDobAndIdStage = new Stage();
		initInIsolationCitizenDobAndIdStyles(inIsolationCandidateDobAndIdGridPane, inIsolationCandidateDobText,
				inIsolationCandidateDobTextField, inIsolationCandidateIdText, inIsolationCandidateIdTextField,
				inIsolationCandidateDobAndIdNextButton, inIsolationCandidateDobAndIdScene,
				inIsolationCandidateDobAndIdStage);
		inIsolationCandidateDobAndIdNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				try {
					candidate.setBirthYear(Integer.parseInt(inIsolationCandidateDobTextField.getText()));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				candidateIdVerification(inIsolationCandidateDobAndIdStage, inIsolationCandidateIdTextField, candidate);
			}
		});

	}

	private void notInIsolationCandidateBallotBoxHandler(Stage notInIsolationCandidateDobStage, Candidate candidate) {
		notInIsolationCandidateDobStage.close();
		Text notInIsolationCandidateText = new Text(
				"Choose a ballot box by it's id, from these available ballot boxes :\n");
		Button notInIsolationCandidateNextButton = new Button("next");
		VBox availableBallotBoxesDetailsVbox = new VBox();
		VBox availableBallotBoxesComboBoxVbox = new VBox();
		BorderPane notInIsolationCandidateBorderPane = new BorderPane();
		ComboBox<String> availableBallotBoxesComboBox = new ComboBox<String>();
		FlowPane notInIsolationCandidateBallotBoxContainer = new FlowPane();
		ScrollPane notInIsolationCandidateBallotBoxScroolPane = new ScrollPane();
		Stage notInIsolationCandidateBallotBoxStage = new Stage();
		Scene notInIsolationCandidateBallotBoxScene = new Scene(notInIsolationCandidateBallotBoxScroolPane, 800, 400);
		initAvailableBallotBoxes(notInIsolationCandidateText, availableBallotBoxesDetailsVbox,
				availableBallotBoxesComboBox, availableBallotBoxesComboBoxVbox, notInIsolationCandidateBorderPane,
				notInIsolationCandidateBallotBoxContainer, notInIsolationCandidateBallotBoxScroolPane,
				notInIsolationCandidateBallotBoxStage, notInIsolationCandidateBallotBoxScene,
				notInIsolationCandidateNextButton);
		availableBallotBoxes(availableBallotBoxesDetailsVbox, availableBallotBoxesComboBox, "BallotBox");
		notInIsolationCandidateNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (!comboBoxIsSelected(availableBallotBoxesComboBox)) {
					System.out.println("Choose a ballot box");
				} else
					notInIsolationSoldierCandidateId(notInIsolationCandidateBallotBoxStage,
							availableBallotBoxesComboBox, candidate);
			}
		});

	}

	private void candidateIdVerification(Stage Stage, TextField idTextField, Candidate candidate) {
		try {
			boolean idIsNotTaken = checkIfIdIsTaken(Integer.parseInt(idTextField.getText()));
			candidate.setId(Integer.parseInt(idTextField.getText()));
			if (idIsNotTaken) {
				setCitizen(candidate);
				Stage.close();
				choosePartyForCandidate(Stage, candidate);
			} else
				System.out.println("This id is taken");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void notInIsolationSoldierCandidateId(Stage notInIsolationSoldierCandidateStage,
			ComboBox availableBallotBoxesComboBox, Candidate candidate) {
		notInIsolationSoldierCandidateStage.close();
		for (int i = 0; i < (Integer) availableBallotBoxesComboBox.getValue(); i++) {
			if (getAllBallotBoxes().get(i).getNumber() == (Integer) availableBallotBoxesComboBox.getValue())

				candidate.setBallotBox(getBallotBoxByNumber((Integer) availableBallotBoxesComboBox.getValue()));
		}
		GridPane notInIsolationSoldierCandidateIdGridPane = new GridPane();
		Text notInIsolationSoldierCandidateIdText = new Text("Type " + candidate.getName() + "'s id");
		TextField notInIsolationSoldierCandidateIdTextField = new TextField();
		Button notInIsolationSoldierCandidateIdNextButton = new Button("next");
		Scene notInIsolationSoldierCandidateIdScene = new Scene(notInIsolationSoldierCandidateIdGridPane, 600, 200);
		Stage notInIsolationSoldierCandidateIdStage = new Stage();
		notInIsolationSoldierCandidateIdGridPane.setMinSize(300, 200);
		notInIsolationSoldierCandidateIdGridPane.setVgap(10);
		notInIsolationSoldierCandidateIdGridPane.setHgap(10);
		notInIsolationSoldierCandidateIdGridPane.setAlignment(Pos.CENTER);
		notInIsolationSoldierCandidateIdGridPane.add(notInIsolationSoldierCandidateIdText, 0, 0);
		notInIsolationSoldierCandidateIdGridPane.add(notInIsolationSoldierCandidateIdTextField, 1, 0);
		notInIsolationSoldierCandidateIdGridPane.add(notInIsolationSoldierCandidateIdNextButton, 2, 2);
		notInIsolationSoldierCandidateIdStage.setScene(notInIsolationSoldierCandidateIdScene);
		notInIsolationSoldierCandidateIdStage.setTitle("Id");
		notInIsolationSoldierCandidateIdStage.setAlwaysOnTop(true);
		notInIsolationSoldierCandidateIdStage.show();
		notInIsolationSoldierCandidateIdNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				candidateIdVerification(notInIsolationSoldierCandidateIdStage,
						notInIsolationSoldierCandidateIdTextField, candidate);
			}
		});

	}

	private void choosePartyForCandidate(Stage inIsolationCandidateDobAndIdStage, Candidate candidate) {
		inIsolationCandidateDobAndIdStage.close();
		Text partyForCandidateText = new Text("which party does " + candidate.getName() + " represent?");
		Button addCandidateButton = new Button("Add candidate");
		VBox partiesDescriptionVbox = new VBox();
		VBox partiesComboBoxVbox = new VBox();
		BorderPane partiesBorderPane = new BorderPane();
		ComboBox<String> partiesComboBox = new ComboBox<String>();
		FlowPane container = new FlowPane();
		ScrollPane scr = new ScrollPane();
		Scene partyCandidateSelectScene = new Scene(scr, 1900, 600);
		Stage partyCandidateSelectStage = new Stage();
		iniStylesForPartyCandidate(partyForCandidateText, partiesDescriptionVbox, partiesComboBox, partiesBorderPane,
				partiesComboBoxVbox);
		availableParties(partiesComboBox, partiesDescriptionVbox, partiesComboBoxVbox, addCandidateButton);
		container.getChildren().add(partiesBorderPane);
		scr.setContent(container);
		scr.setPannable(true);
		partyCandidateSelectStage.setTitle("Available parties");
		partyCandidateSelectStage.setScene(partyCandidateSelectScene);
		partyCandidateSelectStage.setAlwaysOnTop(true);
		partyCandidateSelectStage.show();
		addCandidateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				for (int i = 0; i < getNumOfParties(); i++) {
					if (getAllParties().get(i).getName().equals(partiesComboBox.getValue())) {
						setPartyByName(candidate, (String) partiesComboBox.getValue());
						setCandidate(candidate);
						System.out.println(candidate.getName() + " added succesfully");
						partyCandidateSelectStage.close();
					}
				}
			}
		});
	}

	private void iniStylesForPartyCandidate(Text partyForCandidateText, VBox partiesDescriptionVbox,
			ComboBox<String> partiesComboBox, BorderPane partiesBorderPane, VBox partiesComboBoxVbox) {
		partyForCandidateText.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
		partyForCandidateText.setFill(Color.BLACK);
		partiesDescriptionVbox.setPadding(new Insets(10, 10, 10, 10));
		partiesDescriptionVbox.setSpacing(20);
		partiesComboBoxVbox.setPadding(new Insets(10, 10, 10, 10));
		partiesComboBoxVbox.setSpacing(20);
		partiesBorderPane.setTop(partyForCandidateText);
		partiesBorderPane.setLeft(partiesDescriptionVbox);
		partiesBorderPane.setRight(partiesComboBoxVbox);
	}

	private void initInIsolationCandidateDobStyle(GridPane notInIsolationCandidateGridPane,
			Text notInIsolationCandidateDobText, TextField notInIsolationCandidateTextField,
			Button notInIsolationCandidateNextButton, Scene notInIsolationCandidateDobScene,
			Stage notInIsolationCandidateDobStage) {
		notInIsolationCandidateGridPane.setMinSize(300, 200);
		notInIsolationCandidateGridPane.setVgap(10);
		notInIsolationCandidateGridPane.setHgap(10);
		notInIsolationCandidateGridPane.setAlignment(Pos.CENTER);
		notInIsolationCandidateGridPane.add(notInIsolationCandidateDobText, 0, 0);
		notInIsolationCandidateGridPane.add(notInIsolationCandidateTextField, 1, 0);
		notInIsolationCandidateGridPane.add(notInIsolationCandidateNextButton, 2, 2);
		notInIsolationCandidateDobStage.setScene(notInIsolationCandidateDobScene);
		notInIsolationCandidateDobStage.setTitle("Birth year");
		notInIsolationCandidateDobStage.setAlwaysOnTop(true);
		notInIsolationCandidateDobStage.show();
	}

	private void notInIsolationSoldierCandidateBallotBoxHandler(Stage notInIsolationCandidateDobStage,
			Candidate candidate) {
		notInIsolationCandidateDobStage.close();
		Text notInIsolationSoldierCandidateText = new Text(
				"Choose a ballot box by it's id, from these available ballot boxes :\n");
		Button notInIsolationSoldierCandidateNextButton = new Button("next");
		VBox availableBallotBoxesDetailsVbox = new VBox();
		VBox availableBallotBoxesComboBoxVbox = new VBox();
		BorderPane notInIsoationSoldierCandidateBorderPane = new BorderPane();
		ComboBox<String> availableBallotBoxesComboBox = new ComboBox<String>();
		FlowPane notInIsolationBallotBoxContainer = new FlowPane();
		ScrollPane notInIsolationSoldierCandidateScroolPane = new ScrollPane();
		Scene notInIsolationSoldierCandidateScene = new Scene(notInIsolationSoldierCandidateScroolPane, 800, 400);
		Stage notInIsolationSoldierCandidateStage = new Stage();
		initAvailableBallotBoxes(notInIsolationSoldierCandidateText, availableBallotBoxesDetailsVbox,
				availableBallotBoxesComboBox, availableBallotBoxesComboBoxVbox, notInIsoationSoldierCandidateBorderPane,
				notInIsolationBallotBoxContainer, notInIsolationSoldierCandidateScroolPane,
				notInIsolationSoldierCandidateStage, notInIsolationSoldierCandidateScene,
				notInIsolationSoldierCandidateNextButton);
		availableBallotBoxes(availableBallotBoxesDetailsVbox, availableBallotBoxesComboBox, "ArmyBallotBox");
		notInIsolationSoldierCandidateNextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (!comboBoxIsSelected(availableBallotBoxesComboBox)) {
					System.out.println("Choose a ballot box");
				} else
					notInIsolationSoldierCandidateId(notInIsolationSoldierCandidateStage, availableBallotBoxesComboBox,
							candidate);
			}
		});

	}

	private void notInIsolationCandidateBallotBoxHandler(Candidate candidate,
			TextField notInIsolationCandidateTextField, Stage notInIsolationCandidateDobStage) {
		try {
			candidate.setBirthYear(Integer.parseInt(notInIsolationCandidateTextField.getText()));
			if (Integer.parseInt(notInIsolationCandidateTextField.getText()) >= 1999
					&& Integer.parseInt(notInIsolationCandidateTextField.getText()) <= 2002) {
				notInIsolationSoldierCandidateBallotBoxHandler(notInIsolationCandidateDobStage, candidate);
			}

			else if (Integer.parseInt(notInIsolationCandidateTextField.getText()) < 1999) {
				notInIsolationCandidateBallotBoxHandler(notInIsolationCandidateDobStage, candidate);

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void availableParties(ComboBox<String> partiesComboBox, VBox partiesDescriptionVbox, VBox partiesComboBoxVbox,
			Button addCandidateButton) {
		for (int i = 0; i < getNumOfParties(); i++) {
			Text partyDescriptionText = new Text(getAllParties().get(i).toString());
			partyDescriptionText.setFont(Font.font("Charter BT", FontWeight.NORMAL, FontPosture.REGULAR, 20));
			partiesComboBox.getItems().add(getAllParties().get(i).getName());
			partiesDescriptionVbox.getChildren().add(partyDescriptionText);
		}
		partiesComboBoxVbox.getChildren().addAll(partiesComboBox, addCandidateButton);
	}

	// allBallotBoxHandler 1079-1105
	private void allBallotBoxesHandler() {
		Text ballotBoxesText = new Text("The ballot boxes are :\n");
		ballotBoxesText.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
		ballotBoxesText.setFill(Color.BLACK);
		VBox ballotBoxesVbox = new VBox();
		ballotBoxesVbox.getChildren().add(ballotBoxesText);
		ballotBoxesVbox.setSpacing(20);

		for (int i = 0; i < getNumOfBallotBoxes(); i++) {
			Text ballotBoxDescription = new Text(getAllBallotBoxes().get(i).toString());
			ballotBoxDescription.setFont(Font.font("Charter BT", FontWeight.NORMAL, FontPosture.REGULAR, 20));
			ballotBoxesVbox.getChildren().add(ballotBoxDescription);

		}

		FlowPane container = new FlowPane();
		container.getChildren().add(ballotBoxesVbox);
		ScrollPane scr = new ScrollPane();
		scr.setContent(container);
		scr.setPannable(true);
		Scene ballotBoxesScene = new Scene(scr, 600, 400);
		Stage ballotBoxesStage = new Stage();
		ballotBoxesStage.setTitle("All ballot boxes");
		ballotBoxesStage.setScene(ballotBoxesScene);
		ballotBoxesStage.setAlwaysOnTop(true);
		ballotBoxesStage.show();
	}

	// allCitizensHandler 1108-1134
	private void allCitizensHandler() {
		Text citizensText = new Text("The citizens are :\n");
		citizensText.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
		citizensText.setFill(Color.BLACK);
		VBox citizensVbox = new VBox();
		citizensVbox.getChildren().add(citizensText);
		citizensVbox.setSpacing(20);
		for (int i = 0; i < getNumOfCitizens(); i++) {
			Text citizenDescription = new Text(getAllCitizens().get(i).toString());
			citizenDescription.setFont(Font.font("Charter BT", FontWeight.NORMAL, FontPosture.REGULAR, 20));
			citizensVbox.getChildren().add(citizenDescription);

		}

		final FlowPane container = new FlowPane();
		container.getChildren().add(citizensVbox);
		ScrollPane scr = new ScrollPane();
		scr.setContent(container);
		scr.setPannable(true);
		Scene citizensScene = new Scene(scr, 1200, 500);
		Stage citizenStage = new Stage();
		citizenStage.setTitle("All Citizens");
		citizenStage.setScene(citizensScene);
		citizenStage.setAlwaysOnTop(true);
		citizenStage.show();
	}

	// allPartiesHandler 1137-1162
	private void allPartiesHandler() {
		Text partiesText = new Text("The parties are :\n");
		partiesText.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
		partiesText.setFill(Color.BLACK);
		VBox partiesVbox = new VBox();
		partiesVbox.getChildren().add(partiesText);
		partiesVbox.setSpacing(20);

		for (int i = 0; i < getNumOfParties(); i++) {
			Text partyDescription = new Text(getAllParties().get(i).toString());
			partyDescription.setFont(Font.font("Charter BT", FontWeight.NORMAL, FontPosture.REGULAR, 20));
			partiesVbox.getChildren().add(partyDescription);
		}

		final FlowPane container = new FlowPane();
		container.getChildren().add(partiesVbox);
		ScrollPane scr = new ScrollPane();
		scr.setContent(container);
		scr.setPannable(true);
		Scene partiesScene = new Scene(scr, 1800, 600);
		Stage partiesStage = new Stage();
		partiesStage.setTitle("All Parties");
		partiesStage.setScene(partiesScene);
		partiesStage.setAlwaysOnTop(true);
		partiesStage.show();
	}

	// votingHandler 1165-1226
	private void votingHandler() {
		Scanner scan = new Scanner(System.in);
		String voterChoice = new String();
		this.numberOfVotes = new int[numOfParties][numOfBallotBoxes];
		resetVoting();
		votingDone = true;
		for (int j = 0; j < numOfBallotBoxes; j++) {
			voteInBallotBox(voterChoice, scan, j);
		}
		System.out.println("Voting is done");
	}

	private void voteInBallotBox(String voterChoice, Scanner scan, int j) {
		System.out.println("Voting in ballot box number " + (j + 1) + ":\n");
		ArrayList<Citizen> allowedCitizens = new ArrayList<Citizen>(allBallotBoxes.get(j).getNumOfCitizens());
		allowedCitizens = allBallotBoxes.get(j).getAllowedCitizens();
		for (Citizen citizen : allowedCitizens) {
			System.out.println(citizen.getName() + " Do you want to vote?(Yes/No)");
			voterChoice = scan.next();
			while (!(voterChoice.equals("Yes") || (voterChoice.equals("No")))) {
				System.out.println("Yes/No");
				voterChoice = scan.next();
			}
			if (voterChoice.equals("Yes")) {
				System.out.println("Ok,choose number for your party");
				for (int k = 0; k < numOfParties; k++) {
					System.out.println(k + 1 + ": for " + "'" + allParties.get(k).getName() + "'");
				}
				int choose = scan.nextInt();
				while (choose < 1 || choose > numOfParties) {
					System.out.println("choose valid number");
					choose = scan.nextInt();
				}
				numOfVoted++;
				allBallotBoxes.get(j).plusNumOfVoted();
				allParties.get(choose - 1).plusNumOfVotes();
				numberOfVotes[choose - 1][j]++;

			}
			if (voterChoice.equals("No")) {
				System.out.println("Ok\n");
			}
		}

	}

	private void resetVoting() {
		for (int i = 0; i < numberOfVotes.length; i++) {
			for (int j = 0; j < numberOfVotes[i].length; j++)
				numberOfVotes[i][j] = 0;

		}
		this.numOfVoted = 0;
		for (int i = 0; i < numOfParties; i++) {
			allParties.get(i).setNumOfVoted(0);
		}

		for (int i = 0; i < numOfBallotBoxes; i++) {
			allBallotBoxes.get(i).setNumOfVoted(0);
		}
	}

	// votingResultsHandler 1229-1304
	private void votingResultsHandler() {
		PieChart pieChart = new PieChart();
		BorderPane votingResultsBorderPane = new BorderPane();
		double votingRate = (getNumOfVoted() * 100.0) / getNumOfCitizens();
		Text votingRateText = new Text("There is " + votingRate + " % voting rate");
		Text peopleVotedText = new Text(getNumOfVoted() + " people voted out of " + getNumOfCitizens());
		VBox votingResultsVbox = new VBox();
		Stage votingResultsStage = new Stage();
		Scene votingResultsScene = new Scene(votingResultsBorderPane);
		setStylesForVotingResults(pieChart, votingResultsBorderPane, votingRateText, peopleVotedText,
				votingResultsVbox);

		for (int i = 0; i < getNumOfParties(); i++) {
			PieChart.Data partySlice = new PieChart.Data(getAllParties().get(i).getName(),
					getAllParties().get(i).getNumOfVotes());
			pieChart.getData().add(partySlice);

		}

		votingResultsMouseEnteredSlice(pieChart, votingResultsVbox);
		votingResultsMouseExitedSlice(pieChart, votingResultsVbox, votingRateText, peopleVotedText);
		votingResultsStage.setTitle("Voting results");
		votingResultsStage.setWidth(1300);
		votingResultsStage.setHeight(900);
		votingResultsStage.setScene(votingResultsScene);
		votingResultsStage.setAlwaysOnTop(true);
		votingResultsStage.show();
	}

	private void votingResultsMouseExitedSlice(PieChart pieChart, VBox votingResultsVbox, Text votingRateText,
			Text peopleVotedText) {
		for (PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					votingResultsVbox.getChildren().clear();
					votingResultsVbox.getChildren().addAll(votingRateText, peopleVotedText);
				}

			});
		}
	}

	private void votingResultsMouseEnteredSlice(PieChart pieChart, VBox votingResultsVbox) {
		for (PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					String partyName = data.getName();
					Text votesForPartyText = new Text();
					for (int i = 0; i < getNumOfParties(); i++) {
						if (partyName.equals(getAllParties().get(i).getName())) {
							votesForPartyText.setText(getAllParties().get(i).getName() + " gets "
									+ getAllParties().get(i).getNumOfVotes() + " votes");
							votesForPartyText
									.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 15));
							votingResultsVbox.getChildren().add(votesForPartyText);
						}
					}

				}
			});
		}

	}

	private void setStylesForVotingResults(PieChart pieChart, BorderPane votingResultsBorderPane, Text votingRateText,
			Text peopleVotedText, VBox votingResultsVbox) {
		pieChart.setTitle("Voting results");
		pieChart.setPrefSize(550.0, 550.0);
		votingResultsBorderPane.setCenter(pieChart);
		votingRateText.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 22));
		peopleVotedText.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 18));
		votingResultsVbox.setPadding(new Insets(50, 50, 50, 50));
		votingResultsVbox.setSpacing(10);
		votingResultsVbox.getChildren().addAll(votingRateText, peopleVotedText);
		votingResultsBorderPane.setLeft(votingResultsVbox);
	}

	// exitSystemHandler
	private void exitSystemHandler(Stage primaryStage, ToggleGroup primaryStageToggleGroup) {
		System.out.println("Good Bye");
		primaryStage.close();
		primaryStageToggleGroup.getSelectedToggle().setSelected(false);
	}

	private void initSystem(ElectionRound round) {
		BallotBox regularBallotBox = new BallotBox("Ben gurion");
		CoronaBallotBox coronaBallotBox = new CoronaBallotBox("Covid");
		ArmyBallotBox armyBallotBox = new ArmyBallotBox("Idf");
		createAndAddFirstCitizens(round, regularBallotBox, coronaBallotBox, armyBallotBox);
		createAndAddFirstParties();
		createAndAddFirstCandidates(regularBallotBox, coronaBallotBox, armyBallotBox);
		setBallotBox(regularBallotBox);
		setBallotBox(coronaBallotBox);
		setBallotBox(armyBallotBox);
	}

	private void setStylesForPrimaryStage(Stage primaryStage, Text primaryTitle, VBox primaryVbox) {
		primaryStage.setHeight(400);
		primaryStage.setWidth(350);
		primaryTitle.setFont(Font.font("Charter BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
		primaryTitle.setFill(Color.BLACK);
		primaryVbox.setSpacing(10);
		primaryVbox.setAlignment(Pos.CENTER_LEFT);
	}

	private void createAndAddFirstCitizens(ElectionRound round, BallotBox regularBallotBox,
			CoronaBallotBox coronaBallotBox, ArmyBallotBox armyBallotBox) {
		Citizen c1 = new Citizen("Jojo", 111111111, 2000, coronaBallotBox, true);
		Citizen c2 = new Citizen("Koko", 222222222, 2001, armyBallotBox, false);
		Citizen c3 = new Citizen("Momo", 333333333, 1994, regularBallotBox, false);
		Citizen c4 = new Citizen("Lolo", 444444444, 2002, armyBallotBox, false);
		Citizen c5 = new Citizen("Popo", 555555555, 1999, coronaBallotBox, true);
		round.setCitizen(c1);
		round.setCitizen(c2);
		round.setCitizen(c3);
		round.setCitizen(c4);
		round.setCitizen(c5);
	}

	private void createAndAddFirstParties() {
		Party p1 = new Party("HaTovimLeTais");
		p1.setFaction("Right");
		Party p2 = new Party("Beer Lovers");
		p2.setFaction("Center");
		Party p3 = new Party("Together");
		p3.setFaction("Left");
		setParty(p1);
		setParty(p2);
		setParty(p3);
	}

	private void createAndAddFirstCandidates(BallotBox regularBallotBox, CoronaBallotBox coronaBallotBox,
			ArmyBallotBox armyBallotBox) {
		Candidate can1 = new Candidate("I promise", 666666666, 1980, regularBallotBox, false, getAllParties().get(0));
		Candidate can2 = new Candidate("I promise too", 777777777, 1990, regularBallotBox, false,
				getAllParties().get(1));
		Candidate can3 = new Candidate("I promise is a liar", 888888888, 1997, coronaBallotBox, true,
				getAllParties().get(1));
		Candidate can4 = new Candidate("Vote for 'Joni Bravo'", 999999999, 1968, regularBallotBox, false,
				getAllParties().get(2));
		Candidate can5 = new Candidate("Joni Bravo", 101010101, 1945, regularBallotBox, false, getAllParties().get(2));
		Candidate can6 = new Candidate("Just not 'I promise too'", 123456789, 1986, coronaBallotBox, true,
				getAllParties().get(0));
		setCandidate(can1);
		setCandidate(can2);
		setCandidate(can3);
		setCandidate(can4);
		setCandidate(can5);
		setCandidate(can6);
	}

	public void startElections(ElectionRound round, Stage primaryStage) {
		Text primaryTitle = new Text("Choose action for:");
		VBox primaryVbox = new VBox();
		setStylesForPrimaryStage(primaryStage, primaryTitle, primaryVbox);
		initSystem(round);
		ToggleGroup primaryStageToggleGroup = new ToggleGroup();
		RadioButton addBallotBoxRadioButton = new RadioButton("Add ballot box");
		RadioButton addCitizenRadioButton = new RadioButton("Add citizen");
		RadioButton addPartyRadioButton = new RadioButton("Add party");
		RadioButton addCandidateRadioButton = new RadioButton("Add candidate");
		RadioButton allBallotBoxesRadioButton = new RadioButton("Show all ballot boxes");
		RadioButton allCitizensRadioButton = new RadioButton("Show all citizens");
		RadioButton allPartiesRadioButton = new RadioButton("Show all parties");
		RadioButton votingRadioButton = new RadioButton("Voting");
		RadioButton votingResultsRadioButton = new RadioButton("Voting results");
		RadioButton exitRadioButton = new RadioButton("Exit");
		setTglForRadioButtons(primaryStageToggleGroup, addBallotBoxRadioButton, addCitizenRadioButton,
				addPartyRadioButton, addCandidateRadioButton, allBallotBoxesRadioButton, allCitizensRadioButton,
				allPartiesRadioButton, votingRadioButton, votingResultsRadioButton, exitRadioButton);
		allbuttonsSetOnActions(primaryStageToggleGroup, primaryStage, addBallotBoxRadioButton, addCitizenRadioButton,
				addPartyRadioButton, addCandidateRadioButton, allBallotBoxesRadioButton, allCitizensRadioButton,
				allPartiesRadioButton, votingRadioButton, votingResultsRadioButton, exitRadioButton);
		primaryVbox.setPadding(new Insets(10));
		primaryVbox.getChildren().addAll(primaryTitle, addBallotBoxRadioButton, addCitizenRadioButton,
				addPartyRadioButton, addCandidateRadioButton, allBallotBoxesRadioButton, allCitizensRadioButton,
				allPartiesRadioButton, votingRadioButton, votingResultsRadioButton, exitRadioButton);
		Scene primaryScene = new Scene(primaryVbox, 250, 350);
		primaryStage.setTitle("Election Round");
		primaryStage.setScene(primaryScene);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.show();
	}

	private void allbuttonsSetOnActions(ToggleGroup primaryStageToggleGroup, Stage primaryStage,
			RadioButton addBallotBoxRadioButton, RadioButton addCitizenRadioButton, RadioButton addPartyRadioButton,
			RadioButton addCandidateRadioButton, RadioButton allBallotBoxesRadioButton,
			RadioButton allCitizensRadioButton, RadioButton allPartiesRadioButton, RadioButton votingRadioButton,
			RadioButton votingResultsRadioButton, RadioButton exitRadioButton) {
		addBallotBoxRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				addBallotBoxHandler(primaryStage, primaryStageToggleGroup);
			}
		});

		addCitizenRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				addCitizenHandler();
				primaryStageToggleGroup.getSelectedToggle().setSelected(false);
			}
		});

		addPartyRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				addPartyHandler(primaryStage);
				primaryStageToggleGroup.getSelectedToggle().setSelected(false);
			}
		});

		addCandidateRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				addCandidateHandler();
				primaryStageToggleGroup.getSelectedToggle().setSelected(false);
			}
		});

		allBallotBoxesRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				allBallotBoxesHandler();
				primaryStageToggleGroup.getSelectedToggle().setSelected(false);
			}
		});

		allCitizensRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				allCitizensHandler();
				primaryStageToggleGroup.getSelectedToggle().setSelected(false);
			}
		});

		allPartiesRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				allPartiesHandler();
				primaryStageToggleGroup.getSelectedToggle().setSelected(false);
			}
		});

		votingRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				votingHandler();
				primaryStageToggleGroup.getSelectedToggle().setSelected(false);
			}
		});
		votingResultsRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (!votingDone) {
					System.out.println("Voting has not been made yet");
				} else {
					votingResultsHandler();
				}
				primaryStageToggleGroup.getSelectedToggle().setSelected(false);
			}
		});

		exitRadioButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				exitSystemHandler(primaryStage, primaryStageToggleGroup);
			}
		});
	}

	private void setTglForRadioButtons(ToggleGroup primaryStageToggleGroup, RadioButton addBallotBoxRadioButton,
			RadioButton addCitizenRadioButton, RadioButton addPartyRadioButton, RadioButton addCandidateRadioButton,
			RadioButton allBallotBoxesRadioButton, RadioButton allCitizensRadioButton,
			RadioButton allPartiesRadioButton, RadioButton votingRadioButton, RadioButton votingResultsRadioButton,
			RadioButton exitRadioButton) {
		addBallotBoxRadioButton.setToggleGroup(primaryStageToggleGroup);
		addCitizenRadioButton.setToggleGroup(primaryStageToggleGroup);
		addPartyRadioButton.setToggleGroup(primaryStageToggleGroup);
		addCandidateRadioButton.setToggleGroup(primaryStageToggleGroup);
		allBallotBoxesRadioButton.setToggleGroup(primaryStageToggleGroup);
		allCitizensRadioButton.setToggleGroup(primaryStageToggleGroup);
		allPartiesRadioButton.setToggleGroup(primaryStageToggleGroup);
		votingRadioButton.setToggleGroup(primaryStageToggleGroup);
		votingResultsRadioButton.setToggleGroup(primaryStageToggleGroup);
		exitRadioButton.setToggleGroup(primaryStageToggleGroup);
	}

	private boolean comboBoxIsSelected(ComboBox<String> cb) {
		if (cb.getSelectionModel().isEmpty())
			return false;
		return true;
	}
}

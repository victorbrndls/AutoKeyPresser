package com.harystolho;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.harystolho.key.AddKeyWindow;
import com.harystolho.key.KeyEvent;
import com.harystolho.key.KeyProfile;
import com.harystolho.key.RecordKeysWindow;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AutoPresserGUI extends Application {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;

	private KeyEvent editingKey;

	private TableView<KeyEvent> keyTable;
	private TableColumn<KeyEvent, String> timeColumn;
	private TableColumn<KeyEvent, String> keyColumn;

	private ObservableList<KeyProfile> profileList = FXCollections.observableArrayList();
	private KeyProfile currentProfile;

	private Scene scene;

	private Button addKeyButton;
	private Button recordKeySequence;

	private TextField keyName;
	private TextField keyDuration;
	private Button keyDelete;
	private Button keySave;

	private ChoiceBox<KeyProfile> profiles;
	private TextField profileName;
	private Button newProfileButton;
	private Button deleteProfileButton;
	private Button loadProfileButton;
	private Button saveProfileButton;

	private RadioButton playOnceRadio;
	private RadioButton playXTimesRadio;
	private RadioButton playUltilStopRadio;
	private TextField playXTimesField;

	private Button playButton;

	private ToggleGroup radios;

	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("AutoPresser");
		window.setWidth(WIDTH);
		window.setHeight(HEIGHT);

		loadGUIItems();
		loadEvents();

		window.setResizable(false);
		window.setScene(scene);
		window.show();
	}

	public void startGUI(String[] args) {
		launch(args);
	}

	private void loadGUIItems() {
		HBox contents = new HBox();
		contents.setId("content");
		contents.setPadding(new Insets(10));
		loadLeftSide(contents);
		loadRightSide(contents);

		scene = new Scene(contents);
		scene.getStylesheets().add("main.css");
	}

	private void loadLeftSide(HBox contents) {
		VBox leftSideContents = new VBox();

		keyTable = new TableView<>();
		keyTable.setId("table");
		keyTable.setEditable(false);
		keyTable.setPrefWidth(WIDTH * 0.305);
		keyTable.setPrefHeight(HEIGHT * 0.8);

		keyTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> setKeyInfoOnGrid(newValue));

		keyTable.setPlaceholder(new Label(""));

		timeColumn = new TableColumn<>();
		timeColumn.setId("timeColumn");
		keyColumn = new TableColumn<>("Key");

		keyColumn.setPrefWidth(WIDTH * 0.2);
		timeColumn.setPrefWidth(WIDTH * 0.1);

		timeColumn.setSortable(false);
		keyColumn.setSortable(false);

		// Delay column header tooltip
		Label timeColumnLabel = new Label("Delay (ms)");
		timeColumnLabel.setTooltip(new Tooltip("Delay before pressing the key."));
		timeColumn.setGraphic(timeColumnLabel);

		timeColumn.setCellValueFactory((cellData) -> cellData.getValue().getKeyDuration());
		keyColumn.setCellValueFactory((cellData) -> cellData.getValue().getKeyName());

		keyTable.getColumns().addAll(timeColumn, keyColumn);

		// Left bottom
		HBox recordBox = new HBox();
		recordBox.setTranslateY(7);

		addKeyButton = new Button("New Key/Mouse");

		recordKeySequence = new Button("Record Many Keys");
		recordKeySequence.setTranslateX(60);

		recordBox.getChildren().addAll(addKeyButton, recordKeySequence);
		//

		leftSideContents.getChildren().addAll(keyTable, recordBox);
		leftSideContents.setAlignment(Pos.TOP_LEFT);
		contents.getChildren().add(leftSideContents);
	}

	private void loadRightSide(HBox contents) {
		VBox rightSideContents = new VBox();
		rightSideContents.setTranslateX(50);

		//
		GridPane topGridPane = new GridPane();
		topGridPane.setId("rightSideContent");
		topGridPane
				.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
		// topGridPane.setGridLinesVisible(true);
		topGridPane.setHgap(20);
		topGridPane.setVgap(20);

		keyName = new TextField("");
		keyName.setMaxWidth(150);
		keyName.setEditable(false);
		keyDelete = new Button("X");
		keyDelete.setTooltip(new Tooltip("Delete key"));
		keyDelete.setTranslateX(30);
		keyDuration = new TextField();
		keyDuration.setMaxWidth(70);
		keySave = new Button("Save key");

		GridPane.setColumnSpan(keyName, GridPane.REMAINING);
		GridPane.setColumnSpan(keyDuration, GridPane.REMAINING);

		topGridPane.add(keyName, 0, 0);
		topGridPane.add(keyDelete, 23, 0);
		topGridPane.add(keyDuration, 0, 1);
		topGridPane.add(keySave, 23, 5);
		//
		rightSideContents.getChildren().add(topGridPane);

		//
		GridPane bottomGridPane = new GridPane();
		bottomGridPane.setId("rightSideContent");
		bottomGridPane
				.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
		// bottomGridPane.setGridLinesVisible(true);
		bottomGridPane.setTranslateY(HEIGHT * 0.1);
		bottomGridPane.setHgap(20);
		bottomGridPane.setVgap(20);

		profiles = new ChoiceBox<>();
		profiles.setMaxWidth(150);
		profiles.setItems(profileList);
		profileName = new TextField();
		newProfileButton = new Button("New Profile");
		deleteProfileButton = new Button("Delete Profile");
		deleteProfileButton.setTranslateY(33);
		loadProfileButton = new Button("Load Profiles");
		saveProfileButton = new Button("Save Profiles");

		bottomGridPane.add(profiles, 0, 0);
		bottomGridPane.add(profileName, 0, 1);
		bottomGridPane.add(deleteProfileButton, 1, 1);
		bottomGridPane.add(newProfileButton, 1, 1);
		bottomGridPane.add(loadProfileButton, 15, 2);
		bottomGridPane.add(saveProfileButton, 15, 3);

		GridPane.setColumnSpan(newProfileButton, GridPane.REMAINING);
		GridPane.setColumnSpan(deleteProfileButton, GridPane.REMAINING);
		//
		rightSideContents.getChildren().add(bottomGridPane);

		//
		GridPane playGridPane = new GridPane();
		playGridPane.setId("rightSideContent");
		playGridPane
				.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
		playGridPane.setTranslateY(HEIGHT * 0.13);
		playGridPane.setVgap(5);
		playGridPane.setHgap(10);

		radios = new ToggleGroup();

		playOnceRadio = new RadioButton("Play once");
		playXTimesRadio = new RadioButton("Play X times");
		playUltilStopRadio = new RadioButton("Play until 'Play' is pressed again");

		playOnceRadio.setStyle("-fx-text-fill: white;");
		playXTimesRadio.setStyle("-fx-text-fill: white;");
		playUltilStopRadio.setStyle("-fx-text-fill: white;");

		playOnceRadio.setToggleGroup(radios);
		playOnceRadio.setSelected(true);
		playXTimesRadio.setToggleGroup(radios);
		playUltilStopRadio.setToggleGroup(radios);

		playXTimesField = new TextField();
		playXTimesField.setPrefWidth(40);
		playXTimesField.setDisable(true);

		playButton = new Button("Play");
		playButton.setTranslateX(7);

		playGridPane.add(playOnceRadio, 0, 0);
		playGridPane.add(playXTimesRadio, 0, 2);
		playGridPane.add(playXTimesField, 2, 2);
		playGridPane.add(playUltilStopRadio, 0, 4);
		playGridPane.add(playButton, 36, 4);

		GridPane.setColumnSpan(playUltilStopRadio, GridPane.REMAINING);

		//
		rightSideContents.getChildren().add(playGridPane);

		contents.getChildren().add(rightSideContents);
	}

	public void addTableRow(KeyEvent key) {
		if (this.currentProfile == null) {
			Alert alert = new Alert(AlertType.ERROR, "Please select a profile", ButtonType.OK);
			alert.show();
		} else {
			this.currentProfile.addKey(key);
		}
	}

	private void setKeyInfoOnGrid(KeyEvent key) {
		if (key != null) {
			editingKey = key;
			keyName.setText(key.getKeyName().get());
			keyDuration.setText(key.getKeyDuration().get() + "ms");
		} else {
			keyName.setText("");
			keyDuration.setText("");
		}
	}

	public void updateProfile() {
		this.keyTable.setItems(currentProfile.getItems());
	}

	private void loadEvents() {
		// left
		addKeyButton.setOnAction((e) -> {
			AddKeyWindow window = new AddKeyWindow();
			window.display(this);
		});

		recordKeySequence.setOnAction((e) -> {
			RecordKeysWindow window = new RecordKeysWindow(this);
			window.display();
		});

		// top right
		keyDelete.setOnAction((e) -> {
			this.currentProfile.removeKey(editingKey);
		});

		keySave.setOnAction((e) -> {
			try {
				editingKey.setDuration(Integer.valueOf(keyDuration.getText().replaceAll("ms", "")));
				keyTable.refresh();
			} catch (NumberFormatException exc) {
				Alert alert = new Alert(AlertType.ERROR, "Invalid number  Eg: (15, 15ms)", ButtonType.CLOSE);
				alert.show();
			}

		});

		// bottom right
		profiles.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.intValue() != -1) {
				this.currentProfile = profileList.get(newValue.intValue());
				updateProfile();
			}

		});

		newProfileButton.setOnAction((e) -> {
			if (profileName.getText().trim().isEmpty()) {
				profileName.setStyle("-fx-border-color: red; -fx-border-radius: 4px");
			} else {
				profileName.setStyle("");
				if (profiles.getItems().size() != 0) {
					boolean found = false;
					for (KeyProfile profile : profileList) {
						if (profileName.getText().toLowerCase().trim().equals(profile.getName().toLowerCase())) {
							Alert alert = new Alert(AlertType.WARNING, "There is already a profile with this name.",
									ButtonType.CLOSE);
							alert.show();
							found = true;
						}
					}
					if (!found) {
						KeyProfile newProfile = new KeyProfile(profileName.getText().trim());
						Platform.runLater(() -> {
							profileList.add(newProfile);
							profiles.getSelectionModel().selectLast();
						});
						profileName.setText("");
					}
				} else {
					KeyProfile newProfile = new KeyProfile(profileName.getText().trim());
					Platform.runLater(() -> {
						profileList.add(newProfile);
						profiles.getSelectionModel().selectLast();
					});
					profileName.setText("");
				}
			}
		});

		deleteProfileButton.setOnAction((e) -> {
			Alert alert = new Alert(AlertType.CONFIRMATION,
					"Are you sure you want to delete this profile? (It Will delete the save)");
			alert.setHeaderText("Delete Profile (permanent)");

			alert.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
				if (profileList.size() > 0) {
					try {
						Files.deleteIfExists(Paths.get(
								ProfileManager.createFolder().getPath() + "/" + currentProfile.getName() + ".atk"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					profileList.remove(currentProfile);
					profiles.getSelectionModel().selectNext();
					updateProfile();
				}
			});
			alert.show();
		});

		saveProfileButton.setOnAction((e) -> {
			ProfileManager.saveProfiles(profileList);
		});

		loadProfileButton.setOnAction((e) -> {
			profileList.setAll(ProfileManager.loadProfiles());
			profiles.getSelectionModel().selectFirst();
			updateProfile();
		});

		// play box
		radios.selectedToggleProperty().addListener((observable, oldVale, newValue) -> {
			if (newValue.equals(playOnceRadio)) {
				playXTimesField.setDisable(true);

			} else if (newValue.equals(playXTimesRadio)) {
				playXTimesField.setDisable(false);
			} else if (newValue.equals(playUltilStopRadio)) {
				playXTimesField.setDisable(true);

			}
		});

		playButton.setOnAction((e) -> {
			PlayKeyProfile.playProfile(this, currentProfile);
		});
	}

	public int getPlayMode() {
		if (radios.getSelectedToggle().toString().contains("once")) {
			return 1;
		} else if (radios.getSelectedToggle().toString().contains("times")) {
			try {
				return Integer.valueOf(playXTimesField.getText());
			} catch (Exception e) {
				return 1;
			}
		} else if (radios.getSelectedToggle().toString().contains("again")) {
			return 0;
		}
		return 1;
	}

	public KeyProfile getCurrentProfile() {
		return this.currentProfile;
	}

}

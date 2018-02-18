package com.harystolho;

import com.harystolho.key.AddKeyWindow;
import com.harystolho.key.KeyEvent;
import com.harystolho.key.KeyProfile;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

	private TextField keyName;
	private TextField keyDuration;

	private ChoiceBox<KeyProfile> profiles;
	private TextField profileName;
	private Button newProfileButton;
	private Button loadProfileButton;
	private Button saveProfileButton;

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
		contents.setPadding(new Insets(10));
		loadLeftSide(contents);
		loadRightSide(contents);

		scene = new Scene(contents);
	}

	private void loadLeftSide(HBox contents) {
		VBox leftSideContents = new VBox();

		keyTable = new TableView<>();
		keyTable.setEditable(false);
		keyTable.setPrefWidth(WIDTH * 0.305);
		keyTable.setPrefHeight(HEIGHT * 0.8);

		keyTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> setKeyInfoOnGrid(newValue));

		timeColumn = new TableColumn<>("Time");
		keyColumn = new TableColumn<>("Key");

		keyColumn.setPrefWidth(WIDTH * 0.2);
		timeColumn.setPrefWidth(WIDTH * 0.1);

		timeColumn.setSortable(false);
		keyColumn.setSortable(false);

		timeColumn.setCellValueFactory((cellData) -> cellData.getValue().getKeyDuration());
		keyColumn.setCellValueFactory((cellData) -> cellData.getValue().getKeyName());

		keyTable.getColumns().addAll(timeColumn, keyColumn);

		// Add Key Button
		addKeyButton = new Button("New Key");
		addKeyButton.setTranslateY(7);
		//

		leftSideContents.getChildren().addAll(keyTable, addKeyButton);
		leftSideContents.setAlignment(Pos.TOP_RIGHT);
		contents.getChildren().add(leftSideContents);
	}

	private void loadRightSide(HBox contents) {
		VBox rightSideContents = new VBox();
		rightSideContents.setTranslateX(50);

		//
		GridPane topGridPane = new GridPane();
		topGridPane
				.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
		// topGridPane.setGridLinesVisible(true);
		topGridPane.setHgap(20);
		topGridPane.setVgap(20);

		keyName = new TextField("");
		keyName.setMaxWidth(150);
		keyName.setEditable(false);
		Button keyDelete = new Button("X");
		keyDelete.setTranslateX(30);
		keyDuration = new TextField();
		keyDuration.setMaxWidth(70);
		Button keySave = new Button("Save key");

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
		loadProfileButton = new Button("Load Profile");
		saveProfileButton = new Button("Save Profile");

		bottomGridPane.add(profiles, 0, 0);
		bottomGridPane.add(profileName, 0, 1);
		bottomGridPane.add(newProfileButton, 1, 1);
		bottomGridPane.add(loadProfileButton, 15, 2);
		bottomGridPane.add(saveProfileButton, 15, 3);

		GridPane.setColumnSpan(newProfileButton, GridPane.REMAINING);
		//
		rightSideContents.getChildren().add(bottomGridPane);

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
		//

		// top right
		//

		// bottom right
		profiles.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			this.currentProfile = profileList.get(newValue.intValue());
			updateProfile();
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
						});
						profileName.setText("");
					}
				} else {
					KeyProfile newProfile = new KeyProfile(profileName.getText().trim());
					Platform.runLater(() -> {
						profileList.add(newProfile);
					});
					profileName.setText("");
				}
			}
		});

		saveProfileButton.setOnAction((e) -> {
			ProfileManager.saveProfiles(profileList);
		});

	}

}

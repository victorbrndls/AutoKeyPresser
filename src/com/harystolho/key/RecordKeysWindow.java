package com.harystolho.key;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecordKeysWindow {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;

	private HashMap<KeyCode, Long> pressedKeys;
	private ObservableList<String> pressedkeysList;

	private boolean recording;

	private CheckBox recordDelay;
	private TextField defaultDelay;
	private ListView<String> keysList;
	private Button recordButton;
	private Button saveButton;

	public RecordKeysWindow() {

	}

	public void display() {
		Stage window = new Stage();
		window.setTitle("Record Key Window");
		window.setWidth(WIDTH);
		window.setHeight(HEIGHT);

		pressedKeys = new HashMap<>();
		pressedkeysList = FXCollections.observableArrayList();
		recording = false;

		//
		VBox contents = new VBox();
		contents.setAlignment(Pos.TOP_CENTER);

		recordDelay = new CheckBox("Record Delay ?");
		recordDelay.setSelected(true);
		recordDelay.setTranslateY(9);
		defaultDelay = new TextField();
		defaultDelay.setDisable(true);
		defaultDelay.setPromptText("Default delay (ms)");
		defaultDelay.setTranslateY(5);
		defaultDelay.setTranslateX(15);
		keysList = new ListView<>();
		keysList.setItems(pressedkeysList);
		keysList.setMaxWidth(WIDTH * 0.5);
		keysList.setMaxHeight(HEIGHT * 0.7);
		keysList.setTranslateY(20);
		recordButton = new Button("RECORD");
		recordButton.setTranslateY(30);
		saveButton = new Button("Save");
		saveButton.setTranslateY(35);

		HBox delayBox = new HBox();
		delayBox.setAlignment(Pos.TOP_CENTER);
		delayBox.getChildren().addAll(recordDelay, defaultDelay);

		contents.getChildren().addAll(delayBox, keysList, recordButton, saveButton);
		//

		loadEvents();

		Scene scene = new Scene(contents);

		keyMouseListeners(scene);

		window.setScene(scene);
		window.show();
	}

	private void loadEvents() {
		recordDelay.setOnAction((e) -> {
			if (recordDelay.isSelected()) {
				defaultDelay.setDisable(true);
			} else {
				defaultDelay.setDisable(false);
			}
		});

		recordButton.setOnAction((e) -> {
			if (recording) {
				recording = false;
				recordButton.setStyle("");
			} else {
				recording = true;
				recordButton.setStyle("-fx-background-color: red; -fx-border-radius: 4px");
			}
		});

	}

	private void keyMouseListeners(Scene scene) {
		scene.setOnKeyPressed((e) -> {
			if (recording) {
				pressedkeysList.add(KeyEvent.getKeyName(AddKeyWindow.getLetterKeyCode(e.getCode())) + " DOWN");
			}
		});

		scene.setOnKeyReleased((e) -> {

		});

		scene.setOnMousePressed((e) -> {
			if (recording) {
				switch (e.getButton()) {
				case PRIMARY:
					pressedkeysList.add(KeyEvent.getKeyName(1024) + " DOWN");
					break;
				case SECONDARY:
					pressedkeysList.add(KeyEvent.getKeyName(2048) + " DOWN");
					break;
				case MIDDLE:
					pressedkeysList.add(KeyEvent.getKeyName(4096) + " DOWN");
					break;
				default:
					break;
				}

			}
		});

		scene.setOnMouseReleased((e) -> {

		});
	}

}

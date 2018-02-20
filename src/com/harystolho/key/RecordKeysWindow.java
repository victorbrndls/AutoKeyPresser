package com.harystolho.key;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecordKeysWindow {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;

	private CheckBox recordDelay;
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

		//
		VBox contents = new VBox();
		contents.setAlignment(Pos.TOP_CENTER);

		recordDelay = new CheckBox("Record Delay ?");
		recordDelay.setTranslateY(5);
		keysList = new ListView<>();
		keysList.setMaxWidth(WIDTH * 0.5);
		keysList.setMaxHeight(HEIGHT * 0.7);
		keysList.setTranslateY(20);
		recordButton = new Button("RECORD");
		recordButton.setTranslateY(30);
		saveButton = new Button("Save");
		saveButton.setTranslateY(35);

		contents.getChildren().addAll(recordDelay, keysList, recordButton, saveButton);
		//

		loadEvents();

		Scene scene = new Scene(contents);

		window.setScene(scene);
		window.show();
	}

	private void loadEvents() {

	}

}

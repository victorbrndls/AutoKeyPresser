package com.harystolho.key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.harystolho.AutoPresserGUI;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddKeyWindow {

	private final static int WIDTH = 300;
	private final static int HEIGHT = 300;

	private Stage window;

	private CheckBox hasDelay;
	private TextField delayValue;
	private Label pressedKey;
	private Button recordButton;
	private Button saveButton;

	private boolean listening;

	public void display(AutoPresserGUI gui) {

		Stage window = new Stage();
		this.window = window;

		window.setTitle("New Key");
		window.setWidth(WIDTH);
		window.setHeight(HEIGHT);

		VBox contents = new VBox();
		contents.setAlignment(Pos.TOP_CENTER);
		contents.setPadding(new Insets(5));

		//
		hasDelay = new CheckBox("Record Delay? (TODO)");
		// hasDelay.setSelected(true)
		hasDelay.setDisable(true);
		//

		//
		HBox delayBox = new HBox();
		delayBox.setTranslateY(10);
		delayBox.setAlignment(Pos.CENTER);

		Label delayLabel = new Label("Delay (ms)");
		delayValue = new TextField();
		delayValue.setTranslateX(5);
		// delayValue.setDisable(true);

		delayBox.getChildren().addAll(delayLabel, delayValue);
		//

		//
		pressedKey = new Label();
		pressedKey.setTranslateY(20);
		//

		//
		recordButton = new Button("Record");
		recordButton.setTranslateY(HEIGHT * 0.43);
		listening = false;
		//

		//
		saveButton = new Button("Save");
		saveButton.setTranslateY(HEIGHT * 0.48);
		//

		contents.getChildren().addAll(hasDelay, delayBox, pressedKey, recordButton, saveButton);

		Scene scene = new Scene(contents);

		keyHandler(scene);

		loadEvents();

		window.setScene(scene);
		window.showAndWait();
	}

	private void loadEvents() {
		hasDelay.setOnAction((e) -> {
			/*
			 * if (hasDelay.isSelected()) { delayValue.setDisable(true); } else {
			 * delayValue.setDisable(false); }
			 */
		});

		recordButton.setOnAction((e) -> {
			if (listening) {
				listening = false;
				recordButton.setStyle("");
			} else {
				listening = true;
				recordButton.setStyle("-fx-border-color: red; -fx-border-radius: 4px");
			}

		});

		saveButton.setOnAction((e) -> {

		});

	}

	private void keyHandler(Scene scene) {
		scene.setOnKeyPressed((e) -> {
			if (listening) {
				pressedKey.setText(getKeyCode(e.getCode()) + "");
			}
		});
		scene.setOnKeyReleased((e) -> {
			if (listening) {
				pressedKey.setText(getKeyCode(e.getCode()) + "");
			}
		});
	}

	public static int getKeyCode(KeyCode code) {
		switch (code) {
		case A:
			return 1;
		case B:
			return 1;
		case C:
			return 1;
		case D:
			return 1;
		case E:
			return 1;
		case F:
			return 1;
		case G:
			return 1;
		case H:
			return 1;
		case I:
			return 1;
		case J:
			return 1;
		case K:
			return 1;
		case L:
			return 1;
		case M:
			return 1;
		case N:
			return 1;
		case O:
			return 1;
		case P:
			return 1;
		case Q:
			return 1;
		case R:
			return 1;
		case S:
			return 1;
		case T:
			return 1;
		case U:
			return 1;
		case V:
			return 1;
		case W:
			return 1;
		case X:
			return 1;
		case Y:
			return 1;
		case Z:
			return 1;
		default:
			return 0;
		}
	}

}

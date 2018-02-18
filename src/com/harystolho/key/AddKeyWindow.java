package com.harystolho.key;

import java.awt.AWTException;
import java.awt.Robot;
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
	private AutoPresserGUI gui;

	private CheckBox hasDelay;
	private TextField delayValue;
	private Label pressedKey;
	private Button recordButton;
	private Button saveButton;

	private boolean listening;

	public void display(AutoPresserGUI gui) {
		this.gui = gui;

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
			listening = false;
			recordButton.setStyle("");
			if (delayValue.getText().isEmpty()) {
				delayValue.setStyle("-fx-border-color: red; -fx-border-radius: 4px");
			} else {
				delayValue.setStyle("");
				com.harystolho.key.KeyEvent key = new com.harystolho.key.KeyEvent(Integer.valueOf(pressedKey.getText()),
						Integer.valueOf(delayValue.getText()));
				gui.addTableRow(key);
				window.close();
			}
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
			return KeyEvent.VK_A;
		case B:
			return KeyEvent.VK_B;
		case C:
			return KeyEvent.VK_C;
		case D:
			return KeyEvent.VK_D;
		case E:
			return KeyEvent.VK_E;
		case F:
			return KeyEvent.VK_F;
		case G:
			return KeyEvent.VK_G;
		case H:
			return KeyEvent.VK_H;
		case I:
			return KeyEvent.VK_I;
		case J:
			return KeyEvent.VK_J;
		case K:
			return KeyEvent.VK_K;
		case L:
			return KeyEvent.VK_L;
		case M:
			return KeyEvent.VK_M;
		case N:
			return KeyEvent.VK_N;
		case O:
			return KeyEvent.VK_O;
		case P:
			return KeyEvent.VK_P;
		case Q:
			return KeyEvent.VK_Q;
		case R:
			return KeyEvent.VK_R;
		case S:
			return KeyEvent.VK_S;
		case T:
			return KeyEvent.VK_T;
		case U:
			return KeyEvent.VK_U;
		case V:
			return KeyEvent.VK_V;
		case W:
			return KeyEvent.VK_W;
		case X:
			return KeyEvent.VK_X;
		case Y:
			return KeyEvent.VK_Y;
		case Z:
			return KeyEvent.VK_Z;
		default:
			return 0;
		}
	}

}

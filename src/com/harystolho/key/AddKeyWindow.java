package com.harystolho.key;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import com.harystolho.AutoPresserGUI;

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
				recordButton.setText("Record");
				listening = false;
				recordButton.setStyle("");
			} else {
				recordButton.setText("Stop Recording");
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

				try {
					com.harystolho.key.KeyEvent keyDown = new com.harystolho.key.KeyEvent(
							getLetterKeyCode(KeyCode.valueOf(pressedKey.getText().toUpperCase())), 0, true);

					com.harystolho.key.KeyEvent keyUp = new com.harystolho.key.KeyEvent(
							getLetterKeyCode(KeyCode.valueOf(pressedKey.getText().toUpperCase())),
							Integer.valueOf(delayValue.getText()), false);

					gui.addTableRow(keyDown);
					gui.addTableRow(keyUp);
				} catch (IllegalArgumentException exc) {
					int buttonNum = getKeyCode(pressedKey.getText());

					com.harystolho.key.KeyEvent keyDown = new com.harystolho.key.KeyEvent(buttonNum, 0, true);
					com.harystolho.key.KeyEvent keyUp = new com.harystolho.key.KeyEvent(buttonNum,
							Integer.valueOf(delayValue.getText()), false);

					gui.addTableRow(keyDown);
					gui.addTableRow(keyUp);
				}

				window.close();
			}
		});
	}

	private void keyHandler(Scene scene) {
		scene.setOnKeyPressed((e) -> {
			if (listening) {
				pressedKey.setText(com.harystolho.key.KeyEvent.getKeyName(getLetterKeyCode(e.getCode())));
			}
		});
		scene.setOnKeyReleased((e) -> {
			if (listening) {
				// pressedKey.setText(com.harystolho.key.KeyEvent.getKeyName(getKeyCode(e.getCode())));
			}
		});
		scene.setOnMousePressed((e) -> {
			if (listening) {
				switch (e.getButton()) {
				case PRIMARY:
					pressedKey.setText("Left Mouse");
					break;
				case SECONDARY:
					pressedKey.setText("Right Mouse");
					break;
				case MIDDLE:
					pressedKey.setText("Middle Mouse");
					break;
				default:
					break;
				}
			}
		});
	}

	public static int getLetterKeyCode(KeyCode code) {
		HashMap<KeyCode, Integer> codes = new HashMap<>();

		System.out.println(code);

		codes.put(KeyCode.A, KeyEvent.VK_A);
		codes.put(KeyCode.B, KeyEvent.VK_B);
		codes.put(KeyCode.C, KeyEvent.VK_C);
		codes.put(KeyCode.D, KeyEvent.VK_D);
		codes.put(KeyCode.E, KeyEvent.VK_E);
		codes.put(KeyCode.F, KeyEvent.VK_F);
		codes.put(KeyCode.G, KeyEvent.VK_G);
		codes.put(KeyCode.H, KeyEvent.VK_H);
		codes.put(KeyCode.I, KeyEvent.VK_I);
		codes.put(KeyCode.J, KeyEvent.VK_J);
		codes.put(KeyCode.K, KeyEvent.VK_K);
		codes.put(KeyCode.L, KeyEvent.VK_L);
		codes.put(KeyCode.M, KeyEvent.VK_M);
		codes.put(KeyCode.N, KeyEvent.VK_N);
		codes.put(KeyCode.O, KeyEvent.VK_O);
		codes.put(KeyCode.P, KeyEvent.VK_P);
		codes.put(KeyCode.Q, KeyEvent.VK_Q);
		codes.put(KeyCode.R, KeyEvent.VK_R);
		codes.put(KeyCode.S, KeyEvent.VK_S);
		codes.put(KeyCode.T, KeyEvent.VK_T);
		codes.put(KeyCode.U, KeyEvent.VK_U);
		codes.put(KeyCode.V, KeyEvent.VK_V);
		codes.put(KeyCode.W, KeyEvent.VK_W);
		codes.put(KeyCode.X, KeyEvent.VK_X);
		codes.put(KeyCode.Y, KeyEvent.VK_Y);
		codes.put(KeyCode.Z, KeyEvent.VK_Z);
		codes.put(KeyCode.BACK_QUOTE, KeyEvent.VK_BACK_QUOTE);
		codes.put(KeyCode.DIGIT0, KeyEvent.VK_0);
		codes.put(KeyCode.DIGIT1, KeyEvent.VK_1);
		codes.put(KeyCode.DIGIT2, KeyEvent.VK_2);
		codes.put(KeyCode.DIGIT3, KeyEvent.VK_3);
		codes.put(KeyCode.DIGIT4, KeyEvent.VK_4);
		codes.put(KeyCode.DIGIT5, KeyEvent.VK_5);
		codes.put(KeyCode.DIGIT6, KeyEvent.VK_6);
		codes.put(KeyCode.DIGIT7, KeyEvent.VK_7);
		codes.put(KeyCode.DIGIT8, KeyEvent.VK_8);
		codes.put(KeyCode.DIGIT9, KeyEvent.VK_9);
		codes.put(KeyCode.MINUS, KeyEvent.VK_MINUS);
		codes.put(KeyCode.EQUALS, KeyEvent.VK_EQUALS);
		codes.put(KeyCode.BACK_SPACE, KeyEvent.VK_BACK_SPACE);

		codes.put(KeyCode.ESCAPE, KeyEvent.VK_ESCAPE);
		codes.put(KeyCode.F1, KeyEvent.VK_F1);
		codes.put(KeyCode.F2, KeyEvent.VK_F2);
		codes.put(KeyCode.F3, KeyEvent.VK_F3);
		codes.put(KeyCode.F4, KeyEvent.VK_F4);
		codes.put(KeyCode.F5, KeyEvent.VK_F5);
		codes.put(KeyCode.F6, KeyEvent.VK_F6);
		codes.put(KeyCode.F7, KeyEvent.VK_F7);
		codes.put(KeyCode.F8, KeyEvent.VK_F8);
		codes.put(KeyCode.F9, KeyEvent.VK_F9);
		codes.put(KeyCode.F10, KeyEvent.VK_F10);
		codes.put(KeyCode.F11, KeyEvent.VK_F11);
		codes.put(KeyCode.F12, KeyEvent.VK_F12);

		codes.put(KeyCode.TAB, KeyEvent.VK_TAB);
		codes.put(KeyCode.CAPS, KeyEvent.VK_CAPS_LOCK);
		codes.put(KeyCode.SHIFT, KeyEvent.VK_SHIFT);
		codes.put(KeyCode.CONTROL, KeyEvent.VK_CONTROL);

		return codes.get(code);

	}

	public static int getKeyCode(String key) {
		HashMap<String, Integer> maps = new HashMap<>();

		maps.put("Left Mouse", 1024);
		maps.put("Right Mouse", 2048);
		maps.put("Middle Mouse", 4096);
		maps.put("0", KeyEvent.VK_0);
		maps.put("1", KeyEvent.VK_1);
		maps.put("2", KeyEvent.VK_2);
		maps.put("3", KeyEvent.VK_3);
		maps.put("4", KeyEvent.VK_4);
		maps.put("5", KeyEvent.VK_5);
		maps.put("6", KeyEvent.VK_6);
		maps.put("7", KeyEvent.VK_7);
		maps.put("8", KeyEvent.VK_8);
		maps.put("9", KeyEvent.VK_9);
		maps.put("Backspace", KeyEvent.VK_BACK_SPACE);
		maps.put("CapsLock", KeyEvent.VK_CAPS_LOCK);
		maps.put("Ctrl", KeyEvent.VK_CONTROL);

		maps.put("Esc", KeyEvent.VK_ESCAPE);
		maps.put("`", KeyEvent.VK_BACK_QUOTE);

		return maps.get(key);
	}

}

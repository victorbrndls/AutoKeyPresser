package com.harystolho.key;

import com.harystolho.AutoPresserGUI;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import lc.kra.system.mouse.event.GlobalMouseListener;

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
		hasDelay = new CheckBox("Record Delay?");
		hasDelay.setSelected(true);
		//

		//
		HBox delayBox = new HBox();
		delayBox.setTranslateY(10);
		delayBox.setAlignment(Pos.CENTER);

		Label delayLabel = new Label("Delay (ms)");
		delayValue = new TextField();
		delayValue.setTranslateX(5);
		delayValue.setDisable(true);

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

		loadEvents();

		window.setScene(scene);
		window.showAndWait();
	}

	private void loadEvents() {
		hasDelay.setOnAction((e) -> {
			if (hasDelay.isSelected()) {
				delayValue.setDisable(true);
			} else {
				delayValue.setDisable(false);
			}
		});

		recordButton.setOnAction((e) -> {

			Thread t = new Thread(() -> {
				GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);
				GlobalMouseHook mouseHook = new GlobalMouseHook(true);

				keyboardHook.addKeyListener(new GlobalKeyListener() {

					@Override
					public void keyReleased(GlobalKeyEvent event) {
						switch (event.getVirtualKeyCode()) {
						case 16:
							// isShift = false;
							break;
						default:
							break;
						}
					}

					@Override
					public void keyPressed(GlobalKeyEvent event) {
						switch (event.getVirtualKeyCode()) {
						case 16:
							// isShift = true;
						}
						Platform.runLater(() -> {
							pressedKey.setText(event.getVirtualKeyCode() + "");
						});
					}
				});

				mouseHook.addMouseListener(new GlobalMouseListener() {

					@Override
					public void mouseWheel(GlobalMouseEvent arg0) {

					}

					@Override
					public void mouseReleased(GlobalMouseEvent arg0) {

					}

					@Override
					public void mousePressed(GlobalMouseEvent event) {
						if (event.getButton() == 1) {
							listening = false;
						} else {
							Platform.runLater(() -> {
								pressedKey.setText(event.getButton() + "");
							});
						}

					}

					@Override
					public void mouseMoved(GlobalMouseEvent arg0) {

					}
				});

				try {
					while (listening) {
						Thread.sleep(128);
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} finally {
					keyboardHook.shutdownHook();
					mouseHook.shutdownHook();
				}
			});

			if (listening) {
				listening = false;
				t.interrupt();
			} else {
				listening = true;
				t.start();
			}

		});

		saveButton.setOnAction((e) -> {

		});

	}

}

package com.harystolho;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AutoPresserGUI extends Application {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;

	Scene scene;

	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("AutoPresser");
		window.setWidth(WIDTH);
		window.setHeight(HEIGHT);

		loadGUIItems();

		window.setScene(scene);
		window.show();
	}

	public void startGUI(String[] args) {
		launch(args);
	}

	public void loadGUIItems() {
		HBox contents = new HBox();

		loadLeftSide();
		loadRightSide();

		scene = new Scene(contents);
	}

	public void loadLeftSide() {
		HBox leftSideContents = new HBox();
		
		
	}

	public void loadRightSide() {

	}
}

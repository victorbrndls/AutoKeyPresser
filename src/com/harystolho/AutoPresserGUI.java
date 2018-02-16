package com.harystolho;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AutoPresserGUI extends Application {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;

	private TableColumn<KeyEvent, String> timeColumn;
	private TableColumn<KeyEvent, String> keyColumn;

	private ObservableList<KeyEvent> keyList = FXCollections.observableArrayList();

	Scene scene;

	private Button addKeyButton;

	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("AutoPresser");
		window.setWidth(WIDTH);
		window.setHeight(HEIGHT);

		loadGUIItems();
		loadEvents();
		//
		KeyEvent k1 = new KeyEvent(15, 1);
		KeyEvent k2 = new KeyEvent(16, 5551);
		KeyEvent k3 = new KeyEvent(17, 1);
		addTableRow(k1);
		addTableRow(k2);
		//

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

		TableView<KeyEvent> keyTable = new TableView<>();
		keyTable.setEditable(false);
		keyTable.setPrefWidth(WIDTH * 0.305);
		keyTable.setPrefHeight(HEIGHT * 0.8);

		timeColumn = new TableColumn<>("Time");
		keyColumn = new TableColumn<>("Key");

		keyColumn.setPrefWidth(WIDTH * 0.2);
		timeColumn.setPrefWidth(WIDTH * 0.1);

		timeColumn.setSortable(false);
		keyColumn.setSortable(false);

		timeColumn.setCellValueFactory((cellData) -> cellData.getValue().getKeyDuration());
		keyColumn.setCellValueFactory((cellData) -> cellData.getValue().getKeyName());

		keyTable.getColumns().addAll(timeColumn, keyColumn);

		keyTable.setItems(keyList);

		// Add Key Button

		addKeyButton = new Button("New Key");
		addKeyButton.setTranslateY(7);

		//
		leftSideContents.getChildren().addAll(keyTable, addKeyButton);
		leftSideContents.setAlignment(Pos.TOP_RIGHT);
		contents.getChildren().add(leftSideContents);
	}

	private void loadRightSide(HBox contents) {

	}

	public void addTableRow(KeyEvent keyEvent) {
		this.keyList.add(keyEvent);
	}

	private void loadEvents() {
		addKeyButton.setOnAction((e) -> {
			AddKeyWindow.display(this);
		});
	}

}

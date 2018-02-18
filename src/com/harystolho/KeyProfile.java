package com.harystolho;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KeyProfile {

	private ObservableList<KeyEvent> keys;
	private String name;

	public KeyProfile(String name) {
		this.name = name;
		this.keys = FXCollections.observableArrayList();
	}

	public ObservableList<KeyEvent> getKeys() {
		return this.keys;
	}

	public void addKey(KeyEvent key) {
		this.keys.add(key);
	}

	public void removeKey(KeyEvent key) {
		this.keys.remove(key);
	}
}

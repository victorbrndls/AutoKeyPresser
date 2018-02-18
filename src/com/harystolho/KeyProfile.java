package com.harystolho;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KeyProfile implements Serializable {

	private ObservableList<KeyEvent> keys;
	private String name;

	public KeyProfile(String name) {
		this.name = name;
		this.keys = FXCollections.observableArrayList();
	}

	public ObservableList<KeyEvent> getItems() {
		return this.keys;
	}

	public void addKey(KeyEvent key) {
		this.keys.add(key);
	}

	public void removeKey(KeyEvent key) {
		this.keys.remove(key);
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

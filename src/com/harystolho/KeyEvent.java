package com.harystolho;

import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KeyEvent {

	private int keyCode;
	private int duration;
	private boolean isDown;

	private StringProperty keyName;

	public KeyEvent(int keyCode, int duration) {
		this.keyCode = keyCode;
		this.duration = duration;
		isDown = true;
		this.keyName = new SimpleStringProperty(getKeyName(keyCode));
	}

	public StringProperty getKeyName() {
		return keyName;
	}

	public StringProperty getKeyDuration() {
		// consider removing
		StringProperty dur = new SimpleStringProperty(String.valueOf(duration));
		return dur;
	}

	public boolean isKeyDown() {
		return this.isDown;
	}

	public void setIsDown(boolean isDown) {
		this.isDown = isDown;
	}

	@Override
	public KeyEvent clone() {
		KeyEvent key = new KeyEvent(this.keyCode, this.duration);
		return key;
	}

	public static String getKeyName(int keyCode) {
		HashMap<Integer, String> keyNames = new HashMap<>();

		keyNames.put(15, "Enter");
		keyNames.put(16, "Delete");
		keyNames.put(17, "Esc");

		return keyNames.get(keyCode);
	}

}

package com.harystolho;

import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KeyEvent {

	private int keyCode;
	private int duration;

	private StringProperty keyName;

	public KeyEvent(int keyCode, int duration) {
		this.keyCode = keyCode;
		this.duration = duration;
		this.keyName = new SimpleStringProperty(getKeyName(keyCode));
	}

	public StringProperty getKeyName() {
		return keyName;
	}

	public StringProperty getKeyDuration() {
		StringProperty dur = new SimpleStringProperty(String.valueOf(duration));
		return dur;
	}

	public static String getKeyName(int keyCode) {
		HashMap<Integer, String> keyNames = new HashMap<>();
		
		keyNames.put(15, "Enter");
		keyNames.put(16, "Delete");
		keyNames.put(17, "Esc");
		
		
		return keyNames.get(keyCode);
	}

}

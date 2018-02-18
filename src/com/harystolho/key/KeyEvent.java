package com.harystolho.key;

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

	public int getKeyCode() {
		return this.keyCode;
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

	@Override
	public int hashCode() {
		return 17 + this.keyCode * this.duration;
	}

	public static String getKeyName(int keyCode) {
		HashMap<Integer, String> keyNames = new HashMap<>();

		keyNames.put(java.awt.event.KeyEvent.VK_A, "A");
		keyNames.put(java.awt.event.KeyEvent.VK_B, "B");
		keyNames.put(java.awt.event.KeyEvent.VK_C, "C");
		keyNames.put(java.awt.event.KeyEvent.VK_D, "D");
		keyNames.put(java.awt.event.KeyEvent.VK_E, "E");
		keyNames.put(java.awt.event.KeyEvent.VK_F, "F");
		keyNames.put(java.awt.event.KeyEvent.VK_G, "G");
		keyNames.put(java.awt.event.KeyEvent.VK_H, "H");
		keyNames.put(java.awt.event.KeyEvent.VK_I, "I");
		keyNames.put(java.awt.event.KeyEvent.VK_J, "J");
		keyNames.put(java.awt.event.KeyEvent.VK_K, "K");
		keyNames.put(java.awt.event.KeyEvent.VK_L, "L");
		keyNames.put(java.awt.event.KeyEvent.VK_M, "M");
		keyNames.put(java.awt.event.KeyEvent.VK_N, "N");
		keyNames.put(java.awt.event.KeyEvent.VK_O, "O");
		keyNames.put(java.awt.event.KeyEvent.VK_P, "P");
		keyNames.put(java.awt.event.KeyEvent.VK_Q, "Q");
		keyNames.put(java.awt.event.KeyEvent.VK_R, "R");
		keyNames.put(java.awt.event.KeyEvent.VK_S, "S");
		keyNames.put(java.awt.event.KeyEvent.VK_T, "T");
		keyNames.put(java.awt.event.KeyEvent.VK_U, "U");
		keyNames.put(java.awt.event.KeyEvent.VK_V, "V");
		keyNames.put(java.awt.event.KeyEvent.VK_W, "W");
		keyNames.put(java.awt.event.KeyEvent.VK_X, "X");
		keyNames.put(java.awt.event.KeyEvent.VK_Y, "Y");
		keyNames.put(java.awt.event.KeyEvent.VK_Z, "Z");

		return keyNames.get(keyCode);
	}

}

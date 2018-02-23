package com.harystolho.key;

import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KeyEvent {

	private int keyCode;
	private int duration;
	private boolean isDown;

	private StringProperty keyName;

	public KeyEvent(int keyCode, int duration, boolean isDown) {
		this.keyCode = keyCode;
		this.duration = duration;
		this.isDown = isDown;
		this.keyName = new SimpleStringProperty(getKeyName(keyCode) + (isDown ? " DOWN" : " UP"));
	}

	public StringProperty getKeyName() {
		return keyName;
	}

	public StringProperty getKeyDuration() {
		// TODO consider removing
		StringProperty dur = new SimpleStringProperty(String.valueOf(duration));
		return dur;
	}

	public int getKeyDurationInt() {
		return this.duration;
	}

	public int getKeyCode() {
		return this.keyCode;
	}

	public boolean isKeyDown() {
		return this.isDown;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public KeyEvent clone() {
		KeyEvent key = new KeyEvent(this.keyCode, this.duration, this.isDown);
		return key;
	}

	@Override
	public int hashCode() {
		return 17 + this.keyCode * this.duration;
	}

	public static String getKeyName(int keyCode) {
		HashMap<Integer, String> keyNames = new HashMap<>();

		keyNames.put(java.awt.event.KeyEvent.VK_A, "a");
		keyNames.put(java.awt.event.KeyEvent.VK_B, "b");
		keyNames.put(java.awt.event.KeyEvent.VK_C, "c");
		keyNames.put(java.awt.event.KeyEvent.VK_D, "d");
		keyNames.put(java.awt.event.KeyEvent.VK_E, "e");
		keyNames.put(java.awt.event.KeyEvent.VK_F, "f");
		keyNames.put(java.awt.event.KeyEvent.VK_G, "g");
		keyNames.put(java.awt.event.KeyEvent.VK_H, "h");
		keyNames.put(java.awt.event.KeyEvent.VK_I, "i");
		keyNames.put(java.awt.event.KeyEvent.VK_J, "j");
		keyNames.put(java.awt.event.KeyEvent.VK_K, "k");
		keyNames.put(java.awt.event.KeyEvent.VK_L, "l");
		keyNames.put(java.awt.event.KeyEvent.VK_M, "m");
		keyNames.put(java.awt.event.KeyEvent.VK_N, "n");
		keyNames.put(java.awt.event.KeyEvent.VK_O, "o");
		keyNames.put(java.awt.event.KeyEvent.VK_P, "p");
		keyNames.put(java.awt.event.KeyEvent.VK_Q, "q");
		keyNames.put(java.awt.event.KeyEvent.VK_R, "r");
		keyNames.put(java.awt.event.KeyEvent.VK_S, "s");
		keyNames.put(java.awt.event.KeyEvent.VK_T, "t");
		keyNames.put(java.awt.event.KeyEvent.VK_U, "u");
		keyNames.put(java.awt.event.KeyEvent.VK_V, "v");
		keyNames.put(java.awt.event.KeyEvent.VK_W, "w");
		keyNames.put(java.awt.event.KeyEvent.VK_X, "x");
		keyNames.put(java.awt.event.KeyEvent.VK_Y, "y");
		keyNames.put(java.awt.event.KeyEvent.VK_Z, "z");

		keyNames.put(java.awt.event.KeyEvent.VK_0, "0");
		keyNames.put(java.awt.event.KeyEvent.VK_1, "1");
		keyNames.put(java.awt.event.KeyEvent.VK_2, "2");
		keyNames.put(java.awt.event.KeyEvent.VK_3, "3");
		keyNames.put(java.awt.event.KeyEvent.VK_4, "4");
		keyNames.put(java.awt.event.KeyEvent.VK_5, "5");
		keyNames.put(java.awt.event.KeyEvent.VK_6, "6");
		keyNames.put(java.awt.event.KeyEvent.VK_7, "7");
		keyNames.put(java.awt.event.KeyEvent.VK_8, "8");
		keyNames.put(java.awt.event.KeyEvent.VK_9, "9");
		keyNames.put(java.awt.event.KeyEvent.VK_MINUS, "Minus");
		keyNames.put(java.awt.event.KeyEvent.VK_EQUALS, "Equals");
		keyNames.put(java.awt.event.KeyEvent.VK_BACK_SPACE, "Backspace");

		keyNames.put(java.awt.event.KeyEvent.VK_ESCAPE, "Esc");
		keyNames.put(java.awt.event.KeyEvent.VK_F1, "F1");
		keyNames.put(java.awt.event.KeyEvent.VK_F2, "F2");
		keyNames.put(java.awt.event.KeyEvent.VK_F3, "F3");
		keyNames.put(java.awt.event.KeyEvent.VK_F4, "F4");
		keyNames.put(java.awt.event.KeyEvent.VK_F5, "F5");
		keyNames.put(java.awt.event.KeyEvent.VK_F6, "F6");
		keyNames.put(java.awt.event.KeyEvent.VK_F7, "F7");
		keyNames.put(java.awt.event.KeyEvent.VK_F8, "F8");
		keyNames.put(java.awt.event.KeyEvent.VK_F9, "F9");
		keyNames.put(java.awt.event.KeyEvent.VK_F10, "F10");
		keyNames.put(java.awt.event.KeyEvent.VK_F11, "F11");
		keyNames.put(java.awt.event.KeyEvent.VK_F12, "F12");

		keyNames.put(java.awt.event.KeyEvent.VK_BACK_QUOTE, "`");

		keyNames.put(java.awt.event.KeyEvent.VK_TAB, "Tab");
		keyNames.put(java.awt.event.KeyEvent.VK_CAPS_LOCK, "CapsLock");
		keyNames.put(java.awt.event.KeyEvent.VK_SHIFT, "Shift");
		keyNames.put(java.awt.event.KeyEvent.VK_CONTROL, "Ctrl");
		keyNames.put(java.awt.event.KeyEvent.VK_ALT, "Alt");
		keyNames.put(java.awt.event.KeyEvent.VK_WINDOWS, "Windows");
		keyNames.put(java.awt.event.KeyEvent.VK_OPEN_BRACKET, "{");
		keyNames.put(java.awt.event.KeyEvent.VK_CLOSE_BRACKET, "}");
		keyNames.put(java.awt.event.KeyEvent.VK_SEMICOLON, ";");
		keyNames.put(java.awt.event.KeyEvent.VK_QUOTE, "'");
		keyNames.put(java.awt.event.KeyEvent.VK_BACK_SLASH, "\\");
		keyNames.put(java.awt.event.KeyEvent.VK_ENTER, "Enter");
		keyNames.put(java.awt.event.KeyEvent.VK_COMMA, ",");
		keyNames.put(java.awt.event.KeyEvent.VK_PERIOD, ".");
		keyNames.put(java.awt.event.KeyEvent.VK_SLASH, "/");

		keyNames.put(1024, "Left Mouse Click");
		keyNames.put(2048, "Right Mouse Click");
		keyNames.put(4096, "Middle Mouse Click");

		return keyNames.get(keyCode);
	}

}

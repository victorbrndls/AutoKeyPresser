package com.harystolho;

import java.awt.AWTException;
import java.awt.Robot;

import com.harystolho.key.KeyEvent;
import com.harystolho.key.KeyProfile;

public class PlayKeyProfile {

	private static boolean playing = false;
	private static Robot robot;

	private PlayKeyProfile() {
	}

	public static void playProfile(AutoPresserGUI gui, KeyProfile profile) {
		int mode = gui.getPlayMode();

		if (robot == null) {
			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}

		if (mode >= 1) {
			playXTimes(mode, profile);
		} else {
			playUntilButtonClicked(profile);
		}

	}

	private static void playXTimes(int times, KeyProfile profile) {
		Thread t = new Thread(() -> {
			for (int x = 0; x < times; x++) {
				playKey(profile);
			}
		});

		t.start();

	}

	private static void playUntilButtonClicked(KeyProfile profile) {

		Thread t = new Thread(() -> {
			while (playing) {
				playKey(profile);
			}
		});

		if (playing) {
			playing = false;
		} else {
			playing = true;
			t.start();
		}

	}

	private static void playKey(KeyProfile profile) {
		for (KeyEvent key : profile.getItems()) {
			if (key.getKeyCode() > 1000) {
				try {
					Thread.sleep(key.getKeyDurationInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (key.isKeyDown()) {
					robot.mousePress(key.getKeyCode());
				} else {
					robot.mouseRelease(key.getKeyCode());
				}

			} else {
				try {
					Thread.sleep(key.getKeyDurationInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (key.isKeyDown()) {
					robot.keyPress(key.getKeyCode());
				} else {
					robot.keyRelease(key.getKeyCode());
				}
			}
		}
	}

}

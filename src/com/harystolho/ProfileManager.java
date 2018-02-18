package com.harystolho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfileManager {

	public static ObservableList<KeyProfile> loadProfiles() {
		ObservableList<KeyProfile> list = FXCollections.observableArrayList();

		for (File file : createFolder().listFiles()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				KeyProfile profile = (KeyProfile) ois.readObject();
				list.add(profile);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static void saveProfiles(ObservableList<KeyProfile> profiles) {
		File folder = createFolder();
		for (KeyProfile profile : profiles) {
			// check if characters are valid
			File file = new File(folder.getPath() + "/" + profile.getName() + ".key");
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				oos.writeObject(profile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static File createFolder() {
		File folder = new File("/profiles");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder;
	}

}

package com.harystolho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.harystolho.key.KeyEvent;
import com.harystolho.key.KeyProfile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfileManager {

	public static ObservableList<KeyProfile> loadProfiles() {
		ObservableList<KeyProfile> list = FXCollections.observableArrayList();

		for (File file : createFolder().listFiles()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				JSONObject json = new JSONObject((String) ois.readObject());
				KeyProfile profile = new KeyProfile(json.get("name") + "");

				JSONArray keys = new JSONArray(json.get("keys").toString());
				keys.forEach((item) -> {
					JSONObject key = new JSONObject(item.toString());
					KeyEvent savedKey = new KeyEvent(key.getInt("keyCode"), key.getInt("duration"));
					profile.addKey(savedKey);
				});

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
			File file = new File(folder.getPath() + "/" + profile.getName() + ".atk");
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				oos.writeObject(generateJSON(profile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static File createFolder() {
		File folder = new File("profiles");
		System.out.println(folder.getAbsolutePath());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder;
	}

	private static String generateJSON(KeyProfile profile) {
		JSONObject json = new JSONObject();
		json.put("name", profile.getName());

		JSONArray keys = new JSONArray();

		for (KeyEvent key : profile.getItems()) {
			JSONObject keyJSON = new JSONObject();
			keyJSON.put("keyCode", key.getKeyCode());
			keyJSON.put("duration", Integer.valueOf(key.getKeyDuration().get()));

			keys.put(keyJSON);
		}

		json.put("keys", keys);

		return json.toString();
	}

}

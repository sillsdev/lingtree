/**
 * Copyright (c) 2024 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.utility.service.keyboards;

import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;

import org.sil.utility.service.keyboards.KeyboardHandler;
import org.sil.utility.service.keyboards.KeyboardInfo;
import org.sil.utility.service.keyboards.LinuxKeyboardHandler;
import org.sil.utility.service.keyboards.MacOSXKeyboardHandler;
import org.sil.utility.service.keyboards.WindowsKeyboardHandler;

/**
 * @author Andy Black
 * Singleton pattern
 * 
 */
public class KeyboardChanger {

	private static KeyboardChanger instance;
	static final String COULD_NOT_CHANGE_KEYBOARD = "Could not change keyboard to ";
	static final String KEYBOARD_ID = "; id=";

	public static KeyboardChanger getInstance(){
        if(instance == null){
            instance = new KeyboardChanger();
        }
        return instance;
    }
    
	KeyboardHandler keyboardHandler = new KeyboardHandler();
	LinuxKeyboardHandler linuxHandler = new LinuxKeyboardHandler();
	MacOSXKeyboardHandler macHandler = new MacOSXKeyboardHandler();
	WindowsKeyboardHandler winHandler = new WindowsKeyboardHandler();
	List<KeyboardInfo> activeKeyboards = new ArrayList<KeyboardInfo>();
	int numberOfKeyboards = 0;
	Stage stage;

	public List<KeyboardInfo> getActiveKeyboards() {
		return activeKeyboards;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void initKeyboardHandler() {
		String sOperatingSystem = System.getProperty("os.name");
		if (sOperatingSystem.toLowerCase().contains("windows")) {
			keyboardHandler = winHandler;
		} else if (sOperatingSystem.toLowerCase().contains("mac")) {
			keyboardHandler = macHandler;
		} else {
			keyboardHandler = linuxHandler;
		}
		activeKeyboards = keyboardHandler.getAvailableKeyboards();
		numberOfKeyboards = activeKeyboards.size();
		keyboardHandler.rememberCurrentKeyboard();
	}

	public void closeKeyboardHandler() {
		keyboardHandler.restoreCurrentKeyboard();
	}

	public void tryToChangeKeyboardTo(KeyboardInfo ki) {
		if (activeKeyboards.size() == 0) {
			return;  // nothing to do
		}
		if (keyboardHandler == winHandler) {
			if (ki.getWindowsLangID() == 0) {
				// always use the first keyboard if the ID is zero
				ki = activeKeyboards.get(0);
			} else {
				ki = new KeyboardInfo(ki.getLocale(),
						ki.getDescription(), ki.getWindowsLangID());
			}
			boolean result = winHandler.changeToKeyboard(ki, stage);
			if (!result) {
				System.out.println(COULD_NOT_CHANGE_KEYBOARD + ki.getDescription() + KEYBOARD_ID + ki.getWindowsLangID());
			}
		} else if (keyboardHandler == macHandler) {
			boolean result = macHandler.changeToKeyboard(ki, stage);
			if (!result) {
				System.out.println(COULD_NOT_CHANGE_KEYBOARD + ki.getDescription() + "; (" + ki.getMacDescription() + ")");
			}
		}
	}
}

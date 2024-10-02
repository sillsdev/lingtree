/**
 * Copyright (c) 2022-2024 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.utility.service.keyboards;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import javafx.stage.Stage;

public class FXWinUtil {

	// Following code comes from 
	// https://stackoverflow.com/questions/15034407/how-can-i-get-the-window-handle-hwnd-for-a-stage-in-javafx
	public static Pointer getNativeHandleForStage(Stage stage) {
		try {
			WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, stage.getTitle());
			return hwnd.getPointer();
		} catch (Exception ex) {
			System.err.println("Unable to determine native handle for window");
			return null;
		}
	}
}
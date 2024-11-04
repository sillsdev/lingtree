/**
 * Copyright (c) 2022-2024 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.utility.service.keyboards;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.sil.lingtree.MainApp;
import org.sil.utility.view.ControllerUtilities;

import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class MacOSXKeyboardHandler extends KeyboardHandler {

	String sCurrentKeyboardName = "";
	final String kXkbSwitch = "/resources/Keyboards/macOS/xkbswitch";

	@Override
	public boolean changeToKeyboard(KeyboardInfo keyboard, Stage stage) {
		StringBuilder sb = new StringBuilder();
		sb.append(getShellCommandBeginning());
		sb.append(" -se ");
		sb.append(keyboard.getMacDescription());
		final String command = sb.toString();
		MainApp.showDebugMessage(command);
		return invokeTerminalCommand(command);
	}

	protected String getShellCommandBeginning() {
		String sLocation = ControllerUtilities.getUriOfProgram();
		String sCommandBeginning = sLocation.substring(5) + kXkbSwitch;
		return sCommandBeginning;
	}

	@Override
	public List<KeyboardInfo> getAvailableKeyboards() {
		List<KeyboardInfo> results = new ArrayList<KeyboardInfo>();
		String[] sLangIDs = new String[100];
		int iCount = getCurrentMacOSXKeyboardIDs(sLangIDs);
		for (int i = 0; i < iCount; i++) {
			KeyboardInfo info = getKeyboardInfoFromKeyboardName(sLangIDs[i]);
			if (info != null) {
				results.add(info);
			}
		}
		return results;
	}

	public KeyboardInfo getKeyboardInfoFromKeyboardName(String imName) {
		if (imName == null)
			return null;
		int idEndIndex = imName.indexOf("|||");
		if (idEndIndex == -1) {
			return null;
		}
		String macDescription = imName.substring(0, idEndIndex);
		if (macDescription.equals("com.apple.CharacterPaletteIM") || macDescription.equals("com.apple.KeyboardViewer")) {
			return null;
		}
		String description = "";
		if (macDescription.equals("keyman.inputmethod.Keyman")) {
			// TODO: handle Keyman situation, whatever that is...
			description = "Keyman keyboard";
			// Keyman on Mac OSX does not work with Java (yet)
			return null;
		} else {
			description = imName.substring(idEndIndex+3);
		}

		KeyboardInfo info = new KeyboardInfo(macDescription, description);
		return info;
	}

	@Override
	public String getExceptionContentMessage() {
		return super.getExceptionContentMessage();
	}

	@Override
	public void setEmailSupportAddress(String emailAddress) {
		// TODO Auto-generated method stub
	}

	@Override
	public void rememberCurrentKeyboard() {
		StringBuilder sb = new StringBuilder();
		sb.append(getShellCommandBeginning());
		sb.append(" -ge");

		final String command = sb.toString();
		MainApp.showDebugMessage(command);
		invokeTerminalCommand(command);
		try {
			final Process process = Runtime.getRuntime().exec(command);
			final InputStream in = process.getInputStream();
			StringBuilder sbs = new StringBuilder();
			int ch;
			while ((ch = in.read()) != -1) {
				if (ch != 13) {
					sbs.append((char)ch);
				}
			}
			int result = process.waitFor();
			if (result != 0) {
				System.out.println("KeyboardHandler.getCurrentEnabledKeyboardIDs() process result wasn't zero; it was " + result);
			}
			sCurrentKeyboardName = sbs.toString();
		} catch (IOException e) {
			ControllerUtilities.showExceptionInErrorDialog(e, sTitle, sHeader, sContent, sLabel);
			e.printStackTrace();
		} catch (InterruptedException e) {
			ControllerUtilities.showExceptionInErrorDialog(e, sTitle, sHeader, sContent, sLabel);
			e.printStackTrace();
		}
	}

	@Override
	public void restoreCurrentKeyboard() {
		StringBuilder sb = new StringBuilder();
		sb.append(getShellCommandBeginning());
		sb.append(" -se ");
		sb.append(sCurrentKeyboardName);
		final String command = sb.toString();
		invokeTerminalCommand(command);
	}

	protected int getCurrentMacOSXKeyboardIDs(String[] sLangIDs) {
		StringBuilder sb = new StringBuilder();
		sb.append(getShellCommandBeginning());
		sb.append(" -l");
		final String command = sb.toString();
		return getCurrentEnabledKeyboardIDs(command, sLangIDs);
	}

}

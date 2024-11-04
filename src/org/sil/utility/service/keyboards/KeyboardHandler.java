/**
 * Copyright (c) 2022-2024 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.utility.service.keyboards;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sil.utility.view.ControllerUtilities;

import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class KeyboardHandler {

	protected final String sTitle = "Error Found!";
	protected final String sHeader = "A serious error happened.";
	protected final String sContent1 = "Please copy the exception information below, email it to ";
	protected final String sContent2 = " along with a description of what you were doing.";
	protected final String sLabel = "The exception stacktrace was:";
	String sEmailSupportAddress = "Language_Software_Support@sil.org";
	protected String sContent = sContent1 + sEmailSupportAddress + sContent2;

	public boolean changeToKeyboard(KeyboardInfo keyboard, Stage stage) {
		return false;
	}

	public List<KeyboardInfo> getAvailableKeyboards() {
		return new ArrayList<KeyboardInfo>();
	}
	
	public String getExceptionContentMessage() {
		return sContent;
	}
	
	public void setEmailSupportAddress(String emailAddress) {
		sContent = emailAddress;
	}

	public void rememberCurrentKeyboard() {
		// do nothing by default;
	}

	public void restoreCurrentKeyboard() {
		// do nothing by default;
	}

	protected int getCurrentEnabledKeyboardIDs(String command, String[] sLangIDs) {
		int iCount = 0;
		try {
			final Process process = Runtime.getRuntime().exec(command);
			final BufferedReader in = process.inputReader(StandardCharsets.UTF_8);
			StringBuilder sbs = new StringBuilder();
			int ch;
			while ((ch = in.read()) != -1) {
				if (ch == 10) {
					sLangIDs[iCount] = sbs.toString();
					sbs = new StringBuilder();
					iCount++;
				} else if (ch != 13) {
					sbs.append((char)ch);
				}
			}
			int result = process.waitFor();
			if (result != 0) {
				System.out.println("KeyboardHandler.getCurrentEnabledKeyboardIDs() process result wasn't zero; it was " + result);
			}
		} catch (IOException e) {
			ControllerUtilities.showExceptionInErrorDialog(e, sTitle, sHeader, sContent, sLabel);
			e.printStackTrace();
		} catch (InterruptedException e) {
			ControllerUtilities.showExceptionInErrorDialog(e, sTitle, sHeader, sContent, sLabel);
			e.printStackTrace();
		}
		return iCount;
	}

	protected boolean invokeTerminalCommand(String command) {
		boolean succeeded = false;
		try {
			final Process process = Runtime.getRuntime().exec(command);
			int result = process.waitFor();
			if (result != 0) {
				System.out.println("KeyboardHandler.invokeCommand() process result wasn't zero; it was " + result);
			}
			succeeded = (result == 0);
		} catch (IOException e) {
			ControllerUtilities.showExceptionInErrorDialog(e, sTitle, sHeader, sContent, sLabel);
			e.printStackTrace();
		} catch (InterruptedException e) {
			ControllerUtilities.showExceptionInErrorDialog(e, sTitle, sHeader, sContent, sLabel);
			e.printStackTrace();
		}
		return succeeded;
	}

	public KeyboardInfo getKeyboardInfoBasedOn(List<KeyboardInfo> availableKeyboards, String sLocale,
			String description, int langId) {
		KeyboardInfo info = null;
		Optional<KeyboardInfo> optInfo = availableKeyboards
				.stream()
				.filter(k -> k.getSLocale().equals(sLocale) && k.getDescription().equals(description)
						&& k.getWindowsLangID() == langId).findFirst();
		if (optInfo.isPresent()) {
			return optInfo.get();
		} else {
			info = new KeyboardInfo(sLocale, description, langId);
		}
		return info;
	}

}

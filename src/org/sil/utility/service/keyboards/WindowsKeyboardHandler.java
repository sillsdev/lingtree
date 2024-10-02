/**
 * Copyright (c) 2022-2024 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.utility.service.keyboards;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sil.utility.view.ControllerUtilities;

import javafx.stage.Stage;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

/**
 * @author Andy Black
 *
 */
public class WindowsKeyboardHandler extends KeyboardHandler {

    public interface WinUserLibrary extends Library {
        WinUserLibrary INSTANCE = (WinUserLibrary)
            Native.load("user32", WinUserLibrary.class);

        boolean PostMessageW(Pointer hWnd, int msg, int wparam, int lparam);
    }

    Pointer hWnd;
    final String notKeymanKeyboard = "??";
    
	@Override
	public boolean changeToKeyboard(KeyboardInfo info, Stage stage) {
		if (hWnd == null) {
			hWnd = FXWinUtil.getNativeHandleForStage(stage);
		}
		int langId = info.getWindowsLangID();
		if (langId == 0) {
			return false;
		}
		final int WM_INPUTLANGCHANGEREQUEST = 0x0050; 
		boolean result = WinUserLibrary.INSTANCE.PostMessageW(hWnd, WM_INPUTLANGCHANGEREQUEST, 0, langId);
		return result;
	}

	@Override
	public void setEmailSupportAddress(String emailAddress) {
		sContent = sContent1 + emailAddress + sContent2;	
	}
	
	protected String findLangIdAsKeymanKeyboard(int langId) {
		final String KEYMAN_REGISTRY = "SOFTWARE\\Keyman\\Keyman Engine\\Active Keyboards";
		if (Advapi32Util.registryKeyExists(WinReg.HKEY_CURRENT_USER, KEYMAN_REGISTRY)) {
			String folders[] = Advapi32Util.registryGetKeys(WinReg.HKEY_CURRENT_USER, KEYMAN_REGISTRY);
			for (String s : folders) {
				String[] subfolders = Advapi32Util.registryGetKeys(WinReg.HKEY_CURRENT_USER, KEYMAN_REGISTRY + "\\" + s);
				for (String sub: subfolders) {
					TreeMap<String,Object> languages = Advapi32Util.registryGetValues(WinReg.HKEY_CURRENT_USER, KEYMAN_REGISTRY + "\\" + s + "\\" + sub);
					for (Map.Entry<String, Object> entry : languages.entrySet()) {
						String key = entry.getKey();
						Object obj = entry.getValue();
						if (obj instanceof String) {
							String data = (String) obj;
							int thisLangId = -1;
							int indexOfColon = data.indexOf(":");
							if (indexOfColon > -1) {
								String sLangId = data.substring(0, indexOfColon);
								thisLangId = Integer.parseInt(sLangId, 16);
							}
							if (thisLangId == langId) {
								// now we know that the langId is for this one; get its display info
								String sResult = findKeymanKeyboardDisplayInfo(s, key);
								return sResult;
							}
						}
					}
				}
			}
		}
		return notKeymanKeyboard;
	}

	protected String findKeymanKeyboardDisplayInfo(String s, String key) {
		final String KEYMAN_PROGRAM_DATA = "C:/ProgramData/Keyman/Keyman Engine/Keyboard/_Package/";
		String sResult = s + "; " + key;
		File json = new File(KEYMAN_PROGRAM_DATA + s + "/kmp.json");
		if (json.exists() && !json.isDirectory()) {
			try {
				String sKeyboardName = "";
				String sKeyboardMaker = "";
				String sJson = new String(Files.readAllBytes(json.toPath()), StandardCharsets.UTF_8);
				JSONParser parser = new JSONParser();
				Object parseObj = parser.parse(sJson);
				JSONObject jsonObject = (JSONObject)parseObj;
				JSONArray keyboards = (JSONArray)jsonObject.get("keyboards");
				if (keyboards != null) {
					JSONObject kb = (JSONObject)keyboards.get(0);
					sKeyboardMaker = (String)kb.get("name");
					JSONArray jLanguages = (JSONArray)kb.get("languages");
					if (jLanguages != null) {
						JSONObject langs = (JSONObject)jLanguages.get(0);
						sKeyboardName = (String)langs.get("name");
					}
				}										
				sResult = sKeyboardName + " (" + key + ") " + sKeyboardMaker;
			} catch (IOException e) {
				ControllerUtilities.showExceptionInErrorDialog(e, sTitle, sHeader, sContent, sLabel);
				e.printStackTrace();
			} catch (ParseException e) {
				ControllerUtilities.showExceptionInErrorDialog(e, sTitle, sHeader, sContent, sLabel);
				e.printStackTrace();
			}
		}
		return sResult;
	}

	@Override
	public List<KeyboardInfo> getAvailableKeyboards() {
		List<KeyboardInfo> results = new ArrayList<KeyboardInfo>();
		String[] sLangIDs = new String[100];
		int iCount = getCurrentWindowsLangIDs(sLangIDs);
		for (int i = 0; i < iCount; i++) {
			KeyboardInfo info = getKeyboardInfoFromProfile(sLangIDs[i]);
			results.add(info);
		}
		return results;
	}

	public KeyboardInfo getKeyboardInfoFromProfile(String profile) {
		int langId = getLangIdFromProfile(profile);
		Locale locale = getLocaleFromProfile(profile);
		String description = "";

		if (locale != null) {
			String country = "";
			if (locale.getCountry().length() > 0) {
				country = "_" + locale.getCountry();
			}
			// We prefer Keyman's description over the one derived from the locale
			description = findLangIdAsKeymanKeyboard(langId);
			if (description.equals(notKeymanKeyboard)) {
				description = locale.getLanguage() + country + "; " + locale.getDisplayName();
			}
		} else {
			// maybe it's a Keyman language
			description = findLangIdAsKeymanKeyboard(langId);
		}
		KeyboardInfo info = new KeyboardInfo(locale, description, langId);
		return info;
	}

	protected int getLangIdFromProfile(String profile) {
		int langId = 0;
		if (profile != null) {
			int iTab = profile.indexOf("\t");
			if(iTab > -1) {
				langId= Integer.parseInt(profile.substring(0, iTab));
			}
		}
		return langId;
	}

	protected Locale getLocaleFromProfile(String profile) {
		String id= "";
		if (profile == null)
			return null;
		int iQuote1 = profile.indexOf("'");
		if(iQuote1 > -1) {
			id = profile.substring(iQuote1 + 1, profile.length() - 1);
		}
		Locale l = Locale.forLanguageTag(id) ;
		return l;
	}

	protected int getCurrentWindowsLangIDs(String[] sLangIDs) {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		sb.append(System.getProperty("user.dir"));
		sb.append("\\resources\\Keyboards\\Windows\\GetKeyboardProfiles.exe\"");

		final String dosCommand = sb.toString();
		return getCurrentEnabledKeyboardIDs(dosCommand, sLangIDs);
	}

	@Override
	public String getExceptionContentMessage() {
		return sContent;
	}
}

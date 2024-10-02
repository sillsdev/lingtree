/**
 * Copyright (c) 2020 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.utility;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * @author Andy Black
 *
 */
public class ClipboardUtilities {
	
	public static void removeAnyFinalNullFromStringOnClipboard() {
		// need to get string contents of the clipboard and remove any final
		// null character; apparently some versions of MS Word append a null
		// at the end of the copied string (maybe a la C?)
		Clipboard clipboard = Clipboard.getSystemClipboard();
		String content = clipboard.getString();
		int lastIndex = content.length()-1;
		if (content.charAt(lastIndex) == 0) {
			content = content.substring(0, lastIndex);
		}
		ClipboardContent cbContent = new ClipboardContent();
		cbContent.putString(content);
		clipboard.setContent(cbContent);
		// now our possibly adjusted string is on the clipboard
	}
}

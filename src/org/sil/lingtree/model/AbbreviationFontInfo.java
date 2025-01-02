/**
 * Copyright (c) 2024-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import javafx.scene.paint.Color;

/**
 * @author Andy Black
 *  Singleton pattern for font information for abbreviations
 */

public class AbbreviationFontInfo extends FontInfo {

	private static AbbreviationFontInfo instance;

	private AbbreviationFontInfo() {
		super("Arial", 12.0, "Regular");
		setColor(Color.GREEN);
	}

	public static AbbreviationFontInfo getInstance() {
		if (instance == null) {
			instance = new AbbreviationFontInfo();
		}
		return instance;
	}

}

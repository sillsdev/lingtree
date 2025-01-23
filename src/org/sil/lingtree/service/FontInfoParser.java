/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import org.sil.lingtree.descriptionparser.DescriptionConstants;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.utility.StringUtilities;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Andy Black Singleton pattern for parsing a custom font info string
 */
public class FontInfoParser {
	private static FontInfoParser instance;
	LingTreeNode node;

	private FontInfoParser() {
	}

	public static FontInfoParser getInstance() {
		if (instance == null) {
			instance = new FontInfoParser();
		}
		return instance;
	}

	public FontInfo parseString(String sDescription, LingTreeNode node, DescriptionParser parser)
			throws FontInfoParserException {
		if (StringUtilities.isNullOrEmpty(sDescription)) {
			throw new FontInfoParserException(1, 0, DescriptionConstants.EMPTY_DESCRIPTION);
		}
		FontInfo typeFontInfo = node.getFontInfoFromNodeType(false);
		FontInfo fontInfo = new FontInfo(typeFontInfo.getFontFamily(), typeFontInfo.getFontSize(),
				typeFontInfo.getFontType());
		fontInfo.setColor(typeFontInfo.getColor());
		fontInfo = adjustFontInfoPerDescription(sDescription, parser, fontInfo);
		node.setCustomFontInfo(fontInfo);
		return fontInfo;
	}

	public FontInfo adjustFontInfoPerDescription(String sDescription, DescriptionParser parser, FontInfo fontInfo)
			throws FontInfoParserException {
		int charPos = 0;
		sDescription = sDescription.trim();
		if (!sDescription.contains("|") || !sDescription.startsWith("|")) {
			throw new FontInfoParserException(1, 0, DescriptionConstants.MISSING_BAR);
		}
		if (sDescription.length() == 1) {
			throw new FontInfoParserException(1, 0, DescriptionConstants.BAD_BAR_CODE);
		}
		// Effectively put initial | at the end so split() works the way we need
		sDescription = sDescription.substring(1) + "|";
		if (sDescription.endsWith("||")) {
			throw new FontInfoParserException(1, sDescription.length()-1, DescriptionConstants.BAD_BAR_CODE);
		}
		String[] items = sDescription.split("\\|");
		if (items.length == 0) {
			if (parser != null) {
				parser.notifyErrorListeners("custom font info missing");
			return null;
			}
			throw new FontInfoParserException(1, 0, DescriptionConstants.BAD_BAR_CODE);
		}
		String type = "";
		for (String item : items) {
			if (item.length() == 0) {
				throw new FontInfoParserException(1, 0, DescriptionConstants.BAD_BAR_CODE);
			}
			String code = item.substring(0, 1);
			charPos += 1;
			switch (code) {
			case "b":
				if (item.length() > 1) {
					throw new FontInfoParserException(1, ++charPos, DescriptionConstants.BAD_BAR_CODE);
				}
				type = fontInfo.getFontType().equals("Italic") ? "Bold Italic" : "Bold";
				fontInfo.setFontType(type);
				break;
			case "c":
				String sColor = item.substring(1, item.length());
				if (sColor.length() == 0) {
					throw new FontInfoParserException(1, ++charPos, DescriptionConstants.MISSING_COLOR);
				}
				try {
					Color color = Color.valueOf(sColor);
					fontInfo.setColor(color);
				} catch (IllegalArgumentException ex) {
					throw new FontInfoParserException(1, ++charPos, DescriptionConstants.BAD_COLOR);
				}
				break;
			case "f":
				String fontFamily = item.substring(1, item.length());
				if (fontFamily.length() == 0) {
					throw new FontInfoParserException(1, charPos, DescriptionConstants.MISSING_FONT_FAMILY);
				}
				if (!Font.getFamilies().stream().anyMatch(f -> f.equals(fontFamily))) {
					throw new FontInfoParserException(1, ++charPos, DescriptionConstants.BAD_FONT_FAMILY);
				}
				fontInfo.setFontFamily(fontFamily);
				break;
			case "i":
				if (item.length() > 1) {
					throw new FontInfoParserException(1, ++charPos, DescriptionConstants.BAD_BAR_CODE);
				}
				type = fontInfo.getFontType().equals("Bold") ? "Bold Italic" : "Italic";
				fontInfo.setFontType(type);
				break;
			case "r":
				if (item.length() > 1) {
					throw new FontInfoParserException(1, ++charPos, DescriptionConstants.BAD_BAR_CODE);
				}
				fontInfo.setFontType("Regular");
				break;
			case "s":
				String sSize = item.substring(1, item.length());
				if (sSize.length() == 0) {
					throw new FontInfoParserException(1, charPos, DescriptionConstants.MISSING_SIZE);
				}
				try {
					double size = Double.parseDouble(sSize);
					fontInfo.setFontSize(size);
				} catch (NumberFormatException | NullPointerException ex) {
					throw new FontInfoParserException(1, ++charPos, DescriptionConstants.BAD_SIZE);
				}
				break;
			default:
				fontInfo = null;
				throw new FontInfoParserException(1, charPos, DescriptionConstants.BAD_BAR_CODE);
			}
			charPos += item.length();
		}
		return fontInfo;
	}

}

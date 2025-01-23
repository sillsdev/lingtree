/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.sil.lingtree.descriptionparser.DescriptionConstants;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeNode;

import javafx.scene.paint.Color;

/**
 * 
 */
public class FontInfoParserTest extends ServiceBaseTest {

	@Test
	public void parseFontInfoTest() throws FontInfoParserException {
		FontInfoParser fontInfoParser = FontInfoParser.getInstance();
		DescriptionParser descParser = null;
		FontInfo fontInfo = null;
		try {
			fontInfo = fontInfoParser.parseString("", new LingTreeNode(), descParser);
		} catch (FontInfoParserException ex) {
			assertEquals(DescriptionConstants.EMPTY_DESCRIPTION, ex.getMsg());
		}
		assertNull(fontInfo);
		// font types
		fontInfo = fontInfoParser.parseString("|r", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|b", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Bold", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|i", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Italic", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|r|i", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Italic", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|i|r", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|b|i", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Bold Italic", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|i|b", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Bold Italic", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|b|i|r", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 12.0, Color.BLACK);

		// font family
		fontInfo = fontInfoParser.parseString("|fCharis SIL", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Regular", "Charis SIL", 12.0, Color.BLACK);

		// font size
		fontInfo = fontInfoParser.parseString("|s16.0", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 16.0, Color.BLACK);

		// color
		fontInfo = fontInfoParser.parseString("|c#ffffff", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 12.0, Color.WHITE);
		
		// combinations
		fontInfo = fontInfoParser.parseString("|b|i|fCharis SIL|s16.0|c#f5f5dc", new LingTreeNode(), descParser);
		checkFontInfo(fontInfo, "Bold Italic", "Charis SIL", 16.0, Color.BEIGE);
}

	protected void checkFontInfo(FontInfo fontInfo, String fontType, String fontFamily, double fontSize,
			Color fontColor) {
		assertNotNull(fontInfo);
		assertEquals(fontType, fontInfo.getFontType());
		assertEquals(fontFamily, fontInfo.getFontFamily());
		assertEquals(fontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(fontColor, fontInfo.getColor());
	}

	@Test
	public void parseFontInfoErrorsTest() {
		FontInfoParser fontInfoParser = FontInfoParser.getInstance();
		DescriptionParser dParser = null;
		FontInfo fontInfo = null;
		try {
			fontInfo = fontInfoParser.parseString("", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 0, 1, DescriptionConstants.EMPTY_DESCRIPTION, "");
		}
		assertNull(fontInfo);
		try {
			fontInfo = fontInfoParser.parseString("r", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 0, 1, DescriptionConstants.MISSING_BAR, "r");
		}
		assertNull(fontInfo);
		try {
			fontInfo = fontInfoParser.parseString("|bi", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 2, 1, DescriptionConstants.BAD_BAR_CODE, "|bi");
		}
		try {
			fontInfo = fontInfoParser.parseString("|", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 0, 1, DescriptionConstants.BAD_BAR_CODE, "|");
		}
		try {
			fontInfo = fontInfoParser.parseString("|sxyz", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 2, 1, DescriptionConstants.BAD_SIZE, "|sxyz");
		}
		try {
			fontInfo = fontInfoParser.parseString("|cjdji", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 2, 1, DescriptionConstants.BAD_COLOR, "|cjdji");
		}
		try {
			fontInfo = fontInfoParser.parseString("|c", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 2, 1, DescriptionConstants.MISSING_COLOR, "|c");
		}
		try {
			fontInfo = fontInfoParser.parseString("|f", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 1, 1, DescriptionConstants.MISSING_FONT_FAMILY, "|f");
		}
		try {
			fontInfo = fontInfoParser.parseString("|fxyz", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 2, 1, DescriptionConstants.BAD_FONT_FAMILY, "|fxyz");
		}
		try {
			fontInfo = fontInfoParser.parseString("|s", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 1, 1, DescriptionConstants.MISSING_SIZE, "|s");
		}
		try {
			fontInfo = fontInfoParser.parseString("|b|i|", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 4, 1, DescriptionConstants.BAD_BAR_CODE, "|b|i|");
		}
		try {
			fontInfo = fontInfoParser.parseString("|b|i|cfgh", new LingTreeNode(), dParser);
		} catch (FontInfoParserException e) {
			checkError(fontInfo, e, 6, 1, DescriptionConstants.BAD_COLOR, "|b|i|cfgh");
		}
	}

	protected void checkError(FontInfo fontInfo, FontInfoParserException e, int charPos,
			int lineNumber, String msg, String sDescription) {
//		System.out.println("for '" + sDescription + "',  char pos = " + charPos + "; msg = " + msg);
		assertEquals(lineNumber, e.getLineNumberOfError());
		assertEquals(charPos, e.getCharacterPositionInLineOfError());
		assertEquals(msg, e.getMsg());
	}
}

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
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeNode;

import javafx.scene.paint.Color;

/**
 * 
 */
public class FontInfoParserTest extends ServiceBaseTest {

	@Test
	public void parseFontInfoTest() {
		FontInfoParser fontInfoParser = FontInfoParser.getInstance();
		DescriptionParser dParser = null;
		FontInfo fontInfo = null;
		fontInfo = fontInfoParser.parseString("", new LingTreeNode(), dParser);
		assertNull(fontInfo);
		// font types
		fontInfo = fontInfoParser.parseString("|r", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|b", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Bold", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|i", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Italic", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|r|i", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Italic", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|i|r", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|b|i", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Bold Italic", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|i|b", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Bold Italic", "Times New Roman", 12.0, Color.BLACK);
		fontInfo = fontInfoParser.parseString("|b|i|r", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 12.0, Color.BLACK);

		// font family
		fontInfo = fontInfoParser.parseString("|fCharis SIL", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Regular", "Charis SIL", 12.0, Color.BLACK);

		// font size
		fontInfo = fontInfoParser.parseString("|s16.0", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 16.0, Color.BLACK);

		// color
		fontInfo = fontInfoParser.parseString("|c#ffffff", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Regular", "Times New Roman", 12.0, Color.WHITE);
		
		// combinations
		fontInfo = fontInfoParser.parseString("|b|i|fCharis|s16.0|c#f5f5dc", new LingTreeNode(), dParser);
		checkFontInfo(fontInfo, "Bold Italic", "Charis", 16.0, Color.BEIGE);
}

	protected void checkFontInfo(FontInfo fontInfo, String fontType, String fontFamily, double fontSize,
			Color fontColor) {
		assertNotNull(fontInfo);
		assertEquals(fontType, fontInfo.getFontType());
		assertEquals(fontFamily, fontInfo.getFontFamily());
		assertEquals(fontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(fontColor, fontInfo.getColor());
	}
}

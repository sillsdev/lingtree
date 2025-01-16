/**
 * Copyright (c) 2024-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

/**
 * @author Andy Black
 *
 */
public class AbbreviationText extends NodeText {
	int lineNumInDescription;
	int characterPositionInLine;

	// custom font info for this abbreviation text
	private FontInfo customFontInfo = null;

	public AbbreviationText(LingTreeNode node) {
		super(node);
	}

	public int getLineNumInDescription() {
		return lineNumInDescription;
	}

	public void setLineNumInDescription(int lineNumInDescription) {
		this.lineNumInDescription = lineNumInDescription;
	}

	public int getCharacterPositionInLine() {
		return characterPositionInLine;
	}

	public void setCharacterPositionInLine(int characterPositionInLine) {
		this.characterPositionInLine = characterPositionInLine;
	}

	public FontInfo getCustomFontInfo() {
		return customFontInfo;
	}

	public void setCustomFontInfo(FontInfo fontInfo) {
		this.customFontInfo = fontInfo;
//		setContent(getContent());
	}


	@Override
	public void setText(String text) {
		if (customFontInfo == null) {
			fontInfo = AbbreviationFontInfo.getInstance();
		} else {
			fontInfo = customFontInfo;
		}
		textBox.setFont(fontInfo.getFont());
		textBox.setFill(fontInfo.getColor());
		textBox.setText(text);
	}
}

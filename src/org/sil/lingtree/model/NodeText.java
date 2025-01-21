/**
 * Copyright (c) 2024-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import javafx.scene.text.Text;

/**
 * @author Andy Black
 *
 */
public class NodeText {
	protected Text textBox = new Text(0, 0, "");
	protected LingTreeNode node = new LingTreeNode();
	protected FontInfo fontInfo;
	int lineNumInDescription;
	int characterPositionInLine;
	// custom font info for this node text
	protected FontInfo customFontInfo = null;
	int customFontLineNumInDescription;
	int customFontCharacterPositionInLine;

	public Text getTextBox() {
		return textBox;
	}

	public void setTextBox(Text textBox) {
		this.textBox = textBox;
	}

	public NodeText(LingTreeNode node) {
		this.node = node;
	}
	
	public String getText() {
		return textBox.getText();
	}

	public void setText(String text) {
		fontInfo = (customFontInfo != null) ? customFontInfo : node.getFontInfoFromNodeType(false);
		textBox.setText(text);
		textBox.setFont(fontInfo.getFont());
		textBox.setFill(fontInfo.getColor());
	}

	public FontInfo getFontInfo() {
		return fontInfo;
	}

	public void setFontInfo(FontInfo fontInfo) {
		this.fontInfo = fontInfo;
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

	public int getCustomFontLineNumInDescription() {
		return customFontLineNumInDescription;
	}

	public void setCustomFontLineNumInDescription(int customFontLineNumInDescription) {
		this.customFontLineNumInDescription = customFontLineNumInDescription;
	}

	public int getCustomFontCharacterPositionInLine() {
		return customFontCharacterPositionInLine;
	}

	public void setCustomFontCharacterPositionInLine(int customFontCharacterPositionInLine) {
		this.customFontCharacterPositionInLine = customFontCharacterPositionInLine;
	}

	public boolean hasCustomFont() {
		return customFontInfo != null;
	}

}

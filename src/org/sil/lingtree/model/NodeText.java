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
		textBox.setText(text);
		fontInfo = node.getFontInfoFromNodeType();
		textBox.setFont(fontInfo.getFont());
		textBox.setFill(fontInfo.getColor());
	}

	public FontInfo getFontInfo() {
		return fontInfo;
	}

	public void setFontInfo(FontInfo fontInfo) {
		this.fontInfo = fontInfo;
	}
}

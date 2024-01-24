/**
 * Copyright (c) 2024 SIL International
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

	public NodeText(LingTreeNode node) {
		this.node = node;
	}
	
	public String getText() {
		return textBox.getText();
	}

	public void setText(String text) {
		textBox.setText(text);
		FontInfo fontInfo = node.getFontInfoFromNodeType();
		textBox.setFont(fontInfo.getFont());
		textBox.setFill(fontInfo.getColor());
	}
}

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

	public AbbreviationText(LingTreeNode node) {
		super(node);
	}

	@Override
	public void setText(String text) {
		textBox.setText(text);
		fontInfo = AbbreviationFontInfo.getInstance();
		textBox.setFont(fontInfo.getFont());
		textBox.setFill(fontInfo.getColor());
	}
}

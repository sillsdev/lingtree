/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import org.sil.lingtree.Constants;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * 
 */
public class SubOrSuperscriptText extends NodeText {

	boolean fRegular = true;

	public SubOrSuperscriptText(LingTreeNode node) {
		super(node);
		// TODO Auto-generated constructor stub
	}

	public boolean isRegular() {
		return fRegular;
	}

	public void setRegular(boolean fRegular) {
		this.fRegular = fRegular;
	}

	@Override
	public void setText(String text) {
		fontInfo = (hasCustomFont()) ? customFontInfo : node.getFontInfoFromNodeType(false);
		double sizeFactor = (hasCustomFont()) ? 1.0 : Constants.SUB_SUPER_SCRIPT_FONT_SIZE_FACTOR;
		double size = fontInfo.getFontSize() * sizeFactor;
		FontWeight weight = (fontInfo.getFontType().contains("Bold")) ? FontWeight.BOLD : FontWeight.NORMAL;
		FontPosture posture = (fontInfo.getFontType().contains("Italic")) ? FontPosture.ITALIC : FontPosture.REGULAR;		
		textBox.setFont(Font.font(fontInfo.getFontFamily(), weight, posture, size));
		textBox.setFill(fontInfo.getColor());
		textBox.setText(text);
	}
}

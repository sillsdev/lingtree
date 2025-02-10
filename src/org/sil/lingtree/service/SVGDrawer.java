/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import org.sil.lingtree.Constants;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NodeText;
import org.sil.lingtree.model.NodeType;
import org.sil.utility.StringUtilities;

import javafx.scene.text.Text;

/**
 * 
 */
public class SVGDrawer extends TreeDrawer {

	/**
	 * @param ltTree
	 */
	public SVGDrawer(LingTreeTree ltTree) {
		super(ltTree);
		// TODO Auto-generated constructor stub
	}

	public StringBuilder drawAsSVG() {
		recalculateValues();
		LingTreeNode node = ltTree.getRootNode();
		StringBuilder sb = new StringBuilder();
		// Trying to convert from pixels to mm does not come out right.
		// So we're going with pixels
		// final String sMM = "mm";
		// sb.append(Constants.SVG_HEADER.replace("{0}",
		// pixelsToMM(ltTree.getXSize()) + sMM).replace(
		// "{1}", pixelsToMM(ltTree.getYSize()) + sMM));
		sb.append(Constants.SVG_HEADER.replace("{0}", String.valueOf(ltTree.getXSize() + 10)).replace(
				"{1}", String.valueOf(ltTree.getYSize())));
		sb.append(Constants.SVG_BACKGROUND_COLOR.replace("{0}",
				StringUtilities.toRGBCode(ltTree.getBackgroundColor())));
		drawNodesAsSVG(node, sb);
		sb.append(Constants.SVG_END_ELEMENT);
		return sb;
	}

	private void drawNodesAsSVG(LingTreeNode node, StringBuilder sb) {
		if (node.hasAbbreviation()) {
			for (NodeText nt : node.getContentsAsList()) {
				createTextAsSVG(nt.getTextBox(), nt.getFontInfo(), sb);
			}
		} else {
			createTextAsSVG(node.getContentTextBox(), node.getFontInfoFromNodeType(false), sb);
		}
		if (node.hasSubscript()) {
			FontInfo fontInfo = node.getFontInfoForSubscript();
			if (node.getSubscriptText().hasCustomFont()) {
				fontInfo = node.getSubscriptText().getCustomFontInfo();
			} else if (node.hasCustomFontInfo()) {
				fontInfo = node.getCustomFontInfo();
				fontInfo.setFontSize(fontInfo.getFontSize() * Constants.SUB_SUPER_SCRIPT_FONT_SIZE_FACTOR);
			}
			createTextAsSVG(node.getSubscriptTextBox(), fontInfo, sb);
		}
		if (node.hasSuperscript()) {
			FontInfo fontInfo = node.getFontInfoForSuperscript();
			if (node.getSuperscriptText().hasCustomFont()) {
				fontInfo = node.getSuperscriptText().getCustomFontInfo();
			} else if (node.hasCustomFontInfo()) {
				fontInfo = node.getCustomFontInfo();
				fontInfo.setFontSize(fontInfo.getFontSize() * Constants.SUB_SUPER_SCRIPT_FONT_SIZE_FACTOR);
			}
			createTextAsSVG(node.getSuperscriptTextBox(), fontInfo, sb);
		}
		if (node.hasMother() && !node.isOmitLine() && node.getNodeType() != NodeType.Gloss) {
			LingTreeNode mother = node.getMother();
			if (!node.isTriangle()) {
				// need to draw a line between mother and this node
				createLineAsSVG(mother.getXMid(), mother.getYLowerMid(), node.getXMid(),
						node.getYUpperMid(), sb);
			} else if (node.isTriangle()) {
				drawTriangleAsSVG(mother, node, sb);
			}
		}
		if (ltTree.isDrawVerticalLineWithEmptyText() && node.getContentTextBox().getText().length() == 0) {
			// Need to draw a line from top to bottom of this node
			createLineAsSVG(node.getXMid(), node.getYLowerMid(), node.getXMid(),
					node.getYUpperMid(), sb);
		}
		for (LingTreeNode daughterNode : node.getDaughters()) {
			drawNodesAsSVG(daughterNode, sb);
		}
	}

	private void createLineAsSVG(double x1, double y1, double x2, double y2, StringBuilder sb) {
		sb.append("<line x1=\"");
		sb.append(x1);
		sb.append("\" y1=\"");
		sb.append(y1);
		sb.append("\" x2=\"");
		sb.append(x2);
		sb.append("\" y2=\"");
		sb.append(y2);
		sb.append("\" stroke=\"");
		sb.append(StringUtilities.toRGBCode(ltTree.getLineColor()));
		sb.append("\" stroke-width=\"");
		sb.append(ltTree.getLineWidth());
		sb.append("\"/>\n");
	}

	private void createTextAsSVG(Text tb, FontInfo fontInfo, StringBuilder sb) {
		sb.append("<text x=\"");
		sb.append(tb.getX());
		sb.append("\" y=\"");
		sb.append(tb.getY());
		sb.append("\" font-family=\"");
		sb.append(fontInfo.getFontFamily());
		sb.append("\" font-size=\"");
		sb.append(fontInfo.getFontSize());
		String sFontType = fontInfo.getFontType();
		if (sFontType.contains("Italic")) {
			sb.append("\" font-style=\"italic");
		}
		if (sFontType.contains("Bold")) {
			sb.append("\" font-weight=\"bold");
		}
		sb.append("\" fill=\"");
		sb.append(StringUtilities.toRGBCode(fontInfo.getColor()));
		sb.append("\">");
		sb.append(tb.getText().replace("<", "&lt;").replace(">", "&gt;").replace(" & ", " &amp; "));
		sb.append("</text>\n");
	}

	private void drawTriangleAsSVG(LingTreeNode mother, LingTreeNode node, StringBuilder sb) {
		double dLeftmostX = node.getXCoordinate() + dTriangleOffset;
		double dRightmostX = node.getXCoordinate() + node.getWidth() - dTriangleOffset;
		double dTopX = mother.getXMid();
		double dBottomY = node.getYUpperMid();
		double dTopY = mother.getYLowerMid();
		// right part of line
		createLineAsSVG(dLeftmostX, dBottomY, dTopX, dTopY, sb);
		// left part of line
		createLineAsSVG(dTopX, dTopY, dRightmostX, dBottomY, sb);
		// bottom part of line
		createLineAsSVG(dLeftmostX, dBottomY, dRightmostX, dBottomY, sb);
	}

}

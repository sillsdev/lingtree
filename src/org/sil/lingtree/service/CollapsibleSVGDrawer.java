/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.sil.lingtree.Constants;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NodeText;
import org.sil.lingtree.model.NodeType;
import org.sil.utility.StringUtilities;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 */
public class CollapsibleSVGDrawer extends TreeDrawer {
	Text ellipsis = new Text();
	double dEllipsisWidth = 0.0;
	double dEllipsisXOffset = dTriangleOffset;
	final String breakLongLine = "\n\t";

	/**
	 * @param ltTree
	 */
	public CollapsibleSVGDrawer(LingTreeTree ltTree) {
		super(ltTree);
		createEllipsisText();
	}
	
	protected void createEllipsisText() {
		Font ellipsisFont = Font.font("Arial", 28.0);
		ellipsis.setFont(ellipsisFont);
		ellipsis.setFill(Color.BLACK);
		ellipsis.setText(". . .");
		dEllipsisWidth = ellipsis.getLayoutBounds().getWidth();
		dEllipsisXOffset = dEllipsisWidth / 2;
	}

	public StringBuilder drawAsCollaspibleSVG() {
		StringBuilder sb = new StringBuilder();
		try {
			recalculateValues();
			LingTreeNode node = ltTree.getRootNode();
			sb.append(Constants.SVG_COLLAPSIBLE_HEADER_BEGIN.replace("{0}", String.valueOf(ltTree.getXSize() + 10)).replace(
					"{1}", String.valueOf(ltTree.getYSize())));
			insertParameters(sb);
			insertStaticJavacriptCode(sb);
			sb.append(Constants.SVG_COLLAPSIBLE_HEADER_END);
			sb.append(Constants.SVG_BACKGROUND_COLOR.replace("{0}",
					StringUtilities.toRGBCode(ltTree.getBackgroundColor())));
			drawNodesAsCollapsibleSVG(node, sb);
			sb.append(Constants.SVG_END_ELEMENT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb;
	}

	protected void insertParameters(StringBuilder sb) {
		sb.append("var dEllipsisWidth = " + dEllipsisWidth + ";\n");
		sb.append("var dEllipsisXOffset = " + dEllipsisXOffset + ";\n");
		sb.append("var dTriangleXOffset = " + dTriangleOffset + ";\n");
		sb.append("var horizontalGap = " + ltTree.getHorizontalGap() + ";\n");
		sb.append("var initialXCoord = " + ltTree.getInitialXCoordinate() + ";\n");
		sb.append("var isCenterColumnOrientedOnDaughtersWidth = " + ltTree.isCenterColumnOrientedOnDaughtersWidth() + ";\n");
		sb.append("var isRightToLeft = " + ltTree.isUseRightToLeftOrientation() + ";\n");
		sb.append("var svgWidth = " + String.valueOf(ltTree.getXSize() + 10) + ";\n");
	}

	protected void insertStaticJavacriptCode(StringBuilder sb) throws IOException {
		File scriptFile = new File(Constants.RESOURCE_SOURCE_LOCATION + "resources/CollapsibleSVG.js");
		String script = new String(Files.readString(scriptFile.toPath(), StandardCharsets.UTF_8));
		script = script.replace(" < ", " &lt; ");
		script = script.replace(" && ", " &amp;&amp; ");
		sb.append(script);
	}

	private void drawNodesAsCollapsibleSVG(LingTreeNode node, StringBuilder sb) {
		int id = node.getItemId();
		int motherId = (node.hasMother()) ? node.getMother().getItemId() : 0;
		if (node.getContentsAsList().size() > 0) {
			createTextAsCollapsibleSVG(node.getContentTextBox(), node, node.getFontInfoFromNodeType(false), sb, id);
			for (NodeText nt : node.getContentsAsList()) {
				createNodeTextItemAsCollapsibleSVG(nt, node, sb, id);
				if (node.getDaughters().size() > 0) {
					// TODO: is this right?
					createCollapsedTextAndTriangle(node, sb, id);
				}
			}
		} else {
			createTextAsCollapsibleSVG(node.getContentTextBox(), node, node.getFontInfoFromNodeType(false), sb, id);
			if (node.hasMother()) {
				createCollapsedTextAndTriangle(node, sb, id);
			}
		}
		if (node.hasSubscript()) {
			FontInfo fontInfo = node.getFontInfoForSubscript();
			if (node.getSubscriptText().hasCustomFont()) {
				fontInfo = node.getSubscriptText().getCustomFontInfo();
			} else if (node.hasCustomFontInfo()) {
				fontInfo = node.getCustomFontInfo();
				fontInfo.setFontSize(fontInfo.getFontSize() * Constants.SUB_SUPER_SCRIPT_FONT_SIZE_FACTOR);
			}
			Text sub = node.getSubscriptTextBox();
			int itemId = node.getSubscriptText().getItemId();
			createTextAsCollapsibleSVG(sub, node, fontInfo, sb, itemId);
			createCollapsedTextAndTriangle(node, sb, itemId);
		}
		if (node.hasSuperscript()) {
			FontInfo fontInfo = node.getFontInfoForSuperscript();
			if (node.getSuperscriptText().hasCustomFont()) {
				fontInfo = node.getSuperscriptText().getCustomFontInfo();
			} else if (node.hasCustomFontInfo()) {
				fontInfo = node.getCustomFontInfo();
				fontInfo.setFontSize(fontInfo.getFontSize() * Constants.SUB_SUPER_SCRIPT_FONT_SIZE_FACTOR);
			}
			Text sup = node.getSuperscriptTextBox();
			int itemId = node.getSuperscriptText().getItemId();
			createTextAsCollapsibleSVG(sup, node, fontInfo, sb, itemId);
			createCollapsedTextAndTriangle(node, sb, itemId);
		}
		if (node.hasMother() && !node.isOmitLine() && node.getNodeType() != NodeType.Gloss) {
			LingTreeNode mother = node.getMother();
			if (!node.isTriangle()) {
				// need to draw a line between mother and this node
				createLineAsCollapsibleSVG(mother.getXMid(), mother.getYLowerMid(), node.getXMid(),
						node.getYUpperMid(), sb, id, motherId, "");
			} else if (node.isTriangle()) {
				drawTriangleAsCollapsibleSVG(mother, node, sb, id);
			}
		}
		if (ltTree.isDrawVerticalLineWithEmptyText() && node.getContentTextBox().getText().length() == 0) {
			// Need to draw a line from top to bottom of this node
			createLineAsCollapsibleSVG(node.getXMid(), node.getYLowerMid(), node.getXMid(),
					node.getYUpperMid(), sb, id, motherId, "");
		}
		for (LingTreeNode daughterNode : node.getDaughters()) {
			drawNodesAsCollapsibleSVG(daughterNode, sb);
		}
	}

	// for collapsed ellipsis
	private void drawEllipsisTriangleAsCollapsibleSVG(LingTreeNode mother, LingTreeNode node, StringBuilder sb, int id) {
		double dLeftmostX = node.getXMid() - dEllipsisXOffset;
		double dRightmostX = node.getXMid() + dEllipsisXOffset;
		double dTopX = node.getXMid();
		double dBottomY = node.getYLowerMid() + ltTree.getVerticalGap();
		if (node.getDaughters().size() > 0) {
			LingTreeNode daughter = node.getDaughters().get(0);
			dBottomY = daughter.getYUpperMid();
		}
		if (ltTree.isShowFlatView() && node.getDaughters().size() == 1) {
			LingTreeNode daughter = node.getDaughters().get(0);
			if (daughter.getNodeType() == NodeType.Lex) {
				dBottomY = daughter.getYUpperMid();
			}
		}
		double dTopY = node.getYLowerMid();
		// right part of line
		createTriangleLineAsCollapsibleSVG(dLeftmostX, dBottomY, dTopX, dTopY, sb, id, "1");
		// left part of line
		createTriangleLineAsCollapsibleSVG(dTopX, dTopY, dRightmostX, dBottomY, sb, id, "2");
		// bottom part of line
		createTriangleLineAsCollapsibleSVG(dLeftmostX, dBottomY, dRightmostX, dBottomY, sb, id, "3");
	}

	private void createTriangleLineAsCollapsibleSVG(double x1, double y1, double x2, double y2, StringBuilder sb,
			int id, String part) {
		sb.append("<line id=\"line");
		sb.append(id);
		sb.append("T");
		sb.append(part);
		sb.append("\" x1=\"");
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
		sb.append("\" visibility=\"hidden\"/>\n");
	}

	private void createTextAsCollapsibleSVG(Text tb, LingTreeNode node, FontInfo fontInfo, StringBuilder sb, int id) {
		sb.append("<text id=\"node");
		sb.append(id);
		sb.append("\" x=\"");
		sb.append(tb.getX());
		sb.append("\" y=\"");
		sb.append(tb.getY());
		sb.append("\"");
		sb.append(breakLongLine);
		insertNodesFontInfo(fontInfo, sb);
		sb.append(breakLongLine);
		double dWidth = tb.getBoundsInLocal().getWidth();
		if (dWidth == 0.0) {
			// a node containing NodeText items will not have any width via its Text.
			dWidth = node.getWidth();
		}
		insertNodesMaxValues(node, sb, dWidth);
		sb.append(breakLongLine);
		insertMother(node, sb);
		sb.append("\"");
		insertNodesDaughters(node, sb);
		sb.append("\"");
		insertRightSister(node, sb);
		sb.append("\"");
		insertIsTriangle(node, sb);
		sb.append("\"");
		insertNodeTextItems(node, sb);
		if (!isNodeCollapsible(node)) {
			sb.append(">");
		} else {
			sb.append(breakLongLine);
			sb.append(" onclick=\"ProcessCollapsibleNode('node");
			sb.append(id);
			sb.append("')\"");
			sb.append(" lt:collapsed=\"false\"");
			sb.append(breakLongLine);
			insertCollapsedNodesAndLines(node, sb);
			sb.append(">");
		}
		sb.append(tb.getText().replace("<", "&lt;").replace(">", "&gt;").replace(" & ", " &amp; "));
		sb.append("</text>\n");
	}

	private void createNodeTextItemAsCollapsibleSVG(NodeText nt, LingTreeNode node, StringBuilder sb, int id) {
		sb.append("<text id=\"node");
		sb.append(id);
		sb.append("nt");
		sb.append(nt.getItemId());
		sb.append("\" x=\"");
		sb.append(nt.getTextBox().getX());
		sb.append("\" y=\"");
		sb.append(nt.getTextBox().getY());
		sb.append("\"");
		sb.append(breakLongLine);
		insertNodesFontInfo(nt.getFontInfo(), sb);
		sb.append(breakLongLine);
		double dWidth = nt.getTextBox().getBoundsInLocal().getWidth();
		insertNodesMaxValues(node, sb, dWidth);
		sb.append(breakLongLine);
		insertMother(node, sb);
		sb.append("\"");
		insertNodesDaughters(node, sb);
		sb.append("\"");
//		insertRightSister(node, sb);
//		sb.append("\"");
//		insertIsTriangle(node, sb);
//		sb.append("\"");
//		insertIsNodeText(node, sb);
//		sb.append("\"");
		if (node.getDaughters().size() > 0) {
			sb.append(breakLongLine);
			sb.append(" onclick=\"ProcessCollapsibleNode('node");
			sb.append(id);
			sb.append("')\"");
			sb.append(" lt:collapsed=\"false\"");
			sb.append(breakLongLine);
			insertCollapsedNodesAndLines(node, sb);
		}
		sb.append(">");
//		} else {
//			sb.append(breakLongLine);
//			sb.append(" onclick=\"ProcessCollapsibleNode('node");
//			sb.append(id);
//			sb.append("')\"");
//			sb.append(" lt:collapsed=\"false\"");
//			sb.append(breakLongLine);
//			insertCollapsedNodesAndLines(node, sb);
//			sb.append(">");
//		}
		sb.append(nt.getTextBox().getText().replace("<", "&lt;").replace(">", "&gt;").replace(" & ", " &amp; "));
		sb.append("</text>\n");
	}

	protected void insertNodeTextItems(LingTreeNode node, StringBuilder sb) {
		if (node.getContentsAsList().size() > 0) {
			sb.append(" lt:nodeTextItems=\"");
			String nodeTextItems = recordNodeTextItems(node);
			sb.append(nodeTextItems);
			sb.append("\"");
		}
	}

	protected void insertIsTriangle(LingTreeNode node, StringBuilder sb) {
		sb.append(" lt:isTriangle=\"");
		if (node.isTriangle()) {
			sb.append("true");
		} else {
			sb.append("false");
		}
	}

	protected void insertMother(LingTreeNode node, StringBuilder sb) {
		sb.append(" lt:mother=\"node");
		if (node.getMother() != null) {
			sb.append(node.getMother().getItemId());
		}
	}

	protected void insertRightSister(LingTreeNode node, StringBuilder sb) {
		sb.append(" lt:rightSister=\"node");
		if (node.getRightSister() != null) {
			sb.append(node.getRightSister().getItemId());
		}
	}

	protected void insertCollapsedNodesAndLines(LingTreeNode node, StringBuilder sb) {
		if (node.getDaughters().size() > 0) {
			sb.append(" lt:collapsedNodes=\"");
			String collapsibleNodes = recordCollapsibleNodes(node);
			// remove final comma
			collapsibleNodes = collapsibleNodes.substring(0, collapsibleNodes.length()-1);
			sb.append(collapsibleNodes);
			sb.append("\"");
			String collapsibleLines = recordCollapsibleLines(node);
			sb.append(breakLongLine);
				sb.append(" lt:collapsedLines=\"");
				if (collapsibleLines.length() > 0) {
					// remove final comma
					collapsibleLines = collapsibleLines.substring(0, collapsibleLines.length() - 1);
				}
				sb.append(collapsibleLines);
			sb.append("\" visibility=\"visible\"");
		}
	}

	protected void insertNodesFontInfo(FontInfo fontInfo, StringBuilder sb) {
		sb.append(" font-family=\"");
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
		sb.append("\"");
	}

	protected void insertNodesMaxValues(LingTreeNode node, StringBuilder sb, double dWidth) {
		sb.append(" lt:width=\"");
		sb.append(dWidth);
		sb.append("\" lt:maxWidthOfDaughters=\"");
		sb.append(node.getMaxWidthOfDaughters());
		sb.append("\" lt:maxWidthInColumn=\"");
		sb.append(node.getMaxWidthInColumn());
		sb.append("\" lt:xMid=\"");
		sb.append(node.getXMid());
		sb.append("\" lt:maxInColumnMothersWidth=\"");
		sb.append(node.calculateMaxInColumnMothersWidth());
		sb.append("\"");
	}

	protected void insertNodesDaughters(LingTreeNode node, StringBuilder sb) {
		sb.append(" lt:daughters=\"");
		if (node.getDaughters().size() > 0) {
			for (LingTreeNode daughter : node.getDaughters()) {
//				if (daughter.getContentsAsList().size() > 0) {
//					for (NodeText nt : daughter.getContentsAsList()) {
//						createNodeTextId(daughter, nt, sb);
//					}
//				} else {
					sb.append("node" + daughter.getItemId() + ",");
//				}
			}
		} else {
			sb.append("node");
		}
	}

	protected void createNodeTextId(LingTreeNode node, NodeText nt, StringBuilder sb) {
		sb.append("node");
		sb.append(node.getItemId());
		sb.append("nt");
		sb.append(nt.getItemId());
		sb.append(",");
	}

	private boolean isNodeCollapsible(LingTreeNode node) {
		boolean result = true;
		if (!node.hasMother()) {
			result = false;
		} else if (node.getNodeType() == NodeType.Lex && node.getDaughters().size() > 0) {
			result = false;
		}
		return result;
	}
	private String recordCollapsibleNodes(LingTreeNode node) {
		StringBuilder sb = new StringBuilder();
		if (node.hasSubscript()) {
			sb.append("node" + node.getSubscriptText().getItemId() + ",");
		}
		if (node.hasSuperscript()) {
			sb.append("node" + node.getSuperscriptText().getItemId() + ",");
		}
		for (LingTreeNode daughter : node.getDaughters()) {
			if (daughter.getContentsAsList().size() > 0) {
				for (NodeText nt : daughter.getContentsAsList()) {
					createNodeTextId(daughter, nt, sb);
				}
			}
				sb.append("node" + daughter.getItemId() + ",");
//			if (daughter.getContentsAsList().size() > 0) {
//				for (NodeText nt : daughter.getContentsAsList()) {
//					sb.append("node" + nt.getItemId() + ",");
//				}
//			} else {
//				sb.append("node" + daughter.getItemId() + ",");
//			}
			sb.append(recordCollapsibleNodes(daughter));
		}
		return sb.toString();
	}

	private String recordCollapsibleLines(LingTreeNode node) {
		StringBuilder sb = new StringBuilder();
		for (LingTreeNode daughter : node.getDaughters()) {
			if (daughter.getNodeType() != NodeType.Gloss) {
				sb.append("line" + daughter.getItemId() + "-" + node.getItemId() + ",");
				// triangle case
				if (daughter.isTriangle()) {
					// include the other two lines
					sb.append("line" + daughter.getItemId() + "-" + node.getItemId() + "T2,");
					sb.append("line" + daughter.getItemId() + "-" + node.getItemId() + "T3,");
				}
				sb.append(recordCollapsibleLines(daughter));
			}
		}
		return sb.toString();
	}

	private String recordNodeTextItems(LingTreeNode node) {
		StringBuilder sb = new StringBuilder();
		for (NodeText nt : node.getContentsAsList()) {
			createNodeTextId(node, nt, sb);
		}
		return sb.toString();
	}

	private void createCollapsedTextAndTriangle(LingTreeNode node, StringBuilder sb, int id) {
		sb.append("<text id=\"collapsed");
		sb.append(id);
		sb.append("\" x=\"");
		sb.append(node.getXMid() - dEllipsisXOffset);
		sb.append("\" y=\"");
		double y = node.getYLowerMid() + ltTree.getVerticalGap();
		if (node.getDaughters().size() > 0) {
			LingTreeNode daughter = node.getDaughters().get(0);
			y = daughter.getYCoordinate();
		}
		sb.append(y);
		sb.append("\" font-family=\"Arial\" font-size=\"28.0\" fill=\"#000000\" visibility=\"hidden\">. . .</text>\n");
		drawEllipsisTriangleAsCollapsibleSVG(node.getMother(), node, sb, id);
	}

	private void createLineAsCollapsibleSVG(double x1, double y1, double x2, double y2, StringBuilder sb, int id, int motherId, String triangleCode) {
		sb.append("<line id=\"");
		sb.append("line");
		sb.append(id);
		sb.append("-");
		sb.append(motherId);
		sb.append(triangleCode);
		sb.append("\" x1=\"");
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
		sb.append("\" visibility=\"visible\"/>\n");
	}

	// normal triangle
	private void drawTriangleAsCollapsibleSVG(LingTreeNode mother, LingTreeNode node, StringBuilder sb, int id) {
		double dLeftmostX = node.getXCoordinate() + dTriangleOffset;
		double dRightmostX = node.getXCoordinate() + node.getWidth() - dTriangleOffset;
		double dTopX = mother.getXMid();
		double dBottomY = node.getYUpperMid();
		double dTopY = mother.getYLowerMid();
		int motherId = node.getMother().getItemId();
		// right part of line
		createLineAsCollapsibleSVG(dLeftmostX, dBottomY, dTopX, dTopY, sb, id, motherId, "");
		// left part of line
		createLineAsCollapsibleSVG(dTopX, dTopY, dRightmostX, dBottomY, sb, id, motherId, "T2");
		// bottom part of line
		createLineAsCollapsibleSVG(dLeftmostX, dBottomY, dRightmostX, dBottomY, sb, id, motherId, "T3");
	}

}

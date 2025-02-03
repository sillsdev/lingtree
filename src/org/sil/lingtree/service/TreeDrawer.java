/**
 * Copyright (c) 2016-2025 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.sil.lingtree.Constants;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NodeText;
import org.sil.lingtree.model.NodeType;
import org.sil.utility.StringUtilities;

/**
 * @author Andy Black
 *
 */
public class TreeDrawer {
	public boolean fDoDebugPrint = false;
	LingTreeTree ltTree;
	HashMap<Integer, Double> maxHeightPerLevel = new HashMap<>();
	Text ellipsis = new Text();
	double dEllipsisXOffset = dTriangleOffset;

	private static final double dYCoordAdjustment = 3; // adjustment value
	private static final double dTriangleOffset = 3;

	public TreeDrawer(LingTreeTree ltTree) {
		super();
		this.ltTree = ltTree;
		createEllipsisText();
	}

	protected void createEllipsisText() {
		Font ellipsisFont = Font.font("Arial", 28.0);
		ellipsis.setFont(ellipsisFont);
		ellipsis.setFill(Color.BLACK);
		ellipsis.setText(". . .");
		dEllipsisXOffset = ellipsis.getLayoutBounds().getWidth() / 2;
	}

	public HashMap<Integer, Double> getMaxHeightPerLevel() {
		return maxHeightPerLevel;
	}

	public void calculateMaxHeightPerLevel() {
		LingTreeNode node = ltTree.getRootNode();
		calculateMaxHeightPerLevel(node);
	}

	private void calculateMaxHeightPerLevel(LingTreeNode node) {
		int iLevel = node.getLevel();
		double thisHeight = node.getHeight();
		if (maxHeightPerLevel.get(iLevel) == null) {
			maxHeightPerLevel.put(iLevel, thisHeight);
		} else if (thisHeight > maxHeightPerLevel.get(iLevel)) {
			maxHeightPerLevel.put(iLevel, thisHeight);
		}
		for (LingTreeNode daughterNode : node.getDaughters()) {
			calculateMaxHeightPerLevel(daughterNode);
		}
	}

	public void calculateYCoordinateOfEveryNode() {
		LingTreeNode node = ltTree.getRootNode();
		calculateYCoordinateOfANode(node, ltTree.getInitialYCoordinate());
		adjustLexAndGlossNodesForFlatView();
	}

	// Determine Y-axis coordinates for this node
	private void calculateYCoordinateOfANode(LingTreeNode node, double dVerticalOffset) {
		if (node.hasMother()) {
			double maxXSpan = 0.0;
			for (LingTreeNode sister : node.getMother().getDaughters()) {
				LingTreeNode rightSister = sister.getRightSister();
				if (rightSister != null) {
					double xSpan = rightSister.getXCoordinate() - sister.getXCoordinate();
					maxXSpan = Math.max(xSpan, maxXSpan);
				}
			}
			if (maxXSpan > ltTree.getMinimumXGapForExtraVerticalSpacing()) {
				double offset = ltTree.getVerticalAdjustmentForExtraVerticalSpacing();
				dVerticalOffset += offset;
			}
		}
		node.setYCoordinate(dVerticalOffset);
		node.setYLowerMid(node.getYLowerMid() + dYCoordAdjustment);
		node.setYUpperMid(node.getYUpperMid() - dYCoordAdjustment);
		if (node.getYLowerMid() > ltTree.getYSize()) {
			// Keep track of total height for scrolling
			ltTree.setYSize(node.getYLowerMid());
		}
		if (node.getNodeType() == NodeType.Lex || node.getNodeType() == NodeType.EmptyElement) {
			// keep track of lowest for "flat" view
			if (node.getYCoordinate() > ltTree.getLexBottomYCoordinate()) {
				ltTree.setLexBottomYCoordinate(node.getYCoordinate());
			}
			if (node.getYUpperMid() > ltTree.getLexBottomYUpperMid()) {
				ltTree.setLexBottomYUpperMid(node.getYUpperMid());
			}
		}
		if (node.getNodeType() == NodeType.Gloss) {
			// keep track of lowest for "flat" view
			if (node.getYCoordinate() > ltTree.getGlossBottomYCoordinate()) {
				ltTree.setGlossBottomYCoordinate(node.getYCoordinate());
			}
		}
		// Determine Y-axis coordinate for any daughters
		for (LingTreeNode daughterNode : node.getDaughters()) {
			double dDaughterYCoordinate = node.getYCoordinate()
					+ maxHeightPerLevel.get(daughterNode.getLevel());
			if (daughterNode.getNodeType() != NodeType.Gloss) {
				dDaughterYCoordinate += ltTree.getVerticalGap();
			} else {
				if (ltTree.getLexGlossGapAdjustment() > 0.0) {
					dDaughterYCoordinate = node.getYCoordinate();
				}
				dDaughterYCoordinate += ltTree.getLexGlossGapAdjustment();
			}
			calculateYCoordinateOfANode(daughterNode, dDaughterYCoordinate);
		}
	}

	public void calculateXCoordinateOfEveryNode() {
		ltTree.setHorizontalOffset(ltTree.getInitialXCoordinate());
		LingTreeNode node = ltTree.getRootNode();
		if (ltTree.isUseColumnOrientedAlgorithm()) {
			doDebugPrint("max width of nodes");
			calculateMaxWidthOfNodes(node);
			doDebugPrint("calculate x-coord and xmid");
			calculateXCoordinateAndXMidOfNodes(node, ltTree.getInitialXCoordinate());
			printNodeValues(node);
			ltTree.setXSize(node.getMaxWidthInColumn() + ltTree.getInitialXCoordinate());
		} else {
			calculateWidthOfEveryNode(node);
			calculateXCoordinateOfANode(node, 0);
		}
	}

	private void printNodeValues(LingTreeNode node) {
		for (LingTreeNode daughter : node.getDaughters()) {
			printNodeValues(daughter);
		}
		doDebugPrint("node is " + node.getContent());
		doDebugPrint("\tx-coord = " + node.getXCoordinate());
		doDebugPrint("\txmid    = " + node.getXMid());
		doDebugPrint("\twidth   = " + node.getWidth());
		doDebugPrint("\tmax     = " + node.getMaxWidthInColumn());

	}

	private void calculateWidthOfEveryNode(LingTreeNode node) {
		node.calculateWidth();
		for (LingTreeNode daughter : node.getDaughters()) {
			calculateWidthOfEveryNode(daughter);
		}
	}

	// The revised algorithm calculates the maximum width of a node in its column
	private double calculateMaxWidthOfNodes(LingTreeNode node) {
		doDebugPrint("\tcalculateMaxWidthOfNodes for " + node.getContent());
		node.calculateWidth();
		double dMaxWidthOfNode = node.getWidth();
		double dMaxWidthOfDaughters = 0.0;
		for (LingTreeNode daughter : node.getDaughters()) {
			dMaxWidthOfDaughters += calculateMaxWidthOfNodes(daughter);
			if (daughter.getRightSister() != null) {
				dMaxWidthOfDaughters += ltTree.getHorizontalGap();
			}
		}
		dMaxWidthOfNode = Math.max(dMaxWidthOfNode, dMaxWidthOfDaughters);
		node.setMaxWidthInColumn(dMaxWidthOfNode);
		node.setMaxWidthOfDaughters(dMaxWidthOfDaughters);
		doDebugPrint("\t\tset max width for " + node.getContent());
		doDebugPrint("\t\t\twidth     = "  + node.getWidth());
		doDebugPrint("\t\t\tmax width = "  + node.getMaxWidthInColumn());
		if (dMaxWidthOfDaughters < node.getWidth() && node.getDaughters().size() == 1) {
			setDaughtersMaxWidth(node.getDaughters().get(0), node.getWidth());
		}
		return dMaxWidthOfNode;
	}

	// When a higher node's width is wider than any of its daughters' width in its column,
	// we need to set the maximum width of the daughters to that node's maximum width
	// so the x-coordinate and x-mid values are correct.
	private void setDaughtersMaxWidth(LingTreeNode node, double newMaxWidth) {
		doDebugPrint("\t\t\tadjusting " + node.getContent() + " from " + node.getMaxWidthInColumn() + " to " + newMaxWidth);
		node.setMaxWidthInColumn(newMaxWidth);
		if (node.getDaughters().size() == 1) {
			setDaughtersMaxWidth(node.getDaughters().get(0), newMaxWidth);
		}
	}

	// We use the maximum width of a node in its column (and its daughter's width) to
	// set the x-coordinate and x-mid values.
	// The left offset value needs to be maintained for the x-coordinate of where the column begins.
	private void calculateXCoordinateAndXMidOfNodes(LingTreeNode node, double leftOffset) {
		double daughtersLeftOffset = leftOffset;
		if (node.getMaxWidthInColumn() > node.getMaxWidthOfDaughters() && node.getDaughters().size() > 1) {
			daughtersLeftOffset += (node.getMaxWidthInColumn() - node.getMaxWidthOfDaughters()) / 2;
		}
		for (int i = 0; i < node.getDaughters().size(); i++) {
			double gap = ltTree.getHorizontalGap();
			LingTreeNode daughter = node.getDaughters().get(i);
			if (i == 0) {
				gap = 0.0;
			}
			calculateXCoordinateAndXMidOfNodes(daughter, daughtersLeftOffset + gap);
			daughtersLeftOffset += daughter.getMaxWidthInColumn() + gap;
		}
		double dXMid = calculateXMidOfNode(node, leftOffset);
		node.setXMid(dXMid);
		node.setXCoordinate(dXMid - (node.getWidth() / 2));
		doDebugPrint("\tfor " + node.getContent());
		doDebugPrint("\t\tleft offset = " + leftOffset);
		doDebugPrint("\t\txmid        = "  + node.getXMid());
		doDebugPrint("\t\txcoord      = "  + node.getXCoordinate());
	}

	protected double calculateXMidOfNode(LingTreeNode node, double leftOffset) {
		double dXMid = leftOffset + (node.getMaxWidthInColumn() / 2);
		int numDaughters = node.getDaughters().size();
		doDebugPrint("for " + node.getContent());
		doDebugPrint("\tnumDaughters = " + numDaughters);
		if (ltTree.isCenterColumnOrientedOnDaughtersWidth() && numDaughters > 0) {
			LingTreeNode firstDaughter = node.getDaughters().get(0);
			doDebugPrint("\t\tfirst is " + firstDaughter.getContent());
			if (numDaughters > 1) {
				LingTreeNode lastDaughter = node.getDaughters().get(numDaughters - 1);
				double dWidthToUse = lastDaughter.getXMid() - firstDaughter.getXMid();
				dXMid = firstDaughter.getXMid() + (dWidthToUse / 2);
				doDebugPrint("\t\tfirst x-mid   = " + firstDaughter.getXMid());
				doDebugPrint("\t\tlast  x-mid   = " + lastDaughter.getXMid());
				doDebugPrint("\t\txmid  to use  = " + (lastDaughter.getXMid() - firstDaughter.getXMid()) / 2 + firstDaughter.getXMid());
			} else if (numDaughters == 1){
				dXMid = firstDaughter.getXMid();
				doDebugPrint("\t\tdXMid         = " + dXMid);
			}
		}
		return dXMid;
	}

	// Determine the X-axis coordinate for this node
	// It assumes that the width of this and all other nodes have
	// already been established.
	// It also assumes that higher branching nodes are not wider than the
	// total width of their daughters (which may not always be true...)
	private double calculateXCoordinateOfANode(LingTreeNode node, double dMaxColumnWidth) {
		node.setXMid(0);
		if (dMaxColumnWidth < node.getWidth()) {
			// remember widest node in the column
			dMaxColumnWidth = node.getWidth();
		}

		if (node.getDaughters().size() > 0) { // is a non-leaf node
			LingTreeNode firstDaughterNode = node.getDaughters().get(0);
			LingTreeNode nextDaughter = firstDaughterNode.getRightSister();
			double dLeftMost = calculateXCoordinateOfANode(firstDaughterNode, dMaxColumnWidth);
			double dRightMost = dLeftMost;
			while (nextDaughter != null) { // calculate coordinates for other
											// daughters
				dRightMost = calculateXCoordinateOfANode(nextDaughter, dMaxColumnWidth);
				nextDaughter = nextDaughter.getRightSister();
			}
			// take mid point between first & last daughter
			node.setXMid((dLeftMost + dRightMost) / 2);
			if (dRightMost > dLeftMost) {
				if (node.getWidth() > (dRightMost - dLeftMost)) {
					double dAdjust = (node.getWidth() - (dRightMost - dLeftMost)) / 2;
					node.setXMid(node.getXMid() + dAdjust);

					nextDaughter = firstDaughterNode;
					while (nextDaughter != null) {
						adjustXValues(nextDaughter, dAdjust);
						nextDaughter = nextDaughter.getRightSister();
					}
				}
			}
		} else { // is a leaf node
			// half the width of this column - Offset from last terminal
			// node plus
			// half the width of the widest node in this column - gap
			// between terminal nodes plus - have a new offset for next
			// terminal node

			// The mid point of this leaf node is the current horizontal offset
			// plus half of the widest node in the column.
			node.setXMid(ltTree.getHorizontalOffset() + dMaxColumnWidth / 2);
			// Update the current horizontal offset to be that mid point plus
			// half of the widest node in the column plus the gap between leaf
			// nodes.
			ltTree.setHorizontalOffset(node.getXMid() + ltTree.getHorizontalGap() + dMaxColumnWidth
					/ 2);
		}
		node.setXCoordinate(node.getXMid() - node.getWidth() / 2); // adjust for
																	// width of
																	// this node
		double dEnd = node.getXCoordinate() + node.getWidth();
		if (node.isTriangle()) {
			dEnd += ltTree.getHorizontalGap() / 2;
		}
		if (dEnd > ltTree.getXSize()) {
			ltTree.setXSize(dEnd); // Keep track of total width for scrolling
		}
		// System.out.printf(
		// "%1$s\tXSize = %2$s,\tWidth = %3$s,\tXCoord = %4$s,\tYCoord = %5$s, \tXMid = %5$s"
		// + "\r\n", node.getContent(), ltTree.getXSize(), node.getWidth(),
		// node.getXCoordinate(), node.getYCoordinate(), node.getXMid());
		// System.out.printf("\tYUpperMid = %1$s, \tYLowerMid = %2$s\r\n",
		// node.getYUpperMid(),
		// node.getYLowerMid());
		return node.getXMid();
	}

	private void adjustXValues(LingTreeNode node, double dAdjust) {
		// adjust this node
		node.setXCoordinate(node.getXCoordinate() + dAdjust);
		node.setXMid(node.getXMid() + dAdjust);
		ltTree.setHorizontalOffset(ltTree.getHorizontalOffset() + dAdjust);
		// adjust any daughter nodes
		for (LingTreeNode daughterNode : node.getDaughters()) {
			adjustXValues(daughterNode, dAdjust);
		}
	}

	private void adjustLexAndGlossNodesForFlatView() {
		if (ltTree.isShowFlatView()) { // adjust lex and gloss Y coordinates
			LingTreeNode node = ltTree.getRootNode();
			adjustLexOrGlossNodeForFlatView(node);
		}
	}

	private void adjustLexOrGlossNodeForFlatView(LingTreeNode node) {
		switch (node.getNodeType()) {
		case EmptyElement:
			// fall through
		case Lex:
			node.setYCoordinate(ltTree.getLexBottomYCoordinate());
			node.setYUpperMid(ltTree.getLexBottomYUpperMid());
			break;
		case Gloss:
			node.setYCoordinate(ltTree.getGlossBottomYCoordinate());
			break;
		default:
			break;
		}
		for (LingTreeNode daughterNode : node.getDaughters()) {
			adjustLexOrGlossNodeForFlatView(daughterNode);
		}
	}

	public void draw(Pane pane) {
		recalculateValues();
		LingTreeNode node = ltTree.getRootNode();
		drawNodes(node, pane);
		pane.setMinWidth(ltTree.getXSize() + 10);
		pane.setStyle("-fx-background-color:"
				+ StringUtilities.toRGBCode(ltTree.getBackgroundColor()) + ";");
	}

	private void drawNodes(LingTreeNode node, Pane pane) {
		if (node.hasAbbreviation()) {
			for (NodeText nt : node.getContentsAsList()) {
				pane.getChildren().add(nt.getTextBox());
			}
		} else {
			pane.getChildren().add(node.getContentTextBox());
		}
		if (node.hasSubscript()) {
			pane.getChildren().add(node.getSubscriptTextBox());
		}
		if (node.hasSuperscript()) {
			pane.getChildren().add(node.getSuperscriptTextBox());
		}
		if (node.hasMother() && !node.isOmitLine() && node.getNodeType() != NodeType.Gloss) {
			LingTreeNode mother = node.getMother();
			if (!node.isTriangle()) {
				// need to draw a line between mother and this node
				Line line = new Line(mother.getXMid(), mother.getYLowerMid(), node.getXMid(),
						node.getYUpperMid());
				line.setStroke(ltTree.getLineColor());
				line.setStrokeWidth(ltTree.getLineWidth());
				pane.getChildren().add(line);
			} else if (node.isTriangle()) {
				drawTriangle(node, pane, mother);
			}
		}
		if (ltTree.isDrawVerticalLineWithEmptyText() && node.getContentTextBox().getText().length() == 0) {
			// Need to draw a line from top to bottom of this node
			Line lineNode = new Line(node.getXMid(), node.getYLowerMid(), node.getXMid(),
					node.getYUpperMid());
			lineNode.setStroke(ltTree.getLineColor());
			lineNode.setStrokeWidth(ltTree.getLineWidth());
			pane.getChildren().add(lineNode);
		}
		for (LingTreeNode daughterNode : node.getDaughters()) {
			drawNodes(daughterNode, pane);
		}
	}

	private void drawTriangle(LingTreeNode node, Pane pane, LingTreeNode mother) {
		double dLeftmostX = node.getXCoordinate() + dTriangleOffset;
		double dRightmostX = node.getXCoordinate() + node.getWidth() - dTriangleOffset;
		double dTopX = mother.getXMid();
		double dBottomY = node.getYUpperMid();
		double dTopY = mother.getYLowerMid();
		Line rightPart = new Line(dLeftmostX, dBottomY, dTopX, dTopY);
		rightPart.setStroke(ltTree.getLineColor());
		rightPart.setStrokeWidth(ltTree.getLineWidth());
		pane.getChildren().add(rightPart);
		Line leftPart = new Line(dTopX, dTopY, dRightmostX, dBottomY);
		leftPart.setStroke(ltTree.getLineColor());
		leftPart.setStrokeWidth(ltTree.getLineWidth());
		pane.getChildren().add(leftPart);
		Line bottomPart = new Line(dLeftmostX, dBottomY, dRightmostX, dBottomY);
		bottomPart.setStroke(ltTree.getLineColor());
		bottomPart.setStrokeWidth(ltTree.getLineWidth());
		pane.getChildren().add(bottomPart);
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

	public StringBuilder drawAsCollaspibleSVG() {
		StringBuilder sb = new StringBuilder();
		try {
			recalculateValues();
			LingTreeNode node = ltTree.getRootNode();
			// Trying to convert from pixels to mm does not come out right.
			// So we're going with pixels
			// final String sMM = "mm";
			// sb.append(Constants.SVG_HEADER.replace("{0}",
			// pixelsToMM(ltTree.getXSize()) + sMM).replace(
			// "{1}", pixelsToMM(ltTree.getYSize()) + sMM));
			sb.append(Constants.SVG_COLLAPSIBLE_HEADER_BEGIN.replace("{0}", String.valueOf(ltTree.getXSize() + 10)).replace(
					"{1}", String.valueOf(ltTree.getYSize())));
//			File scriptFile = new File(Constants.RESOURCE_LOCATION + ".CollapsibleSVG.js");
			File scriptFile = new File(Constants.RESOURCE_SOURCE_LOCATION + "resources/CollapsibleSVG.js");
			String script;
			script = new String(Files.readString(scriptFile.toPath(), StandardCharsets.UTF_8));
			script = script.replace(" < ", " &lt; ");
			sb.append(script);
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

	private void recalculateValues() {
		calculateXCoordinateOfEveryNode();
		calculateMaxHeightPerLevel();
		calculateYCoordinateOfEveryNode();
		if (ltTree.isShowFlatView()
				&& ltTree.getGlossBottomYCoordinate() < ltTree.getLexBottomYCoordinate()) {
			double dNewGlossBottom = ltTree.getLexBottomYCoordinate()
					+ ltTree.getLexGlossGapAdjustment() + ltTree.getVerticalGap();
			ltTree.setGlossBottomYCoordinate(dNewGlossBottom);
			// now calculate the Y coordinate again
			calculateYCoordinateOfEveryNode();
			if (ltTree.getYSize() < dNewGlossBottom) {
				ltTree.setYSize(dNewGlossBottom);
			}
		}
		if (ltTree.isUseRightToLeftOrientation()) {
			adjustForRightToLeftOrientation();
		}
	}

	public void adjustForRightToLeftOrientation()
	{
		double adjust = ltTree.getXSize() + ltTree.getInitialXCoordinate();
		LingTreeNode node = ltTree.getRootNode();
		adjustForRightToLeftOrientation(node, adjust);
	}

	private void adjustForRightToLeftOrientation(LingTreeNode node, double adjust) {
		node.setXCoordinate((adjust - node.getWidth()) - node.getXCoordinate());
		node.setXMid(adjust - node.getXMid());
		for (LingTreeNode daughterNode : node.getDaughters()) {
			adjustForRightToLeftOrientation(daughterNode, adjust);
		}
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

	private void drawNodesAsCollapsibleSVG(LingTreeNode node, StringBuilder sb) {
		int id = node.hashCode();
		int motherId = (node.hasMother()) ? node.getMother().hashCode() : 0;
		if (node.getContentsAsList().size() > 0) {
			for (NodeText nt : node.getContentsAsList()) {
				createTextAsCollapsibleSVG(nt.getTextBox(), node, nt.getFontInfo(), sb, nt.hashCode());
				if (node.getDaughters().size() > 0) {
					createCollapsedTextAndTriangle(node, sb, nt.hashCode());
				}
//				if (node.getNodeType() != NodeType.Gloss) {
//					createCollapsedTextAndTriangle(node, sb, nt.hashCode());
//				}
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
			createTextAsCollapsibleSVG(sub, node, fontInfo, sb, sub.hashCode());
			createCollapsedTextAndTriangle(node, sb, sub.hashCode());
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
			createTextAsCollapsibleSVG(sup, node, fontInfo, sb, sup.hashCode());
			createCollapsedTextAndTriangle(node, sb, sup.hashCode());
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

	// for collapsed ellipsis
	private void drawEllipsisTriangleAsCollapsibleSVG(LingTreeNode mother, LingTreeNode node, StringBuilder sb, int id) {
		double dLeftmostX = node.getXMid() - dEllipsisXOffset;
		double dRightmostX = node.getXMid() + dEllipsisXOffset;
		double dTopX = node.getXMid();
		double dBottomY = node.getYLowerMid() + ltTree.getVerticalGap();
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
		if (!isNodeCollapsible(node)) {
			sb.append("\">");
		} else {
			sb.append("\" onclick=\"ProcessCollapsibleNode('node");
			sb.append(id);
			sb.append("')\"");
			sb.append(" lt:collapsed=\"false\"");
			if (node.getDaughters().size() > 0) {
				sb.append(" lt:collapsedNodes=\"");
				String collapsibleNodes = recordCollapsibleNodes(node);
				// remove final comma
				collapsibleNodes = collapsibleNodes.substring(0, collapsibleNodes.length()-1);
				sb.append(collapsibleNodes);
				sb.append("\"");
				String collapsibleLines = recordCollapsibleLines(node);
					sb.append(" lt:collapsedLines=\"");
					if (collapsibleLines.length() > 0) {
						// remove final comma
						collapsibleLines = collapsibleLines.substring(0, collapsibleLines.length() - 1);
					}
					sb.append(collapsibleLines);
				sb.append("\" visibility=\"visible\"");
			}
			sb.append(">");
		}
		sb.append(tb.getText().replace("<", "&lt;").replace(">", "&gt;").replace(" & ", " &amp; "));
		sb.append("</text>\n");
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
			sb.append("node" + node.getSubscriptTextBox().hashCode() + ",");
		}
		if (node.hasSuperscript()) {
			sb.append("node" + node.getSuperscriptTextBox().hashCode() + ",");
		}
		for (LingTreeNode daughter : node.getDaughters()) {
			if (daughter.getContentsAsList().size() > 0) {
				for (NodeText nt : daughter.getContentsAsList()) {
					sb.append("node" + nt.hashCode() + ",");
				}
			} else {
				sb.append("node" + daughter.hashCode() + ",");
			}
			sb.append(recordCollapsibleNodes(daughter));
		}
		return sb.toString();
	}

	private String recordCollapsibleLines(LingTreeNode node) {
		StringBuilder sb = new StringBuilder();
		for (LingTreeNode daughter : node.getDaughters()) {
			if (daughter.getNodeType() != NodeType.Gloss) {
				sb.append("line" + daughter.hashCode() + "-" + node.hashCode() + ",");
				// triangle case
				if (daughter.isTriangle()) {
					// include the other two lines
					sb.append("line" + daughter.hashCode() + "-" + node.hashCode() + "T2,");
					sb.append("line" + daughter.hashCode() + "-" + node.hashCode() + "T3,");
				}
				sb.append(recordCollapsibleLines(daughter));
			}
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
		int motherId = node.getMother().hashCode();
		// right part of line
		createLineAsCollapsibleSVG(dLeftmostX, dBottomY, dTopX, dTopY, sb, id, motherId, "");
		// left part of line
		createLineAsCollapsibleSVG(dTopX, dTopY, dRightmostX, dBottomY, sb, id, motherId, "T2");
		// bottom part of line
		createLineAsCollapsibleSVG(dLeftmostX, dBottomY, dRightmostX, dBottomY, sb, id, motherId, "T3");
	}

	private void doDebugPrint(String msg) {
		if (fDoDebugPrint) {
			System.out.println(msg);
		}
	}
}

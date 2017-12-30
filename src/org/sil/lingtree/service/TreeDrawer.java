/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import java.util.HashMap;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NodeType;

/**
 * @author Andy Black
 *
 */
public class TreeDrawer {
	LingTreeTree ltTree;
	HashMap<Integer, Double> maxHeightPerLevel = new HashMap<>();

	private static final double dYCoordAdjustment = 3; // adjustment value
	private static final double dTriangleOffset = 3;

	public TreeDrawer(LingTreeTree ltTree) {
		super();
		this.ltTree = ltTree;
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
		node.setYCoordinate(dVerticalOffset);
		node.setYLowerMid(node.getYLowerMid() + dYCoordAdjustment);
		node.setYUpperMid(node.getYUpperMid() - dYCoordAdjustment);
		if (node.getYLowerMid() > ltTree.getYSize()) {
			// Keep track of total height for scrolling
			ltTree.setYSize(node.getYLowerMid());
		}
		if (node.getNodeType() == NodeType.Lex) {
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
					+ maxHeightPerLevel.get(node.getLevel());
			if (daughterNode.getNodeType() != NodeType.Gloss) {
				dDaughterYCoordinate += ltTree.getVerticalGap();
			} else {
				dDaughterYCoordinate += ltTree.getLexGlossGapAdjustment();
			}
			calculateYCoordinateOfANode(daughterNode, dDaughterYCoordinate);
		}
	}

	public void calculateXCoordinateOfEveryNode() {
		LingTreeNode node = ltTree.getRootNode();
		calculateXCoordinateOfANode(node, 0);
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
		System.out.printf(
				"%1$s\tXSize = %2$s,\tWidth = %3$s,\tXCoord = %4$s,\tYCoord = %5$s, \tXMid = %5$s"
						+ "\r\n", node.getContent(), ltTree.getXSize(), node.getWidth(),
				node.getXCoordinate(), node.getYCoordinate(), node.getXMid());
		System.out.printf("\tYUpperMid = %1$s, \tYLowerMid = %2$s\r\n", node.getYUpperMid(),
				node.getYLowerMid());
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
		calculateMaxHeightPerLevel();
		calculateYCoordinateOfEveryNode();
		calculateXCoordinateOfEveryNode();
		LingTreeNode node = ltTree.getRootNode();
		drawNodes(node, pane);
	}

	private void drawNodes(LingTreeNode node, Pane pane) {
		pane.getChildren().add(node.getContentTextBox());
		if (node.hasMother() && !node.isOmitLine() && node.getNodeType() != NodeType.Gloss) {
			LingTreeNode mother = node.getMother();
			if (!node.isTriangle()) {
				// need to draw a line between mother and this node
				Line line = new Line(mother.getXMid(), mother.getYLowerMid(), node.getXMid(),
						node.getYUpperMid());
				pane.getChildren().add(line);
			} else if (node.isTriangle()) {
				drawTriangle(node, pane, mother);
			}
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
		pane.getChildren().add(rightPart);
		Line leftPart = new Line(dTopX, dTopY, dRightmostX, dBottomY);
		pane.getChildren().add(leftPart);
		Line bottomPart = new Line(dLeftmostX, dBottomY, dRightmostX, dBottomY);
		pane.getChildren().add(bottomPart);
	}
}

/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.NodeType;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class LingTreeNode {

	private ObservableList<LingTreeNode> daughters = FXCollections.observableArrayList();
	private NodeType nodeType = NodeType.values()[0]; // node type
														// (non-terminal, lex,
														// gloss)

	Text contentTextBox = new Text(0, 0, ""); // content of the node
	Text subscriptTextBox = new Text(0, 0, ""); // subscript at the end of the
												// node content
	Text superscriptTextBox = new Text(0, 0, ""); // superscript at the end of
													// the node content

	private int iLevel; // level (or depth) of the node within the tree

	private boolean fTriangle; // draw triangle instead of line
	private boolean fOmitLine; // no line above node

	// TODO: why these and not a (Observable?) List<LingTreeNode>? or some such?
	// private LingTreeNode daughter; // leftmost daughter of this node in the
	// tree
	private LingTreeNode rightSister; // immediate sister to the right of this
										// node in the tree
	private LingTreeNode mother; // mother of this node in the tree

	private double dHeight; // height of the node
	private double dWidth; // width of the node

	private int iIndex; // index of node within its tree
	private double dXCoord; // left horizontal position of the node
	private double dXMid; // mid horizontal position of the node
	private double dYCoord; // upper vertical position of the node
	private double dYLowerMid; // lower mid position of the node for drawing a
								// line below the node
	private double dYUpperMid; // upper mid position of the node for drawing a
								// line above the node
	private String id; // id of the node (used, e.g., by PcPatrBrowser to refer
						// to a rule id)

	// TODO: what are these for?
	private static final double dYCoordAdjustment = 40; // adjustment value
	private static final double dTriangleOffset = 300;

	public String getContent() {
		return contentTextBox.getText();
	}

	public void setContent(String content) {
		contentTextBox.setText(content);
		changeFontInfo();
	}

	public String getSubscript() {
		return subscriptTextBox.getText();
	}

	public void setSubscript(String subscript) {
		subscriptTextBox.setText(subscript);
		FontInfo fontInfo = SubscriptFontInfo.getInstance();
		subscriptTextBox.setFont(fontInfo.getFont());
		subscriptTextBox.setFill(fontInfo.getColor());
	}

	public String getSuperscript() {
		return superscriptTextBox.getText();
	}

	public void setSuperscript(String superscript) {
		superscriptTextBox.setText(superscript);
		FontInfo fontInfo = SuperscriptFontInfo.getInstance();
		superscriptTextBox.setFont(fontInfo.getFont());
		superscriptTextBox.setFill(fontInfo.getColor());
	}

	public Text getContentTextBox() {
		return contentTextBox;
	}

	public Text getSubscriptTextBox() {
		return subscriptTextBox;
	}

	public Text getSuperscriptTextBox() {
		return superscriptTextBox;
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
		changeFontInfo();
	}

	public boolean isTriangle() {
		return fTriangle;
	}

	public void setTriangle(boolean fTriangle) {
		this.fTriangle = fTriangle;
	}

	public boolean isOmitLine() {
		return fOmitLine;
	}

	public void setOmitLine(boolean fOmitLine) {
		this.fOmitLine = fOmitLine;
	}

	public LingTreeNode getRightSister() {
		return rightSister;
	}

	public void setRightSister(LingTreeNode sister) {
		this.rightSister = sister;
	}

	public LingTreeNode getMother() {
		return mother;
	}

	public void setMother(LingTreeNode mother) {
		this.mother = mother;
	}

	public int getiLevel() {
		return iLevel;
	}

	public void setiLevel(int iLevel) {
		this.iLevel = iLevel;
	}

	public ObservableList<LingTreeNode> getDaughters() {
		return daughters;
	}

	public void setDaughters(ObservableList<LingTreeNode> daughters) {
		this.daughters = daughters;
	}

	public double getHeight() {
		final double dSubscriptPercentage = 0.333333;
		final double dSuperscriptPercentage = 0.333333;
		// dHeight = contentTextBox.getBoundsInLocal().getHeight();
		FontInfo fontInfo = getFontInfoFromNodeType();
		double dContentFontSize = fontInfo.getFontSize();
		double dSubscriptHeightAdjust = (subscriptTextBox.getText().length() > 0) ? dSubscriptPercentage
				* dContentFontSize
				: 0.0;
		double dSuperscriptHeightAdjust = (superscriptTextBox.getText().length() > 0) ? dSuperscriptPercentage
				* dContentFontSize
				: 0.0;
		dHeight = contentTextBox.getBoundsInLocal().getHeight() + dSubscriptHeightAdjust
				+ dSuperscriptHeightAdjust;

		// double d = contentTextBox.getLayoutBounds().getHeight();
		// System.out.println("bounds in loc height=" + dHeight);
		// System.out.println("layout bounds height=" + d);
		return dHeight;
	}

	public double getWidth() {
		double dSubscript = subscriptTextBox.getBoundsInLocal().getWidth();
		double dSuperscript = superscriptTextBox.getBoundsInLocal().getWidth();
		double dAdjust = Math.max(dSubscript, dSuperscript);
		dWidth = contentTextBox.getBoundsInLocal().getWidth() + dAdjust;
		// double d = contentTextBox.getLayoutBounds().getWidth();
		// System.out.println("bounds in loc width=" + dWidth);
		// System.out.println("layout bounds width=" + d);
		return dWidth;
	}

	private void changeFontInfo() {
		FontInfo fontInfo = getFontInfoFromNodeType();
		contentTextBox.setFont(fontInfo.getFont());
		contentTextBox.setFill(fontInfo.getColor());
	}

	private FontInfo getFontInfoFromNodeType() {
		FontInfo fontInfo;
		switch (nodeType) {

		case Gloss:
			fontInfo = GlossFontInfo.getInstance();
			break;

		case Lex:
			fontInfo = LexFontInfo.getInstance();
			break;

		default:
			fontInfo = NonTerminalFontInfo.getInstance();
			break;
		}
		;
		return fontInfo;
	}

	private void adjustHeightForSubscript() {
		FontInfo fontInfo = getFontInfoFromNodeType();
		double dContentFontSize = fontInfo.getFontSize();
		double dHeightAdjust = .33333 * dContentFontSize;
		dHeight = contentTextBox.getBoundsInLocal().getHeight() + dHeightAdjust;
	}
}

/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import org.sil.lingtree.Constants;
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
	// node type (non-terminal, lex, gloss)
	private NodeType nodeType = NodeType.values()[0];
	// content of the node
	Text contentTextBox = new Text(0, 0, "");
	// subscript at the end of the node content
	Text subscriptTextBox = new Text(0, 0, "");
	// superscript at the end of the node content
	Text superscriptTextBox = new Text(0, 0, "");
	boolean fSubscriptRegular = true;
	boolean fSuperscriptRegular = true;

	private int iLevel; // level (or depth) of the node within the tree

	private boolean fTriangle; // draw triangle instead of line
	private boolean fOmitLine; // no line above node

	// TODO: why these and not a (Observable?) List<LingTreeNode>? or some such?
	// private LingTreeNode daughter; // leftmost daughter of this node in the
	// tree
	// immediate sister to the right of this node in the tree
	private LingTreeNode rightSister;
	// mother of this node in the tree
	private LingTreeNode mother;

	private double dHeight; // height of the node
	private double dWidth; // width of the node

	private int iIndex; // index of node within its tree
	// left horizontal position of the node
	private double dXCoordinate;
	// mid horizontal position of the node
	private double dXMid;
	// upper vertical position of the node
	private double dYCoordinate;
	// lower mid position of the node for drawing a line below the node
	private double dYLowerMid;
	// upper mid position of the node for drawing a line above the node
	private double dYUpperMid;
	// id of the node (used, e.g., by PcPatrBrowser to refer to a rule id)
	private String id;

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
		FontInfo fontInfo = getFontInfoForSubscript();
		subscriptTextBox.setFont(fontInfo.getFont());
		subscriptTextBox.setFill(fontInfo.getColor());
	}

	public String getSuperscript() {
		return superscriptTextBox.getText();
	}

	public void setSuperscript(String superscript) {
		superscriptTextBox.setText(superscript);
		FontInfo fontInfo = getFontInfoForSuperscript();
		superscriptTextBox.setFont(fontInfo.getFont());
		superscriptTextBox.setFill(fontInfo.getColor());
	}

	public boolean isSubscriptRegular() {
		return fSubscriptRegular;
	}

	public void setSubscriptRegular(boolean subscriptRegular) {
		this.fSubscriptRegular = subscriptRegular;
	}

	public boolean isSuperscriptRegular() {
		return fSuperscriptRegular;
	}

	public void setSuperscriptRegular(boolean superscriptRegular) {
		this.fSuperscriptRegular = superscriptRegular;
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

	public int getLevel() {
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

	public boolean hasMother() {
		return (mother == null) ? false : true;
	}

	public boolean hasSubscript() {
		return (subscriptTextBox.getText().length() == 0) ? false : true;
	}

	public boolean hasSuperscript() {
		return (superscriptTextBox.getText().length() == 0) ? false : true;
	}

	public FontInfo getFontInfoForSubscript() {
		return getFontInfoForSubOrSuperscript(isSubscriptRegular());
	}

	private FontInfo getFontInfoForSubOrSuperscript(boolean fIsRegular) {
		FontInfo fontInfo = getFontInfoFromNodeType();
		double newSize = fontInfo.getFontSize()
				* Constants.SUB_SUPER_SCRIPT_FONT_SIZE_FACTOR;
		FontInfo newFontInfo = new FontInfo(fontInfo.getFontFamily(), newSize,
				fIsRegular ? "Regular" : "Italic");
		newFontInfo.setColor(fontInfo.getColor());
		return newFontInfo;
	}

	public FontInfo getFontInfoForSuperscript() {
		return getFontInfoForSubOrSuperscript(isSuperscriptRegular());
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

	public FontInfo getFontInfoFromNodeType() {
		FontInfo fontInfo;
		switch (nodeType) {

		case EmptyElement:
			fontInfo = EmptyElementFontInfo.getInstance();
			break;

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
		return fontInfo;
	}

	public double getXCoordinate() {
		return dXCoordinate;
	}

	public void setXCoordinate(double dXCoordinate) {
		this.dXCoordinate = dXCoordinate;
		contentTextBox.setX(dXCoordinate);
		double dContentWidth = contentTextBox.getBoundsInLocal().getWidth();
		if (hasSubscript()) {
			subscriptTextBox.setX(dXCoordinate + dContentWidth);
		}
		if (hasSuperscript()) {
			superscriptTextBox.setX(dXCoordinate + dContentWidth);
		}
	}

	public double getXMid() {
		return dXMid;
	}

	public void setXMid(double dXMid) {
		this.dXMid = dXMid;
	}

	public double getYCoordinate() {
		return dYCoordinate;
	}

	public void setYCoordinate(double dYCoordinate) {
		// the baseline y coordinate
		this.dYCoordinate = dYCoordinate;
		contentTextBox.setY(dYCoordinate);
		if (hasSubscript()) {
			subscriptTextBox.setY(dYCoordinate + adjustHeightForSubscript());
		}
		if (hasSuperscript()) {
			superscriptTextBox.setY(dYCoordinate - adjustHeightForSuperscript());
		}
		// TODO: fix subscript and superscript text boxes
		// set y upper mid and lower mid (top of box and bottom of box)
		dYUpperMid = contentTextBox.getLayoutBounds().getMinY();
		dYLowerMid = contentTextBox.getLayoutBounds().getMaxY();
	}

	public double getYLowerMid() {
		return dYLowerMid;
	}

	public void setYLowerMid(double dYLowerMid) {
		this.dYLowerMid = dYLowerMid;
	}

	public double getYUpperMid() {
		return dYUpperMid;
	}

	public void setYUpperMid(double dYUpperMid) {
		this.dYUpperMid = dYUpperMid;
	}

	private double adjustHeightForSubscript() {
		FontInfo fontInfo = getFontInfoFromNodeType();
		double dContentFontSize = fontInfo.getFontSize();
		double dHeightAdjust = .14 * dContentFontSize;
		// dHeight = contentTextBox.getBoundsInLocal().getHeight() +
		// dHeightAdjust;
		return dHeightAdjust;
	}

	private double adjustHeightForSuperscript() {
		FontInfo fontInfo = getFontInfoFromNodeType();
		double dContentFontSize = fontInfo.getFontSize();
		double dHeightAdjust = .33333 * dContentFontSize;
		// dHeight = contentTextBox.getBoundsInLocal().getHeight() -
		// dHeightAdjust;
		return dHeightAdjust;
	}
}

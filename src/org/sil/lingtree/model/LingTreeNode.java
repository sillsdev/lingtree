/**
 * Copyright (c) 2016-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import java.util.ArrayList;
import java.util.List;

import org.sil.lingtree.Constants;
import org.sil.lingtree.model.LingTreeNode;

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
	int lineNumInDescription;
	int characterPositionInLine;

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
	// custom font info for this node
	private FontInfo customFontInfo = null;

	private double dHeight; // height of the node
	private double dWidth; // width of the node
	private double dMaxWidthInColumn; // maximum width of this node in its column
	private double dMaxWidthOfDaughters; // maximum width of this node's daughters

//	private int iIndex; // index of node within its tree
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
//	private String id;

	List<NodeText> contentsAsList = new ArrayList<NodeText>();
	private boolean hasAbbreviation;

	public List<NodeText> getContentsAsList() {
		return contentsAsList;
	}

	public boolean hasAbbreviation() {
		return hasAbbreviation;
	}

	public void setHasAbbreviation(boolean hasAbbreviation) {
		this.hasAbbreviation = hasAbbreviation;
	}

	public boolean hasCustomFontInfo() {
		return customFontInfo != null;
	}

	public String getContent() {
		return contentTextBox.getText();
	}

	public void setContent(String content) {
		changeFontInfo();
		contentTextBox.setText(content);
	}

	public String getSubscript() {
		return subscriptTextBox.getText();
	}

	public void setSubscript(String subscript) {
		FontInfo fontInfo = getFontInfoForSubscript();
		subscriptTextBox.setFont(fontInfo.getFont());
		subscriptTextBox.setFill(fontInfo.getColor());
		subscriptTextBox.setText(subscript);
	}

	public String getSuperscript() {
		return superscriptTextBox.getText();
	}

	public void setSuperscript(String superscript) {
		FontInfo fontInfo = getFontInfoForSuperscript();
		superscriptTextBox.setFont(fontInfo.getFont());
		superscriptTextBox.setFill(fontInfo.getColor());
		superscriptTextBox.setText(superscript);
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
		if (hasAbbreviation()) {
			getHeightOfNodeWithAbbreviation();
		} else {
			getHeightOfRegularNode();
		}
	return dHeight;
	}

	protected void getHeightOfNodeWithAbbreviation() {
		dHeight = 0.0;
		for (NodeText nt : getContentsAsList()) {
			double dNtHeight = nt.getTextBox().getBoundsInLocal().getHeight();
			if (dNtHeight > dHeight) {
				dHeight = dNtHeight;
			}
		}
	}

	protected void getHeightOfRegularNode() {
		final double dSubscriptPercentage = 0.333333;
		final double dSuperscriptPercentage = 0.333333;
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
	}

	public double calculateWidth() {
		if (hasAbbreviation()) {
			getWidthOfNodeWithAbbreviation();
		} else {
			getWidthOfRegularNode();
		}
		return dWidth;
	}

	public double getWidth() {
		return dWidth;
	}

	public void setWidth(double dWidth) {
		this.dWidth = dWidth;
	}


	/**
	 * @return the dMaxWidthInColumn
	 */
	public double getMaxWidthInColumn() {
		return dMaxWidthInColumn;
	}

	/**
	 * @param dMaxWidthInColumn the dMaxWidthInColumn to set
	 */
	public void setMaxWidthInColumn(double dMaxWidthInColumn) {
		this.dMaxWidthInColumn = dMaxWidthInColumn;
	}

	public double getMaxWidthOfDaughters() {
		return dMaxWidthOfDaughters;
	}

	public void setMaxWidthOfDaughters(double dMaxWidthOfDaughters) {
		this.dMaxWidthOfDaughters = dMaxWidthOfDaughters;
	}

	protected void getWidthOfNodeWithAbbreviation() {
		dWidth = 0.0;
		for (NodeText nt : getContentsAsList()) {
			dWidth += nt.getTextBox().getBoundsInLocal().getWidth();
		}
	}

	protected double getWidthOfRegularNode() {
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
		contentTextBox.setText(getContent());
	}

	public FontInfo getFontInfoFromNodeType() {
		FontInfo fontInfo;
		if (hasCustomFontInfo()) {
			return customFontInfo;
		}
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
		if (hasAbbreviation()) {
			double dX = dXCoordinate;
			for (NodeText nt : getContentsAsList()) {
				nt.getTextBox().setX(dX);
				dX += nt.getTextBox().getBoundsInLocal().getWidth();
			}
		} else {
			contentTextBox.setX(dXCoordinate);
			double dContentWidth = contentTextBox.getBoundsInLocal().getWidth();
			if (hasSubscript()) {
				subscriptTextBox.setX(dXCoordinate + dContentWidth);
			}
			if (hasSuperscript()) {
				superscriptTextBox.setX(dXCoordinate + dContentWidth);
			}
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
		if (hasAbbreviation()) {
			dYUpperMid = 100000.0;
			dYLowerMid = 0.0;
			for (NodeText nt : getContentsAsList()) {
				nt.getTextBox().setY(dYCoordinate);
				dYUpperMid = Math.min(dYUpperMid, nt.getTextBox().getLayoutBounds().getMinY());
				dYLowerMid = Math.max(dYLowerMid, nt.getTextBox().getLayoutBounds().getMaxY());
			}
		} else {
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

	public FontInfo getCustomFontInfo() {
		return customFontInfo;
	}

	public void setCustomFontInfo(FontInfo fontInfo) {
		this.customFontInfo = fontInfo;
		setContent(getContent());
	}

	public int getLineNumInDescription() {
		return lineNumInDescription;
	}

	public void setLineNumInDescription(int lineNumInDescription) {
		this.lineNumInDescription = lineNumInDescription;
	}

	public int getCharacterPositionInLine() {
		return characterPositionInLine;
	}

	public void setCharacterPositionInLine(int characterPositionInLine) {
		this.characterPositionInLine = characterPositionInLine;
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

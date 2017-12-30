/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

public class LingTreeTree {
	LingTreeNode rootNode;

	double dInitialXCoordinate; // initial, leftmost X coordinate
	double dInitialYCoordinate; // initial, leftmost Y coordinate
	double dXSize; // total width of tree
	double dYSize; // total height of tree
	double dVerticalGap; // extra gap between levels

	double dHorizontalGap; // extra gap between leaf nodes
	double dHorizontalOffset; // current XCoord of last leaf node processed

	double dGlossBottomYCoordinate; // lowest Gloss Y Coordinate (for "flat" view)
	double dLexBottomYCoordinate; // lowest Lex Y Coordinate (for "flat" view)
	double dLexBottomYUpperMid; // lowest Lex Y upper mid (for "flat" view)
	double dLexGlossGapAdjustment; // extra gap adjustment between lex and gloss

	boolean fShowFlatView;

	/**
	 * 
	 */
	public LingTreeTree() {
		dInitialXCoordinate = 100;
		dInitialYCoordinate = 100;
		dVerticalGap = 20;
		dHorizontalGap = 30;
		dHorizontalOffset = 100;
		dLexGlossGapAdjustment = 0;
		fShowFlatView = false;
	}

	public LingTreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(LingTreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public double getInitialXCoordinate() {
		return dInitialXCoordinate;
	}

	public void setInitialXCoordinate(double dInitialXCoordinate) {
		this.dInitialXCoordinate = dInitialXCoordinate;
	}

	public double getInitialYCoordinate() {
		return dInitialYCoordinate;
	}

	public void setInitialYCoordinate(double dInitialYCoordinate) {
		this.dInitialYCoordinate = dInitialYCoordinate;
	}

	public double getXSize() {
		return dXSize;
	}

	public void setXSize(double dXSize) {
		this.dXSize = dXSize;
	}

	public double getYSize() {
		return dYSize;
	}

	public void setYSize(double dYSize) {
		this.dYSize = dYSize;
	}

	public double getGlossBottomYCoordinate() {
		return dGlossBottomYCoordinate;
	}

	public void setGlossBottomYCoordinate(double dGlossBottomYCoordinate) {
		this.dGlossBottomYCoordinate = dGlossBottomYCoordinate;
	}

	public double getLexBottomYCoordinate() {
		return dLexBottomYCoordinate;
	}

	public void setLexBottomYCoordinate(double dLexBottomYCoordinate) {
		this.dLexBottomYCoordinate = dLexBottomYCoordinate;
	}

	public double getLexBottomYUpperMid() {
		return dLexBottomYUpperMid;
	}

	public void setLexBottomYUpperMid(double dLexBottomYUpperMid) {
		this.dLexBottomYUpperMid = dLexBottomYUpperMid;
	}

	public double getLexGlossGapAdjustment() {
		return dLexGlossGapAdjustment;
	}

	public void setLexGlossGapAdjustment(double dLexGlossGapAdjustment) {
		this.dLexGlossGapAdjustment = dLexGlossGapAdjustment;
	}

	public double getVerticalGap() {
		return dVerticalGap;
	}

	public void setVerticalGap(double dVerticalGap) {
		this.dVerticalGap = dVerticalGap;
	}

	public double getHorizontalGap() {
		return dHorizontalGap;
	}

	public void setHorizontalGap(double dHorizontalGap) {
		this.dHorizontalGap = dHorizontalGap;
	}

	public double getHorizontalOffset() {
		return dHorizontalOffset;
	}

	public void setHorizontalOffset(double dHorizontalOffset) {
		this.dHorizontalOffset = dHorizontalOffset;
	}

	public boolean isShowFlatView() {
		return fShowFlatView;
	}

	public void setShowFlatView(boolean showFlatView) {
		this.fShowFlatView = showFlatView;
	}

}

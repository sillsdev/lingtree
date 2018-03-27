/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import javafx.scene.paint.Color;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sil.lingtree.Constants;

@XmlRootElement(name = "lingTreeTree")
public class LingTreeTree {
	LingTreeNode rootNode;
	private int version;
	private String description;

	double dInitialXCoordinate; // initial, leftmost X coordinate
	double dInitialYCoordinate; // initial, topmost Y coordinate
	double dXSize; // total width of tree
	double dYSize; // total height of tree
	double dVerticalGap; // extra gap between levels

	double dHorizontalGap; // extra gap between leaf nodes
	double dHorizontalOffset; // current XCoord of last leaf node processed

	double dGlossBottomYCoordinate; // lowest Gloss Y Coordinate (for "flat" view)
	double dLexBottomYCoordinate; // lowest Lex Y Coordinate (for "flat" view)
	double dLexBottomYUpperMid; // lowest Lex Y upper mid (for "flat" view)
	double dLexGlossGapAdjustment; // extra gap adjustment between lex and gloss

	FontInfo nonTerminalFontInfo;
	FontInfo lexicalFontInfo;
	FontInfo glossFontInfo;
	FontInfo emptyElementFontInfo;

	Color backgroundColor;
	Color lineColor;
	double lineWidth;

	boolean fSaveAsPng;
	boolean fSaveAsSVG;
	boolean fShowFlatView;
	boolean fUseRightToLeftOrientation;

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
		fUseRightToLeftOrientation = false;
		nonTerminalFontInfo = NonTerminalFontInfo.getInstance();
		lexicalFontInfo = LexFontInfo.getInstance();
		glossFontInfo = GlossFontInfo.getInstance();
		emptyElementFontInfo = EmptyElementFontInfo.getInstance();
		lineWidth = 10;
		lineColor = Color.BLACK;
		backgroundColor = Color.WHITE;
		version=Constants.CURRENT_DATABASE_VERSION;
	}

	@XmlTransient
	public LingTreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(LingTreeNode rootNode) {
		this.rootNode = rootNode;
	}

	@XmlElement(name = "initialXCoordinate")
	public double getInitialXCoordinate() {
		return dInitialXCoordinate;
	}

	public void setInitialXCoordinate(double dInitialXCoordinate) {
		this.dInitialXCoordinate = dInitialXCoordinate;
	}

	@XmlElement(name = "initialYCoordinate")
	public double getInitialYCoordinate() {
		return dInitialYCoordinate;
	}

	public void setInitialYCoordinate(double dInitialYCoordinate) {
		this.dInitialYCoordinate = dInitialYCoordinate;
	}

	@XmlTransient
	public double getXSize() {
		return dXSize;
	}

	public void setXSize(double dXSize) {
		this.dXSize = dXSize;
	}

	@XmlTransient
	public double getYSize() {
		return dYSize;
	}

	public void setYSize(double dYSize) {
		this.dYSize = dYSize;
	}

	@XmlTransient
	public double getGlossBottomYCoordinate() {
		return dGlossBottomYCoordinate;
	}

	public void setGlossBottomYCoordinate(double dGlossBottomYCoordinate) {
		this.dGlossBottomYCoordinate = dGlossBottomYCoordinate;
	}

	@XmlTransient
	public double getLexBottomYCoordinate() {
		return dLexBottomYCoordinate;
	}

	public void setLexBottomYCoordinate(double dLexBottomYCoordinate) {
		this.dLexBottomYCoordinate = dLexBottomYCoordinate;
	}

	@XmlTransient
	public double getLexBottomYUpperMid() {
		return dLexBottomYUpperMid;
	}

	public void setLexBottomYUpperMid(double dLexBottomYUpperMid) {
		this.dLexBottomYUpperMid = dLexBottomYUpperMid;
	}

	@XmlElement(name = "lexGlossGapAdjustment")
	public double getLexGlossGapAdjustment() {
		return dLexGlossGapAdjustment;
	}

	public void setLexGlossGapAdjustment(double dLexGlossGapAdjustment) {
		this.dLexGlossGapAdjustment = dLexGlossGapAdjustment;
	}

	@XmlElement(name = "verticalGap")
	public double getVerticalGap() {
		return dVerticalGap;
	}

	public void setVerticalGap(double dVerticalGap) {
		this.dVerticalGap = dVerticalGap;
	}

	@XmlElement(name = "horizontalGap")
	public double getHorizontalGap() {
		return dHorizontalGap;
	}

	public void setHorizontalGap(double dHorizontalGap) {
		this.dHorizontalGap = dHorizontalGap;
	}

	@XmlElement(name = "horizontalOffset")
	public double getHorizontalOffset() {
		return dHorizontalOffset;
	}

	public void setHorizontalOffset(double dHorizontalOffset) {
		this.dHorizontalOffset = dHorizontalOffset;
	}

	@XmlElement(name = "saveAsPng")
	public boolean isSaveAsPng() {
		return fSaveAsPng;
	}

	public void setSaveAsPng(boolean fSaveAsPng) {
		this.fSaveAsPng = fSaveAsPng;
	}

	@XmlElement(name = "saveAsSVG")
	public boolean isSaveAsSVG() {
		return fSaveAsSVG;
	}

	public void setSaveAsSVG(boolean fSaveAsSVG) {
		this.fSaveAsSVG = fSaveAsSVG;
	}

	@XmlElement(name = "showFlatView")
	public boolean isShowFlatView() {
		return fShowFlatView;
	}

	public void setShowFlatView(boolean showFlatView) {
		this.fShowFlatView = showFlatView;
	}

	@XmlElement(name = "useRightToLeftOrientation")
	public boolean isUseRightToLeftOrientation() {
		return fUseRightToLeftOrientation;
	}

	public void setUseRightToLeftOrientation(boolean useRightToLeftOrientation) {
		this.fUseRightToLeftOrientation = useRightToLeftOrientation;
	}

	@XmlElement(name = "nonTerminalFontInfo")
	public FontInfo getNonTerminalFontInfo() {
		return nonTerminalFontInfo;
	}

	public void setNonTerminalFontInfo(FontInfo nonTerminalFontInfo) {
		this.nonTerminalFontInfo = nonTerminalFontInfo;
	}

	@XmlElement(name = "lexicalFontInfo")
	public FontInfo getLexicalFontInfo() {
		return lexicalFontInfo;
	}

	public void setLexicalFontInfo(FontInfo lexicalFontInfo) {
		this.lexicalFontInfo = lexicalFontInfo;
	}

	@XmlElement(name = "glossFontInfo")
	public FontInfo getGlossFontInfo() {
		return glossFontInfo;
	}

	public void setGlossFontInfo(FontInfo glossFontInfo) {
		this.glossFontInfo = glossFontInfo;
	}

	@XmlElement(name = "emptyElementFontInfo")
	public FontInfo getEmptyElementFontInfo() {
		return emptyElementFontInfo;
	}

	public void setEmptyElementFontInfo(FontInfo emptyElementFontInfo) {
		this.emptyElementFontInfo = emptyElementFontInfo;
	}

	@XmlJavaTypeAdapter(ColorXmlAdaptor.class)
	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	@XmlElement(name = "lineWidth")
	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	@XmlJavaTypeAdapter(ColorXmlAdaptor.class)
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public int getVersion() {
		return version;
	}

	@XmlAttribute(name="dbversion")
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the description
	 */
	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Clear out all data in this language project
	 */
	public void clear() {
		rootNode = null;
		// TODO: set default values of parameters
	}

	/**
	 * @param ltTree
	 */
	public void load(LingTreeTree ltTree) {
		version = ltTree.getVersion();
	}

	public void setFontsAndColors() {
		EmptyElementFontInfo.getInstance().setFont(getEmptyElementFontInfo().getFont());
		EmptyElementFontInfo.getInstance().setColor(getEmptyElementFontInfo().getColor());
		GlossFontInfo.getInstance().setFont(getGlossFontInfo().getFont());
		GlossFontInfo.getInstance().setColor(getGlossFontInfo().getColor());
		LexFontInfo.getInstance().setFont(getLexicalFontInfo().getFont());
		LexFontInfo.getInstance().setColor(getLexicalFontInfo().getColor());
		NonTerminalFontInfo.getInstance().setFont(getNonTerminalFontInfo().getFont());
		NonTerminalFontInfo.getInstance().setColor(getNonTerminalFontInfo().getColor());
	}

}

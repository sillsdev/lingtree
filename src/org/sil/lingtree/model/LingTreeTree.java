/**
 * Copyright (c) 2016-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import javafx.scene.paint.Color;

import org.sil.lingtree.Constants;
import org.sil.utility.StringUtilities;
import org.sil.utility.service.keyboards.KeyboardInfo;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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

	double dMinimumXGapForExtraVerticalSpacing = 500.0; // when X space between sisters is above this value
	double dVerticalAdjustmentForExtraVerticalSpacing = 75.0; // add this extra vertical space between rows so lines appear better

	FontInfo nonTerminalFontInfo;
	FontInfo lexicalFontInfo;
	FontInfo glossFontInfo;
	FontInfo abbreviationFontInfo;
	FontInfo emptyElementFontInfo;

	KeyboardInfo lexicalKeyboard;
	KeyboardInfo glossKeyboard;
	KeyboardInfo nonTerminalKeyboard;
	KeyboardInfo emptyElementKeyboard;
	KeyboardInfo syntagmemeKeyboard;

	Color backgroundColor;
	Color lineColor;
	double lineWidth;

	boolean fSaveAsPng;
	boolean fSaveAsSVG;
	boolean fSaveAsCollapsibleSVG;
	boolean fShowFlatView;
	boolean fUseRightToLeftOrientation;
	boolean fDrawVerticalLineWithEmptyText;
	boolean fUseColumnOrientedAlgorithm = false;
	boolean fCenterColumnOrientedOnDaughtersWidth = false;

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
		nonTerminalKeyboard = NonTerminalKeyboard.getInstance();
		lexicalFontInfo = LexFontInfo.getInstance();
		lexicalKeyboard = LexicalKeyboard.getInstance();
		glossFontInfo = GlossFontInfo.getInstance();
		glossKeyboard = GlossKeyboard.getInstance();
		abbreviationFontInfo = AbbreviationFontInfo.getInstance();
		emptyElementFontInfo = EmptyElementFontInfo.getInstance();
		emptyElementKeyboard = EmptyElementKeyboard.getInstance();
		syntagmemeKeyboard = SyntagmemeKeyboard.getInstance();
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

	@XmlElement(name = "minimumXGapForExtraVerticalSpacing")
	public double getMinimumXGapForExtraVerticalSpacing() {
		return dMinimumXGapForExtraVerticalSpacing;
	}

	public void setMinimumXGapForExtraVerticalSpacing(double dMinimumXGapForExtraVerticalSpacing) {
		this.dMinimumXGapForExtraVerticalSpacing = dMinimumXGapForExtraVerticalSpacing;
	}

	@XmlElement(name = "verticalAdjustmentForExtraVerticalSpacing")
	public double getVerticalAdjustmentForExtraVerticalSpacing() {
		return dVerticalAdjustmentForExtraVerticalSpacing;
	}

	public void setVerticalAdjustmentForExtraVerticalSpacing(
			double dVerticalAdjustmentForExtraVerticalSpacing) {
		this.dVerticalAdjustmentForExtraVerticalSpacing = dVerticalAdjustmentForExtraVerticalSpacing;
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

	@XmlElement(name = "saveAsCollapsibleSVG")
	public boolean isSaveAsCollapsibleSVG() {
		return fSaveAsCollapsibleSVG;
	}

	public void setSaveAsCollapsibleSVG(boolean fSaveAsCollapsibleSVG) {
		this.fSaveAsCollapsibleSVG = fSaveAsCollapsibleSVG;
	}

	@XmlElement(name = "drawVerticalLineWithEmptyText")
	public boolean isDrawVerticalLineWithEmptyText() {
		return fDrawVerticalLineWithEmptyText;
	}

	public void setDrawVerticalLineWithEmptyText(boolean value) {
		this.fDrawVerticalLineWithEmptyText = value;
	}

	@XmlElement(name = "useColumnOrientedAlgorithm")
	public boolean isUseColumnOrientedAlgorithm() {
		return fUseColumnOrientedAlgorithm;
	}

	public void setUseColumnOrientedAlgorithm(boolean fUseColumnOrientedAlgorithm) {
		this.fUseColumnOrientedAlgorithm = fUseColumnOrientedAlgorithm;
	}

	@XmlElement(name = "centerColumnOrientedOnDaughtersWidth")
	public boolean isCenterColumnOrientedOnDaughtersWidth() {
		return fCenterColumnOrientedOnDaughtersWidth;
	}

	public void setCenterColumnOrientedOnDaughtersWidth(boolean fCenterColumnOrientedOnDaughtersWidth) {
		this.fCenterColumnOrientedOnDaughtersWidth = fCenterColumnOrientedOnDaughtersWidth;
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

	@XmlElement(name = "abbreviationFontInfo")
	public FontInfo getAbbreviationFontInfo() {
		return abbreviationFontInfo;
	}

	public void setAbbreviationFontInfo(FontInfo abbreviationFontInfo) {
		this.abbreviationFontInfo = abbreviationFontInfo;
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

	@XmlElement(name = "lexicalKeyboard")
	public KeyboardInfo getLexicalKeyboard() {
		return lexicalKeyboard;
	}

	public void setLexicalKeyboard(KeyboardInfo lexicalKeyboard) {
		this.lexicalKeyboard = lexicalKeyboard;
	}

	@XmlElement(name = "glossKeyboard")
	public KeyboardInfo getGlossKeyboard() {
		return glossKeyboard;
	}

	public void setGlossKeyboard(KeyboardInfo glossKeyboard) {
		this.glossKeyboard = glossKeyboard;
	}

	@XmlElement(name = "nonTerminalKeyboard")
	public KeyboardInfo getNonTerminalKeyboard() {
		return nonTerminalKeyboard;
	}

	public void setNonTerminalKeyboard(KeyboardInfo nonTerminalKeyboard) {
		this.nonTerminalKeyboard = nonTerminalKeyboard;
	}

	@XmlElement(name = "emptyElementKeyboard")
	public KeyboardInfo getEmptyElementKeyboard() {
		return emptyElementKeyboard;
	}

	public void setEmptyElementKeyboard(KeyboardInfo emptyElementKeyboard) {
		this.emptyElementKeyboard = emptyElementKeyboard;
	}

	@XmlElement(name = "synTagmemeKeyboard")
	public KeyboardInfo getSyntagmemeKeyboard() {
		return syntagmemeKeyboard;
	}

	public void setSyntagmemeKeyboard(KeyboardInfo syntagmemeKeyboard) {
		this.syntagmemeKeyboard = syntagmemeKeyboard;
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
		AbbreviationFontInfo.getInstance().setFont(getAbbreviationFontInfo().getFont());
		AbbreviationFontInfo.getInstance().setColor(getAbbreviationFontInfo().getColor());
		EmptyElementFontInfo.getInstance().setFont(getEmptyElementFontInfo().getFont());
		EmptyElementFontInfo.getInstance().setColor(getEmptyElementFontInfo().getColor());
		GlossFontInfo.getInstance().setFont(getGlossFontInfo().getFont());
		GlossFontInfo.getInstance().setColor(getGlossFontInfo().getColor());
		LexFontInfo.getInstance().setFont(getLexicalFontInfo().getFont());
		LexFontInfo.getInstance().setColor(getLexicalFontInfo().getColor());
		NonTerminalFontInfo.getInstance().setFont(getNonTerminalFontInfo().getFont());
		NonTerminalFontInfo.getInstance().setColor(getNonTerminalFontInfo().getColor());
	}

	public boolean canUseFlatTree() {
		if (StringUtilities.isNullOrEmpty(description)) {
			return false;
		}
		String[] itemsAroundLexicalNodes = description.split("\\([ \t]*\\\\L");
		if (itemsAroundLexicalNodes.length <= 1)
			return false;
		return true;
	}
}

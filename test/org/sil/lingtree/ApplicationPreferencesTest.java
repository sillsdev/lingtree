// Copyright (c) 2016-2024 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 

package org.sil.lingtree;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.lingtree.model.ColorXmlAdaptor;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.utility.view.JavaFXThreadingRule;

public class ApplicationPreferencesTest {

	@Rule
	public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

	ApplicationPreferences applicationPreferences;
	File fileLastUsed;
	String languageLastUsed;
	Stage windowStageLastUsed;
	boolean drawAsTypeLastUsed;
	boolean showFullFilePath;
	boolean showMatchingParenWithArrowKeysLastUsed;
	double treeDescriptionFontSizeLastUsed;
	double showMatchingParenDelayLastUsed;
	double splitPaneDividerPositionLastUsed;

	LingTreeTree ltTreeLastUsed;
	ColorXmlAdaptor adaptor;

	@Before
	public void setUp() throws Exception {
		adaptor = new ColorXmlAdaptor();
		applicationPreferences = new ApplicationPreferences(this);
		fileLastUsed = applicationPreferences.getLastOpenedFile();
		languageLastUsed = applicationPreferences.getLastLocaleLanguage();
		drawAsTypeLastUsed = applicationPreferences.getDrawAsType();
		showFullFilePath = applicationPreferences.getShowFullFilePath();
		showMatchingParenWithArrowKeysLastUsed = applicationPreferences.getShowMatchingParenWithArrowKeys();
		showMatchingParenDelayLastUsed = applicationPreferences.getShowMatchingParenDelay();
		splitPaneDividerPositionLastUsed = applicationPreferences.getLastSplitPaneDividerPosition();
		treeDescriptionFontSizeLastUsed = applicationPreferences.getTreeDescriptionFontSize();
		applicationPreferences.setLastOpenedFilePath("last opened file");
		applicationPreferences.setLastLocaleLanguage("en");
		windowStageLastUsed = new Stage();
		windowStageLastUsed = applicationPreferences.getLastWindowParameters(ApplicationPreferences.LAST_WINDOW, windowStageLastUsed, 400., 400.);

		ltTreeLastUsed = new LingTreeTree();
		applicationPreferences.getSavedTreeParameters(ltTreeLastUsed);
		applicationPreferences.setTreeDescriptionFontSize(12.0);
		applicationPreferences.setDrawAsType(false);
		applicationPreferences.setShowFullFilePath(false);
		applicationPreferences.setShowMatchingParenWithArrowKeys(false);
		applicationPreferences.setShowMatchingParenDelay(750.0);
		applicationPreferences.setLastSplitPaneDividerPosition(0.48);
	}

	@After
	public void tearDown() throws Exception {
		applicationPreferences.setLastOpenedFilePath(fileLastUsed);
		applicationPreferences.setLastLocaleLanguage(languageLastUsed);
		applicationPreferences.setDrawAsType(drawAsTypeLastUsed);
		applicationPreferences.setShowFullFilePath(showFullFilePath);
		applicationPreferences.setShowMatchingParenWithArrowKeys(showMatchingParenWithArrowKeysLastUsed);
		applicationPreferences.setShowMatchingParenDelay(showMatchingParenDelayLastUsed);
		applicationPreferences.setLastSplitPaneDividerPosition(splitPaneDividerPositionLastUsed);
		applicationPreferences.setTreeDescriptionFontSize(treeDescriptionFontSizeLastUsed);
		applicationPreferences.setLastWindowParameters(ApplicationPreferences.LAST_WINDOW, windowStageLastUsed);
		applicationPreferences.setSavedTreeParameters(ltTreeLastUsed);
	}

	@Test
	public void lastOpenedFile() {
		assertEquals("last opened file", "last opened file",
				applicationPreferences.getLastOpenedFilePath());
		applicationPreferences.setLastOpenedFilePath("mimi");
		assertEquals("last opened file", "mimi", applicationPreferences.getLastOpenedFilePath());
		try {
			File f = File.createTempFile("ApplicationPreferencesTest", null);
			applicationPreferences.setLastOpenedFilePath(f);
			String fPath = f.getPath();
			f.delete();
			assertEquals("file path", fPath, applicationPreferences.getLastOpenedFilePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void lastLanguage() {
		assertEquals("locale language", "en", applicationPreferences.getLastLocaleLanguage());
		applicationPreferences.setLastLocaleLanguage("es");
		assertEquals("locale language", "es", applicationPreferences.getLastLocaleLanguage());
	}

	@Test
	public void drawAsType() {
		assertEquals("draw as type", false, applicationPreferences.getDrawAsType());
		applicationPreferences.setDrawAsType(true);
		assertEquals("draw as type", true, applicationPreferences.getDrawAsType());
	}

	@Test
	public void showFullFilePath() {
		assertEquals("show full file path", false, applicationPreferences.getShowFullFilePath());
		applicationPreferences.setShowFullFilePath(true);
		assertEquals("show full file path", true, applicationPreferences.getShowFullFilePath());
	}

	@Test
	public void showMatchingParenWithArrowKeys() {
		assertEquals("show matching paren with arrow keys", false, applicationPreferences.getShowMatchingParenWithArrowKeys());
		applicationPreferences.setShowMatchingParenWithArrowKeys(true);
		assertEquals("show matching paren with arrow keys", true, applicationPreferences.getShowMatchingParenWithArrowKeys());
	}

	@Test
	public void showMatchingParenDelay() {
		assertEquals("show matching paren delay", 750.0, applicationPreferences.getShowMatchingParenDelay(), 0.0);
		applicationPreferences.setShowMatchingParenDelay(1000.0);;
		assertEquals("show matching paren delay", 1000.0, applicationPreferences.getShowMatchingParenDelay(), 0.0);
	}

	@Test
	public void splitPaneDividerPosition() {
		assertEquals("split pane divider position", 0.48, applicationPreferences.getLastSplitPaneDividerPosition(), 0.0);
		applicationPreferences.setLastSplitPaneDividerPosition(0.67);;
		assertEquals("split pane divider position", 0.67, applicationPreferences.getLastSplitPaneDividerPosition(), 0.0);
	}

	@Test
	public void treeDescriptionFontSize() {
		assertEquals("tree description font size", 12.0, applicationPreferences.getTreeDescriptionFontSize(), 0.0);
		applicationPreferences.setTreeDescriptionFontSize(16.0);;
		assertEquals("tree description font size", 16.0, applicationPreferences.getTreeDescriptionFontSize(), 0.0);
	}

	@Test
	public void lastMainWindow() {
		checkWindowParameters(560., 860., 20., 21., ApplicationPreferences.LAST_WINDOW);
	}

	public void checkWindowParameters(Double height, Double width, Double positionX,
			Double positionY, String sWindow) {
		Stage testSetStage = new Stage();
		Stage testGetStage = new Stage();
		testSetStage.setHeight(height);
		testSetStage.setWidth(width);
		testSetStage.setX(positionX);
		testSetStage.setY(positionY);
		testSetStage.setMaximized(false);
		applicationPreferences.setLastWindowParameters(sWindow, testSetStage);
		testGetStage = applicationPreferences.getLastWindowParameters(sWindow, testGetStage, 400., 400.);
		assertEquals(height, testGetStage.getHeight(), 0);
		assertEquals(width, testGetStage.getWidth(), 0);
		assertEquals(positionX, testGetStage.getX(), 0);
		assertEquals(positionY, testGetStage.getY(), 0);
		assertEquals(false, testGetStage.isMaximized());
		testSetStage.setMaximized(true);
		applicationPreferences.setLastWindowParameters(sWindow, testSetStage);
		testGetStage = applicationPreferences.getLastWindowParameters(sWindow, testGetStage, 400., 400.);
		assertEquals(height, testGetStage.getHeight(), 0);
		assertEquals(width, testGetStage.getWidth(), 0);
		assertEquals(positionX, testGetStage.getX(), 0);
		assertEquals(positionY, testGetStage.getY(), 0);
		assertEquals(true, testGetStage.isMaximized());
	}

	@Test
	public void treeParametersTest() throws Exception {
		// create set of values to store
		double horizontalGap = 250;
		double horizontalOffset = 300;
		double initialXCoordinate = 100;
		double initialYCoordinate = 104;
		double lexGlossGapAdjustment = 7;
		double minimumXGapForExtraVerticalSpacing = 7.0;
		double verticalAdjustmentForExtraVerticalSpacing = 7.0;
		double lineWidth = 10;
		double verticalGap = 25;
		boolean saveAsPng = true;
		boolean saveAsSVG = true;
		boolean showFlatView = true;
		Color backgroundColor = Color.ALICEBLUE;
		Color lineColor = Color.NAVY;
		Color abbreviationFontColor = Color.CORNSILK;
		String abbreviationFontFamily = "Charis SIL SmallCaps";
		double abbreviationFontSize = 8.0;
		String abbreviationFontType = "Italic";
		FontInfo abbreviationFontInfo = new FontInfo(abbreviationFontFamily, abbreviationFontSize, abbreviationFontType);
		abbreviationFontInfo.setColor(abbreviationFontColor);
		Color emptyElementFontColor = Color.CHARTREUSE;
		String emptyElementFontFamily = "Verdana";
		double emptyElementFontSize = 13.0;
		String emptyElementFontType = "Italic";
		FontInfo emptyElementFontInfo = new FontInfo(emptyElementFontFamily, emptyElementFontSize, emptyElementFontType);
		emptyElementFontInfo.setColor(emptyElementFontColor);
		Color glossFontColor = Color.BURLYWOOD;
		String glossFontFamily = "Verdana";
		double glossFontSize = 10.5;
		String glossFontType = "Italic";
		FontInfo glossFontInfo = new FontInfo(glossFontFamily, glossFontSize, glossFontType);
		glossFontInfo.setColor(glossFontColor);
		Color lexicalFontColor = Color.PURPLE;
		String lexicalFontFamily = "Charis SIL AmArea";
		double lexicalFontSize = 11;
		String lexicalFontType = "Bold";
		FontInfo lexicalFontInfo = new FontInfo(lexicalFontFamily, lexicalFontSize, lexicalFontType);
		lexicalFontInfo.setColor(lexicalFontColor);
		Color nonTerminalFontColor = Color.MAROON;
		String nonTerminalFontFamily = "Coppertone";
		double nonTerminalFontSize = 17.75;
		String nonTerminalFontType = "Bold";
		FontInfo nonTerminalFontInfo = new FontInfo(nonTerminalFontFamily, nonTerminalFontSize, nonTerminalFontType);
		nonTerminalFontInfo.setColor(nonTerminalFontColor);

		// store the values in a LingTreeTree object
		LingTreeTree ltTree = new LingTreeTree();
		ltTree.setHorizontalGap(horizontalGap);
		ltTree.setHorizontalOffset(horizontalOffset);
		ltTree.setInitialXCoordinate(initialXCoordinate);
		ltTree.setInitialYCoordinate(initialYCoordinate);
		ltTree.setLexGlossGapAdjustment(lexGlossGapAdjustment);
		ltTree.setMinimumXGapForExtraVerticalSpacing(minimumXGapForExtraVerticalSpacing);;
		ltTree.setVerticalAdjustmentForExtraVerticalSpacing(verticalAdjustmentForExtraVerticalSpacing);
		ltTree.setLineWidth(lineWidth);
		ltTree.setVerticalGap(verticalGap);
		ltTree.setSaveAsPng(saveAsPng);
		ltTree.setSaveAsSVG(saveAsSVG);
		ltTree.setShowFlatView(showFlatView);
		ltTree.setBackgroundColor(backgroundColor);
		ltTree.setLineColor(lineColor);
		ltTree.setAbbreviationFontInfo(abbreviationFontInfo);
		ltTree.setEmptyElementFontInfo(emptyElementFontInfo);
		ltTree.setGlossFontInfo(glossFontInfo);
		ltTree.setLexicalFontInfo(lexicalFontInfo);
		ltTree.setNonTerminalFontInfo(nonTerminalFontInfo);

		// save them as preferences
		applicationPreferences.setSavedTreeParameters(ltTree);

		// retrieve what is in the preferences
		LingTreeTree ltRetrieved = new LingTreeTree();
		applicationPreferences.getSavedTreeParameters(ltRetrieved);

		// compare results
		assertEquals(horizontalGap, ltRetrieved.getHorizontalGap(), 0.0);
		assertEquals(horizontalOffset, ltRetrieved.getHorizontalOffset(), 0.0);
		assertEquals(initialXCoordinate, ltRetrieved.getInitialXCoordinate(), 0.0);
		assertEquals(initialYCoordinate, ltRetrieved.getInitialYCoordinate(), 0.0);
		assertEquals(lexGlossGapAdjustment, ltRetrieved.getLexGlossGapAdjustment(), 0.0);
		assertEquals(minimumXGapForExtraVerticalSpacing, ltRetrieved.getMinimumXGapForExtraVerticalSpacing(), 0.0);
		assertEquals(verticalAdjustmentForExtraVerticalSpacing, ltRetrieved.getVerticalAdjustmentForExtraVerticalSpacing(), 0.0);
		assertEquals(lineWidth, ltRetrieved.getLineWidth(), 0.0);
		assertEquals(verticalGap, ltRetrieved.getVerticalGap(), 0.0);
		assertEquals(saveAsPng, ltRetrieved.isSaveAsPng());
		assertEquals(saveAsSVG, ltRetrieved.isSaveAsSVG());
		assertEquals(showFlatView, ltRetrieved.isShowFlatView());
		assertEquals(backgroundColor, ltRetrieved.getBackgroundColor());
		assertEquals(lineColor, ltRetrieved.getLineColor());
		FontInfo fontInfo = ltRetrieved.getAbbreviationFontInfo();
		assertEquals(abbreviationFontColor, fontInfo.getColor());
		assertEquals(abbreviationFontFamily, fontInfo.getFontFamily());
		assertEquals(abbreviationFontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(abbreviationFontType, fontInfo.getFontType());
		fontInfo = ltRetrieved.getEmptyElementFontInfo();
		assertEquals(emptyElementFontColor, fontInfo.getColor());
		assertEquals(emptyElementFontFamily, fontInfo.getFontFamily());
		assertEquals(emptyElementFontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(emptyElementFontType, fontInfo.getFontType());
		fontInfo = ltRetrieved.getGlossFontInfo();
		assertEquals(glossFontColor, fontInfo.getColor());
		assertEquals(glossFontFamily, fontInfo.getFontFamily());
		assertEquals(glossFontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(glossFontType, fontInfo.getFontType());
		fontInfo = ltRetrieved.getLexicalFontInfo();
		assertEquals(lexicalFontColor, fontInfo.getColor());
		assertEquals(lexicalFontFamily, fontInfo.getFontFamily());
		assertEquals(lexicalFontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(lexicalFontType, fontInfo.getFontType());
		fontInfo = ltRetrieved.getNonTerminalFontInfo();
		assertEquals(nonTerminalFontColor, fontInfo.getColor());
		assertEquals(nonTerminalFontFamily, fontInfo.getFontFamily());
		assertEquals(nonTerminalFontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(nonTerminalFontType, fontInfo.getFontType());
	}
}

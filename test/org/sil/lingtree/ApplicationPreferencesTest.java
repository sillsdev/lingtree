// Copyright (c) 2016-2018 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 

package org.sil.lingtree;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.lingtree.ApplicationPreferences;
import org.sil.lingtree.model.ColorXmlAdaptor;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.view.JavaFXThreadingRule;

import com.sun.javafx.collections.SetListenerHelper;

public class ApplicationPreferencesTest {

	@Rule
	public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

	ApplicationPreferences applicationPreferences;
	File fileLastUsed;
	String languageLastUsed;
	Stage windowStageLastUsed;

	LingTreeTree ltTreeLastUsed;
	ColorXmlAdaptor adaptor;

	@Before
	public void setUp() throws Exception {
		adaptor = new ColorXmlAdaptor();
		applicationPreferences = new ApplicationPreferences(this);
		fileLastUsed = applicationPreferences.getLastOpenedFile();
		languageLastUsed = applicationPreferences.getLastLocaleLanguage();
		applicationPreferences.setLastOpenedFilePath("last opened file");
		applicationPreferences.setLastLocaleLanguage("en");
		windowStageLastUsed = new Stage();
		windowStageLastUsed = applicationPreferences.getLastWindowParameters(ApplicationPreferences.LAST_WINDOW, windowStageLastUsed, 400., 400.);

		ltTreeLastUsed = new LingTreeTree();
		applicationPreferences.getSavedTreeParameters(ltTreeLastUsed);
	}

	@After
	public void tearDown() throws Exception {
		applicationPreferences.setLastOpenedFilePath(fileLastUsed);
		applicationPreferences.setLastLocaleLanguage(languageLastUsed);
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
		double lineWidth = 10;
		double verticalGap = 25;
		boolean saveAsPng = true;
		boolean saveAsSVG = true;
		boolean showFlatView = true;
		Color backgroundColor = Color.ALICEBLUE;
		Color lineColor = Color.NAVY;
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
		Color subscriptFontColor = Color.CYAN;
		String subscriptFontFamily = "Courier";
		double subscriptFontSize = 6.5;
		String subscriptFontType = "Bold Italic";
		FontInfo subscriptFontInfo = new FontInfo(subscriptFontFamily, subscriptFontSize, subscriptFontType);
		subscriptFontInfo.setColor(subscriptFontColor);
		Color superscriptFontColor = Color.TAN;
		String superscriptFontFamily = "Brookdale";
		double superscriptFontSize = 9;
		String superscriptFontType = "Italic Bold";
		FontInfo superscriptFontInfo = new FontInfo(superscriptFontFamily, superscriptFontSize, superscriptFontType);
		superscriptFontInfo.setColor(superscriptFontColor);

		// store the values in a LingTreeTree object
		LingTreeTree ltTree = new LingTreeTree();
		ltTree.setHorizontalGap(horizontalGap);
		ltTree.setHorizontalOffset(horizontalOffset);
		ltTree.setInitialXCoordinate(initialXCoordinate);
		ltTree.setInitialYCoordinate(initialYCoordinate);
		ltTree.setLexGlossGapAdjustment(lexGlossGapAdjustment);
		ltTree.setLineWidth(lineWidth);
		ltTree.setVerticalGap(verticalGap);
		ltTree.setSaveAsPng(saveAsPng);
		ltTree.setSaveAsSVG(saveAsSVG);
		ltTree.setShowFlatView(showFlatView);
		ltTree.setBackgroundColor(backgroundColor);
		ltTree.setLineColor(lineColor);
		ltTree.setGlossFontInfo(glossFontInfo);
		ltTree.setLexicalFontInfo(lexicalFontInfo);
		ltTree.setNonTerminalFontInfo(nonTerminalFontInfo);
		ltTree.setSubscriptFontInfo(subscriptFontInfo);
		ltTree.setSuperscriptFontInfo(superscriptFontInfo);

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
		assertEquals(lineWidth, ltRetrieved.getLineWidth(), 0.0);
		assertEquals(verticalGap, ltRetrieved.getVerticalGap(), 0.0);
		assertEquals(saveAsPng, ltRetrieved.isSaveAsPng());
		assertEquals(saveAsSVG, ltRetrieved.isSaveAsSVG());
		assertEquals(showFlatView, ltRetrieved.isShowFlatView());
		assertEquals(backgroundColor, ltRetrieved.getBackgroundColor());
		assertEquals(lineColor, ltRetrieved.getLineColor());
		FontInfo fontInfo = ltRetrieved.getGlossFontInfo();
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
		fontInfo = ltRetrieved.getSubscriptFontInfo();
		assertEquals(subscriptFontColor, fontInfo.getColor());
		assertEquals(subscriptFontFamily, fontInfo.getFontFamily());
		assertEquals(subscriptFontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(subscriptFontType, fontInfo.getFontType());
		fontInfo = ltRetrieved.getSuperscriptFontInfo();
		assertEquals(superscriptFontColor, fontInfo.getColor());
		assertEquals(superscriptFontFamily, fontInfo.getFontFamily());
		assertEquals(superscriptFontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(superscriptFontType, fontInfo.getFontType());
	}
}

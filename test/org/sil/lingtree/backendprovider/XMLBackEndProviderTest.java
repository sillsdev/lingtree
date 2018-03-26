// Copyright (c) 2016-2017 SIL International
// This software is licensed under the LGPL, version 2.1 or later
// (http://www.gnu.org/licenses/lgpl-2.1.html)
/**
 *
 */
package org.sil.lingtree.backendprovider;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.lingtree.Constants;
import org.sil.lingtree.backendprovider.XMLBackEndProvider;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.view.JavaFXThreadingRule;

/**
 * @author Andy Black
 *
 */
public class XMLBackEndProviderTest {

	XMLBackEndProvider xmlBackEndProvider;
	LingTreeTree ltTree;
	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		ltTree = new LingTreeTree();
		Locale locale = new Locale("en");
		xmlBackEndProvider = new XMLBackEndProvider(ltTree, locale);
		File file = new File(Constants.UNIT_TEST_DATA_FILE);
		xmlBackEndProvider.loadTreeDataFromFile(file);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void loadTreeDataFromFileTest() {
		checkLoadedData();
	}

	public void checkLoadedData() {
		ltTree = xmlBackEndProvider.getLingTree();
		assertNotNull(ltTree);
		assertEquals(2, ltTree.getVersion());
		assertEquals("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))", ltTree.getDescription());
		assertEquals(20, ltTree.getVerticalGap(), 0.0);
		assertEquals(30, ltTree.getHorizontalGap(), 0.0);
		assertEquals(10, ltTree.getInitialXCoordinate(), 0.0);
		assertEquals(20, ltTree.getInitialYCoordinate(), 0.0);
		assertEquals(136.275390625, ltTree.getHorizontalOffset(), 0.0);
		assertEquals(0.0, ltTree.getLexGlossGapAdjustment(), 0.0);
		assertEquals(true, ltTree.isShowFlatView());
		FontInfo fontInfo = ltTree.getNonTerminalFontInfo();
		checkFontInfo(fontInfo, "Times New Roman", 12.0, "Regular", Color.BLACK);
		fontInfo = ltTree.getLexicalFontInfo();
		checkFontInfo(fontInfo, "Charis SIL", 12.0, "Regular", Color.BLUE);
		fontInfo = ltTree.getGlossFontInfo();
		checkFontInfo(fontInfo, "Arial", 12.0, "Regular", Color.GREEN);
		fontInfo = ltTree.getEmptyElementFontInfo();
		checkFontInfo(fontInfo, "Verdana", 13.5, "Italic", Color.RED);
		assertEquals(Color.BLACK, ltTree.getLineColor());
		assertEquals(1, ltTree.getLineWidth(), 0.0);
		assertEquals(Color.WHITE, ltTree.getBackgroundColor());
	}
	
	private void checkFontInfo(FontInfo fontInfo, String family, double size, String type, Color color) {
		assertEquals(family, fontInfo.getFontFamily());
		assertEquals(size, fontInfo.getFontSize(), 0.0);
		assertEquals(type, fontInfo.getFontType());
		assertEquals(color, fontInfo.getColor());
	}

	@Test
	public void saveLanguageDataToFileTest() {
		File tempSaveFile = null;
		try {
			tempSaveFile = File.createTempFile("LingTreeTestSave", ".tre");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tempSaveFile != null) {
			tempSaveFile.deleteOnExit();
		}
		xmlBackEndProvider.saveTreeDataToFile(tempSaveFile);
		xmlBackEndProvider.loadTreeDataFromFile(tempSaveFile);
		checkLoadedData();
	}
}

// Copyright (c) 2016-2024 SIL International
// This software is licensed under the LGPL, version 2.1 or later
// (http://www.gnu.org/licenses/lgpl-2.1.html)
/**
 *
 */
package org.sil.lingtree.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.Keyboard;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.Constants;
import org.sil.lingtree.backendprovider.XMLBackEndProvider;
import org.sil.lingtree.service.DatabaseMigrator;
import org.sil.utility.view.JavaFXThreadingRule;
import org.sil.utility.StringUtilities;

/**
 * @author Andy Black
 *
 */
public class DatabaseMigratorTest {

	File databaseFile;
	DatabaseMigrator migrator;
	LingTreeTree ltTree;
	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// prime the pump by making version 1 be the same as version 000
		// because doMigration changes the content of the version 1 file
		Files.copy(Paths.get(Constants.UNIT_TEST_DATA_FILE_VERSION_000),
				Paths.get(Constants.UNIT_TEST_DATA_FILE_VERSION_001),
				StandardCopyOption.REPLACE_EXISTING);
		databaseFile = new File(Constants.UNIT_TEST_DATA_FILE_VERSION_001);
		migrator = new DatabaseMigrator(databaseFile);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// restore version 001 back to its initial state
		Files.copy(Paths.get(Constants.UNIT_TEST_DATA_FILE_VERSION_000),
				Paths.get(Constants.UNIT_TEST_DATA_FILE_VERSION_001),
				StandardCopyOption.REPLACE_EXISTING);
		Files.copy(Paths.get(Constants.UNIT_TEST_DATA_FILE_WITH_WEDGES_IN_DESCRIPTION_VERSION_000),
				Paths.get(Constants.UNIT_TEST_DATA_FILE_WITH_WEDGES_IN_DESCRIPTION_VERSION_001),
				StandardCopyOption.REPLACE_EXISTING);
	}

	@Test
	public void testGetVersion() {
		int version = migrator.getVersion();
		assertEquals(1, version);
		migrator.doMigrationOn(1);
		version = migrator.getVersion();
		assertEquals(2, version);
		migrator.doMigrationOn(2);
		version = migrator.getVersion();
		assertEquals(3, version);
		migrator.doMigrationOn(3);
		version = migrator.getVersion();
		assertEquals(4, version);
	}

	@Test
	public void testMigratorStepbyStep() {
		int version = migrator.getVersion();
		assertEquals(1, version);
		migrator.setDpi(96);
		migrator.doMigrationOn(1);
		version = migrator.getVersion();
		assertEquals(2, version);
		ltTree = new LingTreeTree();
		Locale locale = new Locale("en");
		XMLBackEndProvider xmlBackEndProvider = new XMLBackEndProvider(ltTree, locale);
		xmlBackEndProvider.loadTreeDataFromFile(databaseFile);
		ltTree = xmlBackEndProvider.getLingTree();
		assertEquals(2, ltTree.getVersion());
		FontInfo fontInfo;
		checkContentsForVersion2();

		migrator.doMigrationOn(2);
		xmlBackEndProvider.loadTreeDataFromFile(databaseFile);
		ltTree = xmlBackEndProvider.getLingTree();
		assertEquals(3, ltTree.getVersion());
		fontInfo = ltTree.getAbbreviationFontInfo();
		checkFontInfo(fontInfo, "Arial", 11.0, "Regular", Color.web("#6666ff"));

		migrator.doMigrationOn(3);
		xmlBackEndProvider.loadTreeDataFromFile(databaseFile);
		ltTree = xmlBackEndProvider.getLingTree();
		assertEquals(4, ltTree.getVersion());
		Keyboard kb = ltTree.getEmptyElementKeyboard();
		checkKeyboardInfo(kb, "English", "en", 0);
		kb = ltTree.getGlossKeyboard();
		checkKeyboardInfo(kb, "English", "en", 0);
		kb = ltTree.getLexicalKeyboard();
		checkKeyboardInfo(kb, "English", "en", 0);
		kb = ltTree.getNonTerminalKeyboard();
		checkKeyboardInfo(kb, "English", "en", 0);
		kb = ltTree.getSyntagmemeKeyboard();
		checkKeyboardInfo(kb, "English", "en", 0);
	}
	@Test
	public void testMigrator() {
		int version = migrator.getVersion();
		assertEquals(1, version);
		migrator.setDpi(96);
		migrator.doMigration();
		version = migrator.getVersion();
		assertEquals(4, version);
		ltTree = new LingTreeTree();
		Locale locale = new Locale("en");
		XMLBackEndProvider xmlBackEndProvider = new XMLBackEndProvider(ltTree, locale);
		xmlBackEndProvider.loadTreeDataFromFile(databaseFile);
		ltTree = xmlBackEndProvider.getLingTree();
		assertEquals(4, ltTree.getVersion());
		FontInfo fontInfo;
		checkContentsForVersion2();
		fontInfo = ltTree.getAbbreviationFontInfo();
		checkFontInfo(fontInfo, "Arial", 11.0, "Regular", Color.web("#6666ff"));
	}

	protected void checkContentsForVersion2() {
		String sColorHexCode = StringUtilities.toRGBCode(ltTree.getBackgroundColor());
		assertEquals("#ffffff", sColorHexCode);
		assertEquals(
				"(IP (DP/_m (D' (D'/s1 (D (\\L le (\\G FM)))\n" 
				+ "(NP (\\T \\L xuz noo (\\G my father))))\n"
				+ "(IP (\\L t/_j))))\n"
				+ "(IP (I' (I/_i (\\T \\L w-guu (\\G C-sow)))\n"
				+ "(VP (VP (DP (\\L t/_m))\n"
				+ "(V' (V (\\L t/_i)) (DP (\\T \\L bni (\\G seed)))))\n"
				+ "(IP/_j (I' (I/_k (\\L y-ra (\\G P-all)))\n"
				+ "(QP (DP/s1 (\\T \\L pro)) (Q' (Q (\\L t/_k))\n"
				+ "(DP/s2 (\\T \\L mee bzaan noo (\\G my brothers)))))))))))",
				ltTree.getDescription());
		FontInfo fontInfo = ltTree.getEmptyElementFontInfo();
		checkFontInfo(fontInfo, "Charis SIL", 13.0, "Bold", Color.BLACK);
		fontInfo = ltTree.getGlossFontInfo();
		checkFontInfo(fontInfo, "Arial", 11.0, "Regular", Color.web("#6666ff"));
		double value = ltTree.getHorizontalGap();
		assertEquals(3.78, value, 0.0);
		value = ltTree.getHorizontalOffset();
		assertEquals(13822.0, value, 0.0);
		value = ltTree.getInitialXCoordinate();
		assertEquals(3.78, value, 0.0);
		value = ltTree.getInitialYCoordinate();
		assertEquals(12.78, value, 0.0);
		value = ltTree.getLexGlossGapAdjustment();
		assertEquals(0.0, value, 0.0);
		fontInfo = ltTree.getLexicalFontInfo();
		checkFontInfo(fontInfo, "Charis SIL", 13.0, "Bold", Color.web("#000000"));
		assertEquals(Color.BLACK, ltTree.getLineColor());
		value = ltTree.getLineWidth();
		assertEquals(0.94, value, 0.0);
		fontInfo = ltTree.getNonTerminalFontInfo();
		checkFontInfo(fontInfo, "Times New Roman", 12.0, "Bold Italic", Color.web("#008000"));
		assertEquals(true, ltTree.isSaveAsPng());
		assertEquals(true, ltTree.isSaveAsSVG());
		assertEquals(false, ltTree.isShowFlatView());
		value = ltTree.getVerticalGap();
		assertEquals(11.34, value, 0.0);
	}

	private void checkFontInfo(FontInfo fontInfo, String fontFamily, double fontSize,
			String fontStyle, Color color) {
		assertEquals(fontFamily, fontInfo.getFontFamily());
		assertEquals(fontSize, fontInfo.getFontSize(), 0.0);
		assertEquals(fontStyle, fontInfo.getFontType());
		assertEquals(color, fontInfo.getColor());
		Font font = fontInfo.getFont();
		assertEquals(fontFamily, font.getFamily());
		assertEquals(fontSize, font.getSize(), 0.0);
		assertEquals(fontStyle, font.getStyle());
	}

	private void checkKeyboardInfo(Keyboard kb, String description, String locale, int winID) {
		assertEquals(description, kb.getDescription());
		assertEquals(locale, kb.getLocale());
		assertEquals(winID, kb.getWindowsLangID());
	}

	@Test
	public void testMigratorWedgesInDescription() throws IOException {
		Files.copy(Paths.get(Constants.UNIT_TEST_DATA_FILE_WITH_WEDGES_IN_DESCRIPTION_VERSION_000),
				Paths.get(Constants.UNIT_TEST_DATA_FILE_WITH_WEDGES_IN_DESCRIPTION_VERSION_001),
				StandardCopyOption.REPLACE_EXISTING);
		databaseFile = new File(Constants.UNIT_TEST_DATA_FILE_WITH_WEDGES_IN_DESCRIPTION_VERSION_001);
		migrator = new DatabaseMigrator(databaseFile);
		int version = migrator.getVersion();
		assertEquals(1, version);
		migrator.setDpi(96);
		migrator.doMigration();
		version = migrator.getVersion();
		assertEquals(4, version);
		ltTree = new LingTreeTree();
		Locale locale = new Locale("en");
		XMLBackEndProvider xmlBackEndProvider = new XMLBackEndProvider(ltTree, locale);
		xmlBackEndProvider.loadTreeDataFromFile(databaseFile);
		ltTree = xmlBackEndProvider.getLingTree();
		assertEquals(Constants.CURRENT_DATABASE_VERSION, ltTree.getVersion());
		String sColorHexCode = StringUtilities.toRGBCode(ltTree.getBackgroundColor());
		assertEquals("#ffffff", sColorHexCode);
		assertEquals(
				"(6. S (V (\\L co/S1 (\\G will play (<agt> (Subj))  )))(NP/s[SUBJ] (N (\\L tsih/S2 "
				+ "(\\G child ))))(S' (COMP (\\L na/S3 (\\G when )))(S (V (\\L ca guah/S1 (\\G ate (<agt, (Subj)) (pat> "
				+ "(\\(Obj\\)))  )))(NP/s[Subj] (N (\\L tsih/S2 (\\G child )))))))",
				ltTree.getDescription());
		FontInfo fontInfo = ltTree.getEmptyElementFontInfo();
		checkFontInfo(fontInfo, "Charis SIL", 12.0, "Regular", Color.web("#0000ff"));
		fontInfo = ltTree.getGlossFontInfo();
		checkFontInfo(fontInfo, "Times New Roman", 12.0, "Regular", Color.web("#008000ff"));
		double value = ltTree.getHorizontalGap();
		assertEquals(3.78, value, 0.0);
		value = ltTree.getHorizontalOffset();
		assertEquals(10310.0, value, 0.0);
		value = ltTree.getInitialXCoordinate();
		assertEquals(3.78, value, 0.0);
		value = ltTree.getInitialYCoordinate();
		assertEquals(12.78, value, 0.0);
		value = ltTree.getLexGlossGapAdjustment();
		assertEquals(3.78, value, 0.0);
		fontInfo = ltTree.getLexicalFontInfo();
		checkFontInfo(fontInfo, "Charis SIL", 12.0, "Regular", Color.web("#0000ffff"));
		assertEquals(Color.BLACK, ltTree.getLineColor());
		value = ltTree.getLineWidth();
		assertEquals(0.94, value, 0.0);
		fontInfo = ltTree.getNonTerminalFontInfo();
		checkFontInfo(fontInfo, "Times New Roman", 12.0, "Regular", Color.web("#ff0000ff"));
		assertEquals(true, ltTree.isSaveAsPng());
		assertEquals(true, ltTree.isSaveAsSVG());
		assertEquals(false, ltTree.isShowFlatView());
		value = ltTree.getVerticalGap();
		assertEquals(11.34, value, 0.0);
	}
}

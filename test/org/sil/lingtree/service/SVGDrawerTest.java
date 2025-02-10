/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sil.lingtree.Constants;
import org.sil.lingtree.model.LingTreeTree;

/**
 * 
 */
public class SVGDrawerTest extends ServiceBaseTest {

	SVGDrawer drawer;
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void drawAsSVGTest() {
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(S (NP< (\\L Juan> (\\G John))) (VP (V (\\L duerme & mas (\\G sleeps)))))", origTree);
		drawer = new SVGDrawer(ltTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		StringBuilder sb = drawer.drawAsSVG();
		String result = sb.toString();
		assertEquals(true, result.contains("NP&lt;"));
		assertEquals(true, result.contains("Juan&gt;"));
		assertEquals(true, result.contains("duerme &amp; "));

		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(Mascoian((Enxet Sur)(Enlhet Norte))((((Angaité)(Sanapaná)))((Kaskihá)(Toba-Maskoy))))", origTree);
        ltTree.setDrawVerticalLineWithEmptyText(true);
		drawer = new SVGDrawer(ltTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		sb = drawer.drawAsSVG();
		result = sb.toString();
		assertEquals(true, result.contains(">Mascoian<"));
		assertEquals(true, result.contains("Enxet Sur"));
		assertEquals(true, result.contains("Enlhet Norte"));
//		System.out.println("result='" + result + "'");
		assertEquals(true, result.contains("<line x1=\"166.2138671875\" y1=\"141.97265625\" x2=\"123.8681640625\" y2=\"158.228515625\" stroke=\"#000000\" stroke-width=\"10.0\"/>"));

		checkSVGContentFor("(S (NP/si (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))",
				"SubscriptPlain.svg", false);
		checkSVGContentFor(
				"(S (NP/f|b|i|s12.0|cmaroon/F/si (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))",
				"SubscriptNodeWithFont.svg", false);
		checkSVGContentFor("(S (NP/si/f|cpink|b/F (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))",
				"SubscriptSubscriptWithFont.svg", false);
		checkSVGContentFor(
				"(S (NP/f|b|i|s12.0|cmaroon/F/si/f|cpink|b/F (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))",
				"SubscriptNodeWithFontSubscriptWithFont.svg", false);

		checkSVGContentFor("(S (NP/Si (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))",
				"SuperscriptPlain.svg", false);
		checkSVGContentFor(
				"(S (NP/f|b|i|s12.0|cmaroon/F/Si (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))",
				"SuperscriptNodeWithFont.svg", false);
		checkSVGContentFor("(S (NP/Si/f|cpink|b/F (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))",
				"SuperscriptSuperscriptWithFont.svg", false);
		checkSVGContentFor(
				"(S (NP/f|b|i|s12.0|cmaroon/F/Si/f|cpink|b/F (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))",
				"SuperscriptNodeWithFontSuperscriptWithFont.svg", false);
	}

	private void checkSVGContentFor(String description, String expectedFile, boolean isCollapsible) {
		try {
			LingTreeTree origTree = new LingTreeTree();
			origTree.setLineWidth(1);
			LingTreeTree ltTree = TreeBuilder.parseAString(description, origTree);
			drawer = new SVGDrawer(ltTree);
			ltTree.setUseColumnOrientedAlgorithm(false);
			StringBuilder sb = drawer.drawAsSVG();
			String result = sb.toString();
//			System.out.println("result='" + result + "'");
			File svgFile = new File(Constants.UNIT_TEST_DATA_FILE_PATH + expectedFile);
			assertTrue(svgFile.exists());
			String expected = new String(Files.readString(svgFile.toPath(), StandardCharsets.UTF_8));
			expected = expected.replace("\r", "");
			assertEquals(expected, result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void drawAsSVGRevisedTest() {
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(S (NP< (\\L Juan> (\\G John))) (VP (V (\\L duerme & mas (\\G sleeps)))))", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new SVGDrawer(ltTree);
		StringBuilder sb = drawer.drawAsSVG();
		String result = sb.toString();
		assertEquals(true, result.contains("NP&lt;"));
		assertEquals(true, result.contains("Juan&gt;"));
		assertEquals(true, result.contains("duerme &amp; "));

		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(1234567890(123(\\\\L1234(\\\\G1)))(12(1234(123456(123(\\\\L456(\\\\G3)))))(0987(345(34(\\\\L123456(\\\\G4)))))))", origTree);
        ltTree.setDrawVerticalLineWithEmptyText(true);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new SVGDrawer(ltTree);
		sb = drawer.drawAsSVG();
		result = sb.toString();
		assertEquals(true, result.contains(">1234567890<"));
		assertEquals(true, result.contains(">123456<"));
		assertEquals(true, result.contains(">123<"));
		assertEquals(true, result.contains("<line x1=\"191.998046875\" y1=\"105.595703125\" x2=\"118.9990234375\" y2=\"119.595703125\" stroke=\"#000000\" stroke-width=\"10.0\"/>"));
	}

}

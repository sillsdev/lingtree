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
public class CollapsibleSVGDrawerTest extends ServiceBaseTest {

	CollapsibleSVGDrawer drawer;
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}


	@Test
	public void drawAsCollapsibleSVGTest() {
		checkCollapsibleSVGContentFor("(S 		(SP[66:SPR=par]\n"
				+ "				(prep(\\L ל/לְ[629] (\\G to)))\n"
				+ "(under /ame/A (\\Tis all of this material ) )\n"
				+ "			(SP[147:SPR=Xrec]\n"
				+ "					(subs1(\\L כל/כָל־[630] (\\G wholea [/am sg c -/A])))\n"
				+ "		(subs2(\\L כל/כָל־[630a] (\\G wholeb [m sg c -])))\n"
				+ "			)\n"
				+ "			(SP[146:SPR=rec]\n"
				+ "					(SP[149:SPR=Xrec]\n"
				+ "							(subs3(\\L עוף/עֹ֨וף [631] (\\G birds [/am sg c -/A ])))\n"
				+ "					)\n"
				+ "					(SP[148:SPR=rec]\n"
				+ "							(DP\n"
				+ "								(art(\\L ה/הַ[632] (\\G the)))\n"
				+ "								(subs4(\\L שׁמים/שָּׁמַ֜יִם [633] (\\G heavens [/am pl a -/A ])))\n"
				+ "							)\n"
				+ "					)\n"
				+ "			)\n"
				+ "		)\n"
				+ " )\n"
				+ "",
				"CollaspibleSVG.svg", true);
	}

	private void checkCollapsibleSVGContentFor(String description, String expectedFile, boolean isCollapsible) {
		try {
			LingTreeTree origTree = new LingTreeTree();
			origTree.setLineWidth(1);
			LingTreeTree ltTree = TreeBuilder.parseAString(description, origTree);
			drawer = new CollapsibleSVGDrawer(ltTree);
			ltTree.setUseColumnOrientedAlgorithm(false);
			StringBuilder sb = drawer.drawAsCollaspibleSVG();
			String result = sb.toString();
//			System.out.println("result='" + result + "'");
			result = result.replace("\r", "");
			File svgFile = new File(Constants.UNIT_TEST_DATA_FILE_PATH + expectedFile);
			assertTrue(svgFile.exists());
			String expected = new String(Files.readString(svgFile.toPath(), StandardCharsets.UTF_8));
			expected = expected.replace("\r", "");
			// For some reason, we may get a slightly different x value for node2.  Not sure why...
			assertEquals(expected, result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

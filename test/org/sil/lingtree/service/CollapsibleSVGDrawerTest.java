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
		checkCollapsibleSVGContentFor("		(PT=NP[4]:PF=Objc:PD=det\n"
				+ "			(part(\\L את/אֶת־[285] (\\G object marker)))\n"
				+ "		\n"
				+ "		(SP[4:SPR=Appo]/apro/A\n"
				+ "\n"
				+ "			(SP[25:SPR=Xatr]\n"
				+ "					(DP/s1\n"
				+ "						(art1(\\L ה/הַ[292] (\\G the1)))\n"
				+ "						(subs(\\L מאור/מָּאֹ֤ור [293] (\\G lamp [/am sg a - /A])))\n"
				+ "					)\n"
				+ "			)\n"
				+ "			(SP[24:SPR=atr] /apro/A\n"
				+ "					(DP/S2\n"
				+ "						(art2(\\L ה/הַ[294] (\\G the2)))\n"
				+ "\n"
				+ "					)\n"
				+ "			)\n"
				+ "		)\n"
				+ "		(tri (\\T abc def ghi ) )\n"
				+ "	)\n"
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

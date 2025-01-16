/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.*;

import org.fxmisc.richtext.InlineCssTextArea;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sil.lingtree.model.AbbreviationFontInfo;
import org.sil.lingtree.model.EmptyElementFontInfo;
import org.sil.lingtree.model.GlossFontInfo;
import org.sil.lingtree.model.LexFontInfo;
import org.sil.lingtree.model.NonTerminalFontInfo;

/**
 * 
 */
public class DescriptionStylerTest extends ServiceBaseTest {

	InlineCssTextArea treeDescription;
	String descriptionBefore = "";
	DescriptionStyler styler = DescriptionStyler.getInstance();
	String style = "";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		treeDescription = new InlineCssTextArea();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void descriptionStylerTest() {
		
		String syntagmeme = "-fx-font-family: Monospaced;\n-fx-fill: black;\n-fx-font-size:10.0pt;";
		String nonterminal = NonTerminalFontInfo.getInstance().getCss();
		String gloss = GlossFontInfo.getInstance().getCss();
		String empty = EmptyElementFontInfo.getInstance().getCss();
		String lexical = LexFontInfo.getInstance().getCss();
		String abbreviation = AbbreviationFontInfo.getInstance().getCss();
		String customfont = "-fx-font-family: Monospaced;\n-fx-fill: black;\n-fx-font-size:10.0pt;\n-fx-fill:grey;\n-fx-font-size:7.5pt;\n";

		
		descriptionBefore = "(S (NP (\\L Juan (\\G John))) (\\E empty) (VP (V (\\L duerme (\\G sleep-/a3sg/A))))(PP /f|b|i|c#9a8765|fVerdana|s18.0/F))";
		treeDescription.replaceText(descriptionBefore);
		styler.styleDescription(treeDescription, 10.0);
		checkStyleAt(0,0,  syntagmeme);  // parenthesis/syntagmeme
		checkStyleAt(0,1,  nonterminal);  // non-terminal
		checkStyleAt(0,11, lexical); // lexical
		checkStyleAt(0,20, gloss); // gloss
		checkStyleAt(0,32, empty); // empty
		checkStyleAt(0,61, gloss); // gloss
		checkStyleAt(0,69, abbreviation); // abbreviation
		checkStyleAt(0,86, customfont); // custom font
	}

	private void checkStyleAt(int rowPos, int charPos, String expected) {
		String result = treeDescription.getStyleOfChar(rowPos, charPos);
//		System.out.println("\n" + rowPos + "," + charPos + " = '" + result + "'");
		assertEquals(expected, result);
		
	}

}

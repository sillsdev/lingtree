/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import static org.junit.Assert.*;
import javafx.scene.paint.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Andy Black
 *
 */
public class FontInfoTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getCssTest() {
		FontInfo fi = new FontInfo("Courier New", 13.75, "");
		fi.setColor(Color.CHARTREUSE);
		String css = fi.getCss();
		assertEquals("-fx-font-family: \"Courier New\";\n-fx-font-size: 13.75pt;\n-fx-fill: #7fff00;\n", css);
		fi.setFontType("Italic");
		css = fi.getCss();
		assertEquals("-fx-font-family: \"Courier New\";\n-fx-font-size: 13.75pt;\n-fx-font-style: italic;\n-fx-fill: #7fff00;\n", css);
		fi.setFontType("Bold");
		css = fi.getCss();
		assertEquals("-fx-font-family: \"Courier New\";\n-fx-font-size: 13.75pt;\n-fx-font-weight: bold;\n-fx-fill: #7fff00;\n", css);
		fi.setFontType("Bold Italic");
		css = fi.getCss();
		assertEquals("-fx-font-family: \"Courier New\";\n-fx-font-size: 13.75pt;\n-fx-font-style: italic;\n-fx-font-weight: bold;\n-fx-fill: #7fff00;\n", css);
		fi.setFontType("Regular");
		css = fi.getCss();
		assertEquals("-fx-font-family: \"Courier New\";\n-fx-font-size: 13.75pt;\n-fx-fill: #7fff00;\n", css);
		
	}

}

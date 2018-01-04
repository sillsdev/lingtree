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
public class ColorXmlAdaptorTest {

	ColorXmlAdaptor adaptor;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		adaptor = new ColorXmlAdaptor();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		assertEquals("#000000", adaptor.marshal(Color.BLACK));
		assertEquals("#ffffff", adaptor.marshal(Color.WHITE));
		assertEquals("#ff0000", adaptor.marshal(Color.RED));
		assertEquals("#008000", adaptor.marshal(Color.GREEN));
		assertEquals("#0000ff", adaptor.marshal(Color.BLUE));
		assertEquals("#ffff00", adaptor.marshal(Color.YELLOW));
		assertEquals("#ffa500", adaptor.marshal(Color.ORANGE));
		assertEquals("#800080", adaptor.marshal(Color.PURPLE));
		assertEquals("#a52929", adaptor.marshal(Color.BROWN));
		assertEquals("#deb887", adaptor.marshal(Color.BURLYWOOD));
		assertEquals("#008b8b", adaptor.marshal(Color.DARKCYAN));
		assertEquals("#8a2ae2", adaptor.marshal(Color.BLUEVIOLET));
	}
}

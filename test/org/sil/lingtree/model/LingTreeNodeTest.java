/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.lingtree.view.JavaFXThreadingRule;

/**
 * @author Andy Black
 *
 */
public class LingTreeNodeTest {
	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

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
	public void textBoxDimensionsTest() {
		LingTreeNode node = new LingTreeNode();
		node.setContent("node");
		node.setNodeType(NodeType.NonTerminal);
		assertEquals(23.326171875, node.getWidth(), 0.0);
		assertEquals(13.2890625, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Lex);
		assertEquals(25.98046875, node.getWidth(), 0.0);
		assertEquals(19.62890625, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Gloss);
		assertEquals(26.6953125, node.getWidth(), 0.0);
		assertEquals(13.40625, node.getHeight(), 0.0);

		node.setSubscript("subscript");
		node.setNodeType(NodeType.NonTerminal);
		assertEquals(56.9384765625, node.getWidth(), 0.0);
		assertEquals(17.2890585, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Lex);
		assertEquals(59.5927734375, node.getWidth(), 0.0);
		assertEquals(23.62890225, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Gloss);
		assertEquals(60.3076171875, node.getWidth(), 0.0);
		assertEquals(17.406246, node.getHeight(), 0.0);
		
		node.setSubscript("");
		node.setNodeType(NodeType.NonTerminal);
		assertEquals(23.326171875, node.getWidth(), 0.0);
		assertEquals(13.2890625, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Lex);
		assertEquals(25.98046875, node.getWidth(), 0.0);
		assertEquals(19.62890625, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Gloss);
		assertEquals(26.6953125, node.getWidth(), 0.0);
		assertEquals(13.40625, node.getHeight(), 0.0);

		node.setSuperscript("superscript");
		node.setNodeType(NodeType.NonTerminal);
		assertEquals(64.40742111206055, node.getWidth(), 0.0);
		assertEquals(17.2890585, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Lex);
		assertEquals(67.06171798706055, node.getWidth(), 0.0);
		assertEquals(23.62890225, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Gloss);
		assertEquals(67.77656173706055, node.getWidth(), 0.0);
		assertEquals(17.406246, node.getHeight(), 0.0);
		
		node.setSuperscript("");
		node.setNodeType(NodeType.NonTerminal);
		assertEquals(23.326171875, node.getWidth(), 0.0);
		assertEquals(13.2890625, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Lex);
		assertEquals(25.98046875, node.getWidth(), 0.0);
		assertEquals(19.62890625, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Gloss);
		assertEquals(26.6953125, node.getWidth(), 0.0);
		assertEquals(13.40625, node.getHeight(), 0.0);
		
		node.setSubscript("subscript");
		node.setSuperscript("superscript");
		node.setNodeType(NodeType.NonTerminal);
		assertEquals(64.40742111206055, node.getWidth(), 0.0);
		assertEquals(21.2890545, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Lex);
		assertEquals(67.06171798706055, node.getWidth(), 0.0);
		assertEquals(27.62889825, node.getHeight(), 0.0);
		node.setNodeType(NodeType.Gloss);
		assertEquals(67.77656173706055, node.getWidth(), 0.0);
		assertEquals(21.406242, node.getHeight(), 0.0);
	}

}

/**
 * Copyright (c) 2024-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sil.lingtree.model.NodeType;

/**
 * @author Andy Black
 *
 */
public class NodeTypeDeterminerTest {

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
	public void NodeTypeTest() {
		NodeType ntype = NodeType.Syntagmeme;
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("");
		assertEquals(NodeType.Syntagmeme, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("a");
		assertEquals(NodeType.Syntagmeme, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(");
		assertEquals(NodeType.Syntagmeme, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(S");
		assertEquals(NodeType.NonTerminal, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\");
		assertEquals(NodeType.Syntagmeme, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\(");
		assertEquals(NodeType.NonTerminal, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\L");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\L t");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\G");
		assertEquals(NodeType.Gloss, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\G la");
		assertEquals(NodeType.Gloss, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\E");
		assertEquals(NodeType.EmptyElement, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\E t/s1");
		assertEquals(NodeType.EmptyElement, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\G /apl/A)");
		assertEquals(NodeType.Syntagmeme, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\L ba))");
		assertEquals(NodeType.Syntagmeme, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\O");
		assertEquals(NodeType.Syntagmeme, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\O\\");
		assertEquals(NodeType.Syntagmeme, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\O\\L");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\O\\L t");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\T\\L");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\T\\L t");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\T");
		assertEquals(NodeType.Syntagmeme, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\T\\");
		assertEquals(NodeType.Syntagmeme, ntype);

		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(S\\(");
		assertEquals(NodeType.NonTerminal, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\(");
		assertEquals(NodeType.NonTerminal, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\L\\(");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\L t\\(");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\G \\(");
		assertEquals(NodeType.Gloss, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\G la \\(");
		assertEquals(NodeType.Gloss, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\E \\(");
		assertEquals(NodeType.EmptyElement, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\E t/s1 \\(");
		assertEquals(NodeType.EmptyElement, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\G /apl/A\\)");
		assertEquals(NodeType.Gloss, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\G /apl/A\\)\\)");
		assertEquals(NodeType.Gloss, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\L ba\\)\\)");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(\\L ba))");
		assertEquals(NodeType.Syntagmeme, ntype);

		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(S (NP (N (\\L book");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(S (NP (N (\\L book\\");
		assertEquals(NodeType.Lex, ntype);
		ntype = NodeTypeDeterminer.determineNodeTypeFrom("(S (NP (N (\\L book\\) ");
		assertEquals(NodeType.Lex, ntype);
	}
}

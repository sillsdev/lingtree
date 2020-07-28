/**
 * Copyright (c) 2016-2020 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.lingtree.service.TreeBuilder;
import org.sil.utility.view.JavaFXThreadingRule;


/**
 * @author Andy Black
 *
 */
public class LingTreeTreeTest {
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
	public void canUseFlatTreeTest() {
		LingTreeTree ltTree = createTreeDescription("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))");
		assertTrue(ltTree.canUseFlatTree());
		// space between paren and \L
		ltTree = createTreeDescription("(S (NP ( \\L Juan (\\G John))) (VP (V ( \\L duerme (\\G sleeps)))))");
		assertTrue(ltTree.canUseFlatTree());
		// tab between paren and \L
		ltTree = createTreeDescription("(S (NP (	\\L Juan (\\G John))) (VP (V (	\\L duerme (\\G sleeps)))))");
		assertTrue(ltTree.canUseFlatTree());
		// space and tab between paren and \L
		ltTree = createTreeDescription("(S (NP ( 	\\L Juan (\\G John))) (VP (V ( 	\\L duerme (\\G sleeps)))))");
		assertTrue(ltTree.canUseFlatTree());
		// space, tab, and space between paren and \L
		ltTree = createTreeDescription("(S (NP ( 	 \\L Juan (\\G John))) (VP (V ( 	 \\L duerme (\\G sleeps)))))");
		assertTrue(ltTree.canUseFlatTree());
		ltTree = createTreeDescription("(S (NP (Juan (\\G John))) (VP (V (duerme (\\G sleeps)))))");
		assertFalse(ltTree.canUseFlatTree());
		ltTree = createTreeDescription("(S (NP (Juan )) (VP (V (duerme ))))");
		assertFalse(ltTree.canUseFlatTree());
		ltTree = createTreeDescription("");
		assertFalse(ltTree.canUseFlatTree());
	}

	public LingTreeTree createTreeDescription(String description) {
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString(description, origTree);
		ltTree.setDescription(description);
		return ltTree;
	}

}

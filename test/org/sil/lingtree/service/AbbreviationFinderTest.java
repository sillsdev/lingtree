/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.sil.lingtree.model.AbbreviationText;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;

/**
 * 
 */
public class AbbreviationFinderTest extends ServiceBaseTest {
	private TreeDrawer drawer;
	private AbbreviationFinder finder;
	private NodeFinder nodeFinder;
	private LingTreeTree origTree;
	private LingTreeTree ltTree;
	private LingTreeNode nodeFound;
	private AbbreviationText textFound;

	@Test
	public void findAbbreviationTest() {
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleep-/a3sg/A)))))", origTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
//		showNodeDetails(ltTree.getRootNode());
		finder = AbbreviationFinder.getInstance();
		nodeFinder = NodeFinder.getInstance();

		// final leaf node at various places
		nodeFound = nodeFinder.nodeAt(ltTree, 159, 220);
		assertNotNull(nodeFound);
		assertEquals("", nodeFound.getContent());
		textFound = finder.findAbbrTextInNodeAround(nodeFound, 159, 220);
		assertNull(textFound);
		textFound = finder.findAbbrTextInNodeAround(nodeFound, 189, 220);
		assertNotNull(textFound);
		assertEquals("3sg", textFound.getText());
	}
	
	void showNodeDetails(LingTreeNode node) {
		System.out.println("node = " + node.getContent());
		System.out.println("\txcoord = " + node.getXCoordinate());
		System.out.println("\tycoord = " + node.getYCoordinate());
		System.out.println("\twidth  = " + node.getWidth());
		System.out.println("\theight = " + node.getHeight());
		for (LingTreeNode daughter : node.getDaughters()) {
			showNodeDetails(daughter);
		}
	}

}

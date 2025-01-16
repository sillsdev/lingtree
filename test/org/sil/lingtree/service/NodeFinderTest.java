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
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;

/**
 * 
 */
public class NodeFinderTest extends ServiceBaseTest {
	private TreeDrawer drawer;
	private NodeFinder finder;
	private LingTreeTree origTree;
	private LingTreeTree ltTree;
	private LingTreeNode nodeFound;

	@Test
	public void findNodeTest() {
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))", origTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
//		showNodeDetails(ltTree.getRootNode());
		finder = NodeFinder.getInstance();
		// upper left corner
		nodeFound = finder.nodeAt(ltTree, 0, 0);
		assertNull(nodeFound);
		// upper right corner
		nodeFound = finder.nodeAt(ltTree, ltTree.getXSize(), 0);
		assertNull(nodeFound);
		// lower left corner
		nodeFound = finder.nodeAt(ltTree, 0, ltTree.getYSize());
		assertNull(nodeFound);
		// lower right corner
		nodeFound = finder.nodeAt(ltTree, ltTree.getXSize(), ltTree.getYSize());
		assertNull(nodeFound);

		// root node at various places
		nodeFound = finder.nodeAt(ltTree, 142, 90);
		assertNotNull(nodeFound);
		assertEquals("S", nodeFound.getContent());
		// at upper left corner
		nodeFound = finder.nodeAt(ltTree, 141.24267578125, 86.7109375);
		assertNotNull(nodeFound);
		assertEquals("S", nodeFound.getContent());
		// at lower right corner
		nodeFound = finder.nodeAt(ltTree, 147.91650390125, 100.0);
		assertNotNull(nodeFound);
		assertEquals("S", nodeFound.getContent());
		// off at right
		nodeFound = finder.nodeAt(ltTree, 147.92650390125, 113.2890625);
		assertNull(nodeFound);
		// off at left
		nodeFound = finder.nodeAt(ltTree, 141.14267578125, 100.0);
		assertNull(nodeFound);
		// off at top
		nodeFound = finder.nodeAt(ltTree, 141.24267578125, 86.6109375);
		assertNull(nodeFound);
		// off at bottom
		nodeFound = finder.nodeAt(ltTree, 141.24267578125, 113.2891625);
		assertNull(nodeFound);

		// final leaf node at various places
		nodeFound = finder.nodeAt(ltTree, 159, 220);
		assertNotNull(nodeFound);
		assertEquals("sleeps", nodeFound.getContent());
		// at upper left corner
		nodeFound = finder.nodeAt(ltTree, 158.9046875, 218.4296875);
		assertNotNull(nodeFound);
		assertEquals("sleeps", nodeFound.getContent());
		// at lower right corner
		nodeFound = finder.nodeAt(ltTree, 193.4921875, 225.8359375);
		assertNotNull(nodeFound);
		assertEquals("sleeps", nodeFound.getContent());
		// off at right
		nodeFound = finder.nodeAt(ltTree, 200, 225.8459375);
		assertNull(nodeFound);
		// off at left
		nodeFound = finder.nodeAt(ltTree, 157.9046875, 225.8459375);
		assertNull(nodeFound);
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

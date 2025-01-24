/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NodeText;
import org.sil.lingtree.model.SubscriptText;
import org.sil.lingtree.model.SuperscriptText;

/**
 * 
 */
public class NodeTextFinderTest extends ServiceBaseTest {
	private TreeDrawer drawer;
	private NodeTextFinder finder;
	private NodeFinder nodeFinder;
	private LingTreeTree origTree;
	private LingTreeTree ltTree;
	private LingTreeNode nodeFound;
	private NodeText textFound;

	@Test
	public void findNodeTextTest() {
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleep-/a3sg/A)))))", origTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
//		showNodeDetails(ltTree.getRootNode());
		finder = NodeTextFinder.getInstance();
		nodeFinder = NodeFinder.getInstance();

		// final leaf node at various places
		nodeFound = nodeFinder.nodeAt(ltTree, 159, 220);
		assertNotNull(nodeFound);
		assertEquals("", nodeFound.getContent());
		textFound = finder.findNodeTextInNodeAround(nodeFound, 159, 220);
		assertNotNull(textFound);
		assertEquals("sleep-", textFound.getText());
		textFound = finder.findNodeTextInNodeAround(nodeFound, 189, 220);
		assertNotNull(textFound);
		assertEquals("3sg", textFound.getText());
	}

	@Test
	public void findSubcriptOrSuperscriptTextTest() {
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (NP/si (\\L Juan (\\G John))) (VP/S0 (V (\\L duerme (\\G sleep-/a3sg/A)))))", origTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
//		showNodeDetails(ltTree.getRootNode());
		finder = NodeTextFinder.getInstance();
		nodeFinder = NodeFinder.getInstance();

		// final leaf node at various places
		nodeFound = nodeFinder.nodeAt(ltTree, 105, 130);
		assertNotNull(nodeFound);
		assertEquals("NP", nodeFound.getContent());
		textFound = finder.findSubOrSuperscriptTextInNodeAround(nodeFound, 120, 130);
		assertNotNull(textFound);
		assertEquals("i", textFound.getText());
		nodeFound = nodeFinder.nodeAt(ltTree, 175, 130);
		assertNotNull(nodeFound);
		assertEquals("VP", nodeFound.getContent());
		textFound = finder.findSubOrSuperscriptTextInNodeAround(nodeFound, 188, 130);
		assertNotNull(textFound);
		assertEquals("0", textFound.getText());
	}

	void showNodeDetails(LingTreeNode node) {
		System.out.println("node = " + node.getContent());
		System.out.println("\txcoord = " + node.getXCoordinate());
		System.out.println("\tycoord = " + node.getYCoordinate());
		System.out.println("\twidth  = " + node.getWidth());
		System.out.println("\theight = " + node.getHeight());
		if (node.hasSubscript()) {
			SubscriptText sub = node.getSubscriptText();
			System.out.println("\tsub    = " + sub.getText());
			System.out.println("\txcoord = " + sub.getTextBox().getX());
			System.out.println("\tycoord = " + sub.getTextBox().getY());
			System.out.println("\twidth  = " + sub.getTextBox().getLayoutBounds().getWidth());
			System.out.println("\theight = " + sub.getTextBox().getLayoutBounds().getHeight());
		}
		if (node.hasSuperscript()) {
			SuperscriptText sup = node.getSuperscriptText();
			System.out.println("\tsuper  = " + sup.getText());
			System.out.println("\txcoord = " + sup.getTextBox().getX());
			System.out.println("\tycoord = " + sup.getTextBox().getY());
			System.out.println("\twidth  = " + sup.getTextBox().getLayoutBounds().getWidth());
			System.out.println("\theight = " + sup.getTextBox().getLayoutBounds().getHeight());
		}
		for (LingTreeNode daughter : node.getDaughters()) {
			showNodeDetails(daughter);
		}
	}

}

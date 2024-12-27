/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;

/**
 * @author Andy Black
 *
 */
public class TreeDrawerTest extends ServiceBaseTest {
	
	private TreeDrawer drawer;

	@Test
	public void calculateMaxHeightsPerLevelTest() {
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		HashMap<Integer, Double> maxHeightPerLevel = drawer.getMaxHeightPerLevel();
		assertEquals(13.2890625, maxHeightPerLevel.get(1), 0.0);
		assertEquals(13.2890625, maxHeightPerLevel.get(2), 0.0);
		assertEquals(19.62890625, maxHeightPerLevel.get(3), 0.0);
		assertEquals(19.62890625, maxHeightPerLevel.get(4), 0.0);
		assertEquals(13.40625, maxHeightPerLevel.get(5), 0.0);
		assertNull(maxHeightPerLevel.get(6));
		
		ltTree = TreeBuilder.parseAString("(S (NP (N' (N (\\L Juan (\\G John))))) (VP (V (\\L duerme (\\G sleeps)))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		maxHeightPerLevel = drawer.getMaxHeightPerLevel();
		assertEquals(13.2890625, maxHeightPerLevel.get(1), 0.0);
		assertEquals(13.2890625, maxHeightPerLevel.get(2), 0.0);
		assertEquals(13.2890625, maxHeightPerLevel.get(3), 0.0);
		assertEquals(19.62890625, maxHeightPerLevel.get(4), 0.0);
		assertEquals(19.62890625, maxHeightPerLevel.get(5), 0.0);
		assertEquals(13.40625, maxHeightPerLevel.get(6), 0.0);
		assertNull(maxHeightPerLevel.get(7));

		// abbreviations
		ltTree = TreeBuilder.parseAString("(NP (N (\\L mi libros (\\G -/a1.poss/A- book -/a pl /A))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		maxHeightPerLevel = drawer.getMaxHeightPerLevel();
		assertEquals(13.2890625, maxHeightPerLevel.get(1), 0.0);
		assertEquals(13.2890625, maxHeightPerLevel.get(2), 0.0);
		assertEquals(19.62890625, maxHeightPerLevel.get(3), 0.0);
		assertEquals(13.40625, maxHeightPerLevel.get(4), 0.0);
		assertNull(maxHeightPerLevel.get(5));

		// no text in node
		ltTree = TreeBuilder.parseAString("(Mascoian((Enxet Sur)(Enlhet Norte))((((Angaité)(Sanapaná)))((Kaskihá)(Toba-Maskoy))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		maxHeightPerLevel = drawer.getMaxHeightPerLevel();
		assertEquals(13.2890625, maxHeightPerLevel.get(1), 0.0);
		assertEquals(15.9609375, maxHeightPerLevel.get(2), 0.0);
		assertEquals(15.9609375, maxHeightPerLevel.get(3), 0.0);
		assertEquals(15.9609375, maxHeightPerLevel.get(4), 0.0);
		assertEquals(13.2890625, maxHeightPerLevel.get(5), 0.0);
		assertNull(maxHeightPerLevel.get(6));
	}

	@Test
	public void calculateYCoordinateOfEveryNodeTest() {
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		LingTreeNode node = ltTree.getRootNode();
		assertEquals(100.0, node.getYCoordinate(), 0.0);
		assertEquals(86.306640625, node.getYUpperMid(), 0.0);
		assertEquals(105.595703125, node.getYLowerMid(), 0.0);
		LingTreeNode node1 = node.getDaughters().get(0);
		assertEquals(133.2890625, node1.getYCoordinate(), 0.0);
		assertEquals(119.595703125, node1.getYUpperMid(), 0.0);
		assertEquals(138.884765625, node1.getYLowerMid(), 0.0);
		LingTreeNode node2 = node.getDaughters().get(1);
		assertEquals(133.2890625, node2.getYCoordinate(), 0.0);
		assertEquals(119.595703125, node2.getYUpperMid(), 0.0);
		assertEquals(138.884765625, node2.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(166.578125, node1.getYCoordinate(), 0.0);
		assertEquals(149.22265625, node1.getYUpperMid(), 0.0);
		assertEquals(174.8515625, node1.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(186.20703125, node1.getYCoordinate(), 0.0);
		assertEquals(172.34375, node1.getYUpperMid(), 0.0);
		assertEquals(191.75, node1.getYLowerMid(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(166.578125, node2.getYCoordinate(), 0.0);
		assertEquals(152.884765625, node2.getYUpperMid(), 0.0);
		assertEquals(172.173828125, node2.getYLowerMid(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(206.20703125, node2.getYCoordinate(), 0.0);
		assertEquals(188.8515625, node2.getYUpperMid(), 0.0);
		assertEquals(214.48046875, node2.getYLowerMid(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(225.8359375, node2.getYCoordinate(), 0.0);
		assertEquals(211.97265625, node2.getYUpperMid(), 0.0);
		assertEquals(231.37890625, node2.getYLowerMid(), 0.0);

		// Now with "show flat view" set
		ltTree = TreeBuilder.parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))", origTree);
		ltTree.setShowFlatView(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(100.0, node.getYCoordinate(), 0.0);
		assertEquals(86.306640625, node.getYUpperMid(), 0.0);
		assertEquals(105.595703125, node.getYLowerMid(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(133.2890625, node1.getYCoordinate(), 0.0);
		assertEquals(119.595703125, node1.getYUpperMid(), 0.0);
		assertEquals(138.884765625, node1.getYLowerMid(), 0.0);
		node2 = node.getDaughters().get(1);
		assertEquals(133.2890625, node2.getYCoordinate(), 0.0);
		assertEquals(119.595703125, node2.getYUpperMid(), 0.0);
		assertEquals(138.884765625, node2.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(206.20703125, node1.getYCoordinate(), 0.0);
		assertEquals(188.8515625, node1.getYUpperMid(), 0.0);
		assertEquals(211.48046875, node1.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(225.8359375, node1.getYCoordinate(), 0.0);
		assertEquals(214.97265625, node1.getYUpperMid(), 0.0);
		assertEquals(228.37890625, node1.getYLowerMid(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(166.578125, node2.getYCoordinate(), 0.0);
		assertEquals(152.884765625, node2.getYUpperMid(), 0.0);
		assertEquals(172.173828125, node2.getYLowerMid(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(206.20703125, node2.getYCoordinate(), 0.0);
		assertEquals(188.8515625, node2.getYUpperMid(), 0.0);
		assertEquals(211.48046875, node2.getYLowerMid(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(225.8359375, node2.getYCoordinate(), 0.0);
		assertEquals(214.97265625, node2.getYUpperMid(), 0.0);
		assertEquals(228.37890625, node2.getYLowerMid(), 0.0);

		// with subscript and superscript
		ltTree = TreeBuilder.parseAString("(S (NP/Ssuper (\\L Juan (\\G John))) (VP (V/ssub (\\L duerme (\\G sleeps)))))", origTree);
		ltTree.setShowFlatView(false);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(100.0, node.getYCoordinate(), 0.0);
		assertEquals(86.306640625, node.getYUpperMid(), 0.0);
		assertEquals(105.595703125, node.getYLowerMid(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(133.2890625, node1.getYCoordinate(), 0.0);
		assertEquals(119.595703125, node1.getYUpperMid(), 0.0);
		assertEquals(138.884765625, node1.getYLowerMid(), 0.0);
		node2 = node.getDaughters().get(1);
		assertEquals(133.2890625, node2.getYCoordinate(), 0.0);
		assertEquals(119.595703125, node2.getYUpperMid(), 0.0);
		assertEquals(138.884765625, node2.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(170.578121, node1.getYCoordinate(), 0.0);
		assertEquals(153.22265225, node1.getYUpperMid(), 0.0);
		assertEquals(178.8515585, node1.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(190.20702725, node1.getYCoordinate(), 0.0);
		assertEquals(176.343746, node1.getYUpperMid(), 0.0);
		assertEquals(195.749996, node1.getYLowerMid(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(170.578121, node2.getYCoordinate(), 0.0);
		assertEquals(156.884761625, node2.getYUpperMid(), 0.0);
		assertEquals(176.173824125, node2.getYLowerMid(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(210.20702725, node2.getYCoordinate(), 0.0);
		assertEquals(192.8515585, node2.getYUpperMid(), 0.0);
		assertEquals(218.48046475, node2.getYLowerMid(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(229.8359335, node2.getYCoordinate(), 0.0);
		assertEquals(215.97265225, node2.getYUpperMid(), 0.0);
		assertEquals(235.37890225, node2.getYLowerMid(), 0.0);

		// abbreviations
		ltTree = TreeBuilder.parseAString("(NP (N (\\L mi libros (\\G -/a1.poss/A- book -/a pl /A))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(100.0, node.getYCoordinate(), 0.0);
		assertEquals(86.306640625, node.getYUpperMid(), 0.0);
		assertEquals(105.595703125, node.getYLowerMid(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(133.2890625, node1.getYCoordinate(), 0.0);
		assertEquals(119.595703125, node1.getYUpperMid(), 0.0);
		assertEquals(138.884765625, node1.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(166.578125, node1.getYCoordinate(), 0.0);
		assertEquals(149.22265625, node1.getYUpperMid(), 0.0);
		assertEquals(174.8515625, node1.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(186.20703125, node1.getYCoordinate(), 0.0);
		assertEquals(172.34375, node1.getYUpperMid(), 0.0);
		assertEquals(191.75, node1.getYLowerMid(), 0.0);

		ltTree = TreeBuilder.parseAString("(NP (N (\\L mi libros (\\G -/a1.poss/A- book -/a pl /A))))", origTree);
		drawer = new TreeDrawer(ltTree);
		ltTree.setLexGlossGapAdjustment(20.0);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(100.0, node.getYCoordinate(), 0.0);
		assertEquals(86.306640625, node.getYUpperMid(), 0.0);
		assertEquals(105.595703125, node.getYLowerMid(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(133.2890625, node1.getYCoordinate(), 0.0);
		assertEquals(119.595703125, node1.getYUpperMid(), 0.0);
		assertEquals(138.884765625, node1.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(166.578125, node1.getYCoordinate(), 0.0);
		assertEquals(149.22265625, node1.getYUpperMid(), 0.0);
		assertEquals(174.8515625, node1.getYLowerMid(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(186.578125, node1.getYCoordinate(), 0.0);
		assertEquals(172.71484375, node1.getYUpperMid(), 0.0);
		assertEquals(192.12109375, node1.getYLowerMid(), 0.0);

		// no text in node (just the first set)
		ltTree = TreeBuilder.parseAString("(Mascoian((Enxet Sur)(Enlhet Norte))((((Angaité)(Sanapaná)))((Kaskihá)(Toba-Maskoy))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();  // Mascoian
		assertEquals(100.0, node.getYCoordinate(), 0.0);
		node1 = node.getDaughters().get(0); // empty
		assertEquals(133.2890625, node1.getYCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0); // Enxet Sur
		assertEquals(169.25, node2.getYCoordinate(), 0.0);
		node2 = node1.getDaughters().get(1); // Enlhet Norte
		assertEquals(169.25, node2.getYCoordinate(), 0.0);

		node1 = node.getDaughters().get(1); // empty
		assertEquals(133.2890625, node1.getYCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0); // empty
		assertEquals(169.25, node2.getYCoordinate(), 0.0);
		LingTreeNode node3 = node2.getDaughters().get(0); // empty
		assertEquals(205.2109375, node3.getYCoordinate(), 0.0);
		LingTreeNode node4 = node3.getDaughters().get(0); // Angaité
		assertEquals(241.171875, node4.getYCoordinate(), 0.0);
		node4 = node3.getDaughters().get(1); // Sanapaná
		assertEquals(241.171875, node4.getYCoordinate(), 0.0);
	}

	@Test
	public void calculateXCoordinateOfEveryNodeTest() {
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = false;
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		LingTreeNode node = ltTree.getRootNode();
		assertEquals(141.24267578125, node.getXCoordinate(), 0.0);
		LingTreeNode node1 = node.getDaughters().get(0);
		assertEquals(105.26171875, node1.getXCoordinate(), 0.0);
		LingTreeNode node2 = node.getDaughters().get(1);
		assertEquals(168.478515625, node2.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(100.234375, node1.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(100.0, node1.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(171.8154296875, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(156.021484375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(158.8046875, node2.getXCoordinate(), 0.0);

		// with subscript and superscript
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (NP/Ssuper (\\L Juan (\\G John))) (VP (V/ssub (\\L duerme (\\G sleeps)))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = false;
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(147.03598546981812, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(100.0, node1.getXCoordinate(), 0.0);
		node2 = node.getDaughters().get(1);
		assertEquals(176.20292854309082, node2.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(104.09658145904541, node1.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(103.86220645904541, node1.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(173.70536994934082, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(163.74589729309082, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(166.52910041809082, node2.getXCoordinate(), 0.0);

		// abbreviations
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(NP (N (\\L mi libros (\\G -/a1.poss/A- book -/a pl /A))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = false;
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(136.9375, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(140.2744140625, node1.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(121.0791015625, node1.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(100, node1.getXCoordinate(), 0.0);

		// no text in node (just the first set)
		ltTree = TreeBuilder.parseAString("(Mascoian((Enxet Sur)(Enlhet Norte))((((Angaité)(Sanapaná)))((Kaskihá)(Toba-Maskoy))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = false;
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();  // Mascoian
		assertEquals(264.9161376953125, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0); // empty
		assertEquals(166.2138671875, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0); // Enxet Sur
		assertEquals(100.0, node2.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(1); // Enlhet Norte
		assertEquals(177.736328125, node2.getXCoordinate(), 0.0);

		node1 = node.getDaughters().get(1); // empty
		assertEquals(410.270751953125, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0); // empty
		assertEquals(331.03515625, node2.getXCoordinate(), 0.0);
		LingTreeNode node3 = node2.getDaughters().get(0); // empty
		assertEquals(331.03515625, node3.getXCoordinate(), 0.0);
		LingTreeNode node4 = node3.getDaughters().get(0); // Angaité
		assertEquals(273.7158203125, node4.getXCoordinate(), 0.0);
		node4 = node3.getDaughters().get(1); // Sanapaná
		assertEquals(346.3720703125, node4.getXCoordinate(), 0.0);
	}
	
	@Test
	public void calculateXCoordinateOfEveryNodeRevisedTest() {
		// some mother nodes are wider than daughter nodes
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(1234567890(123(\\L1234(\\G1)))(12(1234(123456(123(\\L456(\\G3)))))(0987(345(34(\\L123456(\\G4)))))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = true;
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		LingTreeNode node = ltTree.getRootNode();
		assertEquals(151.779296875, node.getXCoordinate(), 0.0);
		LingTreeNode node1 = node.getDaughters().get(0);
		assertEquals(104.51171875, node1.getXCoordinate(), 0.0);
		LingTreeNode node2 = node1.getDaughters().get(0);
		assertEquals(100.0, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(110.1748046875, node2.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(1);
		assertEquals(204.291015625, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(163.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(157.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(166.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(164.8896484375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(171.6865234375, node2.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(1);
		assertEquals(231.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(234.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(237.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(223.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(239.9541015625, node2.getXCoordinate(), 0.0);

		// root node is wider than rest of the tree
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(12345678901234567890123(123(\\L1234(\\G1)))(12(1234(123456(123(\\L456(\\G3)))))\n"
				+ " (0987(345(34(\\L123456(\\G4)))))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = true;
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(112.779296875, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(104.51171875, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(100.0, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(110.1748046875, node2.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(1);
		assertEquals(204.291015625, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(163.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(157.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(166.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(164.8896484375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(171.6865234375, node2.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(1);
		assertEquals(231.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(234.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(237.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(223.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(239.9541015625, node2.getXCoordinate(), 0.0);

		// three daughters of root node with various widths in tree
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(12345678901234567890123(123(\\L1234(\\G1)))(12(1234(123456(123(\\L456(\\G3)))))\n"
				+ " (0987(345(34(\\L123456(\\G4))))))(1234567890(1(2(\\L3(\\G4)))) \n"
				+ "(2(3(\\L4(\\G5))))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = true;
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(157.779296875, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(104.51171875, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(100.0, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(110.1748046875, node2.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(1);
		assertEquals(204.291015625, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(163.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(157.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(166.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(164.8896484375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(171.6865234375, node2.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(1);
		assertEquals(231.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(234.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(237.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(223.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(239.9541015625, node2.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(2);
		assertEquals(293.55859375, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(302.1806640625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(302.1806640625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(301.802734375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(301.84375, node2.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(1);
		assertEquals(338.9365234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(338.9365234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(338.55859375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(338.599609375, node2.getXCoordinate(), 0.0);

		// root node wider than its three daughters with various widths in tree
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(123456789012345678901231234567890123456(123(\\L 1234(\\G1)))(12(1234(123456(123(\\L456(\\G3)))))(0987(345(34(\\L123456(\\G4))))))(1234567890(1(2(\\L3(\\G4))))(2(3(\\L4(\\G5))))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = true;
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(109.779296875, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(104.51171875, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(100.0, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(110.1748046875, node2.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(1);
		assertEquals(204.291015625, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(163.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(157.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(166.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(164.8896484375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(171.6865234375, node2.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(1);
		assertEquals(231.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(234.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(237.291015625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(223.0234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(239.9541015625, node2.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(2);
		assertEquals(293.55859375, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(302.1806640625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(302.1806640625, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(301.802734375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(301.84375, node2.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(1);
		assertEquals(338.9365234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(338.9365234375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(338.55859375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(338.599609375, node2.getXCoordinate(), 0.0);

		// single single double
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (VP (V (\\L ʃu (\\G ab ) ) ) (aux (\\L be (\\G cd ) ) )   ) )", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = true;
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(127.0, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(122.6669921875, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(102.3408203125, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(101.130859375, node2.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(1);
		assertEquals(143.34765625, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(145.73828125, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(145.673828125, node2.getXCoordinate(), 0.0);
	}

	@Test
	public void drawAsSVGTest() {
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(S (NP< (\\L Juan> (\\G John))) (VP (V (\\L duerme & mas (\\G sleeps)))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = false;
		StringBuilder sb = drawer.drawAsSVG();
		String result = sb.toString();
		assertEquals(true, result.contains("NP&lt;"));
		assertEquals(true, result.contains("Juan&gt;"));
		assertEquals(true, result.contains("duerme &amp; "));

		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(Mascoian((Enxet Sur)(Enlhet Norte))((((Angaité)(Sanapaná)))((Kaskihá)(Toba-Maskoy))))", origTree);
        ltTree.setDrawVerticalLineWithEmptyText(true);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = false;
		sb = drawer.drawAsSVG();
		result = sb.toString();
		assertEquals(true, result.contains(">Mascoian<"));
		assertEquals(true, result.contains("Enxet Sur"));
		assertEquals(true, result.contains("Enlhet Norte"));
		assertEquals(true, result.contains("<line x1=\"166.2138671875\" y1=\"139.30078125\" x2=\"166.2138671875\" y2=\"117.33984375\" stroke=\"#000000\" stroke-width=\"10.0\"/>"));
	}

	@Test
	public void drawAsSVGRevisedTest() {
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(S (NP< (\\L Juan> (\\G John))) (VP (V (\\L duerme & mas (\\G sleeps)))))", origTree);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = true;
		StringBuilder sb = drawer.drawAsSVG();
		String result = sb.toString();
		assertEquals(true, result.contains("NP&lt;"));
		assertEquals(true, result.contains("Juan&gt;"));
		assertEquals(true, result.contains("duerme &amp; "));

		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(1234567890(123(\\\\L1234(\\\\G1)))(12(1234(123456(123(\\\\L456(\\\\G3)))))(0987(345(34(\\\\L123456(\\\\G4)))))))", origTree);
        ltTree.setDrawVerticalLineWithEmptyText(true);
		drawer = new TreeDrawer(ltTree);
		drawer.fUseRevisedAlgorithm = true;
		sb = drawer.drawAsSVG();
		result = sb.toString();
		System.out.print(result);
		assertEquals(true, result.contains(">1234567890<"));
		assertEquals(true, result.contains(">123456<"));
		assertEquals(true, result.contains(">123<"));
		assertEquals(true, result.contains("<line x1=\"191.998046875\" y1=\"105.595703125\" x2=\"118.9990234375\" y2=\"119.595703125\" stroke=\"#000000\" stroke-width=\"10.0\"/>"));
	}
}
/**
 * Copyright (c) 2016-2025 SIL Global
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
		ltTree.setUseColumnOrientedAlgorithm(false);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		LingTreeNode node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "S", 141.24267578125);
		LingTreeNode node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "NP", 105.26171875);
		LingTreeNode node2 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "VP", 168.478515625);
		node1 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "Juan", 100.234375);
		node1 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "John", 100.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "V", 171.8154296875);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "duerme", 156.021484375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "sleeps", 158.8046875);

		// with subscript and superscript
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (NP/Ssuper (\\L Juan (\\G John))) (VP (V/ssub (\\L duerme (\\G sleeps)))))", origTree);
		drawer = new TreeDrawer(ltTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "S", 147.03598546981812);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "NP", 100.0);
		node2 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "VP", 176.20292854309082);
		node1 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "Juan", 104.09658145904541);
		node1 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "John", 103.86220645904541);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "V", 173.70536994934082);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "duerme", 0163.74589729309082);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "sleeps", 166.52910041809082);

		// abbreviations
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(NP (N (\\L mi libros (\\G -/a1.poss/A- book -/a pl /A))))", origTree);
		drawer = new TreeDrawer(ltTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "NP", 136.9375);
		assertEquals(136.9375, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "N", 140.2744140625);
		assertEquals(140.2744140625, node1.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "mi libros", 121.0791015625);
		assertEquals(121.0791015625, node1.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "", 100.0);
		assertEquals(100, node1.getXCoordinate(), 0.0);

		// no text in node (just the first set)
		ltTree = TreeBuilder.parseAString("(Mascoian((Enxet Sur)(Enlhet Norte))((((Angaité)(Sanapaná)))((Kaskihá)(Toba-Maskoy))))", origTree);
		drawer = new TreeDrawer(ltTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "Mascoian", 264.9161376953125);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "", 166.2138671875);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "Enxet Sur", 100.0);
		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "Enlhet Norte", 177.736328125);

		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "", 410.270751953125);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "", 331.03515625);
		LingTreeNode node3 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "", 331.03515625);
		LingTreeNode node4 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "Angaité", 273.7158203125);
		node4 = node3.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node4, "Sanapaná", 346.3720703125);

		// long tree that goes from left to right as it goes down
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(IP(DP1 V-IIA-3P (\\T \\L ēsan (\\G They were ) ) )"
				+ " (I'(VP1 (Adv-Conj? (\\L de (\\G now ) ) )(VP2(V'(V-PPA-NMP(\\L proskarterountes (\\G steadfastly continuing ) ) )"
				+ " (DP2(DP3(D'1(Art-DFS1 (\\L tē (\\G the )) ) ( NP1 (N'1(N-DFS1 (\\L didachē (\\G teaching ) ) )"
				+ " (DP4 (D'2 (Art-GMP (\\L tōn (\\G of the ) ) ) ) (NP2 (N'2  (N (\\L apostolōn (\\G apostles ) ) ) )))))))"
				+ " (Conj (\\L kai(\\G and ) ) )(DP5 (DP6(D'3(Art-DFS2 (\\L tē (\\G  the )  ) )"
				+ " (NP3(N'3(N-DFS2 (\\L koinōnia (\\G fellowship )) ) ) )) )"
				+ " (DP7 (DP8 (D'4(Art-DFS3 (\\L tē (\\G the ) ) )(NP4(N'4(N-DFS3(\\L klasei (\\G breaking of bread ) ) ) ) ) ) )"
				+ " (Conj (\\L kai (\\G and ) ) )(DP9 (D'5 (Art-DFP (\\L tais (\\G the ) ) )"
				+ " (NP5 (N'5 (N-DFP (\\L proseuchais (\\G prayers ) ) ) ) )) ) )) ))))) ) ", origTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		ltTree.setCenterColumnOrientedOnDaughtersWidth(false);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "IP", 256.9041290283203);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "DP1 V-IIA-3P", 100.0);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ēsan", 123.3818359375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "They were", 107.154296875);
		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "I'", 386.2340393066406);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "VP1", 378.6432189941406);
		node3 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "Adv-Conj?", 200.330078125);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "de", 220.66796875);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "now", 215.986328125);
		node3 = node2.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node3, "VP2", 540.9631958007812);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "V'", 546.2190551757812);
		node4 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "V-PPA-NMP", 309.0185546875);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "proskarterountes", 296.62890625);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "steadfastly continuing", 283.65625);
		node4 = node3.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node4, "DP2", 751.2369384765625);
		// and so on...

		// goes from right to left
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(Word  (Infl[n] (Stem[n]  (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))\n"
				+ " (Aspect (\\L yka: (\\G  ipfv ))) \n"
				+ "(Object (\\L  ma: (\\G 1.obj)))) \n"
				+ "(Derv (\\L  na (\\G nmlz))) )\n"
				+ " (Poss (\\L  yki (\\G  2.poss)))\n"
				+ "     (Case (\\L  paq (\\G  purp))))\n"
				+ ")", origTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		ltTree.setCenterColumnOrientedOnDaughtersWidth(false);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "Word", 342.47705078125);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "Infl[n]", 340.49365234375);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "Stem[n]", 237.3203125);
		node3 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "Infl[v]", 172.3310546875);
		node4 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "Stem[v]", 100.0);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "root", 109.65625);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "rika", 108.90625);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "to.see", 102.9853515625);
		node4 = node3.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node4, "Aspect", 171.3203125);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "yka:", 176.875);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "ipfv", 178.6533203125);
		node4 = node3.getDaughters().get(2);
		checkNodeContentAndXCoordinate(node4, "Object", 240.6484375);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "ma:", 246.818359375);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "1.obj", 243.6396484375);
		node3 = node2.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node3, "Derv", 313.31640625);
		node4 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "na", 318.8564453125);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "nmlz", 312.642578125);
		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "Poss", 381.3115234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "yki", 384.115234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "2.poss", 374.640625);
		node2 = node1.getDaughters().get(2);
		checkNodeContentAndXCoordinate(node2, "Case", 443.9921875);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "paq", 445.955078125);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "purp", 443.646484375);
}
	
	@Test
	public void calculateXCoordinateOfEveryNodeRevisedTest() {
		// some mother nodes are wider than daughter nodes
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(1234567890(123(\\L1234(\\G1)))(12(1234(123456(123(\\L456(\\G3)))))(0987(345(34(\\L123456(\\G4)))))))", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		LingTreeNode node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "1234567890", 151.779296875);
		LingTreeNode node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "123", 104.51171875);
		LingTreeNode node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1234", 100.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1", 110.1748046875);
		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "12", 204.291015625);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1234", 163.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123456", 157.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123", 166.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "456", 164.8896484375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "3", 171.6865234375);
		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "0987", 231.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "345", 234.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "34", 237.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123456", 223.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "4", 239.9541015625);

		// root node is wider than rest of the tree
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(12345678901234567890123(123(\\L1234(\\G1)))(12(1234(123456(123(\\L456(\\G3)))))\n"
				+ " (0987(345(34(\\L123456(\\G4)))))))", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "12345678901234567890123", 112.779296875);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "123", 104.51171875);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1234", 100.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1", 110.1748046875);
		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "12", 204.291015625);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1234", 163.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123456", 157.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123", 166.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "456", 164.8896484375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "3", 171.6865234375);
		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "0987", 231.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "345", 234.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "34", 237.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123456", 223.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "4", 239.9541015625);

		// three daughters of root node with various widths in tree
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(12345678901234567890123(123(\\L1234(\\G1)))(12(1234(123456(123(\\L456(\\G3)))))\n"
				+ " (0987(345(34(\\L123456(\\G4))))))(1234567890(1(2(\\L3(\\G4)))) \n"
				+ "(2(3(\\L4(\\G5))))))", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "12345678901234567890123", 157.779296875);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "123", 104.51171875);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1234", 100.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1", 110.1748046875);
		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "12", 204.291015625);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1234", 163.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123456", 157.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123", 166.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "456", 164.8896484375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "3", 171.6865234375);
		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "0987", 231.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "345", 234.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "34", 237.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123456", 223.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "4", 239.9541015625);
		node1 = node.getDaughters().get(2);
		checkNodeContentAndXCoordinate(node1, "1234567890", 293.55859375);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1", 302.1806640625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "2", 302.1806640625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "3", 301.802734375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "4", 301.84375);
		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "2", 338.9365234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "3", 338.9365234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "4", 338.55859375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "5", 338.599609375);

		// root node wider than its three daughters with various widths in tree
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(123456789012345678901231234567890123456(123(\\L 1234(\\G1)))(12(1234(123456(123(\\L456(\\G3)))))(0987(345(34(\\L123456(\\G4))))))(1234567890(1(2(\\L3(\\G4))))(2(3(\\L4(\\G5))))))", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "123456789012345678901231234567890123456", 109.779296875);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "123", 104.51171875);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1234", 100.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1", 110.1748046875);
		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "12", 204.291015625);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1234", 163.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123456", 157.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123", 166.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "456", 164.8896484375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "3", 171.6865234375);
		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "0987", 231.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "345", 234.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "34", 237.291015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "123456", 223.0234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "4", 239.9541015625);
		node1 = node.getDaughters().get(2);
		checkNodeContentAndXCoordinate(node1, "1234567890", 293.55859375);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "1", 302.1806640625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "2", 302.1806640625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "3", 301.802734375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "4", 301.84375);
		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "2", 338.9365234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "3", 338.9365234375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "4", 338.55859375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "5", 338.599609375);

		// single single double
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (VP (V (\\L ʃu (\\G ab ) ) ) (aux (\\L be (\\G cd ) ) )   ) )", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "S", 127.0);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "VP", 122.6669921875);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "V", 102.3408203125);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ʃu", 101.130859375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ab", 100.0);
		node1 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "aux", 143.34765625);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "be", 145.73828125);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "cd", 145.673828125);

		// single (wider) single double
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(Smmmmmmmm (VP (V (\\L ʃu (\\G ab ) ) ) (aux (\\L be (\\G cd ) ) )   ) )", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "Smmmmmmmm", 100.0);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "VP", 133.0029296875);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "V", 112.6767578125);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ʃu", 111.466796875);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ab", 110.3359375);
		node1 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "aux", 153.68359375);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "be", 156.07421875);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "cd", 156.009765625);

		// single single (wider) double
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (VPmmmmmmmmmm (V (\\L ʃu (\\G ab ) ) ) (aux (\\L be (\\G cd ) ) )   ) )", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();

		checkNodeContentAndXCoordinate(node, "S", 151.0029296875);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "VPmmmmmmmmmm", 100.0);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "V", 126.34375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ʃu", 125.1337890625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ab", 124.0029296875);
		node1 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "aux", 167.3505859375);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "be", 169.7412109375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "cd", 169.6767578125);

		// single double single
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (VP (V (\\L ʃu (\\G ab ) ) ) (aux (\\L be (\\G cd ) ) ) )(NP (Det (\\L the (\\G jj ) ) ) ) )", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "S", 150.671875);
		assertEquals(150.671875, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "VP", 122.6669921875);
		assertEquals(122.6669921875, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "V", 102.3408203125);
		assertEquals(102.3408203125, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ʃu", 101.130859375);
		assertEquals(101.130859375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ab", 100.0);
		node1 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "aux", 143.34765625);
		assertEquals(143.34765625, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "be", 145.73828125);
		assertEquals(145.73828125, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "cd", 145.673828125);
		assertEquals(145.673828125, node2.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "NP", 191.5966796875);
		assertEquals(191.5966796875, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "Det", 190.673828125);
		assertEquals(190.673828125, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "the", 190.919921875);
		assertEquals(190.919921875, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "jj", 196.404296875);
		assertEquals(196.404296875, node2.getXCoordinate(), 0.0);

		// single double (wider) single
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (VPmmmmmmmmmm (V (\\L ʃu (\\G ab ) ) ) (aux (\\L be (\\G cd ) ) ) )(NP (Det (\\L the (\\G jj ) ) ) ) )", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		assertEquals(174.6748046875, node.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(0);
		assertEquals(100.0, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(126.34375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(125.1337890625, node2.getXCoordinate(), 0.0);
		node1 = node1.getDaughters().get(1);
		assertEquals(167.3505859375, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(169.7412109375, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(169.6767578125, node2.getXCoordinate(), 0.0);
		node1 = node.getDaughters().get(1);
		assertEquals(239.6025390625, node1.getXCoordinate(), 0.0);
		node2 = node1.getDaughters().get(0);
		assertEquals(238.6796875, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(238.92578125, node2.getXCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(244.41015625, node2.getXCoordinate(), 0.0);

		// two non-terminals getting zero or negative x-coordinates before fix
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(S (part) (S2 (DP1) ) )", origTree);
		ltTree.setInitialXCoordinate(10);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "S", 41.6904296875);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "part", 10.0);
		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "S2", 63.0478515625);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "DP1", 58.71484375);

		// two leaf nodes overlapping x-coordinates before fix
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(PT=NP[4]:PF=Objc:PD=det\n"
				+ "(part(\\L את/אֶת־[285] (\\G object marker)))\n"
				+ "(SP[4:SPR=Appo]\n"
				+ "(SP[25:SPR=Xatr] (DP\n"
				+ "(art(\\L ה/הַ[292] (\\G the)))\n"
				+ "(subs(\\L מאור/מָּאֹ֤ור [293] (\\G lamp [/am sg a - /A])))))\n"
				+ "(SP[24:SPR=atr] (DP (art(\\L ה/הַ[294] (\\G the)))))))", origTree);
		ltTree.setInitialXCoordinate(10);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "PT=NP[4]:PF=Objc:PD=det", 135.056640625);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "part", 38.6259765625);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "את/אֶת־[285]", 10.0);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "object marker", 11.5556640625);

		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "SP[4:SPR=Appo]", 213.8095703125);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "SP[25:SPR=Xatr]", 159.7568359375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "DP", 195.14453125);
		LingTreeNode node3 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "art", 135.912109375);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "ה/הַ[292]", 115.966796875);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "the", 133.908203125);

		node3 = node2.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node3, "subs", 233.4267578125);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "מאור/מָּאֹ֤ור [293]", 198.53125);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "", 204.419921875);

		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "SP[24:SPR=atr]", 319.662109375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "DP", 350.716796875);
		node3 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "art", 352.0498046875);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "ה/הַ[294]", 332.1044921875);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "the", 350.0458984375);
	}

	@Test
	public void calculateXCoordinateOfEveryNodeRevisedCenterOnDaughtersTest() {
		// long tree that goes from left to right as it goes down
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(IP(DP1 V-IIA-3P (\\T \\L ēsan (\\G They were ) ) )"
				+ " (I'(VP1 (Adv-Conj? (\\L de (\\G now ) ) )(VP2(V'(V-PPA-NMP(\\L proskarterountes (\\G steadfastly continuing ) ) )"
				+ " (DP2(DP3(D'1(Art-DFS1 (\\L tē (\\G the )) ) ( NP1 (N'1(N-DFS1 (\\L didachē (\\G teaching ) ) )"
				+ " (DP4 (D'2 (Art-GMP (\\L tōn (\\G of the ) ) ) ) (NP2 (N'2  (N (\\L apostolōn (\\G apostles ) ) ) )))))))"
				+ " (Conj (\\L kai(\\G and ) ) )(DP5 (DP6(D'3(Art-DFS2 (\\L tē (\\G  the )  ) )"
				+ " (NP3(N'3(N-DFS2 (\\L koinōnia (\\G fellowship )) ) ) )) )"
				+ " (DP7 (DP8 (D'4(Art-DFS3 (\\L tē (\\G the ) ) )(NP4(N'4(N-DFS3(\\L klasei (\\G breaking of bread ) ) ) ) ) ) )"
				+ " (Conj (\\L kai (\\G and ) ) )(DP9 (D'5 (Art-DFP (\\L tais (\\G the ) ) )"
				+ " (NP5 (N'5 (N-DFP (\\L proseuchais (\\G prayers ) ) ) ) )) ) )) ))))) ) ", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		ltTree.setCenterColumnOrientedOnDaughtersWidth(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		LingTreeNode node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "IP", 256.9041290283203);
		LingTreeNode node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "DP1 V-IIA-3P", 100.0);
		LingTreeNode node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "ēsan", 123.3818359375);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "They were", 107.154296875);
		node1 = node.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node1, "I'", 386.2340393066406);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "VP1", 378.6432189941406);
		LingTreeNode node3 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "Adv-Conj?", 200.330078125);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "de", 220.66796875);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "now", 215.986328125);
		node3 = node2.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node3, "VP2", 540.9631958007812);
		node3 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "V'", 546.2190551757812);
		LingTreeNode node4 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "V-PPA-NMP", 309.0185546875);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "proskarterountes", 296.62890625);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "steadfastly continuing", 283.65625);
		node4 = node3.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node4, "DP2", 751.2369384765625);
		// and so on...

		// tree that goes from right to left as it goes down
		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(Word  (Infl[n] (Stem[n]  (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))\n"
				+ " (Aspect (\\L yka: (\\G  ipfv ))) \n"
				+ "(Object (\\L  ma: (\\G 1.obj)))) \n"
				+ "(Derv (\\L  na (\\G nmlz))) )\n"
				+ " (Poss (\\L  yki (\\G  2.poss)))\n"
				+ "     (Case (\\L  paq (\\G  purp))))\n"
				+ ")", origTree);
		ltTree.setUseColumnOrientedAlgorithm(true);
		ltTree.setCenterColumnOrientedOnDaughtersWidth(true);
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		node = ltTree.getRootNode();
		checkNodeContentAndXCoordinate(node, "Word", 322.2607421875);
		node1 = node.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node1, "Infl[n]", 320.27734375);
		node2 = node1.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "Stem[n]", 225.8388671875);
		node3 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node3, "Infl[v]", 168.0068359375);
		node4 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "Stem[v]", 100.0);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "root", 109.65625);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "rika", 108.90625);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "to.see", 102.9853515625);
		node4 = node3.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node4, "Aspect", 168.66015625);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "yka:", 174.21484375);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "ipfv", 175.9931640625);
		node4 = node3.getDaughters().get(2);
		checkNodeContentAndXCoordinate(node4, "Object", 232.0);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "ma:", 238.169921875);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "1.obj", 234.9912109375);
		node3 = node2.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node3, "Derv", 294.677734375);
		node4 = node3.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "na", 300.2177734375);
		node4 = node4.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node4, "nmlz", 294.00390625);
		node2 = node1.getDaughters().get(1);
		checkNodeContentAndXCoordinate(node2, "Poss", 356.0107421875);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "yki", 358.814453125);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "2.poss", 349.33984375);
		node2 = node1.getDaughters().get(2);
		checkNodeContentAndXCoordinate(node2, "Case", 415.041015625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "paq", 417.00390625);
		node2 = node2.getDaughters().get(0);
		checkNodeContentAndXCoordinate(node2, "purp", 414.6953125);
	}

	private void checkNodeContentAndXCoordinate(LingTreeNode node, String content, double xcoord) {
		assertEquals(content, node.getContent());
		assertEquals(xcoord, node.getXCoordinate(), 0.0);

	}
	@Test
	public void drawAsSVGTest() {
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(S (NP< (\\L Juan> (\\G John))) (VP (V (\\L duerme & mas (\\G sleeps)))))", origTree);
		drawer = new TreeDrawer(ltTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
		StringBuilder sb = drawer.drawAsSVG();
		String result = sb.toString();
		assertEquals(true, result.contains("NP&lt;"));
		assertEquals(true, result.contains("Juan&gt;"));
		assertEquals(true, result.contains("duerme &amp; "));

		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(Mascoian((Enxet Sur)(Enlhet Norte))((((Angaité)(Sanapaná)))((Kaskihá)(Toba-Maskoy))))", origTree);
        ltTree.setDrawVerticalLineWithEmptyText(true);
		drawer = new TreeDrawer(ltTree);
		ltTree.setUseColumnOrientedAlgorithm(false);
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
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		StringBuilder sb = drawer.drawAsSVG();
		String result = sb.toString();
		assertEquals(true, result.contains("NP&lt;"));
		assertEquals(true, result.contains("Juan&gt;"));
		assertEquals(true, result.contains("duerme &amp; "));

		origTree = new LingTreeTree();
		ltTree = TreeBuilder.parseAString("(1234567890(123(\\\\L1234(\\\\G1)))(12(1234(123456(123(\\\\L456(\\\\G3)))))(0987(345(34(\\\\L123456(\\\\G4)))))))", origTree);
        ltTree.setDrawVerticalLineWithEmptyText(true);
		ltTree.setUseColumnOrientedAlgorithm(true);
		drawer = new TreeDrawer(ltTree);
		sb = drawer.drawAsSVG();
		result = sb.toString();
		assertEquals(true, result.contains(">1234567890<"));
		assertEquals(true, result.contains(">123456<"));
		assertEquals(true, result.contains(">123<"));
		assertEquals(true, result.contains("<line x1=\"191.998046875\" y1=\"105.595703125\" x2=\"118.9990234375\" y2=\"119.595703125\" stroke=\"#000000\" stroke-width=\"10.0\"/>"));
	}
}
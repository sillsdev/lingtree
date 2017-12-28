/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.view.JavaFXThreadingRule;

/**
 * @author Andy Black
 *
 */
public class TreeDrawerTest extends ServiceBaseTest {
	
	private TreeDrawer drawer;

	@Test
	public void calculateMaxHeightsPerLevelTest() {
		LingTreeTree ltTree = parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))");
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		HashMap<Integer, Double> maxHeightPerLevel = drawer.getMaxHeightPerLevel();
		assertEquals(13.2890625, maxHeightPerLevel.get(1), 0.0);
		assertEquals(13.2890625, maxHeightPerLevel.get(2), 0.0);
		assertEquals(19.62890625, maxHeightPerLevel.get(3), 0.0);
		assertEquals(19.62890625, maxHeightPerLevel.get(4), 0.0);
		assertEquals(13.40625, maxHeightPerLevel.get(5), 0.0);
		assertNull(maxHeightPerLevel.get(6));
		
		ltTree = parseAString("(S (NP (N' (N (\\L Juan (\\G John))))) (VP (V (\\L duerme (\\G sleeps)))))");
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
	}

	@Test
	public void calculateYCoordinateOfEveryNodeTest() {
		LingTreeTree ltTree = parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))");
		drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		LingTreeNode node = ltTree.getRootNode();
		assertEquals(100.0, node.getYCoordinate(), 0.0);
		LingTreeNode node1 = node.getDaughters().get(0);
		assertEquals(133.2890625, node1.getYCoordinate(), 0.0);
		LingTreeNode node2 = node.getDaughters().get(1);
		assertEquals(133.2890625, node2.getYCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(166.578125, node1.getYCoordinate(), 0.0);
		node1 = node1.getDaughters().get(0);
		assertEquals(186.20703125, node1.getYCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(166.578125, node2.getYCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(206.20703125, node2.getYCoordinate(), 0.0);
		node2 = node2.getDaughters().get(0);
		assertEquals(225.8359375, node2.getYCoordinate(), 0.0);
	}

	@Test
	public void calculateXCoordinateOfEveryNodeTest() {
		LingTreeTree ltTree = parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))");
		drawer = new TreeDrawer(ltTree);
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
	}
}

/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.view;

import static org.junit.Assert.*;
import javafx.scene.control.TextArea;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TreeDescriptionUIServiceTest {
	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	TextArea treeDescription;
	
	@Before
	public void setUp() throws Exception {
		treeDescription = new TextArea("()");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void processRightParenthesisTest() {
		treeDescription.setText("(S (NP (N (beans))) (VP (V (grow))))");
		checkMatchingLeft(0, -1);
		checkMatchingLeft(2, 0);
		checkMatchingLeft(6, 3);
		checkMatchingLeft(9, 7);
		checkMatchingLeft(12, 10);
		checkMatchingLeft(16, 10);
		checkMatchingLeft(18, 7);
		checkMatchingLeft(19, 3);
		checkMatchingLeft(20, 0);
		checkMatchingLeft(23, 20);
		checkMatchingLeft(25, 20);
		checkMatchingLeft(26, 24);
		checkMatchingLeft(29, 27);
		checkMatchingLeft(34, 24);
		checkMatchingLeft(35, 20);
		checkMatchingLeft(36, 0);
		checkMatchingLeft(37, -1);
	}

	private void checkMatchingLeft(int iRightPos, int iLeftExpected) {
		treeDescription.positionCaret(iRightPos);
		TreeDescriptionUIService.processRightParenthesis(treeDescription, null, null);
		int iLeftPos = TreeDescriptionUIService.findMatchingLeftParenthesisAndHighlightIt(iRightPos);
		assertEquals(iLeftExpected, iLeftPos);
	}

	@Test
	public void processLeftParenthesisTest() {
		treeDescription.setText("(S (NP (N (beans))) (VP (V (grow))))");
		checkMatchingRight(0, -1);
		checkMatchingRight(1, 35);
		checkMatchingRight(4, 18);
		checkMatchingRight(8, 17);
		checkMatchingRight(12, 16);
		checkMatchingRight(20, 35);
		checkMatchingRight(21, 34);
		checkMatchingRight(25, 33);
		checkMatchingRight(28, 32);
		checkMatchingRight(36, -1);
	}

	private void checkMatchingRight(int iLeftPos, int iRightExpected) {
		treeDescription.positionCaret(iLeftPos);
		TreeDescriptionUIService.processLeftParenthesis(treeDescription, null, null);
		int iRightPos = TreeDescriptionUIService.findMatchingRightParenthesisAndHighlightIt(iLeftPos);
		assertEquals(iRightExpected, iRightPos);
	}
}

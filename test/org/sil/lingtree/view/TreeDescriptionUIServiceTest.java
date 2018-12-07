/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.view;

import static org.junit.Assert.*;

import org.fxmisc.richtext.InlineCssTextArea;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.utility.view.JavaFXThreadingRule;

public class TreeDescriptionUIServiceTest {
	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	InlineCssTextArea treeDescription;
	
	@Before
	public void setUp() throws Exception {
		treeDescription = new InlineCssTextArea("()");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void processRightParenthesisTest() {
		treeDescription.replaceText("(S (NP (N (beans))) (VP (V (grow))))");
		checkMatchingLeft(0, -1);
		checkMatchingLeft(2, 0);
		checkMatchingLeft(6, 3);
		checkMatchingLeft(9, 7);
		checkMatchingLeft(12, 10);
		checkMatchingLeft(16, 10);
		checkMatchingLeft(17, 7);
		checkMatchingLeft(18, 3);
		checkMatchingLeft(32, 27);
		checkMatchingLeft(33, 24);
		checkMatchingLeft(35, 0);
		checkMatchingLeft(36, -1);
	}

	private void checkMatchingLeft(int iRightPos, int iLeftExpected) {
		treeDescription.moveTo(iRightPos);
		TreeDescriptionUIService.processRightParenthesis(treeDescription, iRightPos, true, 750.0, null, null);
		int iLeftPos = TreeDescriptionUIService.findMatchingLeftParenthesisAndHighlightIt(iRightPos);
		assertEquals(iLeftExpected, iLeftPos);
	}

	@Test
	public void processLeftParenthesisTest() {
		treeDescription.replaceText("(S (NP (N (beans))) (VP (V (grow))))");
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
		treeDescription.moveTo(iLeftPos);
		TreeDescriptionUIService.processLeftParenthesis(treeDescription, false, 125.0, null, null);
		int iRightPos = TreeDescriptionUIService.findMatchingRightParenthesisAndHighlightIt(iLeftPos, false);
		assertEquals(iRightExpected, iRightPos);
	}
}

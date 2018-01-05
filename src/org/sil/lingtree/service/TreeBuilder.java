/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.assertEquals;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.sil.lingtree.descriptionparser.DescriptionLexer;
import org.sil.lingtree.descriptionparser.DescriptionParser;
import org.sil.lingtree.model.LingTreeTree;

/**
 * @author Andy Black
 *
 */
public class TreeBuilder {

	
	public static LingTreeTree parseAString(String sInput, LingTreeTree origTree) {
		CharStream input = CharStreams.fromString(sInput);
		DescriptionLexer lexer = new DescriptionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DescriptionParser parser = new DescriptionParser(tokens);

		// begin parsing at rule 'description'
		ParseTree parseTree = parser.description();
		int numErrors = parser.getNumberOfSyntaxErrors();
		assertEquals(0, numErrors);
		ParseTreeWalker walker = new ParseTreeWalker(); // create standard
														// walker
		BuildTreeFromDescriptionListener validator = new BuildTreeFromDescriptionListener(parser);
		walker.walk(validator, parseTree); // initiate walk of tree with
											// listener
		LingTreeTree ltTree = validator.getTree();
		restoreTreeParameters(origTree, ltTree);
		return ltTree;
	}

	private static void restoreTreeParameters(LingTreeTree origTree, LingTreeTree ltTree) {
		ltTree.setBackgroundColor(origTree.getBackgroundColor());
		ltTree.setGlossFontInfo(origTree.getGlossFontInfo());
		ltTree.setHorizontalGap(origTree.getHorizontalGap());
		ltTree.setInitialXCoordinate(origTree.getInitialXCoordinate());
		ltTree.setInitialYCoordinate(origTree.getInitialYCoordinate());
		ltTree.setLexGlossGapAdjustment(origTree.getLexGlossGapAdjustment());
		ltTree.setLexicalFontInfo(origTree.getLexicalFontInfo());
		ltTree.setLineColor(origTree.getLineColor());
		ltTree.setLineWidth(origTree.getLineWidth());
		ltTree.setNonTerminalFontInfo(origTree.getNonTerminalFontInfo());
		ltTree.setShowFlatView(origTree.isShowFlatView());
		ltTree.setSubscriptFontInfo(origTree.getSubscriptFontInfo());
		ltTree.setSuperscriptFontInfo(origTree.getSuperscriptFontInfo());
		ltTree.setVerticalGap(origTree.getVerticalGap());
	}
}

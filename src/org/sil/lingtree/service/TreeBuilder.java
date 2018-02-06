/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.sil.lingtree.descriptionparser.DescriptionErrorInfo;
import org.sil.lingtree.descriptionparser.DescriptionErrorListener;
import org.sil.lingtree.descriptionparser.DescriptionLexer;
import org.sil.lingtree.descriptionparser.DescriptionParser;
import org.sil.lingtree.descriptionparser.DescriptionErrorListener.VerboseListener;
import org.sil.lingtree.model.LingTreeTree;

/**
 * @author Andy Black
 *
 */
public class TreeBuilder {

	static int numberOfErrors;
	static int characterPositionInLineOfError;
	static int lineNumberOfError;
	static String errorMessage;
	static String sDescription;

	public static int getNumberOfErrors() {
		return numberOfErrors;
	}

	public static int getLineNumberOfError() {
		return lineNumberOfError;
	}

	public static int getCharacterPositionInLineOfError() {
		return characterPositionInLineOfError;
	}

	public static String getErrorMessage() {
		return errorMessage;
	}

	public static String getMarkedDescription(String sMark) {
		StringBuilder sb = new StringBuilder();
		int iLineNum = lineNumberOfError - 1;
		int iLineBeg = sDescription.indexOf("\n");
		if (iLineNum == 0) {
			iLineBeg = 0;
		} else {
			while (iLineBeg < iLineNum) {
				String sRest = sDescription.substring(iLineBeg + 1);
				iLineBeg += sRest.indexOf("\n");
			}
		}
		sb.append(sDescription.substring(0, iLineBeg + characterPositionInLineOfError));
		sb.append(sMark);
		sb.append(sDescription.substring(iLineBeg + characterPositionInLineOfError));
		return sb.toString();
	}

	public static LingTreeTree parseAString(String sInput, LingTreeTree origTree) {
		sDescription = sInput;
		CharStream input = CharStreams.fromString(sInput);
		DescriptionLexer lexer = new DescriptionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DescriptionParser parser = new DescriptionParser(tokens);

		// add error listener
		parser.removeErrorListeners();
		VerboseListener errListener = new DescriptionErrorListener.VerboseListener();
		errListener.clearErrorMessageList();
		parser.addErrorListener(errListener);

		// begin parsing at rule 'description'
		ParseTree parseTree = parser.description();
		numberOfErrors = parser.getNumberOfSyntaxErrors();
		if (numberOfErrors > 0) {
			errListener = (VerboseListener) parser.getErrorListeners().get(0);
			DescriptionErrorInfo info = errListener.getErrorMessages().get(0);
			errorMessage = info.getMsg();
			lineNumberOfError = info.getLine();
			characterPositionInLineOfError = info.getCharPositionInLine();
			return origTree;
		}
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
		ltTree.setSaveAsPng(origTree.isSaveAsPng());
		ltTree.setSaveAsSVG(origTree.isSaveAsSVG());
		ltTree.setShowFlatView(origTree.isShowFlatView());
		ltTree.setSubscriptFontInfo(origTree.getSubscriptFontInfo());
		ltTree.setSuperscriptFontInfo(origTree.getSuperscriptFontInfo());
		ltTree.setVerticalGap(origTree.getVerticalGap());
	}
}

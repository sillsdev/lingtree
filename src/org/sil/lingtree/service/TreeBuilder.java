/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.sil.lingtree.descriptionparser.DescriptionErrorInfo;
import org.sil.lingtree.descriptionparser.DescriptionErrorListener;
import org.sil.lingtree.descriptionparser.DescriptionErrorListener.VerboseListener;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionLexer;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser;
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
		int iCharPosOfMark = getPositionOfMark();
		sb.append(sDescription.substring(0, iCharPosOfMark));
		sb.append(sMark);
		sb.append(sDescription.substring(iCharPosOfMark));
		return sb.toString();
	}

	public static String getDescriptionBeforeMark() {
		int iCharPosOfMark = getPositionOfMark();
		//System.out.println("Before: iCharPosOfMark=" + iCharPosOfMark);
		return sDescription.substring(0, iCharPosOfMark);
	}

	public static String getDescriptionAfterMark() {
		int iCharPosOfMark = getPositionOfMark();
		//System.out.println("After:  iCharPosOfMark=" + iCharPosOfMark);
		return sDescription.substring(iCharPosOfMark);
	}

	private static int getPositionOfMark() {
		int iCharPosOfMark = 0;
		if (lineNumberOfError == 1) {
			iCharPosOfMark = characterPositionInLineOfError;
		} else {
			int iCurrentLineNum = 2;
			int iCharPosOfNL = sDescription.indexOf("\n");
			while (iCharPosOfNL > -1 && iCurrentLineNum <= lineNumberOfError) {
				//System.out.println("iCharPosOfMark=" + iCharPosOfMark + " iCharPosOfNL=" + iCharPosOfNL);
				iCharPosOfMark += (iCharPosOfNL + 1);
				String sRest = sDescription.substring(iCharPosOfMark);
				//System.out.println("\tiCharPosOfMark=" + iCharPosOfMark + " sRest='" + sRest + "'");
				iCharPosOfNL = sRest.indexOf("\n");
				iCurrentLineNum++;
			}
			iCharPosOfMark = iCharPosOfMark + characterPositionInLineOfError;
		}
		return iCharPosOfMark;
	}

	public static LingTreeTree parseAString(String sInput, LingTreeTree origTree) {
		sDescription = sInput;
		CharStream input = CharStreams.fromString(sInput);
		DescriptionLexer lexer = new DescriptionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DescriptionParser parser = new DescriptionParser(tokens);

		// try with simpler/faster SLL(*)
		parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
		// add error listener
		parser.removeErrorListeners();
		VerboseListener errListener = new DescriptionErrorListener.VerboseListener();
		errListener.clearErrorMessageList();
		parser.addErrorListener(errListener);
		parser.setErrorHandler(new BailErrorStrategy());

		ParseTree parseTree = null;
		try {
		// begin parsing at rule 'description'
		parseTree = parser.description();
		// if we get here, there was no syntax error and SLL(*) was enough;
		// there is no need to try full LL(*)
		}
		catch (ParseCancellationException ex) {// thrown by BailErrorStrategy
			System.out.println("exception found");
			tokens.reset(); // rewind input stream
			parser.reset();
			parser.removeErrorListeners();
			errListener = new DescriptionErrorListener.VerboseListener();
			errListener.clearErrorMessageList();
			parser.addErrorListener(errListener);
			// full now with full LL(*)
			parser.getInterpreter().setPredictionMode(PredictionMode.LL);
			parser.description();
		}
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
		ltTree.setEmptyElementFontInfo(origTree.getEmptyElementFontInfo());
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
		ltTree.setVerticalGap(origTree.getVerticalGap());
	}
}

// Copyright (c) 2016-2017 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * @author Andy Black
 * 
 */

package org.sil.lingtree.descriptionparser;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.antlr.v4.runtime.*;
import org.sil.lingtree.descriptionparser.DescriptionConstants;
import org.sil.lingtree.descriptionparser.DescriptionErrorInfo;
import org.sil.lingtree.descriptionparser.DescriptionErrorListener;
import org.sil.lingtree.descriptionparser.DescriptionErrorListener.VerboseListener;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionLexer;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser;

public class DescriptionRecognizerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validDescriptionsTest() {
		// following from original Help Topics documentation
		checkValidDescription(
				"(S (NP) (VP))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP) (closeParen )  )) (node (openParen () (content VP) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (NP (Lee)) (VP (V (gets)) (NP (Det (the)) (N (idea)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP ) (node (openParen () (content Lee) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (content gets) (closeParen ))) (closeParen )  )) (node (openParen () (content NP ) (node (openParen () (content Det ) (node (openParen () (content the) (closeParen ))) (closeParen )  )) (node (openParen () (content N ) (node (openParen () (content idea) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (Paul \\(the bear\\)))",
				"(description (node (openParen () (content NP ) (node (openParen () (content Paul \\(the bear\\)) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (Paul (the bear)))",
				"(description (node (openParen () (content NP ) (node (openParen () (content Paul ) (node (openParen () (content the bear) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (NP (\\L John)) (VP (V (\\L sleeps))))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP ) (node (openParen () (type (nodeType \\L)) (content  John) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (type (nodeType \\L)) (content  sleeps) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (NP (John)) (VP (V (sleeps))))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP ) (node (openParen () (content John) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (content sleeps) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP ) (node (openParen () (type (nodeType \\L)) (content  Juan ) (node (openParen () (type (nodeType \\G)) (content  John) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (type (nodeType \\L)) (content  duerme ) (node (openParen () (type (nodeType \\G)) (content  sleeps) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (NP (\\L Juan (John))) (VP (V (\\L duerme (sleeps)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP ) (node (openParen () (type (nodeType \\L)) (content  Juan ) (node (openParen () (content John) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (type (nodeType \\L)) (content  duerme ) (node (openParen () (content sleeps) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (\\T all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (type (lineType \\T )) (content all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (content all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"((\\O σ (O (\\L t)) (N (R (\\L e)))) (\\O σ (O (\\L p)) (N (R (\\L i)) (C (\\L k)))))",
				"(description (node (openParen () (node (openParen () (type (lineType \\O )) (content σ ) (node (openParen () (content O ) (node (openParen () (type (nodeType \\L)) (content  t) (closeParen ))) (closeParen )  )) (node (openParen () (content N ) (node (openParen () (content R ) (node (openParen () (type (nodeType \\L)) (content  e) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (type (lineType \\O )) (content σ ) (node (openParen () (content O ) (node (openParen () (type (nodeType \\L)) (content  p) (closeParen ))) (closeParen )  )) (node (openParen () (content N ) (node (openParen () (content R ) (node (openParen () (type (nodeType \\L)) (content  i) (closeParen ))) (closeParen )  )) (node (openParen () (content C ) (node (openParen () (type (nodeType \\L)) (content  k) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"((σ (O (\\L t)) (N (R (\\L e)))) (σ (O (\\L p)) (N (R (\\L i)) (C (\\L k)))))",
				"(description (node (openParen () (node (openParen () (content σ ) (node (openParen () (content O ) (node (openParen () (type (nodeType \\L)) (content  t) (closeParen ))) (closeParen )  )) (node (openParen () (content N ) (node (openParen () (content R ) (node (openParen () (type (nodeType \\L)) (content  e) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content σ ) (node (openParen () (content O ) (node (openParen () (type (nodeType \\L)) (content  p) (closeParen ))) (closeParen )  )) (node (openParen () (content N ) (node (openParen () (content R ) (node (openParen () (type (nodeType \\L)) (content  i) (closeParen ))) (closeParen )  )) (node (openParen () (content C ) (node (openParen () (type (nodeType \\L)) (content  k) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (NP/s1 (N (dogs))) (VP (V (chase)) (NP/s2 (N (cats)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP (subscript /s 1 )) (node (openParen () (content N ) (node (openParen () (content dogs) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (content chase) (closeParen ))) (closeParen )  )) (node (openParen () (content NP (subscript /s 2 )) (node (openParen () (content N ) (node (openParen () (content cats) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (NP1 (N (dogs))) (VP (V (chase)) (NP2 (N (cats)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP1 ) (node (openParen () (content N ) (node (openParen () (content dogs) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (content chase) (closeParen ))) (closeParen )  )) (node (openParen () (content NP2 ) (node (openParen () (content N ) (node (openParen () (content cats) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(IP (DP) (I/S'))",
				"(description (node (openParen () (content IP ) (node (openParen () (content DP) (closeParen )  )) (node (openParen () (content I (superscript /S ')) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(IP (DP) (I'))",
				"(description (node (openParen () (content IP ) (node (openParen () (content DP) (closeParen )  )) (node (openParen () (content I') (closeParen ))) (closeParen ))) <EOF>)");

		// need examples with both \T and \L, in both orders, etc.
		checkValidDescription(
				"(NP (\\T\\L all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (type (lineType \\T) (nodeType \\L)) (content  all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (\\L\\T all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (type (nodeType \\L) (lineType \\T )) (content all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (\\T\\G all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (type (lineType \\T) (nodeType \\G)) (content  all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (\\G\\T all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (type (nodeType \\G) (lineType \\T )) (content all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (\\O\\L all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (type (lineType \\O) (nodeType \\L)) (content  all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (\\L\\O all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (type (nodeType \\L) (lineType \\O )) (content all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (\\O\\G all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (type (lineType \\O) (nodeType \\G)) (content  all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(NP (\\G\\O all the King’s men))",
				"(description (node (openParen () (content NP ) (node (openParen () (type (nodeType \\G) (lineType \\O )) (content all the King’s men) (closeParen ))) (closeParen ))) <EOF>)");

		// single combined Unicode acute  i (Ã­)
		checkValidDescription(
				"(S (NP (\\L \u00ED)) (VP (V (\\L i\u0301))))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP ) (node (openParen () (type (nodeType \\L)) (content  í) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (type (nodeType \\L)) (content  í) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)"); 
		// subscript and superscript
		checkValidDescription(
				"(S (NP/s1/S' (N (dogs))) (VP (V (chase)) (NP/S'/s2 (N (cats)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content NP (subscript /s 1) (superscript /S ' )) (node (openParen () (content N ) (node (openParen () (content dogs) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (content chase) (closeParen ))) (closeParen )  )) (node (openParen () (content NP (superscript /S ') (subscript /s 2 )) (node (openParen () (content N ) (node (openParen () (content cats) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (/s1/S' (N (dogs))) (VP (V (chase)) (/S'/s2 (N (cats)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content (subscript /s 1) (superscript /S ' )) (node (openParen () (content N ) (node (openParen () (content dogs) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (content chase) (closeParen ))) (closeParen )  )) (node (openParen () (content (superscript /S ') (subscript /s 2 )) (node (openParen () (content N ) (node (openParen () (content cats) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (/s1 (N (dogs))) (VP (V (chase)) (/S' (N (cats)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content (subscript /s 1 )) (node (openParen () (content N ) (node (openParen () (content dogs) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (content chase) (closeParen ))) (closeParen )  )) (node (openParen () (content (superscript /S ' )) (node (openParen () (content N ) (node (openParen () (content cats) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
		checkValidDescription(
				"(S (/S' (N (dogs))) (VP (V (chase)) (/s2 (N (cats)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content (superscript /S ' )) (node (openParen () (content N ) (node (openParen () (content dogs) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (content chase) (closeParen ))) (closeParen )  )) (node (openParen () (content (subscript /s 2 )) (node (openParen () (content N ) (node (openParen () (content cats) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");

		// backslashes and forward slashes with non-keyword items
		checkValidDescription(
				"(S (/S'/Comp (N (do\\gs))) (VP (V (chase)) (/s2 (N (cats)))))",
				"(description (node (openParen () (content S ) (node (openParen () (content (superscript /S ' /C omp )) (node (openParen () (content N ) (node (openParen () (content do \\g s) (closeParen ))) (closeParen ))) (closeParen )  )) (node (openParen () (content VP ) (node (openParen () (content V ) (node (openParen () (content chase) (closeParen ))) (closeParen )  )) (node (openParen () (content (subscript /s 2 )) (node (openParen () (content N ) (node (openParen () (content cats) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");

		// empty elements
		checkValidDescription("(IP (DP/_m (D' (D'/s1 (D (\\L le (\\G FM)))\n" +
				"(NP (\\T \\L xuz noo (\\G my father))))\n" +
				"(IP (\\E t/_j))))\n" +
				"(IP (I' (I/_i (\\T \\L w-guu (\\G C-sow)))\n" +
				"(VP (VP (DP (\\E t/_m))\n" +
				"(V' (V (\\E t/_i)) (DP (\\T \\L bni (\\G seed)))))\n" +
				"(IP/_j (I' (I/_k (\\L y-ra (\\G P-all)))\n" +
				"(QP (DP/s1 (\\T \\E pro)) (Q' (Q (\\E t/_k))\n" +
				"(DP/s2 (\\T \\L mee bzaan noo (\\G my brothers)))))))))))",
				"(description (node (openParen () (content IP ) (node (openParen () " +
						"(content DP (subscript /_ m )) (node (openParen () (content D' ) (node (openParen () " +
						"(content D' (subscript /s 1 )) (node (openParen () (content D ) (node " +
						"(openParen () (type (nodeType \\L)) (content  le ) (node (openParen () (type " +
						"(nodeType \\G)) (content  FM) (closeParen ))) (closeParen ))) (closeParen ))) " +
						"(node (openParen () (content NP ) (node (openParen () (type (lineType \\T ) " +
						"(nodeType \\L)) (content  xuz noo ) (node (openParen () (type (nodeType \\G)) " +
						"(content  my father) (closeParen ))) (closeParen ))) (closeParen ))) " +
						"(closeParen ))) (node (openParen () (content IP ) (node (openParen () (type " +
						"(nodeType \\E)) (content  t (subscript /_ j)) (closeParen ))) (closeParen ))) " +
						"(closeParen ))) (closeParen ))) (node (openParen () (content IP ) " +
						"(node (openParen () (content I' ) (node (openParen () (content I (subscript /_ i )) " +
						"(node (openParen () (type (lineType \\T ) (nodeType \\L)) (content  w-guu ) " +
						"(node (openParen () (type (nodeType \\G)) (content  C-sow) (closeParen ))) " +
						"(closeParen ))) (closeParen ))) (node (openParen () (content VP ) " +
						"(node (openParen () (content VP ) (node (openParen () (content DP ) " +
						"(node (openParen () (type (nodeType \\E)) (content  t (subscript /_ m)) (closeParen ))) " +
						"(closeParen ))) (node (openParen () (content V' ) (node (openParen () " +
						"(content V ) (node (openParen () (type (nodeType \\E)) (content  t (subscript /_ i)) " +
						"(closeParen ))) (closeParen )  )) (node (openParen () (content DP ) " +
						"(node (openParen () (type (lineType \\T ) (nodeType \\L)) (content  bni ) " +
						"(node (openParen () (type (nodeType \\G)) (content  seed) (closeParen ))) " +
						"(closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) " +
						"(node (openParen () (content IP (subscript /_ j )) (node (openParen () (content I' ) " +
						"(node (openParen () (content I (subscript /_ k )) (node (openParen () " +
						"(type (nodeType \\L)) (content  y-ra ) (node (openParen () (type (nodeType \\G)) " +
						"(content  P-all) (closeParen ))) (closeParen ))) (closeParen ))) " +
						"(node (openParen () (content QP ) (node (openParen () (content DP " +
						"(subscript /s 1 )) (node (openParen () (type (lineType \\T ) (nodeType \\E)) " +
						"(content  pro) (closeParen ))) (closeParen )  )) (node (openParen () " +
						"(content Q' ) (node (openParen () (content Q ) (node (openParen () " +
						"(type (nodeType \\E)) (content  t (subscript /_ k)) (closeParen ))) (closeParen ))) " +
						"(node (openParen () (content DP (subscript /s 2 )) (node (openParen () " +
						"(type (lineType \\T ) (nodeType \\L)) (content  mee bzaan noo ) " +
						"(node (openParen () (type (nodeType \\G)) (content  my brothers) " +
						"(closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) " +
						"(closeParen ))) (closeParen ))) (closeParen ))) (closeParen ))) " +
						"(closeParen ))) (closeParen ))) (closeParen ))) <EOF>)");
	}

	private void checkValidDescription(String sDescription, String sANTLRTree) {
		DescriptionParser parser = parseAString(sDescription);
		int numErrors = parser.getNumberOfSyntaxErrors();
		assertEquals(0, numErrors);
		ParseTree tree = parser.description();
		assertEquals(sANTLRTree, tree.toStringTree(parser));
	}

	private DescriptionParser parseAString(String sInput) {
		CharStream input = CharStreams.fromString(sInput);
		DescriptionLexer lexer = new DescriptionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DescriptionParser parser = new DescriptionParser(tokens);
		return parser;
	}

	private DescriptionParser parseAStringExpectFailure(String sInput) {
		CharStream input = CharStreams.fromString(sInput);
		DescriptionLexer lexer = new DescriptionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DescriptionParser parser = new DescriptionParser(tokens);
		parser.removeErrorListeners();
		VerboseListener errListener = new DescriptionErrorListener.VerboseListener();
		errListener.clearErrorMessageList();
		parser.addErrorListener(errListener);
		// begin parsing at rule 'description'
		ParseTree tree = parser.description();
		// uncomment the next two lines to see what parsed
//		String sTree = tree.toStringTree(parser);
//		System.out.println(sTree);
		return parser;
	}

	@Test
	public void invalidDescriptionsTest() {
		checkInvalidDescription("(S NP) (VP))", DescriptionConstants.MISSING_OPENING_PAREN, 8, 1);
		checkInvalidDescription("S (NP (VP))", DescriptionConstants.MISSING_OPENING_PAREN, 11, 1);
		checkInvalidDescription("\\O (NP (VP))", DescriptionConstants.MISSING_OPENING_PAREN, 12, 1);
		checkInvalidDescription("\\L (NP (VP))", DescriptionConstants.MISSING_OPENING_PAREN, 12, 1);
		checkInvalidDescription("(S \\O (NP (VP))", DescriptionConstants.MISSING_OPENING_PAREN, 6, 2);
		checkInvalidDescription("(S \\L (NP (VP))", DescriptionConstants.MISSING_OPENING_PAREN, 5, 2);
		checkInvalidDescription("(S (NP (VP))", DescriptionConstants.MISSING_CLOSING_PAREN, 10, 1);
		checkInvalidDescription("(S (NP (VP)", DescriptionConstants.MISSING_CLOSING_PAREN, 10, 2);
		checkInvalidDescription("(\\O\\TS (NP) (VP))", DescriptionConstants.TOO_MANY_lINE_TYPES, 5,
				1);
		checkInvalidDescription("(\\T\\OS (NP) (VP))", DescriptionConstants.TOO_MANY_lINE_TYPES, 5,
				1);
		checkInvalidDescription("(NP (\\L\\Gnoun))", DescriptionConstants.TOO_MANY_NODE_TYPES, 9, 1);
		checkInvalidDescription("(NP (\\E\\Gnoun))", DescriptionConstants.TOO_MANY_NODE_TYPES, 9, 1);
		checkInvalidDescription("(NP (\\L\\Enoun))", DescriptionConstants.TOO_MANY_NODE_TYPES, 9, 1);
	}

	private void checkInvalidDescription(String sDescription, String sFailedPortion, int iPos,
			int iNumErrors) {
		// TODO: check position and message
		DescriptionParser parser = parseAStringExpectFailure(sDescription);
		assertEquals(iNumErrors, parser.getNumberOfSyntaxErrors());
		VerboseListener errListener = (VerboseListener) parser.getErrorListeners().get(0);
		assertNotNull(errListener);
		DescriptionErrorInfo info = errListener.getErrorMessages().get(0);
		assertNotNull(info);
		assertEquals(sFailedPortion, info.getMsg());
		assertEquals(iPos, info.getCharPositionInLine());
	}

}

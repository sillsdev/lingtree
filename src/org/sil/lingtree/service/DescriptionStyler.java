/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.fxmisc.richtext.InlineCssTextArea;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionLexer;
import org.sil.lingtree.model.AbbreviationFontInfo;
import org.sil.lingtree.model.EmptyElementFontInfo;
import org.sil.lingtree.model.GlossFontInfo;
import org.sil.lingtree.model.LexFontInfo;
import org.sil.lingtree.model.NonTerminalFontInfo;

/**
 * @author Andy Black
 * Singleton pattern for styling the tree description using CSS
 */
public class DescriptionStyler {

    private static DescriptionStyler instance;
    
    private DescriptionStyler(){
    }
    
    public static DescriptionStyler getInstance(){
        if(instance == null){
            instance = new DescriptionStyler();
        }
        return instance;
    }

	public void styleDescription(InlineCssTextArea treeDescription, double basicFontSize) {
		CharStream input = CharStreams.fromString(treeDescription.getText());
		DescriptionLexer lexer = new DescriptionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();

		String syntagmeme = "-fx-font-family: Monospaced;\n-fx-fill: black;\n-fx-font-size:"
				+ basicFontSize + "pt;";
		String nonterminal = NonTerminalFontInfo.getInstance().getCss();
		String gloss = GlossFontInfo.getInstance().getCss();
		String empty = EmptyElementFontInfo.getInstance().getCss();
		String lexical = LexFontInfo.getInstance().getCss();
		String abbreviation = AbbreviationFontInfo.getInstance().getCss();
		double dCustomFontSize = Math.max(basicFontSize-2.5, 3);
		String customfont = "\n-fx-fill:grey;\n-fx-font-size:" + dCustomFontSize + "pt;\n";

		String cssStyleClass = syntagmeme;
		String textClassToUse = NonTerminalFontInfo.getInstance().getCss();
		String textClassBeforeAbbreviation = "";
		for (Token token : tokens.getTokens()) {
			// We keep the following output for when we want to see the set of
			// tokens and their types
//			 System.out.println("token='" + token.getText() + "'; type=" +
//			 token.getType());
			switch (token.getType()) {
			// TODO: if the description grammar changes, we may need to adjust
			// the case values as they may change
			case 1: // opening parenthesis
				cssStyleClass = syntagmeme;
				textClassToUse = nonterminal;
				break;
			case 5: // \L
				cssStyleClass = syntagmeme;
				textClassToUse = lexical;
				break;
			case 6: // \G
				cssStyleClass = syntagmeme;
				textClassToUse = gloss;
				break;
			case 7: // \E
				cssStyleClass = syntagmeme;
				textClassToUse = empty;
				break;
			case 12: // /a
				cssStyleClass = syntagmeme;
				textClassBeforeAbbreviation = textClassToUse;
				textClassToUse = abbreviation;
				break;
			case 13: // /A
				cssStyleClass = syntagmeme;
				textClassToUse = textClassBeforeAbbreviation;
				break;
			case 14: // /f
				cssStyleClass = syntagmeme;
				textClassToUse = syntagmeme + customfont;
				break;
			case 15: // /F
				cssStyleClass = syntagmeme;
				textClassToUse = customfont;
				break;
			case 16: // text
			case 17: // text with spaces
				cssStyleClass = textClassToUse;
				break;
			default:
				cssStyleClass = syntagmeme;
				break;
			}
			if (token.getType() != -1) { // -1 is EOF
				int iStart = token.getStartIndex();
				int iStop = token.getStopIndex() + 1;
				treeDescription.setStyle(iStart, iStop, cssStyleClass);
			}
		}
	}

}

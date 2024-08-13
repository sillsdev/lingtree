// Generated from Description.g4 by ANTLR 4.7

	package org.sil.lingtree.descriptionparser.antlr4generated;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DescriptionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, OMIT=3, TRIANGLE=4, LEX=5, GLOSS=6, EMPTY=7, SUBSCRIPT=8, 
		SUBSCRIPTITALIC=9, SUPERSCRIPT=10, SUPERSCRIPTITALIC=11, ABBREVIATIONBEGIN=12, 
		ABBREVIATIONEND=13, TEXT=14, TEXTWITHSPACES=15, BACKSLASH=16, SLASH=17, 
		WS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "OMIT", "TRIANGLE", "LEX", "GLOSS", "EMPTY", "SUBSCRIPT", 
		"SUBSCRIPTITALIC", "SUPERSCRIPT", "SUPERSCRIPTITALIC", "ABBREVIATIONBEGIN", 
		"ABBREVIATIONEND", "TEXT", "TEXTWITHSPACES", "BACKSLASH", "SLASH", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'\\O'", "'\\T'", "'\\L'", "'\\G'", "'\\E'", "'/s'", 
		"'/_'", "'/S'", "'/^'", "'/a'", "'/A'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "OMIT", "TRIANGLE", "LEX", "GLOSS", "EMPTY", "SUBSCRIPT", 
		"SUBSCRIPTITALIC", "SUPERSCRIPT", "SUPERSCRIPTITALIC", "ABBREVIATIONBEGIN", 
		"ABBREVIATIONEND", "TEXT", "TEXTWITHSPACES", "BACKSLASH", "SLASH", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public DescriptionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Description.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24m\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\6\17S\n\17\r\17"+
		"\16\17T\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20_\n\20\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\23\6\23h\n\23\r\23\16\23i\3\23\3\23\2\2\24\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\3\2\7\t\2#),\60\62]_ac}\177\177\u0082\1\5\2bb~~\u0080\u0080"+
		"\6\2IINNQQVV\4\2UUuu\5\2\13\f\17\17\"\"\2r\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2"+
		"\5)\3\2\2\2\7+\3\2\2\2\t.\3\2\2\2\13\61\3\2\2\2\r\64\3\2\2\2\17\67\3\2"+
		"\2\2\21:\3\2\2\2\23=\3\2\2\2\25@\3\2\2\2\27C\3\2\2\2\31F\3\2\2\2\33I\3"+
		"\2\2\2\35R\3\2\2\2\37^\3\2\2\2!`\3\2\2\2#c\3\2\2\2%g\3\2\2\2\'(\7*\2\2"+
		"(\4\3\2\2\2)*\7+\2\2*\6\3\2\2\2+,\7^\2\2,-\7Q\2\2-\b\3\2\2\2./\7^\2\2"+
		"/\60\7V\2\2\60\n\3\2\2\2\61\62\7^\2\2\62\63\7N\2\2\63\f\3\2\2\2\64\65"+
		"\7^\2\2\65\66\7I\2\2\66\16\3\2\2\2\678\7^\2\289\7G\2\29\20\3\2\2\2:;\7"+
		"\61\2\2;<\7u\2\2<\22\3\2\2\2=>\7\61\2\2>?\7a\2\2?\24\3\2\2\2@A\7\61\2"+
		"\2AB\7U\2\2B\26\3\2\2\2CD\7\61\2\2DE\7`\2\2E\30\3\2\2\2FG\7\61\2\2GH\7"+
		"c\2\2H\32\3\2\2\2IJ\7\61\2\2JK\7C\2\2K\34\3\2\2\2LS\t\2\2\2MN\7^\2\2N"+
		"S\7*\2\2OP\7^\2\2PS\7+\2\2QS\t\3\2\2RL\3\2\2\2RM\3\2\2\2RO\3\2\2\2RQ\3"+
		"\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\36\3\2\2\2VW\5\35\17\2WX\7\"\2\2"+
		"XY\5\35\17\2Y_\3\2\2\2Z[\5\35\17\2[\\\7\"\2\2\\]\5\37\20\2]_\3\2\2\2^"+
		"V\3\2\2\2^Z\3\2\2\2_ \3\2\2\2`a\7^\2\2ab\n\4\2\2b\"\3\2\2\2cd\7\61\2\2"+
		"de\n\5\2\2e$\3\2\2\2fh\t\6\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2"+
		"jk\3\2\2\2kl\b\23\2\2l&\3\2\2\2\7\2RT^i\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
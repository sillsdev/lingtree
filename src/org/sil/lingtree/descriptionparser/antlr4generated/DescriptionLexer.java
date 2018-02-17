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
		T__0=1, T__1=2, T__2=3, OMIT=4, TRIANGLE=5, LEX=6, GLOSS=7, EMPTY=8, SUBSCRIPT=9, 
		SUPERSCRIPT=10, TEXT=11, BACKSLASH=12, SLASH=13, WS=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "OMIT", "TRIANGLE", "LEX", "GLOSS", "EMPTY", "SUBSCRIPT", 
		"SUPERSCRIPT", "TEXT", "BACKSLASH", "SLASH", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "' '", "')'", null, null, "'\\L'", "'\\G'", "'\\E'", "'/s'", 
		"'/S'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "OMIT", "TRIANGLE", "LEX", "GLOSS", "EMPTY", "SUBSCRIPT", 
		"SUPERSCRIPT", "TEXT", "BACKSLASH", "SLASH", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20]\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\7\5*\n\5\f\5\16\5-\13\5\3\6\3\6\3\6\3\6\7\6\63\n\6\f\6\16\6"+
		"\66\13\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\6\fM\n\f\r\f\16\fN\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\17\6\17X\n\17\r\17\16\17Y\3\17\3\17\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\3\2\7\t\2\"),\60\62]_ac}"+
		"\177\177\u0082\1\5\2bb~~\u0080\u0080\6\2IINNQQVV\4\2UUuu\4\2\13\f\17\17"+
		"\2c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3"+
		"\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2"+
		"\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5!\3\2\2\2\7#\3"+
		"\2\2\2\t%\3\2\2\2\13.\3\2\2\2\r\67\3\2\2\2\17:\3\2\2\2\21=\3\2\2\2\23"+
		"@\3\2\2\2\25C\3\2\2\2\27L\3\2\2\2\31P\3\2\2\2\33S\3\2\2\2\35W\3\2\2\2"+
		"\37 \7*\2\2 \4\3\2\2\2!\"\7\"\2\2\"\6\3\2\2\2#$\7+\2\2$\b\3\2\2\2%&\7"+
		"^\2\2&\'\7Q\2\2\'+\3\2\2\2(*\7\"\2\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3"+
		"\2\2\2,\n\3\2\2\2-+\3\2\2\2./\7^\2\2/\60\7V\2\2\60\64\3\2\2\2\61\63\7"+
		"\"\2\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\f\3"+
		"\2\2\2\66\64\3\2\2\2\678\7^\2\289\7N\2\29\16\3\2\2\2:;\7^\2\2;<\7I\2\2"+
		"<\20\3\2\2\2=>\7^\2\2>?\7G\2\2?\22\3\2\2\2@A\7\61\2\2AB\7u\2\2B\24\3\2"+
		"\2\2CD\7\61\2\2DE\7U\2\2E\26\3\2\2\2FM\t\2\2\2GH\7^\2\2HM\7*\2\2IJ\7^"+
		"\2\2JM\7+\2\2KM\t\3\2\2LF\3\2\2\2LG\3\2\2\2LI\3\2\2\2LK\3\2\2\2MN\3\2"+
		"\2\2NL\3\2\2\2NO\3\2\2\2O\30\3\2\2\2PQ\7^\2\2QR\n\4\2\2R\32\3\2\2\2ST"+
		"\7\61\2\2TU\n\5\2\2U\34\3\2\2\2VX\t\6\2\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2"+
		"\2YZ\3\2\2\2Z[\3\2\2\2[\\\b\17\2\2\\\36\3\2\2\2\b\2+\64LNY\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
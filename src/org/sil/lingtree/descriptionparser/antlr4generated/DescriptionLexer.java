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
		ABBREVIATIONEND=13, CUSTOMFONTBEGIN=14, CUSTOMFONTEND=15, TEXT=16, TEXTWITHSPACES=17, 
		BACKSLASH=18, SLASH=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "OMIT", "TRIANGLE", "LEX", "GLOSS", "EMPTY", "SUBSCRIPT", 
		"SUBSCRIPTITALIC", "SUPERSCRIPT", "SUPERSCRIPTITALIC", "ABBREVIATIONBEGIN", 
		"ABBREVIATIONEND", "CUSTOMFONTBEGIN", "CUSTOMFONTEND", "TEXT", "TEXTWITHSPACES", 
		"BACKSLASH", "SLASH", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'\\O'", "'\\T'", "'\\L'", "'\\G'", "'\\E'", "'/s'", 
		"'/_'", "'/S'", "'/^'", "'/a'", "'/A'", "'/f'", "'/F'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "OMIT", "TRIANGLE", "LEX", "GLOSS", "EMPTY", "SUBSCRIPT", 
		"SUBSCRIPTITALIC", "SUPERSCRIPT", "SUPERSCRIPTITALIC", "ABBREVIATIONBEGIN", 
		"ABBREVIATIONEND", "CUSTOMFONTBEGIN", "CUSTOMFONTEND", "TEXT", "TEXTWITHSPACES", 
		"BACKSLASH", "SLASH", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26w\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\6\21]\n\21\r\21\16\21^\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\5\22i\n\22\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\25\6\25r\n\25\r\25\16\25s\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"\3\2\7\t\2#),\60\62]_ac}\177\177\u0082\1\5\2bb~~\u0080\u0080\6\2IINNQ"+
		"QVV\4\2UUuu\5\2\13\f\17\17\"\"\2|\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\62\3\2\2\2\13\65\3\2\2\2\r8\3\2"+
		"\2\2\17;\3\2\2\2\21>\3\2\2\2\23A\3\2\2\2\25D\3\2\2\2\27G\3\2\2\2\31J\3"+
		"\2\2\2\33M\3\2\2\2\35P\3\2\2\2\37S\3\2\2\2!\\\3\2\2\2#h\3\2\2\2%j\3\2"+
		"\2\2\'m\3\2\2\2)q\3\2\2\2+,\7*\2\2,\4\3\2\2\2-.\7+\2\2.\6\3\2\2\2/\60"+
		"\7^\2\2\60\61\7Q\2\2\61\b\3\2\2\2\62\63\7^\2\2\63\64\7V\2\2\64\n\3\2\2"+
		"\2\65\66\7^\2\2\66\67\7N\2\2\67\f\3\2\2\289\7^\2\29:\7I\2\2:\16\3\2\2"+
		"\2;<\7^\2\2<=\7G\2\2=\20\3\2\2\2>?\7\61\2\2?@\7u\2\2@\22\3\2\2\2AB\7\61"+
		"\2\2BC\7a\2\2C\24\3\2\2\2DE\7\61\2\2EF\7U\2\2F\26\3\2\2\2GH\7\61\2\2H"+
		"I\7`\2\2I\30\3\2\2\2JK\7\61\2\2KL\7c\2\2L\32\3\2\2\2MN\7\61\2\2NO\7C\2"+
		"\2O\34\3\2\2\2PQ\7\61\2\2QR\7h\2\2R\36\3\2\2\2ST\7\61\2\2TU\7H\2\2U \3"+
		"\2\2\2V]\t\2\2\2WX\7^\2\2X]\7*\2\2YZ\7^\2\2Z]\7+\2\2[]\t\3\2\2\\V\3\2"+
		"\2\2\\W\3\2\2\2\\Y\3\2\2\2\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_"+
		"\"\3\2\2\2`a\5!\21\2ab\7\"\2\2bc\5!\21\2ci\3\2\2\2de\5!\21\2ef\7\"\2\2"+
		"fg\5#\22\2gi\3\2\2\2h`\3\2\2\2hd\3\2\2\2i$\3\2\2\2jk\7^\2\2kl\n\4\2\2"+
		"l&\3\2\2\2mn\7\61\2\2no\n\5\2\2o(\3\2\2\2pr\t\6\2\2qp\3\2\2\2rs\3\2\2"+
		"\2sq\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\b\25\2\2v*\3\2\2\2\7\2\\^hs\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
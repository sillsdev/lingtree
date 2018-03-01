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
		T__0=1, T__1=2, T__2=3, SPACES=4, OMIT=5, TRIANGLE=6, LEX=7, GLOSS=8, 
		EMPTY=9, SUBSCRIPT=10, SUBSCRIPTITALIC=11, SUPERSCRIPT=12, SUPERSCRIPTITALIC=13, 
		TEXT=14, BACKSLASH=15, SLASH=16, WS=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "SPACES", "OMIT", "TRIANGLE", "LEX", "GLOSS", 
		"EMPTY", "SUBSCRIPT", "SUBSCRIPTITALIC", "SUPERSCRIPT", "SUPERSCRIPTITALIC", 
		"TEXT", "BACKSLASH", "SLASH", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "' '", "'('", "')'", null, null, null, null, null, null, "'/s'", 
		"'/_'", "'/S'", "'/^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "SPACES", "OMIT", "TRIANGLE", "LEX", "GLOSS", 
		"EMPTY", "SUBSCRIPT", "SUBSCRIPTITALIC", "SUPERSCRIPT", "SUPERSCRIPTITALIC", 
		"TEXT", "BACKSLASH", "SLASH", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23x\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\6\5.\n\5\r\5\16\5/\3\6\3\6\3\6\3\6\7"+
		"\6\66\n\6\f\6\16\69\13\6\3\7\3\7\3\7\3\7\7\7?\n\7\f\7\16\7B\13\7\3\b\3"+
		"\b\3\b\3\b\5\bH\n\b\3\t\3\t\3\t\3\t\5\tN\n\t\3\n\3\n\3\n\3\n\5\nT\n\n"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\6\17h\n\17\r\17\16\17i\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\22\6\22s\n\22\r\22\16\22t\3\22\3\22\2\2\23\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23\3\2\b\5\2\13\f"+
		"\17\17\"\"\t\2\"),\60\62]_ac}\177\177\u0082\1\5\2bb~~\u0080\u0080\6\2"+
		"IINNQQVV\4\2UUuu\4\2\13\f\17\17\2\u0082\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5\'\3\2\2\2\7)"+
		"\3\2\2\2\t+\3\2\2\2\13\61\3\2\2\2\r:\3\2\2\2\17C\3\2\2\2\21I\3\2\2\2\23"+
		"O\3\2\2\2\25U\3\2\2\2\27X\3\2\2\2\31[\3\2\2\2\33^\3\2\2\2\35g\3\2\2\2"+
		"\37k\3\2\2\2!n\3\2\2\2#r\3\2\2\2%&\7\"\2\2&\4\3\2\2\2\'(\7*\2\2(\6\3\2"+
		"\2\2)*\7+\2\2*\b\3\2\2\2+-\7\"\2\2,.\t\2\2\2-,\3\2\2\2./\3\2\2\2/-\3\2"+
		"\2\2/\60\3\2\2\2\60\n\3\2\2\2\61\62\7^\2\2\62\63\7Q\2\2\63\67\3\2\2\2"+
		"\64\66\7\"\2\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\f"+
		"\3\2\2\29\67\3\2\2\2:;\7^\2\2;<\7V\2\2<@\3\2\2\2=?\7\"\2\2>=\3\2\2\2?"+
		"B\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\16\3\2\2\2B@\3\2\2\2CD\7^\2\2DE\7N\2\2"+
		"EG\3\2\2\2FH\5\t\5\2GF\3\2\2\2GH\3\2\2\2H\20\3\2\2\2IJ\7^\2\2JK\7I\2\2"+
		"KM\3\2\2\2LN\5\t\5\2ML\3\2\2\2MN\3\2\2\2N\22\3\2\2\2OP\7^\2\2PQ\7G\2\2"+
		"QS\3\2\2\2RT\5\t\5\2SR\3\2\2\2ST\3\2\2\2T\24\3\2\2\2UV\7\61\2\2VW\7u\2"+
		"\2W\26\3\2\2\2XY\7\61\2\2YZ\7a\2\2Z\30\3\2\2\2[\\\7\61\2\2\\]\7U\2\2]"+
		"\32\3\2\2\2^_\7\61\2\2_`\7`\2\2`\34\3\2\2\2ah\t\3\2\2bc\7^\2\2ch\7*\2"+
		"\2de\7^\2\2eh\7+\2\2fh\t\4\2\2ga\3\2\2\2gb\3\2\2\2gd\3\2\2\2gf\3\2\2\2"+
		"hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\36\3\2\2\2kl\7^\2\2lm\n\5\2\2m \3\2\2"+
		"\2no\7\61\2\2op\n\6\2\2p\"\3\2\2\2qs\t\7\2\2rq\3\2\2\2st\3\2\2\2tr\3\2"+
		"\2\2tu\3\2\2\2uv\3\2\2\2vw\b\22\2\2w$\3\2\2\2\f\2/\67@GMSgit\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
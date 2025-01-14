// Generated from Description.g4 by ANTLR 4.7

	package org.sil.lingtree.descriptionparser.antlr4generated;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DescriptionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, OMIT=3, TRIANGLE=4, LEX=5, GLOSS=6, EMPTY=7, SUBSCRIPT=8, 
		SUBSCRIPTITALIC=9, SUPERSCRIPT=10, SUPERSCRIPTITALIC=11, ABBREVIATIONBEGIN=12, 
		ABBREVIATIONEND=13, CUSTOMFONTBEGIN=14, CUSTOMFONTEND=15, TEXT=16, TEXTWITHSPACES=17, 
		BACKSLASH=18, SLASH=19, WS=20;
	public static final int
		RULE_description = 0, RULE_node = 1, RULE_openParen = 2, RULE_closeParen = 3, 
		RULE_type = 4, RULE_lineType = 5, RULE_nodeType = 6, RULE_content = 7, 
		RULE_subscript = 8, RULE_superscript = 9, RULE_abbreviation = 10, RULE_abbreviationWithText = 11, 
		RULE_customFontInfo = 12;
	public static final String[] ruleNames = {
		"description", "node", "openParen", "closeParen", "type", "lineType", 
		"nodeType", "content", "subscript", "superscript", "abbreviation", "abbreviationWithText", 
		"customFontInfo"
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

	@Override
	public String getGrammarFileName() { return "Description.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DescriptionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DescriptionContext extends ParserRuleContext {
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public TerminalNode EOF() { return getToken(DescriptionParser.EOF, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CloseParenContext closeParen() {
			return getRuleContext(CloseParenContext.class,0);
		}
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitDescription(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_description);
		try {
			setState(55);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				node();
				setState(27);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				match(EOF);
				notifyErrorListeners("missingOpeningParen");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				notifyErrorListeners("missingOpeningParen");
				setState(32);
				content();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(33);
				node();
				notifyErrorListeners("contentAfterCompletedTree");
				setState(35);
				content();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(37);
				node();
				notifyErrorListeners("contentAfterCompletedTree");
				setState(39);
				node();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				notifyErrorListeners("missingOpeningParen");
				setState(42);
				content();
				setState(43);
				node();
				setState(44);
				match(EOF);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				notifyErrorListeners("missingOpeningParen");
				setState(47);
				type();
				setState(48);
				node();
				setState(49);
				match(EOF);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(51);
				node();
				setState(52);
				closeParen();
				notifyErrorListeners("tooManyCloseParens");
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeContext extends ParserRuleContext {
		public OpenParenContext openParen() {
			return getRuleContext(OpenParenContext.class,0);
		}
		public CloseParenContext closeParen() {
			return getRuleContext(CloseParenContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}
		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class,i);
		}
		public CustomFontInfoContext customFontInfo() {
			return getRuleContext(CustomFontInfoContext.class,0);
		}
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public List<AbbreviationWithTextContext> abbreviationWithText() {
			return getRuleContexts(AbbreviationWithTextContext.class);
		}
		public AbbreviationWithTextContext abbreviationWithText(int i) {
			return getRuleContext(AbbreviationWithTextContext.class,i);
		}
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitNode(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_node);
		int _la;
		try {
			int _alt;
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				openParen();
				setState(59);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(58);
					type();
					}
					break;
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(61);
					content();
					}
				}

				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(64);
					customFontInfo();
					}
				}

				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(67);
					node();
					}
					}
					setState(72);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(73);
				closeParen();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				openParen();
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					setState(76);
					type();
					}
				}

				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(79);
					abbreviationWithText();
					}
					}
					setState(82); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABBREVIATIONBEGIN) | (1L << TEXT) | (1L << TEXTWITHSPACES))) != 0) );
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(84);
					customFontInfo();
					}
				}

				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(87);
					node();
					}
					}
					setState(92);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(93);
				closeParen();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				openParen();
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					setState(96);
					type();
					}
				}

				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(99);
					abbreviationWithText();
					}
					}
					setState(102); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABBREVIATIONBEGIN) | (1L << TEXT) | (1L << TEXTWITHSPACES))) != 0) );
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(104);
					customFontInfo();
					}
				}

				setState(107);
				closeParen();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				openParen();
				setState(111);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(110);
					type();
					}
					break;
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(113);
					content();
					}
				}

				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(116);
					customFontInfo();
					}
				}

				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(119);
					node();
					}
					}
					setState(124);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(125);
				closeParen();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(127);
				openParen();
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					setState(128);
					type();
					}
				}

				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(131);
					abbreviationWithText();
					}
					}
					setState(134); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABBREVIATIONBEGIN) | (1L << TEXT) | (1L << TEXTWITHSPACES))) != 0) );
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(136);
					customFontInfo();
					}
				}

				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(139);
					node();
					}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(145);
				closeParen();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(147);
				openParen();
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					setState(148);
					type();
					}
				}

				setState(152); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(151);
					abbreviationWithText();
					}
					}
					setState(154); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABBREVIATIONBEGIN) | (1L << TEXT) | (1L << TEXTWITHSPACES))) != 0) );
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(156);
					customFontInfo();
					}
				}

				setState(159);
				closeParen();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(161);
				openParen();
				setState(163);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(162);
					type();
					}
					break;
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(165);
					content();
					}
				}

				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(168);
					customFontInfo();
					}
				}

				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(171);
					node();
					}
					}
					setState(176);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(177);
				closeParen();
				notifyErrorListeners("missingOpeningParen");
				setState(179);
				content();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(181);
				openParen();
				setState(183);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(182);
					type();
					}
					break;
				}
				setState(186);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(185);
					content();
					}
					break;
				}
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(188);
					customFontInfo();
					}
				}

				setState(194);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(191);
						node();
						}
						} 
					}
					setState(196);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				notifyErrorListeners("missingClosingParen");
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(199);
				type();
				setState(201);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(200);
					content();
					}
					break;
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(203);
					customFontInfo();
					}
				}

				notifyErrorListeners("missingOpeningParen");
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpenParenContext extends ParserRuleContext {
		public OpenParenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_openParen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterOpenParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitOpenParen(this);
		}
	}

	public final OpenParenContext openParen() throws RecognitionException {
		OpenParenContext _localctx = new OpenParenContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_openParen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CloseParenContext extends ParserRuleContext {
		public CloseParenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_closeParen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterCloseParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitCloseParen(this);
		}
	}

	public final CloseParenContext closeParen() throws RecognitionException {
		CloseParenContext _localctx = new CloseParenContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_closeParen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public List<NodeTypeContext> nodeType() {
			return getRuleContexts(NodeTypeContext.class);
		}
		public NodeTypeContext nodeType(int i) {
			return getRuleContext(NodeTypeContext.class,i);
		}
		public List<LineTypeContext> lineType() {
			return getRuleContexts(LineTypeContext.class);
		}
		public LineTypeContext lineType(int i) {
			return getRuleContext(LineTypeContext.class,i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			setState(240);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				nodeType();
				setState(215);
				lineType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(217);
				lineType();
				setState(218);
				nodeType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
				nodeType();
				setState(221);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(224);
				lineType();
				setState(225);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(228);
				nodeType();
				setState(229);
				lineType();
				setState(230);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(233);
				lineType();
				setState(234);
				nodeType();
				setState(235);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(238);
				lineType();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(239);
				nodeType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LineTypeContext extends ParserRuleContext {
		public TerminalNode OMIT() { return getToken(DescriptionParser.OMIT, 0); }
		public TerminalNode TRIANGLE() { return getToken(DescriptionParser.TRIANGLE, 0); }
		public LineTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lineType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterLineType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitLineType(this);
		}
	}

	public final LineTypeContext lineType() throws RecognitionException {
		LineTypeContext _localctx = new LineTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_lineType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_la = _input.LA(1);
			if ( !(_la==OMIT || _la==TRIANGLE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeTypeContext extends ParserRuleContext {
		public TerminalNode LEX() { return getToken(DescriptionParser.LEX, 0); }
		public TerminalNode GLOSS() { return getToken(DescriptionParser.GLOSS, 0); }
		public TerminalNode EMPTY() { return getToken(DescriptionParser.EMPTY, 0); }
		public NodeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterNodeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitNodeType(this);
		}
	}

	public final NodeTypeContext nodeType() throws RecognitionException {
		NodeTypeContext _localctx = new NodeTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_nodeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContentContext extends ParserRuleContext {
		public SubscriptContext subscript() {
			return getRuleContext(SubscriptContext.class,0);
		}
		public SuperscriptContext superscript() {
			return getRuleContext(SuperscriptContext.class,0);
		}
		public List<TerminalNode> TEXT() { return getTokens(DescriptionParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(DescriptionParser.TEXT, i);
		}
		public List<TerminalNode> TEXTWITHSPACES() { return getTokens(DescriptionParser.TEXTWITHSPACES); }
		public TerminalNode TEXTWITHSPACES(int i) {
			return getToken(DescriptionParser.TEXTWITHSPACES, i);
		}
		public List<TerminalNode> BACKSLASH() { return getTokens(DescriptionParser.BACKSLASH); }
		public TerminalNode BACKSLASH(int i) {
			return getToken(DescriptionParser.BACKSLASH, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(DescriptionParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(DescriptionParser.SLASH, i);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitContent(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_content);
		int _la;
		try {
			int _alt;
			setState(287);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(247); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(246);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(249); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(251);
				subscript();
				setState(252);
				superscript();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(255); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(254);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(257); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(259);
				superscript();
				setState(260);
				subscript();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(263); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(262);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(265); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(267);
				subscript();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(269); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(268);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(271); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(273);
				superscript();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(275); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(274);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(277); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(279);
				subscript();
				setState(280);
				superscript();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(282);
				superscript();
				setState(283);
				subscript();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(285);
				subscript();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(286);
				superscript();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubscriptContext extends ParserRuleContext {
		public TerminalNode SUBSCRIPT() { return getToken(DescriptionParser.SUBSCRIPT, 0); }
		public List<TerminalNode> TEXT() { return getTokens(DescriptionParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(DescriptionParser.TEXT, i);
		}
		public List<TerminalNode> TEXTWITHSPACES() { return getTokens(DescriptionParser.TEXTWITHSPACES); }
		public TerminalNode TEXTWITHSPACES(int i) {
			return getToken(DescriptionParser.TEXTWITHSPACES, i);
		}
		public List<TerminalNode> BACKSLASH() { return getTokens(DescriptionParser.BACKSLASH); }
		public TerminalNode BACKSLASH(int i) {
			return getToken(DescriptionParser.BACKSLASH, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(DescriptionParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(DescriptionParser.SLASH, i);
		}
		public TerminalNode SUBSCRIPTITALIC() { return getToken(DescriptionParser.SUBSCRIPTITALIC, 0); }
		public SubscriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subscript; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterSubscript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitSubscript(this);
		}
	}

	public final SubscriptContext subscript() throws RecognitionException {
		SubscriptContext _localctx = new SubscriptContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_subscript);
		int _la;
		try {
			int _alt;
			setState(305);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(289);
				match(SUBSCRIPT);
				setState(291); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(290);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(293); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(295);
				match(SUBSCRIPTITALIC);
				setState(297); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(296);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(299); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(301);
				match(SUBSCRIPT);
				notifyErrorListeners("missingContentAfterSubscript");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(303);
				match(SUBSCRIPTITALIC);
				notifyErrorListeners("missingContentAfterSubscript");
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperscriptContext extends ParserRuleContext {
		public TerminalNode SUPERSCRIPT() { return getToken(DescriptionParser.SUPERSCRIPT, 0); }
		public List<TerminalNode> TEXT() { return getTokens(DescriptionParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(DescriptionParser.TEXT, i);
		}
		public List<TerminalNode> TEXTWITHSPACES() { return getTokens(DescriptionParser.TEXTWITHSPACES); }
		public TerminalNode TEXTWITHSPACES(int i) {
			return getToken(DescriptionParser.TEXTWITHSPACES, i);
		}
		public List<TerminalNode> BACKSLASH() { return getTokens(DescriptionParser.BACKSLASH); }
		public TerminalNode BACKSLASH(int i) {
			return getToken(DescriptionParser.BACKSLASH, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(DescriptionParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(DescriptionParser.SLASH, i);
		}
		public TerminalNode SUPERSCRIPTITALIC() { return getToken(DescriptionParser.SUPERSCRIPTITALIC, 0); }
		public SuperscriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superscript; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterSuperscript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitSuperscript(this);
		}
	}

	public final SuperscriptContext superscript() throws RecognitionException {
		SuperscriptContext _localctx = new SuperscriptContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_superscript);
		int _la;
		try {
			int _alt;
			setState(323);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(307);
				match(SUPERSCRIPT);
				setState(309); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(308);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(311); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				match(SUPERSCRIPTITALIC);
				setState(315); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(314);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(317); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(319);
				match(SUPERSCRIPT);
				notifyErrorListeners("missingContentAfterSuperscript");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(321);
				match(SUPERSCRIPTITALIC);
				notifyErrorListeners("missingContentAfterSuperscript");
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbbreviationContext extends ParserRuleContext {
		public TerminalNode ABBREVIATIONBEGIN() { return getToken(DescriptionParser.ABBREVIATIONBEGIN, 0); }
		public TerminalNode ABBREVIATIONEND() { return getToken(DescriptionParser.ABBREVIATIONEND, 0); }
		public List<TerminalNode> TEXT() { return getTokens(DescriptionParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(DescriptionParser.TEXT, i);
		}
		public List<TerminalNode> TEXTWITHSPACES() { return getTokens(DescriptionParser.TEXTWITHSPACES); }
		public TerminalNode TEXTWITHSPACES(int i) {
			return getToken(DescriptionParser.TEXTWITHSPACES, i);
		}
		public AbbreviationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abbreviation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterAbbreviation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitAbbreviation(this);
		}
	}

	public final AbbreviationContext abbreviation() throws RecognitionException {
		AbbreviationContext _localctx = new AbbreviationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_abbreviation);
		int _la;
		try {
			int _alt;
			setState(342);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(325);
				match(ABBREVIATIONBEGIN);
				setState(327); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(326);
					_la = _input.LA(1);
					if ( !(_la==TEXT || _la==TEXTWITHSPACES) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(329); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(331);
				match(ABBREVIATIONEND);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				match(ABBREVIATIONBEGIN);
				setState(334); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(333);
						_la = _input.LA(1);
						if ( !(_la==TEXT || _la==TEXTWITHSPACES) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(336); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				notifyErrorListeners("missingAbbreviationEnd");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(339);
				match(ABBREVIATIONBEGIN);
				setState(340);
				match(ABBREVIATIONEND);
				notifyErrorListeners("missingContentAfterAbbreviationBegin");
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbbreviationWithTextContext extends ParserRuleContext {
		public AbbreviationContext abbreviation() {
			return getRuleContext(AbbreviationContext.class,0);
		}
		public List<TerminalNode> TEXT() { return getTokens(DescriptionParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(DescriptionParser.TEXT, i);
		}
		public List<TerminalNode> TEXTWITHSPACES() { return getTokens(DescriptionParser.TEXTWITHSPACES); }
		public TerminalNode TEXTWITHSPACES(int i) {
			return getToken(DescriptionParser.TEXTWITHSPACES, i);
		}
		public AbbreviationWithTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abbreviationWithText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterAbbreviationWithText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitAbbreviationWithText(this);
		}
	}

	public final AbbreviationWithTextContext abbreviationWithText() throws RecognitionException {
		AbbreviationWithTextContext _localctx = new AbbreviationWithTextContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_abbreviationWithText);
		int _la;
		try {
			int _alt;
			setState(368);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(345); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(344);
					_la = _input.LA(1);
					if ( !(_la==TEXT || _la==TEXTWITHSPACES) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(347); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(349);
				abbreviation();
				setState(351); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(350);
						_la = _input.LA(1);
						if ( !(_la==TEXT || _la==TEXTWITHSPACES) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(353); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(355);
				abbreviation();
				setState(357); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(356);
						_la = _input.LA(1);
						if ( !(_la==TEXT || _la==TEXTWITHSPACES) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(359); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(362); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(361);
					_la = _input.LA(1);
					if ( !(_la==TEXT || _la==TEXTWITHSPACES) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(364); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(366);
				abbreviation();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(367);
				abbreviation();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CustomFontInfoContext extends ParserRuleContext {
		public TerminalNode CUSTOMFONTBEGIN() { return getToken(DescriptionParser.CUSTOMFONTBEGIN, 0); }
		public TerminalNode CUSTOMFONTEND() { return getToken(DescriptionParser.CUSTOMFONTEND, 0); }
		public List<TerminalNode> TEXT() { return getTokens(DescriptionParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(DescriptionParser.TEXT, i);
		}
		public List<TerminalNode> TEXTWITHSPACES() { return getTokens(DescriptionParser.TEXTWITHSPACES); }
		public TerminalNode TEXTWITHSPACES(int i) {
			return getToken(DescriptionParser.TEXTWITHSPACES, i);
		}
		public CustomFontInfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_customFontInfo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).enterCustomFontInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DescriptionListener ) ((DescriptionListener)listener).exitCustomFontInfo(this);
		}
	}

	public final CustomFontInfoContext customFontInfo() throws RecognitionException {
		CustomFontInfoContext _localctx = new CustomFontInfoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_customFontInfo);
		int _la;
		try {
			int _alt;
			setState(387);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(370);
				match(CUSTOMFONTBEGIN);
				setState(372); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(371);
					_la = _input.LA(1);
					if ( !(_la==TEXT || _la==TEXTWITHSPACES) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(374); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(376);
				match(CUSTOMFONTEND);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				match(CUSTOMFONTBEGIN);
				setState(379); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(378);
						_la = _input.LA(1);
						if ( !(_la==TEXT || _la==TEXTWITHSPACES) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(381); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				notifyErrorListeners("missingCustomFontEnd");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(384);
				match(CUSTOMFONTBEGIN);
				setState(385);
				match(CUSTOMFONTEND);
				notifyErrorListeners("missingContentAfterCustomFontBegin");
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u0188\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\5\2:\n\2\3\3\3\3\5\3>\n\3\3\3\5\3A\n\3\3\3\5\3D\n\3\3\3\7\3G"+
		"\n\3\f\3\16\3J\13\3\3\3\3\3\3\3\3\3\5\3P\n\3\3\3\6\3S\n\3\r\3\16\3T\3"+
		"\3\5\3X\n\3\3\3\7\3[\n\3\f\3\16\3^\13\3\3\3\3\3\3\3\3\3\5\3d\n\3\3\3\6"+
		"\3g\n\3\r\3\16\3h\3\3\5\3l\n\3\3\3\3\3\3\3\3\3\5\3r\n\3\3\3\5\3u\n\3\3"+
		"\3\5\3x\n\3\3\3\7\3{\n\3\f\3\16\3~\13\3\3\3\3\3\3\3\3\3\5\3\u0084\n\3"+
		"\3\3\6\3\u0087\n\3\r\3\16\3\u0088\3\3\5\3\u008c\n\3\3\3\7\3\u008f\n\3"+
		"\f\3\16\3\u0092\13\3\3\3\3\3\3\3\3\3\5\3\u0098\n\3\3\3\6\3\u009b\n\3\r"+
		"\3\16\3\u009c\3\3\5\3\u00a0\n\3\3\3\3\3\3\3\3\3\5\3\u00a6\n\3\3\3\5\3"+
		"\u00a9\n\3\3\3\5\3\u00ac\n\3\3\3\7\3\u00af\n\3\f\3\16\3\u00b2\13\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\3\u00ba\n\3\3\3\5\3\u00bd\n\3\3\3\5\3\u00c0\n\3"+
		"\3\3\7\3\u00c3\n\3\f\3\16\3\u00c6\13\3\3\3\3\3\3\3\3\3\5\3\u00cc\n\3\3"+
		"\3\5\3\u00cf\n\3\3\3\3\3\5\3\u00d3\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6\u00f3\n\6\3\7\3\7\3\b\3\b\3\t\6\t\u00fa\n\t\r\t"+
		"\16\t\u00fb\3\t\3\t\3\t\3\t\6\t\u0102\n\t\r\t\16\t\u0103\3\t\3\t\3\t\3"+
		"\t\6\t\u010a\n\t\r\t\16\t\u010b\3\t\3\t\6\t\u0110\n\t\r\t\16\t\u0111\3"+
		"\t\3\t\6\t\u0116\n\t\r\t\16\t\u0117\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\t\u0122\n\t\3\n\3\n\6\n\u0126\n\n\r\n\16\n\u0127\3\n\3\n\6\n\u012c\n"+
		"\n\r\n\16\n\u012d\3\n\3\n\3\n\3\n\5\n\u0134\n\n\3\13\3\13\6\13\u0138\n"+
		"\13\r\13\16\13\u0139\3\13\3\13\6\13\u013e\n\13\r\13\16\13\u013f\3\13\3"+
		"\13\3\13\3\13\5\13\u0146\n\13\3\f\3\f\6\f\u014a\n\f\r\f\16\f\u014b\3\f"+
		"\3\f\3\f\6\f\u0151\n\f\r\f\16\f\u0152\3\f\3\f\3\f\3\f\5\f\u0159\n\f\3"+
		"\r\6\r\u015c\n\r\r\r\16\r\u015d\3\r\3\r\6\r\u0162\n\r\r\r\16\r\u0163\3"+
		"\r\3\r\6\r\u0168\n\r\r\r\16\r\u0169\3\r\6\r\u016d\n\r\r\r\16\r\u016e\3"+
		"\r\3\r\5\r\u0173\n\r\3\16\3\16\6\16\u0177\n\16\r\16\16\16\u0178\3\16\3"+
		"\16\3\16\6\16\u017e\n\16\r\16\16\16\u017f\3\16\3\16\3\16\3\16\5\16\u0186"+
		"\n\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\6\3\2\5\6\3\2\7\t"+
		"\3\2\22\25\3\2\22\23\2\u01d6\29\3\2\2\2\4\u00d2\3\2\2\2\6\u00d4\3\2\2"+
		"\2\b\u00d6\3\2\2\2\n\u00f2\3\2\2\2\f\u00f4\3\2\2\2\16\u00f6\3\2\2\2\20"+
		"\u0121\3\2\2\2\22\u0133\3\2\2\2\24\u0145\3\2\2\2\26\u0158\3\2\2\2\30\u0172"+
		"\3\2\2\2\32\u0185\3\2\2\2\34\35\5\4\3\2\35\36\7\2\2\3\36:\3\2\2\2\37 "+
		"\7\2\2\3 :\b\2\1\2!\"\b\2\1\2\":\5\20\t\2#$\5\4\3\2$%\b\2\1\2%&\5\20\t"+
		"\2&:\3\2\2\2\'(\5\4\3\2()\b\2\1\2)*\5\4\3\2*:\3\2\2\2+,\b\2\1\2,-\5\20"+
		"\t\2-.\5\4\3\2./\7\2\2\3/:\3\2\2\2\60\61\b\2\1\2\61\62\5\n\6\2\62\63\5"+
		"\4\3\2\63\64\7\2\2\3\64:\3\2\2\2\65\66\5\4\3\2\66\67\5\b\5\2\678\b\2\1"+
		"\28:\3\2\2\29\34\3\2\2\29\37\3\2\2\29!\3\2\2\29#\3\2\2\29\'\3\2\2\29+"+
		"\3\2\2\29\60\3\2\2\29\65\3\2\2\2:\3\3\2\2\2;=\5\6\4\2<>\5\n\6\2=<\3\2"+
		"\2\2=>\3\2\2\2>@\3\2\2\2?A\5\20\t\2@?\3\2\2\2@A\3\2\2\2AC\3\2\2\2BD\5"+
		"\32\16\2CB\3\2\2\2CD\3\2\2\2DH\3\2\2\2EG\5\4\3\2FE\3\2\2\2GJ\3\2\2\2H"+
		"F\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\5\b\5\2L\u00d3\3\2\2\2MO\5\6"+
		"\4\2NP\5\n\6\2ON\3\2\2\2OP\3\2\2\2PR\3\2\2\2QS\5\30\r\2RQ\3\2\2\2ST\3"+
		"\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2\2VX\5\32\16\2WV\3\2\2\2WX\3\2\2\2X"+
		"\\\3\2\2\2Y[\5\4\3\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2"+
		"\2^\\\3\2\2\2_`\5\b\5\2`\u00d3\3\2\2\2ac\5\6\4\2bd\5\n\6\2cb\3\2\2\2c"+
		"d\3\2\2\2df\3\2\2\2eg\5\30\r\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2"+
		"ik\3\2\2\2jl\5\32\16\2kj\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\5\b\5\2n\u00d3"+
		"\3\2\2\2oq\5\6\4\2pr\5\n\6\2qp\3\2\2\2qr\3\2\2\2rt\3\2\2\2su\5\20\t\2"+
		"ts\3\2\2\2tu\3\2\2\2uw\3\2\2\2vx\5\32\16\2wv\3\2\2\2wx\3\2\2\2x|\3\2\2"+
		"\2y{\5\4\3\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~|\3"+
		"\2\2\2\177\u0080\5\b\5\2\u0080\u00d3\3\2\2\2\u0081\u0083\5\6\4\2\u0082"+
		"\u0084\5\n\6\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\3\2"+
		"\2\2\u0085\u0087\5\30\r\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a\u008c\5\32"+
		"\16\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u0090\3\2\2\2\u008d"+
		"\u008f\5\4\3\2\u008e\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2"+
		"\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093"+
		"\u0094\5\b\5\2\u0094\u00d3\3\2\2\2\u0095\u0097\5\6\4\2\u0096\u0098\5\n"+
		"\6\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3\2\2\2\u0099"+
		"\u009b\5\30\r\2\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3"+
		"\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u00a0\5\32\16\2\u009f"+
		"\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\5\b"+
		"\5\2\u00a2\u00d3\3\2\2\2\u00a3\u00a5\5\6\4\2\u00a4\u00a6\5\n\6\2\u00a5"+
		"\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a9\5\20"+
		"\t\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa"+
		"\u00ac\5\32\16\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00b0\3"+
		"\2\2\2\u00ad\u00af\5\4\3\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0\3\2"+
		"\2\2\u00b3\u00b4\5\b\5\2\u00b4\u00b5\b\3\1\2\u00b5\u00b6\5\20\t\2\u00b6"+
		"\u00d3\3\2\2\2\u00b7\u00b9\5\6\4\2\u00b8\u00ba\5\n\6\2\u00b9\u00b8\3\2"+
		"\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00bd\5\20\t\2\u00bc"+
		"\u00bb\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00c0\5\32"+
		"\16\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c4\3\2\2\2\u00c1"+
		"\u00c3\5\4\3\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2"+
		"\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7"+
		"\u00c8\b\3\1\2\u00c8\u00d3\3\2\2\2\u00c9\u00cb\5\n\6\2\u00ca\u00cc\5\20"+
		"\t\2\u00cb\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd"+
		"\u00cf\5\32\16\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\3"+
		"\2\2\2\u00d0\u00d1\b\3\1\2\u00d1\u00d3\3\2\2\2\u00d2;\3\2\2\2\u00d2M\3"+
		"\2\2\2\u00d2a\3\2\2\2\u00d2o\3\2\2\2\u00d2\u0081\3\2\2\2\u00d2\u0095\3"+
		"\2\2\2\u00d2\u00a3\3\2\2\2\u00d2\u00b7\3\2\2\2\u00d2\u00c9\3\2\2\2\u00d3"+
		"\5\3\2\2\2\u00d4\u00d5\7\3\2\2\u00d5\7\3\2\2\2\u00d6\u00d7\7\4\2\2\u00d7"+
		"\t\3\2\2\2\u00d8\u00d9\5\16\b\2\u00d9\u00da\5\f\7\2\u00da\u00f3\3\2\2"+
		"\2\u00db\u00dc\5\f\7\2\u00dc\u00dd\5\16\b\2\u00dd\u00f3\3\2\2\2\u00de"+
		"\u00df\5\16\b\2\u00df\u00e0\5\16\b\2\u00e0\u00e1\b\6\1\2\u00e1\u00f3\3"+
		"\2\2\2\u00e2\u00e3\5\f\7\2\u00e3\u00e4\5\f\7\2\u00e4\u00e5\b\6\1\2\u00e5"+
		"\u00f3\3\2\2\2\u00e6\u00e7\5\16\b\2\u00e7\u00e8\5\f\7\2\u00e8\u00e9\5"+
		"\16\b\2\u00e9\u00ea\b\6\1\2\u00ea\u00f3\3\2\2\2\u00eb\u00ec\5\f\7\2\u00ec"+
		"\u00ed\5\16\b\2\u00ed\u00ee\5\f\7\2\u00ee\u00ef\b\6\1\2\u00ef\u00f3\3"+
		"\2\2\2\u00f0\u00f3\5\f\7\2\u00f1\u00f3\5\16\b\2\u00f2\u00d8\3\2\2\2\u00f2"+
		"\u00db\3\2\2\2\u00f2\u00de\3\2\2\2\u00f2\u00e2\3\2\2\2\u00f2\u00e6\3\2"+
		"\2\2\u00f2\u00eb\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f1\3\2\2\2\u00f3"+
		"\13\3\2\2\2\u00f4\u00f5\t\2\2\2\u00f5\r\3\2\2\2\u00f6\u00f7\t\3\2\2\u00f7"+
		"\17\3\2\2\2\u00f8\u00fa\t\4\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2\2"+
		"\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe"+
		"\5\22\n\2\u00fe\u00ff\5\24\13\2\u00ff\u0122\3\2\2\2\u0100\u0102\t\4\2"+
		"\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104"+
		"\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\5\24\13\2\u0106\u0107\5\22\n"+
		"\2\u0107\u0122\3\2\2\2\u0108\u010a\t\4\2\2\u0109\u0108\3\2\2\2\u010a\u010b"+
		"\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u0122\5\22\n\2\u010e\u0110\t\4\2\2\u010f\u010e\3\2\2\2\u0110\u0111\3"+
		"\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0122\5\24\13\2\u0114\u0116\t\4\2\2\u0115\u0114\3\2\2\2\u0116\u0117\3"+
		"\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0122\3\2\2\2\u0119"+
		"\u011a\5\22\n\2\u011a\u011b\5\24\13\2\u011b\u0122\3\2\2\2\u011c\u011d"+
		"\5\24\13\2\u011d\u011e\5\22\n\2\u011e\u0122\3\2\2\2\u011f\u0122\5\22\n"+
		"\2\u0120\u0122\5\24\13\2\u0121\u00f9\3\2\2\2\u0121\u0101\3\2\2\2\u0121"+
		"\u0109\3\2\2\2\u0121\u010f\3\2\2\2\u0121\u0115\3\2\2\2\u0121\u0119\3\2"+
		"\2\2\u0121\u011c\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122"+
		"\21\3\2\2\2\u0123\u0125\7\n\2\2\u0124\u0126\t\4\2\2\u0125\u0124\3\2\2"+
		"\2\u0126\u0127\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0134"+
		"\3\2\2\2\u0129\u012b\7\13\2\2\u012a\u012c\t\4\2\2\u012b\u012a\3\2\2\2"+
		"\u012c\u012d\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0134"+
		"\3\2\2\2\u012f\u0130\7\n\2\2\u0130\u0134\b\n\1\2\u0131\u0132\7\13\2\2"+
		"\u0132\u0134\b\n\1\2\u0133\u0123\3\2\2\2\u0133\u0129\3\2\2\2\u0133\u012f"+
		"\3\2\2\2\u0133\u0131\3\2\2\2\u0134\23\3\2\2\2\u0135\u0137\7\f\2\2\u0136"+
		"\u0138\t\4\2\2\u0137\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u0137\3\2"+
		"\2\2\u0139\u013a\3\2\2\2\u013a\u0146\3\2\2\2\u013b\u013d\7\r\2\2\u013c"+
		"\u013e\t\4\2\2\u013d\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u013d\3\2"+
		"\2\2\u013f\u0140\3\2\2\2\u0140\u0146\3\2\2\2\u0141\u0142\7\f\2\2\u0142"+
		"\u0146\b\13\1\2\u0143\u0144\7\r\2\2\u0144\u0146\b\13\1\2\u0145\u0135\3"+
		"\2\2\2\u0145\u013b\3\2\2\2\u0145\u0141\3\2\2\2\u0145\u0143\3\2\2\2\u0146"+
		"\25\3\2\2\2\u0147\u0149\7\16\2\2\u0148\u014a\t\5\2\2\u0149\u0148\3\2\2"+
		"\2\u014a\u014b\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d"+
		"\3\2\2\2\u014d\u0159\7\17\2\2\u014e\u0150\7\16\2\2\u014f\u0151\t\5\2\2"+
		"\u0150\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153"+
		"\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0159\b\f\1\2\u0155\u0156\7\16\2\2"+
		"\u0156\u0157\7\17\2\2\u0157\u0159\b\f\1\2\u0158\u0147\3\2\2\2\u0158\u014e"+
		"\3\2\2\2\u0158\u0155\3\2\2\2\u0159\27\3\2\2\2\u015a\u015c\t\5\2\2\u015b"+
		"\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2"+
		"\2\2\u015e\u015f\3\2\2\2\u015f\u0161\5\26\f\2\u0160\u0162\t\5\2\2\u0161"+
		"\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2"+
		"\2\2\u0164\u0173\3\2\2\2\u0165\u0167\5\26\f\2\u0166\u0168\t\5\2\2\u0167"+
		"\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2"+
		"\2\2\u016a\u0173\3\2\2\2\u016b\u016d\t\5\2\2\u016c\u016b\3\2\2\2\u016d"+
		"\u016e\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\3\2"+
		"\2\2\u0170\u0173\5\26\f\2\u0171\u0173\5\26\f\2\u0172\u015b\3\2\2\2\u0172"+
		"\u0165\3\2\2\2\u0172\u016c\3\2\2\2\u0172\u0171\3\2\2\2\u0173\31\3\2\2"+
		"\2\u0174\u0176\7\20\2\2\u0175\u0177\t\5\2\2\u0176\u0175\3\2\2\2\u0177"+
		"\u0178\3\2\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017a\3\2"+
		"\2\2\u017a\u0186\7\21\2\2\u017b\u017d\7\20\2\2\u017c\u017e\t\5\2\2\u017d"+
		"\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2"+
		"\2\2\u0180\u0181\3\2\2\2\u0181\u0186\b\16\1\2\u0182\u0183\7\20\2\2\u0183"+
		"\u0184\7\21\2\2\u0184\u0186\b\16\1\2\u0185\u0174\3\2\2\2\u0185\u017b\3"+
		"\2\2\2\u0185\u0182\3\2\2\2\u0186\33\3\2\2\2<9=@CHOTW\\chkqtw|\u0083\u0088"+
		"\u008b\u0090\u0097\u009c\u009f\u00a5\u00a8\u00ab\u00b0\u00b9\u00bc\u00bf"+
		"\u00c4\u00cb\u00ce\u00d2\u00f2\u00fb\u0103\u010b\u0111\u0117\u0121\u0127"+
		"\u012d\u0133\u0139\u013f\u0145\u014b\u0152\u0158\u015d\u0163\u0169\u016e"+
		"\u0172\u0178\u017f\u0185";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
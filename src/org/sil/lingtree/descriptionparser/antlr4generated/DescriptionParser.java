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
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
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
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(84);
					node();
					}
					}
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(90);
				closeParen();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				openParen();
				setState(94);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(93);
					type();
					}
					break;
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(96);
					content();
					}
				}

				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(99);
					customFontInfo();
					}
				}

				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(102);
					node();
					}
					}
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(108);
				closeParen();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(110);
				openParen();
				setState(112);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(111);
					type();
					}
					break;
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(114);
					content();
					}
				}

				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(117);
					customFontInfo();
					}
				}

				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(120);
					node();
					}
					}
					setState(125);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(126);
				closeParen();
				notifyErrorListeners("missingOpeningParen");
				setState(128);
				content();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(130);
				openParen();
				setState(132);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(131);
					type();
					}
					break;
				}
				setState(135);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(134);
					content();
					}
					break;
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(137);
					customFontInfo();
					}
				}

				setState(143);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(140);
						node();
						}
						} 
					}
					setState(145);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				}
				notifyErrorListeners("missingClosingParen");
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(148);
				type();
				setState(150);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(149);
					content();
					}
					break;
				}
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(152);
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
			setState(159);
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
			setState(161);
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
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				nodeType();
				setState(164);
				lineType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				lineType();
				setState(167);
				nodeType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(169);
				nodeType();
				setState(170);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(173);
				lineType();
				setState(174);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(177);
				nodeType();
				setState(178);
				lineType();
				setState(179);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(182);
				lineType();
				setState(183);
				nodeType();
				setState(184);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(187);
				lineType();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(188);
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
			setState(191);
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
			setState(193);
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
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(196); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(195);
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
					setState(198); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(200);
				subscript();
				setState(201);
				superscript();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(204); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(203);
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
					setState(206); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(208);
				superscript();
				setState(209);
				subscript();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(212); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(211);
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
					setState(214); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(216);
				subscript();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(218); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(217);
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
					setState(220); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(222);
				superscript();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(224); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(223);
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
					setState(226); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(228);
				subscript();
				setState(229);
				superscript();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(231);
				superscript();
				setState(232);
				subscript();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(234);
				subscript();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(235);
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
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				match(SUBSCRIPT);
				setState(240); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(239);
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
					setState(242); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
				match(SUBSCRIPTITALIC);
				setState(246); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(245);
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
					setState(248); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				match(SUBSCRIPT);
				notifyErrorListeners("missingContentAfterSubscript");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(252);
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
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				match(SUPERSCRIPT);
				setState(258); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(257);
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
					setState(260); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				match(SUPERSCRIPTITALIC);
				setState(264); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(263);
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
					setState(266); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(268);
				match(SUPERSCRIPT);
				notifyErrorListeners("missingContentAfterSuperscript");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(270);
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
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(274);
				match(ABBREVIATIONBEGIN);
				setState(276); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(275);
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
					setState(278); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(280);
				match(ABBREVIATIONEND);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				match(ABBREVIATIONBEGIN);
				setState(283); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(282);
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
					setState(285); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				notifyErrorListeners("missingAbbreviationEnd");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(288);
				match(ABBREVIATIONBEGIN);
				setState(289);
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
		public List<CustomFontInfoContext> customFontInfo() {
			return getRuleContexts(CustomFontInfoContext.class);
		}
		public CustomFontInfoContext customFontInfo(int i) {
			return getRuleContext(CustomFontInfoContext.class,i);
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
			setState(341);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(294); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(293);
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
					setState(296); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(298);
					customFontInfo();
					}
				}

				setState(301);
				abbreviation();
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(302);
					customFontInfo();
					}
				}

				setState(306); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(305);
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
					setState(308); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(310);
					customFontInfo();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				abbreviation();
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(314);
					customFontInfo();
					}
				}

				setState(318); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(317);
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
					setState(320); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(322);
					customFontInfo();
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(326); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(325);
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
					setState(328); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(330);
					customFontInfo();
					}
				}

				setState(333);
				abbreviation();
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(334);
					customFontInfo();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(337);
				abbreviation();
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CUSTOMFONTBEGIN) {
					{
					setState(338);
					customFontInfo();
					}
				}

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
			setState(360);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(343);
				match(CUSTOMFONTBEGIN);
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
				match(CUSTOMFONTEND);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(350);
				match(CUSTOMFONTBEGIN);
				setState(352); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(351);
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
					setState(354); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				notifyErrorListeners("missingCustomFontEnd");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(357);
				match(CUSTOMFONTBEGIN);
				setState(358);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u016d\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\5\2:\n\2\3\3\3\3\5\3>\n\3\3\3\5\3A\n\3\3\3\5\3D\n\3\3\3\7\3G"+
		"\n\3\f\3\16\3J\13\3\3\3\3\3\3\3\3\3\5\3P\n\3\3\3\6\3S\n\3\r\3\16\3T\3"+
		"\3\7\3X\n\3\f\3\16\3[\13\3\3\3\3\3\3\3\3\3\5\3a\n\3\3\3\5\3d\n\3\3\3\5"+
		"\3g\n\3\3\3\7\3j\n\3\f\3\16\3m\13\3\3\3\3\3\3\3\3\3\5\3s\n\3\3\3\5\3v"+
		"\n\3\3\3\5\3y\n\3\3\3\7\3|\n\3\f\3\16\3\177\13\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\5\3\u0087\n\3\3\3\5\3\u008a\n\3\3\3\5\3\u008d\n\3\3\3\7\3\u0090\n\3"+
		"\f\3\16\3\u0093\13\3\3\3\3\3\3\3\3\3\5\3\u0099\n\3\3\3\5\3\u009c\n\3\3"+
		"\3\3\3\5\3\u00a0\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\5\6\u00c0\n\6\3\7\3\7\3\b\3\b\3\t\6\t\u00c7\n\t\r\t\16\t\u00c8\3\t\3"+
		"\t\3\t\3\t\6\t\u00cf\n\t\r\t\16\t\u00d0\3\t\3\t\3\t\3\t\6\t\u00d7\n\t"+
		"\r\t\16\t\u00d8\3\t\3\t\6\t\u00dd\n\t\r\t\16\t\u00de\3\t\3\t\6\t\u00e3"+
		"\n\t\r\t\16\t\u00e4\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ef\n\t\3\n"+
		"\3\n\6\n\u00f3\n\n\r\n\16\n\u00f4\3\n\3\n\6\n\u00f9\n\n\r\n\16\n\u00fa"+
		"\3\n\3\n\3\n\3\n\5\n\u0101\n\n\3\13\3\13\6\13\u0105\n\13\r\13\16\13\u0106"+
		"\3\13\3\13\6\13\u010b\n\13\r\13\16\13\u010c\3\13\3\13\3\13\3\13\5\13\u0113"+
		"\n\13\3\f\3\f\6\f\u0117\n\f\r\f\16\f\u0118\3\f\3\f\3\f\6\f\u011e\n\f\r"+
		"\f\16\f\u011f\3\f\3\f\3\f\3\f\5\f\u0126\n\f\3\r\6\r\u0129\n\r\r\r\16\r"+
		"\u012a\3\r\5\r\u012e\n\r\3\r\3\r\5\r\u0132\n\r\3\r\6\r\u0135\n\r\r\r\16"+
		"\r\u0136\3\r\5\r\u013a\n\r\3\r\3\r\5\r\u013e\n\r\3\r\6\r\u0141\n\r\r\r"+
		"\16\r\u0142\3\r\5\r\u0146\n\r\3\r\6\r\u0149\n\r\r\r\16\r\u014a\3\r\5\r"+
		"\u014e\n\r\3\r\3\r\5\r\u0152\n\r\3\r\3\r\5\r\u0156\n\r\5\r\u0158\n\r\3"+
		"\16\3\16\6\16\u015c\n\16\r\16\16\16\u015d\3\16\3\16\3\16\6\16\u0163\n"+
		"\16\r\16\16\16\u0164\3\16\3\16\3\16\3\16\5\16\u016b\n\16\3\16\2\2\17\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\2\6\3\2\5\6\3\2\7\t\3\2\22\25\3\2\22\23"+
		"\2\u01b5\29\3\2\2\2\4\u009f\3\2\2\2\6\u00a1\3\2\2\2\b\u00a3\3\2\2\2\n"+
		"\u00bf\3\2\2\2\f\u00c1\3\2\2\2\16\u00c3\3\2\2\2\20\u00ee\3\2\2\2\22\u0100"+
		"\3\2\2\2\24\u0112\3\2\2\2\26\u0125\3\2\2\2\30\u0157\3\2\2\2\32\u016a\3"+
		"\2\2\2\34\35\5\4\3\2\35\36\7\2\2\3\36:\3\2\2\2\37 \7\2\2\3 :\b\2\1\2!"+
		"\"\b\2\1\2\":\5\20\t\2#$\5\4\3\2$%\b\2\1\2%&\5\20\t\2&:\3\2\2\2\'(\5\4"+
		"\3\2()\b\2\1\2)*\5\4\3\2*:\3\2\2\2+,\b\2\1\2,-\5\20\t\2-.\5\4\3\2./\7"+
		"\2\2\3/:\3\2\2\2\60\61\b\2\1\2\61\62\5\n\6\2\62\63\5\4\3\2\63\64\7\2\2"+
		"\3\64:\3\2\2\2\65\66\5\4\3\2\66\67\5\b\5\2\678\b\2\1\28:\3\2\2\29\34\3"+
		"\2\2\29\37\3\2\2\29!\3\2\2\29#\3\2\2\29\'\3\2\2\29+\3\2\2\29\60\3\2\2"+
		"\29\65\3\2\2\2:\3\3\2\2\2;=\5\6\4\2<>\5\n\6\2=<\3\2\2\2=>\3\2\2\2>@\3"+
		"\2\2\2?A\5\20\t\2@?\3\2\2\2@A\3\2\2\2AC\3\2\2\2BD\5\32\16\2CB\3\2\2\2"+
		"CD\3\2\2\2DH\3\2\2\2EG\5\4\3\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2"+
		"IK\3\2\2\2JH\3\2\2\2KL\5\b\5\2L\u00a0\3\2\2\2MO\5\6\4\2NP\5\n\6\2ON\3"+
		"\2\2\2OP\3\2\2\2PR\3\2\2\2QS\5\30\r\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU"+
		"\3\2\2\2UY\3\2\2\2VX\5\4\3\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z"+
		"\\\3\2\2\2[Y\3\2\2\2\\]\5\b\5\2]\u00a0\3\2\2\2^`\5\6\4\2_a\5\n\6\2`_\3"+
		"\2\2\2`a\3\2\2\2ac\3\2\2\2bd\5\20\t\2cb\3\2\2\2cd\3\2\2\2df\3\2\2\2eg"+
		"\5\32\16\2fe\3\2\2\2fg\3\2\2\2gk\3\2\2\2hj\5\4\3\2ih\3\2\2\2jm\3\2\2\2"+
		"ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\5\b\5\2o\u00a0\3\2\2\2pr\5"+
		"\6\4\2qs\5\n\6\2rq\3\2\2\2rs\3\2\2\2su\3\2\2\2tv\5\20\t\2ut\3\2\2\2uv"+
		"\3\2\2\2vx\3\2\2\2wy\5\32\16\2xw\3\2\2\2xy\3\2\2\2y}\3\2\2\2z|\5\4\3\2"+
		"{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177}\3\2\2"+
		"\2\u0080\u0081\5\b\5\2\u0081\u0082\b\3\1\2\u0082\u0083\5\20\t\2\u0083"+
		"\u00a0\3\2\2\2\u0084\u0086\5\6\4\2\u0085\u0087\5\n\6\2\u0086\u0085\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u008a\5\20\t\2\u0089"+
		"\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b\u008d\5\32"+
		"\16\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0091\3\2\2\2\u008e"+
		"\u0090\5\4\3\2\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2"+
		"\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"\u0095\b\3\1\2\u0095\u00a0\3\2\2\2\u0096\u0098\5\n\6\2\u0097\u0099\5\20"+
		"\t\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a"+
		"\u009c\5\32\16\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3"+
		"\2\2\2\u009d\u009e\b\3\1\2\u009e\u00a0\3\2\2\2\u009f;\3\2\2\2\u009fM\3"+
		"\2\2\2\u009f^\3\2\2\2\u009fp\3\2\2\2\u009f\u0084\3\2\2\2\u009f\u0096\3"+
		"\2\2\2\u00a0\5\3\2\2\2\u00a1\u00a2\7\3\2\2\u00a2\7\3\2\2\2\u00a3\u00a4"+
		"\7\4\2\2\u00a4\t\3\2\2\2\u00a5\u00a6\5\16\b\2\u00a6\u00a7\5\f\7\2\u00a7"+
		"\u00c0\3\2\2\2\u00a8\u00a9\5\f\7\2\u00a9\u00aa\5\16\b\2\u00aa\u00c0\3"+
		"\2\2\2\u00ab\u00ac\5\16\b\2\u00ac\u00ad\5\16\b\2\u00ad\u00ae\b\6\1\2\u00ae"+
		"\u00c0\3\2\2\2\u00af\u00b0\5\f\7\2\u00b0\u00b1\5\f\7\2\u00b1\u00b2\b\6"+
		"\1\2\u00b2\u00c0\3\2\2\2\u00b3\u00b4\5\16\b\2\u00b4\u00b5\5\f\7\2\u00b5"+
		"\u00b6\5\16\b\2\u00b6\u00b7\b\6\1\2\u00b7\u00c0\3\2\2\2\u00b8\u00b9\5"+
		"\f\7\2\u00b9\u00ba\5\16\b\2\u00ba\u00bb\5\f\7\2\u00bb\u00bc\b\6\1\2\u00bc"+
		"\u00c0\3\2\2\2\u00bd\u00c0\5\f\7\2\u00be\u00c0\5\16\b\2\u00bf\u00a5\3"+
		"\2\2\2\u00bf\u00a8\3\2\2\2\u00bf\u00ab\3\2\2\2\u00bf\u00af\3\2\2\2\u00bf"+
		"\u00b3\3\2\2\2\u00bf\u00b8\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00be\3\2"+
		"\2\2\u00c0\13\3\2\2\2\u00c1\u00c2\t\2\2\2\u00c2\r\3\2\2\2\u00c3\u00c4"+
		"\t\3\2\2\u00c4\17\3\2\2\2\u00c5\u00c7\t\4\2\2\u00c6\u00c5\3\2\2\2\u00c7"+
		"\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\3\2"+
		"\2\2\u00ca\u00cb\5\22\n\2\u00cb\u00cc\5\24\13\2\u00cc\u00ef\3\2\2\2\u00cd"+
		"\u00cf\t\4\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00ce\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\5\24\13\2\u00d3"+
		"\u00d4\5\22\n\2\u00d4\u00ef\3\2\2\2\u00d5\u00d7\t\4\2\2\u00d6\u00d5\3"+
		"\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00ef\5\22\n\2\u00db\u00dd\t\4\2\2\u00dc\u00db\3"+
		"\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0\u00ef\5\24\13\2\u00e1\u00e3\t\4\2\2\u00e2\u00e1\3"+
		"\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00ef\3\2\2\2\u00e6\u00e7\5\22\n\2\u00e7\u00e8\5\24\13\2\u00e8\u00ef"+
		"\3\2\2\2\u00e9\u00ea\5\24\13\2\u00ea\u00eb\5\22\n\2\u00eb\u00ef\3\2\2"+
		"\2\u00ec\u00ef\5\22\n\2\u00ed\u00ef\5\24\13\2\u00ee\u00c6\3\2\2\2\u00ee"+
		"\u00ce\3\2\2\2\u00ee\u00d6\3\2\2\2\u00ee\u00dc\3\2\2\2\u00ee\u00e2\3\2"+
		"\2\2\u00ee\u00e6\3\2\2\2\u00ee\u00e9\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ed\3\2\2\2\u00ef\21\3\2\2\2\u00f0\u00f2\7\n\2\2\u00f1\u00f3\t\4\2"+
		"\2\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u0101\3\2\2\2\u00f6\u00f8\7\13\2\2\u00f7\u00f9\t\4\2\2"+
		"\u00f8\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb"+
		"\3\2\2\2\u00fb\u0101\3\2\2\2\u00fc\u00fd\7\n\2\2\u00fd\u0101\b\n\1\2\u00fe"+
		"\u00ff\7\13\2\2\u00ff\u0101\b\n\1\2\u0100\u00f0\3\2\2\2\u0100\u00f6\3"+
		"\2\2\2\u0100\u00fc\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\23\3\2\2\2\u0102"+
		"\u0104\7\f\2\2\u0103\u0105\t\4\2\2\u0104\u0103\3\2\2\2\u0105\u0106\3\2"+
		"\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0113\3\2\2\2\u0108"+
		"\u010a\7\r\2\2\u0109\u010b\t\4\2\2\u010a\u0109\3\2\2\2\u010b\u010c\3\2"+
		"\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u0113\3\2\2\2\u010e"+
		"\u010f\7\f\2\2\u010f\u0113\b\13\1\2\u0110\u0111\7\r\2\2\u0111\u0113\b"+
		"\13\1\2\u0112\u0102\3\2\2\2\u0112\u0108\3\2\2\2\u0112\u010e\3\2\2\2\u0112"+
		"\u0110\3\2\2\2\u0113\25\3\2\2\2\u0114\u0116\7\16\2\2\u0115\u0117\t\5\2"+
		"\2\u0116\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119"+
		"\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u0126\7\17\2\2\u011b\u011d\7\16\2\2"+
		"\u011c\u011e\t\5\2\2\u011d\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u011d"+
		"\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0126\b\f\1\2\u0122"+
		"\u0123\7\16\2\2\u0123\u0124\7\17\2\2\u0124\u0126\b\f\1\2\u0125\u0114\3"+
		"\2\2\2\u0125\u011b\3\2\2\2\u0125\u0122\3\2\2\2\u0126\27\3\2\2\2\u0127"+
		"\u0129\t\5\2\2\u0128\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2"+
		"\2\2\u012a\u012b\3\2\2\2\u012b\u012d\3\2\2\2\u012c\u012e\5\32\16\2\u012d"+
		"\u012c\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\5\26"+
		"\f\2\u0130\u0132\5\32\16\2\u0131\u0130\3\2\2\2\u0131\u0132\3\2\2\2\u0132"+
		"\u0134\3\2\2\2\u0133\u0135\t\5\2\2\u0134\u0133\3\2\2\2\u0135\u0136\3\2"+
		"\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0139\3\2\2\2\u0138"+
		"\u013a\5\32\16\2\u0139\u0138\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u0158\3"+
		"\2\2\2\u013b\u013d\5\26\f\2\u013c\u013e\5\32\16\2\u013d\u013c\3\2\2\2"+
		"\u013d\u013e\3\2\2\2\u013e\u0140\3\2\2\2\u013f\u0141\t\5\2\2\u0140\u013f"+
		"\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143"+
		"\u0145\3\2\2\2\u0144\u0146\5\32\16\2\u0145\u0144\3\2\2\2\u0145\u0146\3"+
		"\2\2\2\u0146\u0158\3\2\2\2\u0147\u0149\t\5\2\2\u0148\u0147\3\2\2\2\u0149"+
		"\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014d\3\2"+
		"\2\2\u014c\u014e\5\32\16\2\u014d\u014c\3\2\2\2\u014d\u014e\3\2\2\2\u014e"+
		"\u014f\3\2\2\2\u014f\u0151\5\26\f\2\u0150\u0152\5\32\16\2\u0151\u0150"+
		"\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0158\3\2\2\2\u0153\u0155\5\26\f\2"+
		"\u0154\u0156\5\32\16\2\u0155\u0154\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0158"+
		"\3\2\2\2\u0157\u0128\3\2\2\2\u0157\u013b\3\2\2\2\u0157\u0148\3\2\2\2\u0157"+
		"\u0153\3\2\2\2\u0158\31\3\2\2\2\u0159\u015b\7\20\2\2\u015a\u015c\t\5\2"+
		"\2\u015b\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e"+
		"\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u016b\7\21\2\2\u0160\u0162\7\20\2\2"+
		"\u0161\u0163\t\5\2\2\u0162\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0162"+
		"\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u016b\b\16\1\2"+
		"\u0167\u0168\7\20\2\2\u0168\u0169\7\21\2\2\u0169\u016b\b\16\1\2\u016a"+
		"\u0159\3\2\2\2\u016a\u0160\3\2\2\2\u016a\u0167\3\2\2\2\u016b\33\3\2\2"+
		"\299=@CHOTY`cfkrux}\u0086\u0089\u008c\u0091\u0098\u009b\u009f\u00bf\u00c8"+
		"\u00d0\u00d8\u00de\u00e4\u00ee\u00f4\u00fa\u0100\u0106\u010c\u0112\u0118"+
		"\u011f\u0125\u012a\u012d\u0131\u0136\u0139\u013d\u0142\u0145\u014a\u014d"+
		"\u0151\u0155\u0157\u015d\u0164\u016a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
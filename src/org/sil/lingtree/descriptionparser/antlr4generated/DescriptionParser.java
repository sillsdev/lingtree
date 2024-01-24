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
		ABBREVIATIONEND=13, TEXT=14, TEXTWITHSPACES=15, BACKSLASH=16, SLASH=17, 
		WS=18;
	public static final int
		RULE_description = 0, RULE_node = 1, RULE_openParen = 2, RULE_closeParen = 3, 
		RULE_type = 4, RULE_lineType = 5, RULE_nodeType = 6, RULE_content = 7, 
		RULE_subscript = 8, RULE_superscript = 9, RULE_abbreviation = 10, RULE_abbreviationWithText = 11;
	public static final String[] ruleNames = {
		"description", "node", "openParen", "closeParen", "type", "lineType", 
		"nodeType", "content", "subscript", "superscript", "abbreviation", "abbreviationWithText"
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
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				node();
				setState(25);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				match(EOF);
				notifyErrorListeners("missingOpeningParen");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				notifyErrorListeners("missingOpeningParen");
				setState(30);
				content();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(31);
				node();
				notifyErrorListeners("contentAfterCompletedTree");
				setState(33);
				content();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(35);
				node();
				notifyErrorListeners("contentAfterCompletedTree");
				setState(37);
				node();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				notifyErrorListeners("missingOpeningParen");
				setState(40);
				content();
				setState(41);
				node();
				setState(42);
				match(EOF);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				notifyErrorListeners("missingOpeningParen");
				setState(45);
				type();
				setState(46);
				node();
				setState(47);
				match(EOF);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(49);
				node();
				setState(50);
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
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				openParen();
				setState(57);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(56);
					type();
					}
					break;
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(59);
					content();
					}
				}

				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(62);
					node();
					}
					}
					setState(67);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(68);
				closeParen();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				openParen();
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					setState(71);
					type();
					}
				}

				setState(75); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(74);
					abbreviationWithText();
					}
					}
					setState(77); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABBREVIATIONBEGIN) | (1L << TEXT) | (1L << TEXTWITHSPACES))) != 0) );
				setState(79);
				closeParen();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				openParen();
				setState(83);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(82);
					type();
					}
					break;
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(85);
					content();
					}
				}

				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(88);
					node();
					}
					}
					setState(93);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(94);
				closeParen();
				notifyErrorListeners("missingOpeningParen");
				setState(96);
				content();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(98);
				openParen();
				setState(100);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(99);
					type();
					}
					break;
				}
				setState(103);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(102);
					content();
					}
					break;
				}
				setState(108);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(105);
						node();
						}
						} 
					}
					setState(110);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				}
				notifyErrorListeners("missingClosingParen");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(113);
				type();
				setState(115);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(114);
					content();
					}
					break;
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
			setState(121);
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
			setState(123);
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
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				nodeType();
				setState(126);
				lineType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				lineType();
				setState(129);
				nodeType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				nodeType();
				setState(132);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(135);
				lineType();
				setState(136);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(139);
				nodeType();
				setState(140);
				lineType();
				setState(141);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(144);
				lineType();
				setState(145);
				nodeType();
				setState(146);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(149);
				lineType();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(150);
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
			setState(153);
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
			setState(155);
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
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(158); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(157);
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
					setState(160); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(162);
				subscript();
				setState(163);
				superscript();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(166); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(165);
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
					setState(168); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(170);
				superscript();
				setState(171);
				subscript();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(174); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(173);
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
					setState(176); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(178);
				subscript();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(180); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(179);
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
					setState(182); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(184);
				superscript();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(186); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(185);
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
					setState(188); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(190);
				subscript();
				setState(191);
				superscript();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(193);
				superscript();
				setState(194);
				subscript();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(196);
				subscript();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(197);
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
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				match(SUBSCRIPT);
				setState(202); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(201);
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
					setState(204); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				match(SUBSCRIPTITALIC);
				setState(208); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(207);
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
					setState(210); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
				match(SUBSCRIPT);
				notifyErrorListeners("missingContentAfterSubscript");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(214);
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
			setState(234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				match(SUPERSCRIPT);
				setState(220); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(219);
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
					setState(222); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(224);
				match(SUPERSCRIPTITALIC);
				setState(226); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(225);
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
					setState(228); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(230);
				match(SUPERSCRIPT);
				notifyErrorListeners("missingContentAfterSuperscript");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(232);
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
			setState(253);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				match(ABBREVIATIONBEGIN);
				setState(238); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(237);
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
					setState(240); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(242);
				match(ABBREVIATIONEND);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				match(ABBREVIATIONBEGIN);
				setState(245); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(244);
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
					setState(247); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				notifyErrorListeners("missingAbbreviationEnd");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				match(ABBREVIATIONBEGIN);
				setState(251);
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
			setState(279);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(256); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(255);
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
					setState(258); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(260);
				abbreviation();
				setState(262); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(261);
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
					setState(264); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				abbreviation();
				setState(268); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(267);
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
					setState(270); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(273); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(272);
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
					setState(275); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TEXT || _la==TEXTWITHSPACES );
				setState(277);
				abbreviation();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(278);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24\u011c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\28\n\2\3\3\3\3\5\3<\n\3\3\3\5\3?\n\3\3\3\7\3B\n\3\f\3\16\3E\13\3\3\3"+
		"\3\3\3\3\3\3\5\3K\n\3\3\3\6\3N\n\3\r\3\16\3O\3\3\3\3\3\3\3\3\5\3V\n\3"+
		"\3\3\5\3Y\n\3\3\3\7\3\\\n\3\f\3\16\3_\13\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3"+
		"g\n\3\3\3\5\3j\n\3\3\3\7\3m\n\3\f\3\16\3p\13\3\3\3\3\3\3\3\3\3\5\3v\n"+
		"\3\3\3\3\3\5\3z\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\5\6\u009a\n\6\3\7\3\7\3\b\3\b\3\t\6\t\u00a1\n\t\r\t\16\t\u00a2\3\t\3"+
		"\t\3\t\3\t\6\t\u00a9\n\t\r\t\16\t\u00aa\3\t\3\t\3\t\3\t\6\t\u00b1\n\t"+
		"\r\t\16\t\u00b2\3\t\3\t\6\t\u00b7\n\t\r\t\16\t\u00b8\3\t\3\t\6\t\u00bd"+
		"\n\t\r\t\16\t\u00be\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c9\n\t\3\n"+
		"\3\n\6\n\u00cd\n\n\r\n\16\n\u00ce\3\n\3\n\6\n\u00d3\n\n\r\n\16\n\u00d4"+
		"\3\n\3\n\3\n\3\n\5\n\u00db\n\n\3\13\3\13\6\13\u00df\n\13\r\13\16\13\u00e0"+
		"\3\13\3\13\6\13\u00e5\n\13\r\13\16\13\u00e6\3\13\3\13\3\13\3\13\5\13\u00ed"+
		"\n\13\3\f\3\f\6\f\u00f1\n\f\r\f\16\f\u00f2\3\f\3\f\3\f\6\f\u00f8\n\f\r"+
		"\f\16\f\u00f9\3\f\3\f\3\f\3\f\5\f\u0100\n\f\3\r\6\r\u0103\n\r\r\r\16\r"+
		"\u0104\3\r\3\r\6\r\u0109\n\r\r\r\16\r\u010a\3\r\3\r\6\r\u010f\n\r\r\r"+
		"\16\r\u0110\3\r\6\r\u0114\n\r\r\r\16\r\u0115\3\r\3\r\5\r\u011a\n\r\3\r"+
		"\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\6\3\2\5\6\3\2\7\t\3\2\20\23\3"+
		"\2\20\21\2\u014f\2\67\3\2\2\2\4y\3\2\2\2\6{\3\2\2\2\b}\3\2\2\2\n\u0099"+
		"\3\2\2\2\f\u009b\3\2\2\2\16\u009d\3\2\2\2\20\u00c8\3\2\2\2\22\u00da\3"+
		"\2\2\2\24\u00ec\3\2\2\2\26\u00ff\3\2\2\2\30\u0119\3\2\2\2\32\33\5\4\3"+
		"\2\33\34\7\2\2\3\348\3\2\2\2\35\36\7\2\2\3\368\b\2\1\2\37 \b\2\1\2 8\5"+
		"\20\t\2!\"\5\4\3\2\"#\b\2\1\2#$\5\20\t\2$8\3\2\2\2%&\5\4\3\2&\'\b\2\1"+
		"\2\'(\5\4\3\2(8\3\2\2\2)*\b\2\1\2*+\5\20\t\2+,\5\4\3\2,-\7\2\2\3-8\3\2"+
		"\2\2./\b\2\1\2/\60\5\n\6\2\60\61\5\4\3\2\61\62\7\2\2\3\628\3\2\2\2\63"+
		"\64\5\4\3\2\64\65\5\b\5\2\65\66\b\2\1\2\668\3\2\2\2\67\32\3\2\2\2\67\35"+
		"\3\2\2\2\67\37\3\2\2\2\67!\3\2\2\2\67%\3\2\2\2\67)\3\2\2\2\67.\3\2\2\2"+
		"\67\63\3\2\2\28\3\3\2\2\29;\5\6\4\2:<\5\n\6\2;:\3\2\2\2;<\3\2\2\2<>\3"+
		"\2\2\2=?\5\20\t\2>=\3\2\2\2>?\3\2\2\2?C\3\2\2\2@B\5\4\3\2A@\3\2\2\2BE"+
		"\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\5\b\5\2Gz\3\2\2\2H"+
		"J\5\6\4\2IK\5\n\6\2JI\3\2\2\2JK\3\2\2\2KM\3\2\2\2LN\5\30\r\2ML\3\2\2\2"+
		"NO\3\2\2\2OM\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QR\5\b\5\2Rz\3\2\2\2SU\5\6\4\2"+
		"TV\5\n\6\2UT\3\2\2\2UV\3\2\2\2VX\3\2\2\2WY\5\20\t\2XW\3\2\2\2XY\3\2\2"+
		"\2Y]\3\2\2\2Z\\\5\4\3\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^`\3\2"+
		"\2\2_]\3\2\2\2`a\5\b\5\2ab\b\3\1\2bc\5\20\t\2cz\3\2\2\2df\5\6\4\2eg\5"+
		"\n\6\2fe\3\2\2\2fg\3\2\2\2gi\3\2\2\2hj\5\20\t\2ih\3\2\2\2ij\3\2\2\2jn"+
		"\3\2\2\2km\5\4\3\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2p"+
		"n\3\2\2\2qr\b\3\1\2rz\3\2\2\2su\5\n\6\2tv\5\20\t\2ut\3\2\2\2uv\3\2\2\2"+
		"vw\3\2\2\2wx\b\3\1\2xz\3\2\2\2y9\3\2\2\2yH\3\2\2\2yS\3\2\2\2yd\3\2\2\2"+
		"ys\3\2\2\2z\5\3\2\2\2{|\7\3\2\2|\7\3\2\2\2}~\7\4\2\2~\t\3\2\2\2\177\u0080"+
		"\5\16\b\2\u0080\u0081\5\f\7\2\u0081\u009a\3\2\2\2\u0082\u0083\5\f\7\2"+
		"\u0083\u0084\5\16\b\2\u0084\u009a\3\2\2\2\u0085\u0086\5\16\b\2\u0086\u0087"+
		"\5\16\b\2\u0087\u0088\b\6\1\2\u0088\u009a\3\2\2\2\u0089\u008a\5\f\7\2"+
		"\u008a\u008b\5\f\7\2\u008b\u008c\b\6\1\2\u008c\u009a\3\2\2\2\u008d\u008e"+
		"\5\16\b\2\u008e\u008f\5\f\7\2\u008f\u0090\5\16\b\2\u0090\u0091\b\6\1\2"+
		"\u0091\u009a\3\2\2\2\u0092\u0093\5\f\7\2\u0093\u0094\5\16\b\2\u0094\u0095"+
		"\5\f\7\2\u0095\u0096\b\6\1\2\u0096\u009a\3\2\2\2\u0097\u009a\5\f\7\2\u0098"+
		"\u009a\5\16\b\2\u0099\177\3\2\2\2\u0099\u0082\3\2\2\2\u0099\u0085\3\2"+
		"\2\2\u0099\u0089\3\2\2\2\u0099\u008d\3\2\2\2\u0099\u0092\3\2\2\2\u0099"+
		"\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\13\3\2\2\2\u009b\u009c\t\2\2"+
		"\2\u009c\r\3\2\2\2\u009d\u009e\t\3\2\2\u009e\17\3\2\2\2\u009f\u00a1\t"+
		"\4\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\5\22\n\2\u00a5\u00a6\5"+
		"\24\13\2\u00a6\u00c9\3\2\2\2\u00a7\u00a9\t\4\2\2\u00a8\u00a7\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2"+
		"\2\2\u00ac\u00ad\5\24\13\2\u00ad\u00ae\5\22\n\2\u00ae\u00c9\3\2\2\2\u00af"+
		"\u00b1\t\4\2\2\u00b0\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b0\3\2"+
		"\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00c9\5\22\n\2\u00b5"+
		"\u00b7\t\4\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00c9\5\24\13\2\u00bb"+
		"\u00bd\t\4\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2"+
		"\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c9\3\2\2\2\u00c0\u00c1\5\22\n\2\u00c1"+
		"\u00c2\5\24\13\2\u00c2\u00c9\3\2\2\2\u00c3\u00c4\5\24\13\2\u00c4\u00c5"+
		"\5\22\n\2\u00c5\u00c9\3\2\2\2\u00c6\u00c9\5\22\n\2\u00c7\u00c9\5\24\13"+
		"\2\u00c8\u00a0\3\2\2\2\u00c8\u00a8\3\2\2\2\u00c8\u00b0\3\2\2\2\u00c8\u00b6"+
		"\3\2\2\2\u00c8\u00bc\3\2\2\2\u00c8\u00c0\3\2\2\2\u00c8\u00c3\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2\u00c9\21\3\2\2\2\u00ca\u00cc\7\n\2"+
		"\2\u00cb\u00cd\t\4\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc"+
		"\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00db\3\2\2\2\u00d0\u00d2\7\13\2\2"+
		"\u00d1\u00d3\t\4\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2"+
		"\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00db\3\2\2\2\u00d6\u00d7\7\n\2\2\u00d7"+
		"\u00db\b\n\1\2\u00d8\u00d9\7\13\2\2\u00d9\u00db\b\n\1\2\u00da\u00ca\3"+
		"\2\2\2\u00da\u00d0\3\2\2\2\u00da\u00d6\3\2\2\2\u00da\u00d8\3\2\2\2\u00db"+
		"\23\3\2\2\2\u00dc\u00de\7\f\2\2\u00dd\u00df\t\4\2\2\u00de\u00dd\3\2\2"+
		"\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00ed"+
		"\3\2\2\2\u00e2\u00e4\7\r\2\2\u00e3\u00e5\t\4\2\2\u00e4\u00e3\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00ed\3\2"+
		"\2\2\u00e8\u00e9\7\f\2\2\u00e9\u00ed\b\13\1\2\u00ea\u00eb\7\r\2\2\u00eb"+
		"\u00ed\b\13\1\2\u00ec\u00dc\3\2\2\2\u00ec\u00e2\3\2\2\2\u00ec\u00e8\3"+
		"\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\25\3\2\2\2\u00ee\u00f0\7\16\2\2\u00ef"+
		"\u00f1\t\5\2\2\u00f0\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f0\3\2"+
		"\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u0100\7\17\2\2\u00f5"+
		"\u00f7\7\16\2\2\u00f6\u00f8\t\5\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3"+
		"\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u0100\b\f\1\2\u00fc\u00fd\7\16\2\2\u00fd\u00fe\7\17\2\2\u00fe\u0100\b"+
		"\f\1\2\u00ff\u00ee\3\2\2\2\u00ff\u00f5\3\2\2\2\u00ff\u00fc\3\2\2\2\u0100"+
		"\27\3\2\2\2\u0101\u0103\t\5\2\2\u0102\u0101\3\2\2\2\u0103\u0104\3\2\2"+
		"\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0108"+
		"\5\26\f\2\u0107\u0109\t\5\2\2\u0108\u0107\3\2\2\2\u0109\u010a\3\2\2\2"+
		"\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u011a\3\2\2\2\u010c\u010e"+
		"\5\26\f\2\u010d\u010f\t\5\2\2\u010e\u010d\3\2\2\2\u010f\u0110\3\2\2\2"+
		"\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u011a\3\2\2\2\u0112\u0114"+
		"\t\5\2\2\u0113\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0113\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u011a\5\26\f\2\u0118\u011a\5"+
		"\26\f\2\u0119\u0102\3\2\2\2\u0119\u010c\3\2\2\2\u0119\u0113\3\2\2\2\u0119"+
		"\u0118\3\2\2\2\u011a\31\3\2\2\2%\67;>CJOUX]finuy\u0099\u00a2\u00aa\u00b2"+
		"\u00b8\u00be\u00c8\u00ce\u00d4\u00da\u00e0\u00e6\u00ec\u00f2\u00f9\u00ff"+
		"\u0104\u010a\u0110\u0115\u0119";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
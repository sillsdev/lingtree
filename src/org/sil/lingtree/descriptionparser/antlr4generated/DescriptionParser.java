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
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
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
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << ABBREVIATIONBEGIN) | (1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
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
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(71);
					type();
					}
					break;
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << ABBREVIATIONBEGIN) | (1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(74);
					content();
					}
				}

				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << OMIT) | (1L << TRIANGLE) | (1L << LEX) | (1L << GLOSS) | (1L << EMPTY))) != 0)) {
					{
					{
					setState(77);
					node();
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(83);
				closeParen();
				notifyErrorListeners("missingOpeningParen");
				setState(85);
				content();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
				openParen();
				setState(89);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(88);
					type();
					}
					break;
				}
				setState(92);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(91);
					content();
					}
					break;
				}
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(94);
						node();
						}
						} 
					}
					setState(99);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				notifyErrorListeners("missingClosingParen");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(102);
				type();
				setState(104);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(103);
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
			setState(110);
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
			setState(112);
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
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				nodeType();
				setState(115);
				lineType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				lineType();
				setState(118);
				nodeType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(120);
				nodeType();
				setState(121);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				lineType();
				setState(125);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(128);
				nodeType();
				setState(129);
				lineType();
				setState(130);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(133);
				lineType();
				setState(134);
				nodeType();
				setState(135);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(138);
				lineType();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(139);
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
			setState(142);
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
			setState(144);
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
		public List<AbbreviationWithTextContext> abbreviationWithText() {
			return getRuleContexts(AbbreviationWithTextContext.class);
		}
		public AbbreviationWithTextContext abbreviationWithText(int i) {
			return getRuleContext(AbbreviationWithTextContext.class,i);
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
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(146);
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
					setState(149); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(151);
				subscript();
				setState(152);
				superscript();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(155); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(154);
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
					setState(157); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(159);
				superscript();
				setState(160);
				subscript();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(163); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(162);
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
					setState(165); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(167);
				subscript();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(168);
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
					setState(171); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(173);
				superscript();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(175); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(174);
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
					setState(177); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(179);
				subscript();
				setState(180);
				superscript();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(182);
				superscript();
				setState(183);
				subscript();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(185);
				subscript();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(186);
				superscript();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(188); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(187);
					abbreviationWithText();
					}
					}
					setState(190); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ABBREVIATIONBEGIN );
				setState(193); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(192);
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
					setState(195); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(198); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(197);
						abbreviationWithText();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(200); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(203); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(202);
					abbreviationWithText();
					}
					}
					setState(205); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ABBREVIATIONBEGIN );
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
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(213); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(212);
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
					setState(215); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(218); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(217);
						abbreviationWithText();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(220); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
			setState(240);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				match(SUBSCRIPT);
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
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				match(SUBSCRIPTITALIC);
				setState(232); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(231);
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
					setState(234); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(236);
				match(SUBSCRIPT);
				notifyErrorListeners("missingContentAfterSubscript");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(238);
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
			setState(258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				match(SUPERSCRIPT);
				setState(244); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(243);
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
					setState(246); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(248);
				match(SUPERSCRIPTITALIC);
				setState(250); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(249);
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
					setState(252); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(254);
				match(SUPERSCRIPT);
				notifyErrorListeners("missingContentAfterSuperscript");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(256);
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
		public List<TerminalNode> BACKSLASH() { return getTokens(DescriptionParser.BACKSLASH); }
		public TerminalNode BACKSLASH(int i) {
			return getToken(DescriptionParser.BACKSLASH, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(DescriptionParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(DescriptionParser.SLASH, i);
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
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				match(ABBREVIATIONBEGIN);
				setState(262); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(261);
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
					setState(264); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << TEXTWITHSPACES) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(266);
				match(ABBREVIATIONEND);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
				match(ABBREVIATIONBEGIN);
				setState(269); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
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
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(271); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				notifyErrorListeners("missingAbbreviationEnd");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(274);
				match(ABBREVIATIONBEGIN);
				setState(275);
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
		public List<TerminalNode> BACKSLASH() { return getTokens(DescriptionParser.BACKSLASH); }
		public TerminalNode BACKSLASH(int i) {
			return getToken(DescriptionParser.BACKSLASH, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(DescriptionParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(DescriptionParser.SLASH, i);
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
			setState(286);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(279);
				abbreviation();
				setState(281); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(280);
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
					setState(283); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24\u0123\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\28\n\2\3\3\3\3\5\3<\n\3\3\3\5\3?\n\3\3\3\7\3B\n\3\f\3\16\3E\13\3\3\3"+
		"\3\3\3\3\3\3\5\3K\n\3\3\3\5\3N\n\3\3\3\7\3Q\n\3\f\3\16\3T\13\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\5\3\\\n\3\3\3\5\3_\n\3\3\3\7\3b\n\3\f\3\16\3e\13\3\3"+
		"\3\3\3\3\3\3\3\5\3k\n\3\3\3\3\3\5\3o\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\5\6\u008f\n\6\3\7\3\7\3\b\3\b\3\t\6\t\u0096\n\t\r"+
		"\t\16\t\u0097\3\t\3\t\3\t\3\t\6\t\u009e\n\t\r\t\16\t\u009f\3\t\3\t\3\t"+
		"\3\t\6\t\u00a6\n\t\r\t\16\t\u00a7\3\t\3\t\6\t\u00ac\n\t\r\t\16\t\u00ad"+
		"\3\t\3\t\6\t\u00b2\n\t\r\t\16\t\u00b3\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\6\t\u00bf\n\t\r\t\16\t\u00c0\3\t\6\t\u00c4\n\t\r\t\16\t\u00c5\3\t"+
		"\6\t\u00c9\n\t\r\t\16\t\u00ca\3\t\6\t\u00ce\n\t\r\t\16\t\u00cf\3\t\6\t"+
		"\u00d3\n\t\r\t\16\t\u00d4\3\t\6\t\u00d8\n\t\r\t\16\t\u00d9\3\t\6\t\u00dd"+
		"\n\t\r\t\16\t\u00de\5\t\u00e1\n\t\3\n\3\n\6\n\u00e5\n\n\r\n\16\n\u00e6"+
		"\3\n\3\n\6\n\u00eb\n\n\r\n\16\n\u00ec\3\n\3\n\3\n\3\n\5\n\u00f3\n\n\3"+
		"\13\3\13\6\13\u00f7\n\13\r\13\16\13\u00f8\3\13\3\13\6\13\u00fd\n\13\r"+
		"\13\16\13\u00fe\3\13\3\13\3\13\3\13\5\13\u0105\n\13\3\f\3\f\6\f\u0109"+
		"\n\f\r\f\16\f\u010a\3\f\3\f\3\f\6\f\u0110\n\f\r\f\16\f\u0111\3\f\3\f\3"+
		"\f\3\f\5\f\u0118\n\f\3\r\3\r\6\r\u011c\n\r\r\r\16\r\u011d\3\r\5\r\u0121"+
		"\n\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\5\3\2\5\6\3\2\7\t\3\2"+
		"\20\23\2\u0158\2\67\3\2\2\2\4n\3\2\2\2\6p\3\2\2\2\br\3\2\2\2\n\u008e\3"+
		"\2\2\2\f\u0090\3\2\2\2\16\u0092\3\2\2\2\20\u00e0\3\2\2\2\22\u00f2\3\2"+
		"\2\2\24\u0104\3\2\2\2\26\u0117\3\2\2\2\30\u0120\3\2\2\2\32\33\5\4\3\2"+
		"\33\34\7\2\2\3\348\3\2\2\2\35\36\7\2\2\3\368\b\2\1\2\37 \b\2\1\2 8\5\20"+
		"\t\2!\"\5\4\3\2\"#\b\2\1\2#$\5\20\t\2$8\3\2\2\2%&\5\4\3\2&\'\b\2\1\2\'"+
		"(\5\4\3\2(8\3\2\2\2)*\b\2\1\2*+\5\20\t\2+,\5\4\3\2,-\7\2\2\3-8\3\2\2\2"+
		"./\b\2\1\2/\60\5\n\6\2\60\61\5\4\3\2\61\62\7\2\2\3\628\3\2\2\2\63\64\5"+
		"\4\3\2\64\65\5\b\5\2\65\66\b\2\1\2\668\3\2\2\2\67\32\3\2\2\2\67\35\3\2"+
		"\2\2\67\37\3\2\2\2\67!\3\2\2\2\67%\3\2\2\2\67)\3\2\2\2\67.\3\2\2\2\67"+
		"\63\3\2\2\28\3\3\2\2\29;\5\6\4\2:<\5\n\6\2;:\3\2\2\2;<\3\2\2\2<>\3\2\2"+
		"\2=?\5\20\t\2>=\3\2\2\2>?\3\2\2\2?C\3\2\2\2@B\5\4\3\2A@\3\2\2\2BE\3\2"+
		"\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\5\b\5\2Go\3\2\2\2HJ\5\6"+
		"\4\2IK\5\n\6\2JI\3\2\2\2JK\3\2\2\2KM\3\2\2\2LN\5\20\t\2ML\3\2\2\2MN\3"+
		"\2\2\2NR\3\2\2\2OQ\5\4\3\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SU\3"+
		"\2\2\2TR\3\2\2\2UV\5\b\5\2VW\b\3\1\2WX\5\20\t\2Xo\3\2\2\2Y[\5\6\4\2Z\\"+
		"\5\n\6\2[Z\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2]_\5\20\t\2^]\3\2\2\2^_\3\2\2"+
		"\2_c\3\2\2\2`b\5\4\3\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2"+
		"\2ec\3\2\2\2fg\b\3\1\2go\3\2\2\2hj\5\n\6\2ik\5\20\t\2ji\3\2\2\2jk\3\2"+
		"\2\2kl\3\2\2\2lm\b\3\1\2mo\3\2\2\2n9\3\2\2\2nH\3\2\2\2nY\3\2\2\2nh\3\2"+
		"\2\2o\5\3\2\2\2pq\7\3\2\2q\7\3\2\2\2rs\7\4\2\2s\t\3\2\2\2tu\5\16\b\2u"+
		"v\5\f\7\2v\u008f\3\2\2\2wx\5\f\7\2xy\5\16\b\2y\u008f\3\2\2\2z{\5\16\b"+
		"\2{|\5\16\b\2|}\b\6\1\2}\u008f\3\2\2\2~\177\5\f\7\2\177\u0080\5\f\7\2"+
		"\u0080\u0081\b\6\1\2\u0081\u008f\3\2\2\2\u0082\u0083\5\16\b\2\u0083\u0084"+
		"\5\f\7\2\u0084\u0085\5\16\b\2\u0085\u0086\b\6\1\2\u0086\u008f\3\2\2\2"+
		"\u0087\u0088\5\f\7\2\u0088\u0089\5\16\b\2\u0089\u008a\5\f\7\2\u008a\u008b"+
		"\b\6\1\2\u008b\u008f\3\2\2\2\u008c\u008f\5\f\7\2\u008d\u008f\5\16\b\2"+
		"\u008et\3\2\2\2\u008ew\3\2\2\2\u008ez\3\2\2\2\u008e~\3\2\2\2\u008e\u0082"+
		"\3\2\2\2\u008e\u0087\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008d\3\2\2\2\u008f"+
		"\13\3\2\2\2\u0090\u0091\t\2\2\2\u0091\r\3\2\2\2\u0092\u0093\t\3\2\2\u0093"+
		"\17\3\2\2\2\u0094\u0096\t\4\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2"+
		"\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a"+
		"\5\22\n\2\u009a\u009b\5\24\13\2\u009b\u00e1\3\2\2\2\u009c\u009e\t\4\2"+
		"\2\u009d\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\5\24\13\2\u00a2\u00a3\5\22\n"+
		"\2\u00a3\u00e1\3\2\2\2\u00a4\u00a6\t\4\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7"+
		"\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00e1\5\22\n\2\u00aa\u00ac\t\4\2\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3"+
		"\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00e1\5\24\13\2\u00b0\u00b2\t\4\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3\3"+
		"\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00e1\3\2\2\2\u00b5"+
		"\u00b6\5\22\n\2\u00b6\u00b7\5\24\13\2\u00b7\u00e1\3\2\2\2\u00b8\u00b9"+
		"\5\24\13\2\u00b9\u00ba\5\22\n\2\u00ba\u00e1\3\2\2\2\u00bb\u00e1\5\22\n"+
		"\2\u00bc\u00e1\5\24\13\2\u00bd\u00bf\5\30\r\2\u00be\u00bd\3\2\2\2\u00bf"+
		"\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c3\3\2"+
		"\2\2\u00c2\u00c4\t\4\2\2\u00c3\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00c9\5\30"+
		"\r\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca"+
		"\u00cb\3\2\2\2\u00cb\u00e1\3\2\2\2\u00cc\u00ce\5\30\r\2\u00cd\u00cc\3"+
		"\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0"+
		"\u00d2\3\2\2\2\u00d1\u00d3\t\4\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2"+
		"\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00e1\3\2\2\2\u00d6"+
		"\u00d8\t\4\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00d7\3\2"+
		"\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00dd\5\30\r\2\u00dc"+
		"\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2"+
		"\2\2\u00df\u00e1\3\2\2\2\u00e0\u0095\3\2\2\2\u00e0\u009d\3\2\2\2\u00e0"+
		"\u00a5\3\2\2\2\u00e0\u00ab\3\2\2\2\u00e0\u00b1\3\2\2\2\u00e0\u00b5\3\2"+
		"\2\2\u00e0\u00b8\3\2\2\2\u00e0\u00bb\3\2\2\2\u00e0\u00bc\3\2\2\2\u00e0"+
		"\u00be\3\2\2\2\u00e0\u00cd\3\2\2\2\u00e0\u00d7\3\2\2\2\u00e1\21\3\2\2"+
		"\2\u00e2\u00e4\7\n\2\2\u00e3\u00e5\t\4\2\2\u00e4\u00e3\3\2\2\2\u00e5\u00e6"+
		"\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00f3\3\2\2\2\u00e8"+
		"\u00ea\7\13\2\2\u00e9\u00eb\t\4\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3"+
		"\2\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00f3\3\2\2\2\u00ee"+
		"\u00ef\7\n\2\2\u00ef\u00f3\b\n\1\2\u00f0\u00f1\7\13\2\2\u00f1\u00f3\b"+
		"\n\1\2\u00f2\u00e2\3\2\2\2\u00f2\u00e8\3\2\2\2\u00f2\u00ee\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f3\23\3\2\2\2\u00f4\u00f6\7\f\2\2\u00f5\u00f7\t\4\2"+
		"\2\u00f6\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9"+
		"\3\2\2\2\u00f9\u0105\3\2\2\2\u00fa\u00fc\7\r\2\2\u00fb\u00fd\t\4\2\2\u00fc"+
		"\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2"+
		"\2\2\u00ff\u0105\3\2\2\2\u0100\u0101\7\f\2\2\u0101\u0105\b\13\1\2\u0102"+
		"\u0103\7\r\2\2\u0103\u0105\b\13\1\2\u0104\u00f4\3\2\2\2\u0104\u00fa\3"+
		"\2\2\2\u0104\u0100\3\2\2\2\u0104\u0102\3\2\2\2\u0105\25\3\2\2\2\u0106"+
		"\u0108\7\16\2\2\u0107\u0109\t\4\2\2\u0108\u0107\3\2\2\2\u0109\u010a\3"+
		"\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\3\2\2\2\u010c"+
		"\u0118\7\17\2\2\u010d\u010f\7\16\2\2\u010e\u0110\t\4\2\2\u010f\u010e\3"+
		"\2\2\2\u0110\u0111\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0118\b\f\1\2\u0114\u0115\7\16\2\2\u0115\u0116\7"+
		"\17\2\2\u0116\u0118\b\f\1\2\u0117\u0106\3\2\2\2\u0117\u010d\3\2\2\2\u0117"+
		"\u0114\3\2\2\2\u0118\27\3\2\2\2\u0119\u011b\5\26\f\2\u011a\u011c\t\4\2"+
		"\2\u011b\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e"+
		"\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u0121\5\26\f\2\u0120\u0119\3\2\2\2"+
		"\u0120\u011f\3\2\2\2\u0121\31\3\2\2\2\'\67;>CJMR[^cjn\u008e\u0097\u009f"+
		"\u00a7\u00ad\u00b3\u00c0\u00c5\u00ca\u00cf\u00d4\u00d9\u00de\u00e0\u00e6"+
		"\u00ec\u00f2\u00f8\u00fe\u0104\u010a\u0111\u0117\u011d\u0120";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
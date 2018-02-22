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
		T__0=1, T__1=2, T__2=3, OMIT=4, TRIANGLE=5, LEX=6, GLOSS=7, EMPTY=8, SUBSCRIPT=9, 
		SUBSCRIPTITALIC=10, SUPERSCRIPT=11, SUPERSCRIPTITALIC=12, TEXT=13, BACKSLASH=14, 
		SLASH=15, WS=16;
	public static final int
		RULE_description = 0, RULE_node = 1, RULE_openParen = 2, RULE_closeParen = 3, 
		RULE_type = 4, RULE_lineType = 5, RULE_nodeType = 6, RULE_content = 7, 
		RULE_subscript = 8, RULE_superscript = 9;
	public static final String[] ruleNames = {
		"description", "node", "openParen", "closeParen", "type", "lineType", 
		"nodeType", "content", "subscript", "superscript"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "' '", "'('", "')'", null, null, "'\\L'", "'\\G'", "'\\E'", "'/s'", 
		"'/_'", "'/S'", "'/^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "OMIT", "TRIANGLE", "LEX", "GLOSS", "EMPTY", "SUBSCRIPT", 
		"SUBSCRIPTITALIC", "SUPERSCRIPT", "SUPERSCRIPTITALIC", "TEXT", "BACKSLASH", 
		"SLASH", "WS"
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
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				node();
				setState(21);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				match(EOF);
				notifyErrorListeners("missingOpeningParen");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				notifyErrorListeners("missingOpeningParen");
				setState(26);
				content();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(27);
				node();
				notifyErrorListeners("contentAfterCompletedTree");
				setState(29);
				content();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(31);
				node();
				notifyErrorListeners("contentAfterCompletedTree");
				setState(33);
				node();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				notifyErrorListeners("missingOpeningParen");
				setState(36);
				content();
				setState(37);
				node();
				setState(38);
				match(EOF);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				notifyErrorListeners("missingOpeningParen");
				setState(41);
				type();
				setState(42);
				node();
				setState(43);
				match(EOF);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(45);
				node();
				setState(46);
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
			setState(104);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				openParen();
				setState(53);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(52);
					type();
					}
					break;
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(55);
					content();
					}
				}

				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(58);
						node();
						}
						} 
					}
					setState(63);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(64);
				closeParen();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				openParen();
				setState(68);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(67);
					type();
					}
					break;
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUBSCRIPT) | (1L << SUBSCRIPTITALIC) | (1L << SUPERSCRIPT) | (1L << SUPERSCRIPTITALIC) | (1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) {
					{
					setState(70);
					content();
					}
				}

				setState(76);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(73);
						node();
						}
						} 
					}
					setState(78);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				}
				setState(79);
				closeParen();
				notifyErrorListeners("missingOpeningParen");
				setState(81);
				content();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				openParen();
				setState(85);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(84);
					type();
					}
					break;
				}
				setState(88);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(87);
					content();
					}
					break;
				}
				setState(93);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(90);
						node();
						}
						} 
					}
					setState(95);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				notifyErrorListeners("missingClosingParen");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(98);
				type();
				setState(100);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(99);
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(106);
				match(T__0);
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
			match(T__1);
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(113);
					match(T__0);
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(119);
				match(T__0);
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(125);
			match(T__2);
			setState(129);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(126);
					match(T__0);
					}
					} 
				}
				setState(131);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				nodeType();
				setState(133);
				lineType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				lineType();
				setState(136);
				nodeType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				nodeType();
				setState(139);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				lineType();
				setState(143);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(146);
				nodeType();
				setState(147);
				lineType();
				setState(148);
				nodeType();
				notifyErrorListeners("tooManyNodeTypes");
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(151);
				lineType();
				setState(152);
				nodeType();
				setState(153);
				lineType();
				notifyErrorListeners("tooManyLineTypes");
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(156);
				lineType();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(157);
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
			setState(160);
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
			setState(162);
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
			setState(229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(165); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(164);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(167); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(169);
					match(T__0);
					}
					}
					setState(174);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(175);
				subscript();
				setState(176);
				superscript();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(179); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(178);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(181); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(183);
					match(T__0);
					}
					}
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(189);
				superscript();
				setState(190);
				subscript();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(193); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(192);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
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
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(197);
					match(T__0);
					}
					}
					setState(202);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(203);
				subscript();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(205); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(204);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(207); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0) );
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(209);
					match(T__0);
					}
					}
					setState(214);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(215);
				superscript();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(217); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(216);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
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
					setState(219); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(221);
				subscript();
				setState(222);
				superscript();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(224);
				superscript();
				setState(225);
				subscript();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(227);
				subscript();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(228);
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
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUBSCRIPT:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				match(SUBSCRIPT);
				setState(233); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(232);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
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
					setState(235); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case SUBSCRIPTITALIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(237);
				match(SUBSCRIPTITALIC);
				setState(239); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(238);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
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
					setState(241); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			default:
				throw new NoViableAltException(this);
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
			setState(257);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUPERSCRIPT:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				match(SUPERSCRIPT);
				setState(247); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(246);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
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
					setState(249); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case SUPERSCRIPTITALIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(251);
				match(SUPERSCRIPTITALIC);
				setState(253); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(252);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << BACKSLASH) | (1L << SLASH))) != 0)) ) {
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
					setState(255); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22\u0106\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\64\n\2\3\3\3"+
		"\3\5\38\n\3\3\3\5\3;\n\3\3\3\7\3>\n\3\f\3\16\3A\13\3\3\3\3\3\3\3\3\3\5"+
		"\3G\n\3\3\3\5\3J\n\3\3\3\7\3M\n\3\f\3\16\3P\13\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\5\3X\n\3\3\3\5\3[\n\3\3\3\7\3^\n\3\f\3\16\3a\13\3\3\3\3\3\3\3\3\3\5"+
		"\3g\n\3\3\3\3\3\5\3k\n\3\3\4\7\4n\n\4\f\4\16\4q\13\4\3\4\3\4\7\4u\n\4"+
		"\f\4\16\4x\13\4\3\5\7\5{\n\5\f\5\16\5~\13\5\3\5\3\5\7\5\u0082\n\5\f\5"+
		"\16\5\u0085\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00a1\n\6\3\7\3"+
		"\7\3\b\3\b\3\t\6\t\u00a8\n\t\r\t\16\t\u00a9\3\t\7\t\u00ad\n\t\f\t\16\t"+
		"\u00b0\13\t\3\t\3\t\3\t\3\t\6\t\u00b6\n\t\r\t\16\t\u00b7\3\t\7\t\u00bb"+
		"\n\t\f\t\16\t\u00be\13\t\3\t\3\t\3\t\3\t\6\t\u00c4\n\t\r\t\16\t\u00c5"+
		"\3\t\7\t\u00c9\n\t\f\t\16\t\u00cc\13\t\3\t\3\t\6\t\u00d0\n\t\r\t\16\t"+
		"\u00d1\3\t\7\t\u00d5\n\t\f\t\16\t\u00d8\13\t\3\t\3\t\6\t\u00dc\n\t\r\t"+
		"\16\t\u00dd\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00e8\n\t\3\n\3\n\6\n"+
		"\u00ec\n\n\r\n\16\n\u00ed\3\n\3\n\6\n\u00f2\n\n\r\n\16\n\u00f3\5\n\u00f6"+
		"\n\n\3\13\3\13\6\13\u00fa\n\13\r\13\16\13\u00fb\3\13\3\13\6\13\u0100\n"+
		"\13\r\13\16\13\u0101\5\13\u0104\n\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24"+
		"\2\5\3\2\6\7\3\2\b\n\3\2\17\21\2\u0131\2\63\3\2\2\2\4j\3\2\2\2\6o\3\2"+
		"\2\2\b|\3\2\2\2\n\u00a0\3\2\2\2\f\u00a2\3\2\2\2\16\u00a4\3\2\2\2\20\u00e7"+
		"\3\2\2\2\22\u00f5\3\2\2\2\24\u0103\3\2\2\2\26\27\5\4\3\2\27\30\7\2\2\3"+
		"\30\64\3\2\2\2\31\32\7\2\2\3\32\64\b\2\1\2\33\34\b\2\1\2\34\64\5\20\t"+
		"\2\35\36\5\4\3\2\36\37\b\2\1\2\37 \5\20\t\2 \64\3\2\2\2!\"\5\4\3\2\"#"+
		"\b\2\1\2#$\5\4\3\2$\64\3\2\2\2%&\b\2\1\2&\'\5\20\t\2\'(\5\4\3\2()\7\2"+
		"\2\3)\64\3\2\2\2*+\b\2\1\2+,\5\n\6\2,-\5\4\3\2-.\7\2\2\3.\64\3\2\2\2/"+
		"\60\5\4\3\2\60\61\5\b\5\2\61\62\b\2\1\2\62\64\3\2\2\2\63\26\3\2\2\2\63"+
		"\31\3\2\2\2\63\33\3\2\2\2\63\35\3\2\2\2\63!\3\2\2\2\63%\3\2\2\2\63*\3"+
		"\2\2\2\63/\3\2\2\2\64\3\3\2\2\2\65\67\5\6\4\2\668\5\n\6\2\67\66\3\2\2"+
		"\2\678\3\2\2\28:\3\2\2\29;\5\20\t\2:9\3\2\2\2:;\3\2\2\2;?\3\2\2\2<>\5"+
		"\4\3\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\5"+
		"\b\5\2Ck\3\2\2\2DF\5\6\4\2EG\5\n\6\2FE\3\2\2\2FG\3\2\2\2GI\3\2\2\2HJ\5"+
		"\20\t\2IH\3\2\2\2IJ\3\2\2\2JN\3\2\2\2KM\5\4\3\2LK\3\2\2\2MP\3\2\2\2NL"+
		"\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\5\b\5\2RS\b\3\1\2ST\5\20\t\2"+
		"Tk\3\2\2\2UW\5\6\4\2VX\5\n\6\2WV\3\2\2\2WX\3\2\2\2XZ\3\2\2\2Y[\5\20\t"+
		"\2ZY\3\2\2\2Z[\3\2\2\2[_\3\2\2\2\\^\5\4\3\2]\\\3\2\2\2^a\3\2\2\2_]\3\2"+
		"\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2bc\b\3\1\2ck\3\2\2\2df\5\n\6\2eg\5\20"+
		"\t\2fe\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\b\3\1\2ik\3\2\2\2j\65\3\2\2\2jD\3"+
		"\2\2\2jU\3\2\2\2jd\3\2\2\2k\5\3\2\2\2ln\7\3\2\2ml\3\2\2\2nq\3\2\2\2om"+
		"\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rv\7\4\2\2su\7\3\2\2ts\3\2\2\2u"+
		"x\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\7\3\2\2\2xv\3\2\2\2y{\7\3\2\2zy\3\2\2\2"+
		"{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~|\3\2\2\2\177\u0083\7\5\2"+
		"\2\u0080\u0082\7\3\2\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0083\u0084\3\2\2\2\u0084\t\3\2\2\2\u0085\u0083\3\2\2\2\u0086"+
		"\u0087\5\16\b\2\u0087\u0088\5\f\7\2\u0088\u00a1\3\2\2\2\u0089\u008a\5"+
		"\f\7\2\u008a\u008b\5\16\b\2\u008b\u00a1\3\2\2\2\u008c\u008d\5\16\b\2\u008d"+
		"\u008e\5\16\b\2\u008e\u008f\b\6\1\2\u008f\u00a1\3\2\2\2\u0090\u0091\5"+
		"\f\7\2\u0091\u0092\5\f\7\2\u0092\u0093\b\6\1\2\u0093\u00a1\3\2\2\2\u0094"+
		"\u0095\5\16\b\2\u0095\u0096\5\f\7\2\u0096\u0097\5\16\b\2\u0097\u0098\b"+
		"\6\1\2\u0098\u00a1\3\2\2\2\u0099\u009a\5\f\7\2\u009a\u009b\5\16\b\2\u009b"+
		"\u009c\5\f\7\2\u009c\u009d\b\6\1\2\u009d\u00a1\3\2\2\2\u009e\u00a1\5\f"+
		"\7\2\u009f\u00a1\5\16\b\2\u00a0\u0086\3\2\2\2\u00a0\u0089\3\2\2\2\u00a0"+
		"\u008c\3\2\2\2\u00a0\u0090\3\2\2\2\u00a0\u0094\3\2\2\2\u00a0\u0099\3\2"+
		"\2\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1\13\3\2\2\2\u00a2\u00a3"+
		"\t\2\2\2\u00a3\r\3\2\2\2\u00a4\u00a5\t\3\2\2\u00a5\17\3\2\2\2\u00a6\u00a8"+
		"\t\4\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\u00ae\3\2\2\2\u00ab\u00ad\7\3\2\2\u00ac\u00ab\3\2"+
		"\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\5\22\n\2\u00b2\u00b3\5"+
		"\24\13\2\u00b3\u00e8\3\2\2\2\u00b4\u00b6\t\4\2\2\u00b5\u00b4\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00bc\3\2"+
		"\2\2\u00b9\u00bb\7\3\2\2\u00ba\u00b9\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00bc\3\2"+
		"\2\2\u00bf\u00c0\5\24\13\2\u00c0\u00c1\5\22\n\2\u00c1\u00e8\3\2\2\2\u00c2"+
		"\u00c4\t\4\2\2\u00c3\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00ca\3\2\2\2\u00c7\u00c9\7\3\2\2\u00c8"+
		"\u00c7\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2"+
		"\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00e8\5\22\n\2\u00ce"+
		"\u00d0\t\4\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00cf\3\2"+
		"\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d6\3\2\2\2\u00d3\u00d5\7\3\2\2\u00d4"+
		"\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00e8\5\24\13\2\u00da"+
		"\u00dc\t\4\2\2\u00db\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00db\3\2"+
		"\2\2\u00dd\u00de\3\2\2\2\u00de\u00e8\3\2\2\2\u00df\u00e0\5\22\n\2\u00e0"+
		"\u00e1\5\24\13\2\u00e1\u00e8\3\2\2\2\u00e2\u00e3\5\24\13\2\u00e3\u00e4"+
		"\5\22\n\2\u00e4\u00e8\3\2\2\2\u00e5\u00e8\5\22\n\2\u00e6\u00e8\5\24\13"+
		"\2\u00e7\u00a7\3\2\2\2\u00e7\u00b5\3\2\2\2\u00e7\u00c3\3\2\2\2\u00e7\u00cf"+
		"\3\2\2\2\u00e7\u00db\3\2\2\2\u00e7\u00df\3\2\2\2\u00e7\u00e2\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8\21\3\2\2\2\u00e9\u00eb\7\13\2"+
		"\2\u00ea\u00ec\t\4\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f6\3\2\2\2\u00ef\u00f1\7\f\2\2\u00f0"+
		"\u00f2\t\4\2\2\u00f1\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2"+
		"\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f6\3\2\2\2\u00f5\u00e9\3\2\2\2\u00f5"+
		"\u00ef\3\2\2\2\u00f6\23\3\2\2\2\u00f7\u00f9\7\r\2\2\u00f8\u00fa\t\4\2"+
		"\2\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc"+
		"\3\2\2\2\u00fc\u0104\3\2\2\2\u00fd\u00ff\7\16\2\2\u00fe\u0100\t\4\2\2"+
		"\u00ff\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102"+
		"\3\2\2\2\u0102\u0104\3\2\2\2\u0103\u00f7\3\2\2\2\u0103\u00fd\3\2\2\2\u0104"+
		"\25\3\2\2\2#\63\67:?FINWZ_fjov|\u0083\u00a0\u00a9\u00ae\u00b7\u00bc\u00c5"+
		"\u00ca\u00d1\u00d6\u00dd\u00e7\u00ed\u00f3\u00f5\u00fb\u0101\u0103";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
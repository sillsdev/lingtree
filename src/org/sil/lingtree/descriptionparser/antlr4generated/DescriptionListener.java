// Generated from Description.g4 by ANTLR 4.7

	package org.sil.lingtree.descriptionparser.antlr4generated;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DescriptionParser}.
 */
public interface DescriptionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(DescriptionParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(DescriptionParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(DescriptionParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(DescriptionParser.NodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#openParen}.
	 * @param ctx the parse tree
	 */
	void enterOpenParen(DescriptionParser.OpenParenContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#openParen}.
	 * @param ctx the parse tree
	 */
	void exitOpenParen(DescriptionParser.OpenParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#closeParen}.
	 * @param ctx the parse tree
	 */
	void enterCloseParen(DescriptionParser.CloseParenContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#closeParen}.
	 * @param ctx the parse tree
	 */
	void exitCloseParen(DescriptionParser.CloseParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(DescriptionParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(DescriptionParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#lineType}.
	 * @param ctx the parse tree
	 */
	void enterLineType(DescriptionParser.LineTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#lineType}.
	 * @param ctx the parse tree
	 */
	void exitLineType(DescriptionParser.LineTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#nodeType}.
	 * @param ctx the parse tree
	 */
	void enterNodeType(DescriptionParser.NodeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#nodeType}.
	 * @param ctx the parse tree
	 */
	void exitNodeType(DescriptionParser.NodeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(DescriptionParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(DescriptionParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#subscript}.
	 * @param ctx the parse tree
	 */
	void enterSubscript(DescriptionParser.SubscriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#subscript}.
	 * @param ctx the parse tree
	 */
	void exitSubscript(DescriptionParser.SubscriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#superscript}.
	 * @param ctx the parse tree
	 */
	void enterSuperscript(DescriptionParser.SuperscriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#superscript}.
	 * @param ctx the parse tree
	 */
	void exitSuperscript(DescriptionParser.SuperscriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#abbreviation}.
	 * @param ctx the parse tree
	 */
	void enterAbbreviation(DescriptionParser.AbbreviationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#abbreviation}.
	 * @param ctx the parse tree
	 */
	void exitAbbreviation(DescriptionParser.AbbreviationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DescriptionParser#abbreviationWithText}.
	 * @param ctx the parse tree
	 */
	void enterAbbreviationWithText(DescriptionParser.AbbreviationWithTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link DescriptionParser#abbreviationWithText}.
	 * @param ctx the parse tree
	 */
	void exitAbbreviationWithText(DescriptionParser.AbbreviationWithTextContext ctx);
}
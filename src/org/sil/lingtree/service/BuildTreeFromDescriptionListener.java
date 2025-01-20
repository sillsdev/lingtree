/**
 * Copyright (c) 2016-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.service;

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.ParserRuleContext;
import org.sil.lingtree.Constants;
import org.sil.lingtree.MainApp;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionBaseListener;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser.AbbreviationWithTextContext;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser.ContentContext;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser.NodeContext;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser.TypeContext;
import org.sil.lingtree.model.AbbreviationFontInfo;
import org.sil.lingtree.model.AbbreviationText;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NodeText;
import org.sil.lingtree.model.NodeType;

/**
 * @author Andy Black build a tree from the parsed description
 */
public class BuildTreeFromDescriptionListener extends DescriptionBaseListener {
	protected DescriptionParser parser;

	private LingTreeTree tree;
	private HashMap<Integer, LingTreeNode> nodeMap = new HashMap<Integer, LingTreeNode>();
	private String sCustomFontText = "";
	FontInfoParser fontInfoParser = FontInfoParser.getInstance();
	FontInfo abbreviationsFontInfo = null;

	public BuildTreeFromDescriptionListener(DescriptionParser parser) {
		super();
		this.parser = parser;
	}

	public LingTreeTree getTree() {
		return tree;
	}

	public void setTree(LingTreeTree tree) {
		this.tree = tree;
	}

	@Override
	public void enterDescription(DescriptionParser.DescriptionContext ctx) {
		tree = new LingTreeTree();

	}

	@Override
	public void enterNode(DescriptionParser.NodeContext ctx) {
		LingTreeNode node = new LingTreeNode();
		node.setLineNumInDescription(ctx.start.getLine());
		node.setCharacterPositionInLine(ctx.start.getCharPositionInLine());
		nodeMap.put(ctx.hashCode(), node);
		if (tree.getRootNode() == null) {
			node.setiLevel(1);
			tree.setRootNode(node);
		} else {
			DescriptionParser.NodeContext parentCtx = (NodeContext) ctx.getParent();
			LingTreeNode mother = nodeMap.get(parentCtx.hashCode());
			int i = mother.getDaughters().size();
			if (i > 0) {
				mother.getDaughters().get(i - 1).setRightSister(node);
			}
			node.setiLevel(mother.getLevel() + 1);
			node.setMother(mother);
			mother.getDaughters().add(node);
		}
	}

//	@Override
//	public void exitNode(DescriptionParser.NodeContext ctx) {
//		LingTreeNode node = nodeMap.get(ctx.hashCode());
//		node.setLineNumInDescription(ctx.start.getLine());
//		node.setCharacterPositionInLine(ctx.start.getCharPositionInLine());
//	}
//
	@Override
	public void exitLineType(DescriptionParser.LineTypeContext ctx) {
		DescriptionParser.TypeContext typeCtx = (TypeContext) ctx.getParent();
		DescriptionParser.NodeContext parentCtx = (NodeContext) typeCtx.getParent();
		LingTreeNode node = nodeMap.get(parentCtx.hashCode());
		if (ctx.getText().trim().equals("\\O")) {
			node.setOmitLine(true);
		}
		if (ctx.getText().trim().equals("\\T")) {
			node.setTriangle(true);
			;
		}
	}

	@Override
	public void exitNodeType(DescriptionParser.NodeTypeContext ctx) {
		DescriptionParser.TypeContext typeCtx = (TypeContext) ctx.getParent();
		DescriptionParser.NodeContext parentCtx = (NodeContext) typeCtx.getParent();
		LingTreeNode node = nodeMap.get(parentCtx.hashCode());
		NodeType nodeType = NodeType.NonTerminal;
		switch (ctx.getText()) {
		case "\\E":
			nodeType = NodeType.EmptyElement;
			break;

		case "\\G":
			nodeType = NodeType.Gloss;
			break;

		case "\\L":
			nodeType = NodeType.Lex;
			break;

		default:
			break;
		}
		node.setNodeType(nodeType);
	}

	@Override
	public void exitContent(DescriptionParser.ContentContext ctx) {
		DescriptionParser.NodeContext parentCtx = (NodeContext) ctx.getParent();
		LingTreeNode node = nodeMap.get(parentCtx.hashCode());
		String sContent = ctx.getText().trim();
		sContent = adjustTextContent(sContent);
		int iEndOfText = getEndOfTextIndex(sContent);
		node.setContent(sContent.substring(0, iEndOfText));
		node.setHasAbbreviation(false);
		node.setCharacterPositionInLine(ctx.start.getCharPositionInLine());
	}

	@Override
	public void exitAbbreviationWithText(DescriptionParser.AbbreviationWithTextContext ctx) {
		DescriptionParser.NodeContext parentCtx = (NodeContext) ctx.getParent();
		LingTreeNode node = nodeMap.get(parentCtx.hashCode());
		node.setHasAbbreviation(true);
		int charPos = ctx.start.getCharPositionInLine();
		for (ParseTree daughterTree : ctx.children) {
			String daughterText = daughterTree.getText();
			int iAbbrBegin = daughterText.indexOf(Constants.ABBREVIATION_BEGIN);
			int iFontBegin = daughterText.indexOf(Constants.CUSTOM_FONT_BEGIN);
			if (iAbbrBegin > -1) {
				// is an abbreviation
				int iAbbrEnd = daughterText.indexOf(Constants.ABBREVIATION_END);
				AbbreviationText abbrNodeText = new AbbreviationText(node);
				String abbrText = daughterText.substring(iAbbrBegin + Constants.ABBREVIATION_BEGIN.length(),
						daughterText.length() - Constants.ABBREVIATION_END.length());
				abbrText = adjustTextContent(abbrText);
				abbrNodeText.setText(abbrText);
				abbrNodeText.setLineNumInDescription(ctx.start.getLine());
				abbrNodeText.setCharacterPositionInLine(charPos + Constants.ABBREVIATION_BEGIN.length());
				charPos += iAbbrEnd + Constants.ABBREVIATION_END.length();
				node.getContentsAsList().add(abbrNodeText);
			} else if (iFontBegin > -1) {
				// is custom font information for previous daughter
				int iLast = node.getContentsAsList().size() - 1;
				NodeText nodeText = node.getContentsAsList().get(iLast);
				nodeText = processCustomFontInfo(daughterText, nodeText, node);
				nodeText.setCustomFontLineNumInDescription(ctx.start.getLine());
				nodeText.setCustomFontCharacterPositionInLine(charPos + iFontBegin);
				// set the text again so the font changes are reflected
				nodeText.setText(nodeText.getText());
				node.getContentsAsList().remove(iLast);
				node.getContentsAsList().add(nodeText);
				charPos += daughterText.length();
			} else {
				// is plain text
				NodeText nodeText = new NodeText(node);
				nodeText.setText(daughterText);
				nodeText.setLineNumInDescription(ctx.start.getLine());
				nodeText.setCharacterPositionInLine(charPos);
				charPos += nodeText.getText().length();
				node.getContentsAsList().add(nodeText);
			}
		}
	}

	protected NodeText processCustomFontInfo(String sFontText, NodeText nodeText, LingTreeNode node) {
		int iCustomFontBegin = sFontText.indexOf(Constants.CUSTOM_FONT_BEGIN);
		int iCustomFontEnd = sFontText.indexOf(Constants.CUSTOM_FONT_END);
		if (iCustomFontBegin >= 0 && iCustomFontEnd > 0) {
			String sCustomFont = sFontText.substring(iCustomFontBegin + Constants.CUSTOM_FONT_BEGIN.length(), iCustomFontEnd);
			try {
				FontInfo fontInfo = (nodeText instanceof AbbreviationText) ? AbbreviationFontInfo.getInstance().clone()
						: node.getFontInfoFromNodeType(true).clone();
				fontInfo = fontInfoParser.adjustFontInfoPerDescription(sCustomFont, parser, fontInfo);
				nodeText.setCustomFontInfo(fontInfo);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				MainApp.reportException(e, null);
			}
		}
		return nodeText;
	}

	@Override
	public void exitCustomFontInfo(DescriptionParser.CustomFontInfoContext ctx) {
		ParserRuleContext parent = ctx.getParent();
		if (parent instanceof NodeContext parentCtx) {
			LingTreeNode node = nodeMap.get(parentCtx.hashCode());
			String sContent = ctx.getText().trim();
			int iCustomFontBegin = sContent.indexOf(Constants.CUSTOM_FONT_BEGIN);
			String sText = sContent;
			int iCustomFontEnd = sText.indexOf(Constants.CUSTOM_FONT_END);
			String sCustomFont = sText.substring(iCustomFontBegin + Constants.CUSTOM_FONT_BEGIN.length(), iCustomFontEnd);
			FontInfo fontInfo = fontInfoParser.parseString(sCustomFont, node, parser);
			node.setCustomFontInfo(fontInfo);
			node.setCustomFontLineNumInDescription(ctx.start.getLine());
			node.setCustomFontCharacterPositionInLine(ctx.start.getCharPositionInLine());
		} else if (parent instanceof AbbreviationWithTextContext) {
			// do nothing here; we'll do it in exitAbbreviationWithText()
		}
	}

	protected String adjustTextContent(String sContent) {
		// Note: in regular expressions, to quote a single backslash we need
		// \\\\ and to quote a paren we need \\(
		sContent = sContent.replaceAll("\\\\\\(", "(").replaceAll("\\\\\\)", ")");
		return sContent;
	}

	private int getEndOfTextIndex(String sContent) {
		int iEndOfText = sContent.length();
		int iSub = sContent.indexOf(Constants.SUBSCRIPT);
		int iSuper = sContent.indexOf(Constants.SUPERSCRIPT);
		if (iSub > -1) {
			if (iSuper > -1) {
				iEndOfText = Math.min(iSub, iSuper);
			} else {
				iEndOfText = iSub;
			}
		} else if (iSuper > -1) {
			iEndOfText = iSuper;
		}
		iSub = sContent.indexOf(Constants.SUBSCRIPTITALIC);
		iSuper = sContent.indexOf(Constants.SUPERSCRIPTITALIC);
		if (iSub > -1) {
			if (iSuper > -1) {
				iEndOfText = Math.min(iSub, iSuper);
			} else {
				iEndOfText = Math.min(iEndOfText, iSub);
			}
		} else if (iSuper > -1) {
			iEndOfText = Math.min(iEndOfText, iSuper);
		}
		return iEndOfText;
	}

	@Override
	public void exitSubscript(DescriptionParser.SubscriptContext ctx) {
		String sRegularToken = Constants.SUBSCRIPT;
		String sItalicToken = Constants.SUBSCRIPTITALIC;
		DescriptionParser.ContentContext parentCtx = (ContentContext) ctx.getParent();
		DescriptionParser.NodeContext parentsParentCtx = (NodeContext) parentCtx.getParent();
		LingTreeNode node = nodeMap.get(parentsParentCtx.hashCode());
		String sText = ctx.getText().trim();
		int iRegular = sText.indexOf(sRegularToken);
		int iItalic = sText.indexOf(sItalicToken);
		if (iItalic > -1) {
			node.setSubscriptRegular(false);
			node.setSubscript(sText.substring(sItalicToken.length()));
		} else if (iRegular > -1) {
			node.setSubscriptRegular(true);
			node.setSubscript(sText.substring(sRegularToken.length()));
		}
	}

	@Override
	public void exitSuperscript(DescriptionParser.SuperscriptContext ctx) {
		String sRegularToken = Constants.SUPERSCRIPT;
		String sItalicToken = Constants.SUPERSCRIPTITALIC;
		DescriptionParser.ContentContext parentCtx = (ContentContext) ctx.getParent();
		DescriptionParser.NodeContext parentsParentCtx = (NodeContext) parentCtx.getParent();
		LingTreeNode node = nodeMap.get(parentsParentCtx.hashCode());
		String sText = ctx.getText().trim();
		int iRegular = sText.indexOf(sRegularToken);
		int iItalic = sText.indexOf(sItalicToken);
		if (iItalic > -1) {
			node.setSuperscriptRegular(false);
			node.setSuperscript(sText.substring(sItalicToken.length()));
		} else if (iRegular > -1) {
			node.setSuperscriptRegular(true);
			node.setSuperscript(sText.substring(sRegularToken.length()));
		}
	}
}

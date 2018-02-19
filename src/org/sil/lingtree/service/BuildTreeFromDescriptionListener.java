/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.service;

import java.util.HashMap;

import org.sil.lingtree.Constants;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionBaseListener;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser.ContentContext;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser.NodeContext;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser.TypeContext;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NodeType;

/**
 * @author Andy Black
 * build a tree from the parsed description
 */
public class BuildTreeFromDescriptionListener extends DescriptionBaseListener {
	protected DescriptionParser parser;
	
	private LingTreeTree tree;
	private HashMap<Integer, LingTreeNode> nodeMap = new HashMap<Integer, LingTreeNode>();
	

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
		nodeMap.put(ctx.hashCode(), node);
		if (tree.getRootNode() == null) {
			node.setiLevel(1);
			tree.setRootNode(node);
		} else {
			DescriptionParser.NodeContext parentCtx = (NodeContext) ctx.getParent();
			LingTreeNode mother = nodeMap.get(parentCtx.hashCode());
			int i = mother.getDaughters().size();
			if (i > 0) {
				mother.getDaughters().get(i-1).setRightSister(node);
			}
			node.setiLevel(mother.getLevel()+1);
			node.setMother(mother);
			mother.getDaughters().add(node);
		}
	}
	
	@Override
	public void exitLineType(DescriptionParser.LineTypeContext ctx) {	
		DescriptionParser.TypeContext typeCtx = (TypeContext) ctx.getParent();
		DescriptionParser.NodeContext parentCtx = (NodeContext) typeCtx.getParent();
		LingTreeNode node = nodeMap.get(parentCtx.hashCode());
		if (ctx.getText().trim().equals("\\O")) {
			node.setOmitLine(true);
		}
		if (ctx.getText().trim().equals("\\T")) {
			node.setTriangle(true);;
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
		int iEndOfText = getEndOfTextIndex(sContent);
		node.setContent(sContent.substring(0, iEndOfText).trim());
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

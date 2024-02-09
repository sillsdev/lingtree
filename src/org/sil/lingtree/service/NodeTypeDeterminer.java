/**
 * Copyright (c) 2024 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import org.sil.lingtree.model.NodeType;

/**
 * @author Andy Black
 *
 */
public class NodeTypeDeterminer {

	public static NodeType determineNodeTypeFrom(String portion) {
		portion = portion.trim();
		NodeType ntype = NodeType.Syntagmeme;
		int iOpenParen = portion.lastIndexOf("(");
		if (iOpenParen < 0) {
			return NodeType.Syntagmeme;
		}
		while (iOpenParen > 0 && portion.substring(Math.max(0, iOpenParen-1)).startsWith("\\")) {
			iOpenParen = portion.substring(0, iOpenParen).lastIndexOf("(");
		}
		int iOpenEnd = (iOpenParen == -1) ? portion.length() : iOpenParen;
		String sThisNodeBeginning = portion.substring(iOpenEnd);
		int iCloseParen = sThisNodeBeginning.lastIndexOf(")");
		while (iCloseParen > 0 && sThisNodeBeginning.substring(0,iCloseParen).endsWith("\\")) {
			iCloseParen = portion.substring(0, iCloseParen).lastIndexOf(")");
		}
		if (iCloseParen > -1) {
			return NodeType.Syntagmeme;
		}
		int iCloseEnd = (iCloseParen == -1) ? sThisNodeBeginning.length() : iCloseParen;
		String sNode = sThisNodeBeginning.substring(0,iCloseEnd);
		if (sNode.endsWith(")") && !sNode.endsWith("\\)")) {
			return NodeType.Syntagmeme;
		}
		if (sNode.startsWith("(")) {
			if (sNode.contains("\\L")) {
				ntype = NodeType.Lex;
			} else if (sNode.contains("\\G")) {			
				ntype = NodeType.Gloss;
			} else if (sNode.contains("\\E")) {
				ntype = NodeType.EmptyElement;
			} else if (isNonTerminal(sNode)) {
				ntype = NodeType.NonTerminal;
			}
		}
		return ntype;
	}
	
	static boolean isNonTerminal(String sNode) {
		if (sNode.length() <= 1)
			return false;
		if (sNode.endsWith("\\"))
			return false;
		if (sNode.endsWith("\\O"))
			return false;
		if (sNode.endsWith("\\T"))
			return false;
//		if (sNode.endsWith("\\("))
//			return false;
//		if (sNode.endsWith("\\)"))
//			return false;
		return true;
	}
}
